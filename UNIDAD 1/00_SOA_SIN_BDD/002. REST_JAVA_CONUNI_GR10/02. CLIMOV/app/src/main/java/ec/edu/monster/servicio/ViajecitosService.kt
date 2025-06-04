package ec.edu.monster.servicio

import android.util.Log
import ec.edu.monster.modelo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViajecitosService {

    // Método para buscar vuelos
    fun buscarVuelos(ciudadOrigen: String, ciudadDestino: String, fecha: String): Call<List<Vuelo>> {
        return RetrofitClient.apiService.buscarVuelos(ciudadOrigen, ciudadDestino, fecha)
    }

    // Método para iniciar sesión
    // Log en el servicio de Retrofit
    fun iniciarSesion(nombreUsuario: String, claveUsuario: String): Call<Usuario> {
        Log.d("ViajecitosService", "Enviando solicitud de inicio de sesión para usuario: $nombreUsuario")
        return RetrofitClient.apiService.iniciarSesion(nombreUsuario, claveUsuario)
    }


    // Método para registrar cliente
    fun registrarCliente(nombre: String, email: String, documentoIdentidad: String): Call<Cliente> {
        val clienteRequest = ClienteRequest(nombre, email, documentoIdentidad)
        return RetrofitClient.apiService.registrarCliente(clienteRequest)
    }

    // Método para crear una factura
    fun crearFactura(numeroFactura: String, idEmpleado: Int, idCliente: Int, idMetodoPago: Int, descuento: Double, detalles: List<DetalleFacturaRequest>): Call<Factura> {
        val facturaRequest = FacturaRequest(numeroFactura, idEmpleado, idCliente, idMetodoPago, descuento, detalles)
        return RetrofitClient.apiService.crearFactura(facturaRequest)
    }

    // Método para obtener facturas de un cliente
    fun obtenerFacturasCliente(idCliente: Int): Call<List<Factura>> {
        return RetrofitClient.apiService.obtenerFacturasCliente(idCliente)
    }

    // Método para obtener todas las facturas de todos los clientes
    /*fun obtenerTodasFacturasPorCliente(): Call<List<ClienteFacturas>> {
        return RetrofitClient.apiService.obtenerTodasFacturasPorCliente()
    }*/
}