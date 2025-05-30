package ec.edu.monster.servicio

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import ec.edu.monster.modelo.Cliente
import ec.edu.monster.modelo.Compra
import ec.edu.monster.modelo.Usuario
import ec.edu.monster.modelo.Vuelo
import ec.edu.monster.util.SoapConstants
import org.ksoap2.SoapEnvelope
import org.ksoap2.SoapFault
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ViajecitosService {

    @Throws(Exception::class)
    fun iniciarSesion(nombreUsuario: String?, claveUsuario: String?): Usuario? {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.LOGIN_METHOD)
        request.addProperty("nombreUsuario", nombreUsuario)
        request.addProperty("claveUsuario", claveUsuario)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LOGIN_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.response as? SoapObject
            if (response != null) {
                return Usuario(
                    idCliente = response.getPropertySafelyAsString("idCliente", "0").toInt(),
                    nombreUsuario = response.getPropertySafelyAsString("nombreUsuario", ""),
                    claveUsuario = response.getPropertySafelyAsString("claveUsuario", "")
                )
            }
            Log.e("ViajecitosService", "Respuesta nula o inválida")
            return null
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en iniciarSesion: ${e.message}", e)
            throw Exception("Error en inicio de sesión: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(Exception::class)
    fun obtenerVueloMasCaro(ciudadOrigen: String?, ciudadDestino: String?, fecha: Date?): Vuelo? {
        // Validate inputs
        if (ciudadOrigen.isNullOrEmpty() || ciudadDestino.isNullOrEmpty() || fecha == null) {
            throw IllegalArgumentException("Ciudad origen, destino y fecha no pueden estar vacíos")
        }

        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.OBTENER_VUELO_MAS_CARO_METHOD)
        request.addProperty("ciudadOrigen", ciudadOrigen)
        request.addProperty("ciudadDestino", ciudadDestino)
        val dateFormatRequest = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        request.addProperty("fecha", dateFormatRequest.format(fecha))

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("ViajecitosService", "Enviando parámetros: ciudadOrigen=$ciudadOrigen, ciudadDestino=$ciudadDestino, fecha=${dateFormatRequest.format(fecha)}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.OBTENER_VUELO_MAS_CARO_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.response as? SoapObject
            if (response != null) {
                val dateFormatResponse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                val fechaString = response.getPropertySafelyAsString("fecha", "")
                val parsedFecha = if (fechaString.isNotEmpty()) {
                    try {
                        dateFormatResponse.parse(fechaString)
                    } catch (e: Exception) {
                        Log.e("ViajecitosService", "Error al parsear fecha: $fechaString", e)
                        Date() // Fallback to current date
                    }
                } else {
                    Log.w("ViajecitosService", "Fecha vacía o no presente en la respuesta")
                    Date() // Fallback to current date
                }

                return Vuelo(
                    idVuelo = response.getPropertySafelyAsString("idVuelo", "0").toInt(),
                    ciudadOrigen = response.getPropertySafelyAsString("ciudadOrigen", ""),
                    ciudadDestino = response.getPropertySafelyAsString("ciudadDestino", ""),
                    valor = response.getPropertySafelyAsString("valor", "0.0").toDouble(),
                    horaSalida = response.getPropertySafelyAsString("horaSalida", ""),
                    fecha = parsedFecha
                )
            }
            Log.w("ViajecitosService", "No se encontró vuelo para los parámetros proporcionados")
            return null
        } catch (e: SoapFault) {
            Log.e("ViajecitosService", "SoapFault en obtenerVueloMasCaro: ${e.faultstring}", e)
            throw Exception("Error del servidor: ${e.faultstring}")
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en obtenerVueloMasCaro: ${e.message}", e)
            throw Exception("Error al buscar vuelo: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun buscarVuelos(ciudadOrigen: String?, ciudadDestino: String?, fecha: Date?): List<Vuelo> {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.BUSCAR_VUELOS_METHOD)
        request.addProperty("ciudadOrigen", ciudadOrigen)
        request.addProperty("ciudadDestino", ciudadDestino)
        if (fecha != null) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            request.addProperty("fecha", dateFormat.format(fecha))
        }

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        val vuelos = mutableListOf<Vuelo>()
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.BUSCAR_VUELOS_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.bodyIn as? SoapObject
            if (response != null) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                for (i in 0 until response.propertyCount) {
                    val prop = response.getProperty(i) as? SoapObject
                    if (prop != null) {
                        vuelos.add(
                            Vuelo(
                                idVuelo = prop.getPropertySafelyAsString("idVuelo", "0").toInt(),
                                ciudadOrigen = prop.getPropertySafelyAsString("ciudadOrigen", ""),
                                ciudadDestino = prop.getPropertySafelyAsString("ciudadDestino", ""),
                                valor = prop.getPropertySafelyAsString("valor", "0.0").toDouble(),
                                horaSalida = prop.getPropertySafelyAsString("horaSalida", ""),
                                fecha = dateFormat.parse(prop.getPropertySafelyAsString("fecha", "")) ?: Date()
                            )
                        )
                    }
                }
            }
            return vuelos
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en buscarVuelos: ${e.message}", e)
            throw Exception("Error al buscar vuelos: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun registrarCliente(nombre: String?, email: String?, documentoIdentidad: String?): Cliente? {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REGISTRAR_CLIENTE_METHOD)
        request.addProperty("nombre", nombre)
        request.addProperty("email", email)
        request.addProperty("documentoIdentidad", documentoIdentidad)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_CLIENTE_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.response as? SoapObject
            if (response != null) {
                return Cliente(
                    idCliente = response.getPropertySafelyAsString("idCliente", "0").toInt(),
                    nombre = response.getPropertySafelyAsString("nombre", ""),
                    email = response.getPropertySafelyAsString("email", ""),
                    documentoIdentidad = response.getPropertySafelyAsString("documentoIdentidad", "")
                )
            }
            return null
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en registrarCliente: ${e.message}", e)
            throw Exception("Error al registrar cliente: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun registrarUsuario(idCliente: Int, nombreUsuario: String?, claveUsuario: String?): Usuario? {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REGISTRAR_USUARIO_METHOD)
        request.addProperty("idCliente", idCliente.toString())
        request.addProperty("nombreUsuario", nombreUsuario)
        request.addProperty("claveUsuario", claveUsuario)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_USUARIO_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.response as? SoapObject
            if (response != null) {
                return Usuario(
                    idCliente = response.getPropertySafelyAsString("idCliente", "0").toInt(),
                    nombreUsuario = response.getPropertySafelyAsString("nombreUsuario", ""),
                    claveUsuario = response.getPropertySafelyAsString("claveUsuario", "")
                )
            }
            return null
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en registrarUsuario: ${e.message}", e)
            throw Exception("Error al registrar usuario: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun obtenerComprasCliente(idCliente: Int): List<Compra> {
        // Validación de entrada
        if (idCliente < 1) {
            throw IllegalArgumentException("Id de cliente inválido")
        }

        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.OBTENER_COMPRAS_CLIENTE_METHOD)
        request.addProperty("idCliente", idCliente.toString())

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        val compras = mutableListOf<Compra>()
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("ViajecitosService", "Enviando parámetro: idCliente=$idCliente")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.OBTENER_COMPRAS_CLIENTE_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.bodyIn as? SoapObject
            if (response != null) {
                val dateFormatResponse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                for (i in 0 until response.propertyCount) {
                    val prop = response.getProperty(i) as? SoapObject
                    if (prop != null) {
                        val vueloObj = prop.getProperty("vuelo") as? SoapObject
                        if (vueloObj != null) {
                            val fechaVueloString = vueloObj.getPropertySafelyAsString("fecha", "")
                            val parsedFechaVuelo = if (fechaVueloString.isNotEmpty()) {
                                try {
                                    dateFormatResponse.parse(fechaVueloString)
                                } catch (e: Exception) {
                                    Log.e("ViajecitosService", "Error al parsear fecha de vuelo: $fechaVueloString", e)
                                    Date() // Fallback a la fecha actual
                                }
                            } else {
                                Log.w("ViajecitosService", "Fecha de vuelo vacía o no presente en la respuesta")
                                Date() // Fallback a la fecha actual
                            }

                            val vuelo = Vuelo(
                                idVuelo = vueloObj.getPropertySafelyAsString("idVuelo", "0").toInt(),
                                ciudadOrigen = vueloObj.getPropertySafelyAsString("ciudadOrigen", ""),
                                ciudadDestino = vueloObj.getPropertySafelyAsString("ciudadDestino", ""),
                                valor = vueloObj.getPropertySafelyAsString("valor", "0.0").toDouble(),
                                horaSalida = vueloObj.getPropertySafelyAsString("horaSalida", ""),
                                fecha = parsedFechaVuelo
                            )

                            val fechaCompraString = prop.getPropertySafelyAsString("fechaCompra", "")
                            val parsedFechaCompra = if (fechaCompraString.isNotEmpty()) {
                                try {
                                    dateFormatResponse.parse(fechaCompraString)
                                } catch (e: Exception) {
                                    Log.e("ViajecitosService", "Error al parsear fechaCompra: $fechaCompraString", e)
                                    Date() // Fallback a la fecha actual
                                }
                            } else {
                                Log.w("ViajecitosService", "Fecha de compra vacía o no presente en la respuesta")
                                Date() // Fallback a la fecha actual
                            }

                            compras.add(
                                Compra(
                                    idCompra = prop.getPropertySafelyAsString("idCompra", "0").toInt(),
                                    vuelo = vuelo,
                                    fechaCompra = parsedFechaCompra
                                )
                            )
                        }
                    }
                }
            }
            return compras
        } catch (e: SoapFault) {
            Log.e("ViajecitosService", "SoapFault en obtenerComprasCliente: ${e.faultstring}", e)
            throw Exception("Error del servidor: ${e.faultstring}")
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en obtenerComprasCliente: ${e.message}", e)
            throw Exception("Error al obtener compras: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun registrarCompra(idVuelo: Int, idCliente: Int): Int {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REGISTRAR_COMPRA_METHOD)
        request.addProperty("idVuelo", idVuelo.toString())
        request.addProperty("idCliente", idCliente.toString())

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("ViajecitosService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_COMPRA_METHOD, envelope)
            Log.d("ViajecitosService", "Request XML: ${transport.requestDump}")
            Log.d("ViajecitosService", "Response XML: ${transport.responseDump}")

            val response = envelope.response as? SoapPrimitive
            return response?.toString()?.toInt() ?: throw Exception("Respuesta nula o inválida")
        } catch (e: Exception) {
            Log.e("ViajecitosService", "Error en registrarCompra: ${e.message}", e)
            throw Exception("Error al registrar compra: ${e.message}")
        }
    }
}