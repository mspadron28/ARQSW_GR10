package ec.edu.monster.servicio

import com.google.gson.GsonBuilder
import ec.edu.monster.modelo.*
import ec.edu.monster.util.RestConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EurekaService {
    private val api: EurekaApi

    init {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'Z'").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(RestConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        api = retrofit.create(EurekaApi::class.java)
    }

    suspend fun login(usuario: String, clave: String): Usuario? {
        val loginRequest = LoginRequest(usuario, clave)
        val response = api.login(loginRequest)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun traerMovimientos(cuenta: String): List<Movimiento> {
        val response = api.traerMovimientos(cuenta)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception(response.errorBody()?.string() ?: "Error al consultar movimientos")
        }
    }

    suspend fun registrarDeposito(cuenta: String, importe: Double, codEmp: String) {
        val request = DepositoRequest(cuenta, importe, codEmp)
        val response = api.registrarDeposito(request)
        if (!response.isSuccessful) {
            throw Exception(response.errorBody()?.string() ?: "Error al registrar depósito")
        }
    }

    suspend fun registrarRetiro(cuenta: String, importe: Double, codEmp: String) {
        val request = RetiroRequest(cuenta, importe, codEmp)
        val response = api.registrarRetiro(request)
        if (!response.isSuccessful) {
            throw Exception(response.errorBody()?.string() ?: "Error al registrar retiro")
        }
    }

    suspend fun realizarTransferencia(cuentaOrigen: String, cuentaDestino: String, importe: Double, codEmp: String) {
        val request = TransferenciaRequest(cuentaOrigen, cuentaDestino, importe, codEmp)
        val response = api.realizarTransferencia(request)
        if (!response.isSuccessful) {
            throw Exception(response.errorBody()?.string() ?: "Error al realizar transferencia")
        }
    }

    suspend fun verificarSaldo(cuenta: String): Double {
        val response = api.verificarSaldo(cuenta)
        if (response.isSuccessful) {
            return response.body()?.saldo ?: -1.0
        } else {
            throw Exception(response.errorBody()?.string() ?: "Error al verificar saldo")
        }
    }

    suspend fun obtenerCostoMovimiento(cuenta: String): Double {
        val response = api.obtenerCostoMovimiento(cuenta)
        if (response.isSuccessful) {
            return response.body()?.costo ?: -1.0
        } else {
            throw Exception(response.errorBody()?.string() ?: "Error al obtener costo")
        }
    }
}