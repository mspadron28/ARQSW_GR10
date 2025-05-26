package ec.edu.monster.servicio

import ec.edu.monster.modelo.*
import retrofit2.Response
import retrofit2.http.*

interface EurekaApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<Usuario>

    @GET("movimientos/{cuenta}")
    suspend fun traerMovimientos(@Path("cuenta") cuenta: String): Response<List<Movimiento>>

    @POST("deposito")
    suspend fun registrarDeposito(@Body depositoRequest: DepositoRequest): Response<Void>

    @POST("retiro")
    suspend fun registrarRetiro(@Body retiroRequest: RetiroRequest): Response<Void>

    @POST("transferencia")
    suspend fun realizarTransferencia(@Body transferenciaRequest: TransferenciaRequest): Response<Void>

    @GET("saldo/{cuenta}")
    suspend fun verificarSaldo(@Path("cuenta") cuenta: String): Response<SaldoResponse>

    @GET("costo/{cuenta}")
    suspend fun obtenerCostoMovimiento(@Path("cuenta") cuenta: String): Response<CostoResponse>
}