package ec.edu.monster.servicio

import com.google.gson.GsonBuilder
import ec.edu.monster.modelo.LoginRequest
import ec.edu.monster.util.RestConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CONUNIApi {
    @GET("pulgadas-a-centimetros")
    suspend fun pulgadasACentimetros(@Query("pulgadas") pulgadas: Double): Double

    @GET("centimetros-a-pulgadas")
    suspend fun centimetrosAPulgadas(@Query("centimetros") centimetros: Double): Double

    @GET("metros-a-pies")
    suspend fun metrosAPies(@Query("metros") metros: Double): Double

    @GET("pies-a-metros")
    suspend fun piesAMetros(@Query("pies") pies: Double): Double

    @GET("metros-a-yardas")
    suspend fun metrosAYardas(@Query("metros") metros: Double): Double

    @GET("yardas-a-metros")
    suspend fun yardasAMetros(@Query("yardas") yardas: Double): Double

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<String>
}

class CONUNIService {
    private val api: CONUNIApi

    init {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(RestConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        api = retrofit.create(CONUNIApi::class.java)
    }

    suspend fun login(usuario: String, contraseña: String): Boolean {
        val loginRequest = LoginRequest(usuario, contraseña)
        val response = api.login(loginRequest)
        return if (response.isSuccessful) {
            response.body() == "Login successful"
        } else {
            false
        }
    }

    suspend fun pulgadasACentimetros(pulgadas: Double): Double {
        return api.pulgadasACentimetros(pulgadas)
    }

    suspend fun centimetrosAPulgadas(centimetros: Double): Double {
        return api.centimetrosAPulgadas(centimetros)
    }

    suspend fun metrosAPies(metros: Double): Double {
        return api.metrosAPies(metros)
    }

    suspend fun piesAMetros(pies: Double): Double {
        return api.piesAMetros(pies)
    }

    suspend fun metrosAYardas(metros: Double): Double {
        return api.metrosAYardas(metros)
    }

    suspend fun yardasAMetros(yardas: Double): Double {
        return api.yardasAMetros(yardas)
    }
}