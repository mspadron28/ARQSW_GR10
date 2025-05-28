package ec.edu.monster.servicio

import ec.edu.monster.modelo.Movimiento
import ec.edu.monster.modelo.Usuario
import ec.edu.monster.util.SoapConstants
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EurekaService {

    @Throws(Exception::class)
    fun login(usuario: String?, contraseña: String?): Usuario? {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.LOGIN_METHOD)
        request.addProperty("username", usuario)
        request.addProperty("clave", contraseña)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LOGIN_METHOD}")
            Log.d("EurekaService", "Enviando credenciales: username=$usuario, clave=$contraseña")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LOGIN_METHOD, envelope)
            Log.d("EurekaService", "Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "Login response: $response")

            if (response is SoapObject) {
                val userResponse = if (response.getPropertyCount() == 1 && response.getProperty(0) is SoapObject) {
                    response.getProperty(0) as SoapObject
                } else {
                    response
                }
                val estado = userResponse.getPropertySafelyAsString("estado", "")
                if (estado == "ACTIVO") {
                    return Usuario(
                        userResponse.getPropertySafelyAsString("codigo", ""),
                        userResponse.getPropertySafelyAsString("usuario", ""),
                        userResponse.getPropertySafelyAsString("clave", ""),
                        estado
                    )
                } else {
                    Log.d("EurekaService", "Usuario no activo o inválido: estado=$estado")
                    return null
                }
            } else if (response is SoapPrimitive) {
                throw Exception("Respuesta inesperada del servidor: ${response.toString()}")
            } else {
                Log.e("EurekaService", "Respuesta nula o vacía del servidor")
                throw Exception("Credenciales inválidas o respuesta vacía del servidor")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error en login: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error en login: ${e.javaClass.name} - ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun leerMovimientos(cuenta: String?): List<Movimiento> {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.LEER_MOVIMIENTOS_METHOD)
        request.addProperty("cuenta", cuenta)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        val movimientos: MutableList<Movimiento> = ArrayList()
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LEER_MOVIMIENTOS_METHOD}")
            Log.d("EurekaService", "Enviando cuenta: $cuenta")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LEER_MOVIMIENTOS_METHOD, envelope)
            Log.d("EurekaService", "LeerMovimientos Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "LeerMovimientos Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.bodyIn
            Log.d("EurekaService", "LeerMovimientos bodyIn: $response")

            if (response is SoapObject) {
                // Inspeccionar todas las propiedades para depuración
                Log.d("EurekaService", "Propiedades de response: ${response.propertyCount}")
                for (i in 0 until response.propertyCount) {
                    val propInfo = response.getPropertyInfo(i)
                    Log.d("EurekaService", "Propiedad $i: $propInfo")
                }

                // Acceder a los elementos Movimiento
                for (i in 0 until response.propertyCount) {
                    val prop = response.getProperty(i)
                    if (prop is SoapObject) { // Eliminar la verificación prop.name == "Movimiento"
                        val mov = prop
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
                        val fecha = try {
                            dateFormat.parse(mov.getPropertySafelyAsString("fecha", ""))
                        } catch (e: Exception) {
                            Log.e("EurekaService", "Error al parsear fecha: ${e.message}")
                            Date()
                        }
                        movimientos.add(
                            Movimiento(
                                mov.getPropertySafelyAsString("cuenta", ""),
                                mov.getPropertySafelyAsString("nroMov", "0").toInt(),
                                fecha ?: Date(),
                                mov.getPropertySafelyAsString("tipo", ""),
                                mov.getPropertySafelyAsString("accion", ""),
                                mov.getPropertySafelyAsString("importe", "0.0").toDouble()
                            )
                        )
                    }
                }
            } else {
                Log.e("EurekaService", "Respuesta no es SoapObject: $response")
                throw Exception("No se encontraron movimientos o respuesta vacía del servidor")
            }

            Log.d("EurekaService", "Movimientos encontrados: ${movimientos.size}")
            return movimientos
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al leer movimientos: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al leer movimientos: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun registrarDeposito(cuenta: String?, importe: Double, codEmp: String?) {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REGISTRAR_DEPOSITO_METHOD)
        request.addProperty("cuenta", cuenta)
        request.addProperty("importe", importe.toString())
        request.addProperty("codEmp", codEmp)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_DEPOSITO_METHOD}")
            Log.d("EurekaService", "Enviando depósito: cuenta=$cuenta, importe=$importe, codEmp=$codEmp")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_DEPOSITO_METHOD, envelope)
            Log.d("EurekaService", "RegistrarDeposito Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "RegistrarDeposito Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "RegistrarDeposito response: $response")

            if (response is SoapObject) {
                val estado = response.getPropertySafelyAsString("estado", "")
                if (estado == "1") {
                    return
                } else {
                    throw Exception("Error al registrar depósito: $estado")
                }
            } else if (response is SoapPrimitive && response.toString() == "1") {
                return // Éxito si la respuesta es un SoapPrimitive con valor "1"
            } else {
                Log.e("EurekaService", "Respuesta nula o inesperada del servidor: $response")
                throw Exception("Error al registrar depósito: Respuesta nula o inesperada: $response")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al registrar depósito: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al registrar depósito: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun registrarRetiro(cuenta: String?, importe: Double, codEmp: String?) {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REGISTRAR_RETIRO_METHOD)
        request.addProperty("cuenta", cuenta)
        request.addProperty("importe", importe.toString())
        request.addProperty("codEmp", codEmp)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_RETIRO_METHOD}")
            Log.d("EurekaService", "Enviando retiro: cuenta=$cuenta, importe=$importe, codEmp=$codEmp")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REGISTRAR_RETIRO_METHOD, envelope)
            Log.d("EurekaService", "RegistrarRetiro Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "RegistrarRetiro Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "RegistrarRetiro response: $response")

            if (response is SoapObject) {
                val estado = response.getPropertySafelyAsString("estado", "")
                if (estado == "1") {
                    return
                } else {
                    throw Exception("Error al registrar retiro: $estado")
                }
            } else if (response is SoapPrimitive && response.toString() == "1") {
                return // Éxito si la respuesta es un SoapPrimitive con valor "1"
            } else {
                Log.e("EurekaService", "Respuesta nula o inesperada del servidor: $response")
                throw Exception("Error al registrar retiro: Respuesta nula o inesperada: $response")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al registrar retiro: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al registrar retiro: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun realizarTransferencia(cuentaOrigen: String?, cuentaDestino: String?, importe: Double, codEmp: String?) {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.REALIZAR_TRANSFERENCIA_METHOD)
        request.addProperty("cuentaOrigen", cuentaOrigen)
        request.addProperty("cuentaDestino", cuentaDestino)
        request.addProperty("importe", importe.toString())
        request.addProperty("codEmp", codEmp)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REALIZAR_TRANSFERENCIA_METHOD}")
            Log.d("EurekaService", "Enviando transferencia: cuentaOrigen=$cuentaOrigen, cuentaDestino=$cuentaDestino, importe=$importe, codEmp=$codEmp")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.REALIZAR_TRANSFERENCIA_METHOD, envelope)
            Log.d("EurekaService", "RealizarTransferencia Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "RealizarTransferencia Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "RealizarTransferencia response: $response")

            if (response is SoapObject) {
                val estado = response.getPropertySafelyAsString("estado", "")
                if (estado == "1") {
                    return
                } else {
                    throw Exception("Error al realizar transferencia: $estado")
                }
            } else if (response is SoapPrimitive && response.toString() == "1") {
                return // Éxito si la respuesta es un SoapPrimitive con valor "1"
            } else {
                Log.e("EurekaService", "Respuesta nula o inesperada del servidor: $response")
                throw Exception("Error al realizar transferencia: Respuesta nula o inesperada: $response")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al realizar transferencia: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al realizar transferencia: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun verificarSaldo(cuenta: String?): Double {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.VERIFICAR_SALDO_METHOD)
        request.addProperty("cuenta", cuenta)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.VERIFICAR_SALDO_METHOD}")
            Log.d("EurekaService", "Enviando cuenta: $cuenta")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.VERIFICAR_SALDO_METHOD, envelope)
            Log.d("EurekaService", "VerificarSaldo Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "VerificarSaldo Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "VerificarSaldo response: $response")

            if (response is SoapPrimitive) {
                return response.toString().toDouble()
            } else if (response is SoapObject) {
                throw Exception("Respuesta inesperada: objeto en lugar de primitivo")
            } else {
                Log.e("EurekaService", "Respuesta nula o vacía del servidor")
                throw Exception("Error al verificar saldo: Respuesta nula o vacía")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al verificar saldo: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al verificar saldo: ${e.message}")
        }
    }

    @Throws(Exception::class)
    fun obtenerCostoMovimiento(cuenta: String?): Double {
        val request = SoapObject(SoapConstants.NAMESPACE, SoapConstants.OBTENER_COSTO_MOVIMIENTO_METHOD)
        request.addProperty("cuenta", cuenta)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(request)
        envelope.dotNet = false

        val transport = HttpTransportSE(SoapConstants.URL, 10000)
        try {
            transport.debug = true
            Log.d("EurekaService", "Iniciando llamada SOAP a: ${SoapConstants.URL}")
            Log.d("EurekaService", "SOAP Action: ${SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.OBTENER_COSTO_MOVIMIENTO_METHOD}")
            Log.d("EurekaService", "Enviando cuenta: $cuenta")
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.OBTENER_COSTO_MOVIMIENTO_METHOD, envelope)
            Log.d("EurekaService", "ObtenerCostoMovimiento Request XML: ${transport.requestDump ?: "No request sent"}")
            Log.d("EurekaService", "ObtenerCostoMovimiento Response XML: ${transport.responseDump ?: "No response received"}")

            val response = envelope.response
            Log.d("EurekaService", "ObtenerCostoMovimiento response: $response")

            if (response is SoapPrimitive) {
                return response.toString().toDouble()
            } else if (response is SoapObject) {
                throw Exception("Respuesta inesperada: objeto en lugar de primitivo")
            } else {
                Log.e("EurekaService", "Respuesta nula o vacía del servidor")
                throw Exception("Error al obtener costo: Respuesta nula o vacía")
            }
        } catch (e: Exception) {
            Log.e("EurekaService", "Error al obtener costo: ${e.javaClass.name} - ${e.message}", e)
            throw Exception("Error al obtener costo: ${e.message}")
        }
    }
}