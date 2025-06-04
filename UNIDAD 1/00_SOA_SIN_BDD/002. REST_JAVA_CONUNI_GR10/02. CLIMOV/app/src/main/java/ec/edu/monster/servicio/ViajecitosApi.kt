package ec.edu.monster.servicio

import ec.edu.monster.modelo.Cliente
import ec.edu.monster.modelo.Factura
import ec.edu.monster.modelo.Usuario
import ec.edu.monster.modelo.Vuelo
import retrofit2.Call
import retrofit2.http.*

interface ViajecitosApi {

    @GET("vuelos")
    fun buscarVuelos(
        @Query("ciudadOrigen") ciudadOrigen: String,
        @Query("ciudadDestino") ciudadDestino: String,
        @Query("fecha") fecha: String
    ): Call<List<Vuelo>>

    @POST("cliente")
    fun registrarCliente(
        @Body clienteData: ClienteRequest
    ): Call<Cliente>

    @POST("login")
    fun iniciarSesion(
        @Query("nombreUsuario") nombreUsuario: String,
        @Query("claveUsuario") claveUsuario: String
    ): Call<Usuario>

    @GET("facturas/{idCliente}")
    fun obtenerFacturasCliente(
        @Path("idCliente") idCliente: Int
    ): Call<List<Factura>>

    @GET("facturas")  // Esta es la nueva ruta para obtener todas las facturas de todos los clientes
    fun obtenerTodasFacturasPorCliente(): Call<List<ClienteFacturas>>  // Aquí se retornan todas las facturas por cliente

    @POST("factura")
    fun crearFactura(
        @Body facturaData: FacturaRequest
    ): Call<Factura>
}

data class ClienteRequest(
    val nombre: String,
    val email: String,
    val documentoIdentidad: String
)

data class FacturaRequest(
    val numeroFactura: String,
    val idEmpleado: Int,
    val idCliente: Int,
    val idMetodoPago: Int,
    val descuento: Double,
    val detalles: List<DetalleFacturaRequest>
)

data class DetalleFacturaRequest(
    val idVuelo: Int,
    val cantidad: Int
)

data class ClienteFacturas(  // Aquí estamos agregando este modelo de ClienteFacturas
    val clienteId: Int,
    val nombre: String,
    val email: String,
    val documentoIdentidad: String,
    val facturas: List<Factura>
)
