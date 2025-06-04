package ec.edu.monster.controlador

import android.util.Log
import androidx.compose.runtime.Composable
import ec.edu.monster.modelo.*
import ec.edu.monster.servicio.DetalleFacturaRequest
import ec.edu.monster.servicio.ViajecitosService
import java.util.*

class ViajecitosController(private val service: ViajecitosService) {

    private var _usuarioAutenticado: Usuario? = null
    var usuarioAutenticado: Usuario?
        get() = _usuarioAutenticado
        set(value) {
            _usuarioAutenticado = value
        }


    // Método para iniciar sesión
    fun iniciarSesion(nombreUsuario: String, claveUsuario: String, callback: (Usuario?) -> Unit) {
        if (nombreUsuario.isBlank() || claveUsuario.isBlank()) {
            Log.e("ViajecitosController", "Usuario y contraseña son requeridos.")
            throw IllegalArgumentException("Usuario y contraseña son requeridos.")
        }

        // Log para verificar los datos enviados
        Log.d("ViajecitosController", "Iniciando sesión con Usuario: $nombreUsuario")

        service.iniciarSesion(nombreUsuario, claveUsuario).enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: retrofit2.Call<Usuario>, response: retrofit2.Response<Usuario>) {
                if (response.isSuccessful) {
                    _usuarioAutenticado = response.body()  // Asignar el usuario autenticado
                    Log.d("ViajecitosController", "Inicio de sesión exitoso para: ${_usuarioAutenticado?.nombreUsuario}")
                    callback(_usuarioAutenticado)
                } else {
                    // Log para ver la respuesta de error
                    Log.e("ViajecitosController", "Error en la respuesta del servidor: ${response.message()}")
                    Log.e("ViajecitosController", "Cuerpo de la respuesta: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<Usuario>, t: Throwable) {
                // Log de error si ocurre alguna falla en la llamada
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }



    // Obtener ID del cliente autenticado
    fun obtenerIdClienteAutenticado(): Int {
        if (_usuarioAutenticado == null) {
            throw IllegalStateException("No hay usuario autenticado.")
        }
        return _usuarioAutenticado?.idCliente ?: 0
    }

    // Buscar vuelos
    fun buscarVuelos(ciudadOrigen: String, ciudadDestino: String, fecha: String, callback: (List<Vuelo>?) -> Unit) {
        if (ciudadOrigen.isBlank() || ciudadDestino.isBlank()) {
            throw IllegalArgumentException("Ciudad de origen y destino son requeridas.")
        }

        service.buscarVuelos(ciudadOrigen, ciudadDestino, fecha).enqueue(object : retrofit2.Callback<List<Vuelo>> {
            override fun onResponse(call: retrofit2.Call<List<Vuelo>>, response: retrofit2.Response<List<Vuelo>>) {
                if (response.isSuccessful) {
                    val vuelos = response.body()
                    callback(vuelos)
                } else {
                    Log.e("ViajecitosController", "Error en la respuesta: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Vuelo>>, t: Throwable) {
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }

    // Registrar un cliente
    fun registrarCliente(nombre: String, email: String, documentoIdentidad: String, callback: (Cliente?) -> Unit) {
        if (nombre.isBlank() || email.isBlank() || documentoIdentidad.isBlank()) {
            throw IllegalArgumentException("Todos los campos son requeridos.")
        }

        service.registrarCliente(nombre, email, documentoIdentidad).enqueue(object : retrofit2.Callback<Cliente> {
            override fun onResponse(call: retrofit2.Call<Cliente>, response: retrofit2.Response<Cliente>) {
                if (response.isSuccessful) {
                    val cliente = response.body()
                    callback(cliente)
                } else {
                    Log.e("ViajecitosController", "Error en la respuesta: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<Cliente>, t: Throwable) {
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }

    // Crear una factura
    fun crearFactura(numeroFactura: String, idEmpleado: Int, idCliente: Int, idMetodoPago: Int, descuento: Double, detalles: List<DetalleFacturaRequest>, callback: (Factura?) -> Unit) {
        if (numeroFactura.isBlank()) {
            throw IllegalArgumentException("El número de factura es requerido.")
        }
        if (idCliente <= 0) {
            throw IllegalArgumentException("ID de cliente inválido.")
        }
        if (idMetodoPago <= 0) {
            throw IllegalArgumentException("Método de pago inválido.")
        }
        if (detalles.isEmpty()) {
            throw IllegalArgumentException("Debe incluir al menos un detalle de factura.")
        }

        service.crearFactura(numeroFactura, idEmpleado, idCliente, idMetodoPago, descuento, detalles).enqueue(object : retrofit2.Callback<Factura> {
            override fun onResponse(call: retrofit2.Call<Factura>, response: retrofit2.Response<Factura>) {
                if (response.isSuccessful) {
                    val factura = response.body()
                    callback(factura)
                } else {
                    Log.e("ViajecitosController", "Error en la respuesta: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<Factura>, t: Throwable) {
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }

    // Obtener las facturas de un cliente
    fun obtenerFacturasCliente(idCliente: Int, callback: (List<Factura>?) -> Unit) {
        if (idCliente <= 0) {
            throw IllegalArgumentException("ID de cliente inválido.")
        }

        service.obtenerFacturasCliente(idCliente).enqueue(object : retrofit2.Callback<List<Factura>> {
            override fun onResponse(call: retrofit2.Call<List<Factura>>, response: retrofit2.Response<List<Factura>>) {
                if (response.isSuccessful) {
                    val facturas = response.body()
                    callback(facturas)
                } else {
                    Log.e("ViajecitosController", "Error en la respuesta: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Factura>>, t: Throwable) {
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }

    // Obtener todas las facturas de todos los clientes
    /*fun obtenerTodasFacturasPorCliente(callback: (List<ClienteFacturas>?) -> Unit) {
        service.obtenerTodasFacturasPorCliente().enqueue(object : retrofit2.Callback<List<ClienteFacturas>> {
            override fun onResponse(call: retrofit2.Call<List<ClienteFacturas>>, response: retrofit2.Response<List<ClienteFacturas>>) {
                if (response.isSuccessful) {
                    val clientesFacturas = response.body()
                    callback(clientesFacturas)
                } else {
                    Log.e("ViajecitosController", "Error en la respuesta: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<ClienteFacturas>>, t: Throwable) {
                Log.e("ViajecitosController", "Error en la llamada: ${t.message}")
                callback(null)
            }
        })
    }*/
}