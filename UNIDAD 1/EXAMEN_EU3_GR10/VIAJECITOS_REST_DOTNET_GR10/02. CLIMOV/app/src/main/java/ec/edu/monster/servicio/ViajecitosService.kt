package ec.edu.monster.servicio

import ec.edu.monster.modelo.Cliente
import ec.edu.monster.modelo.Compra
import ec.edu.monster.modelo.Usuario
import ec.edu.monster.modelo.Vuelo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class ViajecitosService {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val baseUrl = "http://192.168.100.11:5158/api"

    @Throws(Exception::class)
    fun iniciarSesion(nombreUsuario: String?, claveUsuario: String?): Usuario? {
        if (nombreUsuario.isNullOrEmpty() || claveUsuario.isNullOrEmpty()) {
            throw IllegalArgumentException("Nombre de usuario y contraseña son requeridos")
        }

        val url = "$baseUrl/login?nombreUsuario=${java.net.URLEncoder.encode(nombreUsuario, "UTF-8")}&claveUsuario=${java.net.URLEncoder.encode(claveUsuario, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post("".toRequestBody()) // POST sin cuerpo
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al iniciar sesión: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)
        return Usuario(
            idCliente = json.getInt("idCliente"),
            nombreUsuario = json.getString("nombreUsuario"),
            claveUsuario = json.getString("claveUsuario")
        )
    }

    @Throws(Exception::class)
    fun obtenerVueloMasCaro(ciudadOrigen: String?, ciudadDestino: String?, fecha: Date?): Vuelo? {
        if (ciudadOrigen.isNullOrEmpty() || ciudadDestino.isNullOrEmpty() || fecha == null) {
            throw IllegalArgumentException("Ciudad origen, destino y fecha no pueden estar vacíos")
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val fechaStr = dateFormat.format(fecha)
        val url = "$baseUrl/vuelo-mas-caro?ciudadOrigen=${java.net.URLEncoder.encode(ciudadOrigen, "UTF-8")}&ciudadDestino=${java.net.URLEncoder.encode(ciudadDestino, "UTF-8")}&fecha=$fechaStr"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al obtener vuelo más caro: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)

        val dateFormatResponse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val dateFormatResponseWithMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)

        val horaSalidaStr = json.optString("horaSalida", "")
        val fechaParsed = try {
            if (horaSalidaStr.isNotEmpty()) {
                if (horaSalidaStr.length > 19) {
                    dateFormatResponseWithMillis.parse(horaSalidaStr)
                } else {
                    dateFormatResponse.parse(horaSalidaStr)
                } ?: Date()
            } else {
                Date()
            }
        } catch (e: Exception) {
            Date() // Valor por defecto en caso de error
        }

        return Vuelo(
            idVuelo = json.getInt("idVuelo"),
            ciudadOrigen = json.getString("ciudadOrigen"),
            ciudadDestino = json.getString("ciudadDestino"),
            valor = json.getDouble("valor"),
            horaSalida = horaSalidaStr,
            fecha = fechaParsed
        )
    }

    @Throws(Exception::class)
    fun buscarVuelos(ciudadOrigen: String?, ciudadDestino: String?, fecha: Date?): List<Vuelo> {
        if (ciudadOrigen.isNullOrEmpty() || ciudadDestino.isNullOrEmpty() || fecha == null) {
            throw IllegalArgumentException("Ciudad origen, destino y fecha no pueden estar vacíos")
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val fechaStr = dateFormat.format(fecha)
        val url = "$baseUrl/vuelos?ciudadOrigen=${java.net.URLEncoder.encode(ciudadOrigen, "UTF-8")}&ciudadDestino=${java.net.URLEncoder.encode(ciudadDestino, "UTF-8")}&fecha=$fechaStr"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al buscar vuelos: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val vuelos = mutableListOf<Vuelo>()

        val dateFormatResponse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val dateFormatResponseWithMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)

        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)

            val horaSalidaStr = json.optString("horaSalida", "")
            val fechaParsed = try {
                if (horaSalidaStr.isNotEmpty()) {
                    if (horaSalidaStr.length > 19) {
                        dateFormatResponseWithMillis.parse(horaSalidaStr)
                    } else {
                        dateFormatResponse.parse(horaSalidaStr)
                    } ?: Date()
                } else {
                    Date()
                }
            } catch (e: Exception) {
                Date() // Valor por defecto en caso de error
            }

            vuelos.add(
                Vuelo(
                    idVuelo = json.getInt("idVuelo"),
                    ciudadOrigen = json.getString("ciudadOrigen"),
                    ciudadDestino = json.getString("ciudadDestino"),
                    valor = json.getDouble("valor"),
                    horaSalida = horaSalidaStr,
                    fecha = fechaParsed
                )
            )
        }
        return vuelos
    }

    @Throws(Exception::class)
    fun registrarCliente(nombre: String?, email: String?, documentoIdentidad: String?): Cliente? {
        if (nombre.isNullOrEmpty() || email.isNullOrEmpty() || documentoIdentidad.isNullOrEmpty()) {
            throw IllegalArgumentException("Nombre, email y documento de identidad son requeridos")
        }

        val url = "$baseUrl/cliente?nombre=${java.net.URLEncoder.encode(nombre, "UTF-8")}&email=${java.net.URLEncoder.encode(email, "UTF-8")}&documentoIdentidad=${java.net.URLEncoder.encode(documentoIdentidad, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post("".toRequestBody()) // POST sin cuerpo
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al registrar cliente: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)
        return Cliente(
            idCliente = json.getInt("idCliente"),
            nombre = json.getString("nombre"),
            email = json.getString("email"),
            documentoIdentidad = json.getString("documentoIdentidad")
        )
    }

    @Throws(Exception::class)
    fun registrarUsuario(idCliente: Int, nombreUsuario: String?, claveUsuario: String?): Usuario? {
        if (idCliente <= 0 || nombreUsuario.isNullOrEmpty() || claveUsuario.isNullOrEmpty()) {
            throw IllegalArgumentException("ID de cliente, nombre de usuario y contraseña son requeridos")
        }

        val url = "$baseUrl/usuario?idCliente=$idCliente&nombreUsuario=${java.net.URLEncoder.encode(nombreUsuario, "UTF-8")}&claveUsuario=${java.net.URLEncoder.encode(claveUsuario, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post("".toRequestBody()) // POST sin cuerpo
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al registrar usuario: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)
        return Usuario(
            idCliente = json.getInt("idCliente"),
            nombreUsuario = json.getString("nombreUsuario"),
            claveUsuario = json.getString("claveUsuario")
        )
    }

    @Throws(Exception::class)
    fun obtenerComprasCliente(idCliente: Int): List<Compra> {
        if (idCliente <= 0) {
            throw IllegalArgumentException("ID de cliente inválido")
        }

        val url = "$baseUrl/compras/$idCliente"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al obtener compras: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val compras = mutableListOf<Compra>()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val dateFormatWithMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)

        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)
            val vueloJson = json.getJSONObject("vuelo")

            val horaSalidaStr = vueloJson.optString("horaSalida", "")
            val fechaVueloParsed = try {
                if (horaSalidaStr.isNotEmpty()) {
                    if (horaSalidaStr.length > 19) {
                        dateFormatWithMillis.parse(horaSalidaStr)
                    } else {
                        dateFormat.parse(horaSalidaStr)
                    } ?: Date()
                } else {
                    Date()
                }
            } catch (e: Exception) {
                Date() // Valor por defecto en caso de error
            }

            val fechaCompraStr = json.optString("fechaCompra", "")
            val fechaCompraParsed = try {
                if (fechaCompraStr.isNotEmpty()) {
                    if (fechaCompraStr.length > 19) {
                        dateFormatWithMillis.parse(fechaCompraStr)
                    } else {
                        dateFormat.parse(fechaCompraStr)
                    } ?: Date()
                } else {
                    Date()
                }
            } catch (e: Exception) {
                Date() // Valor por defecto en caso de error
            }

            val vuelo = Vuelo(
                idVuelo = vueloJson.getInt("idVuelo"),
                ciudadOrigen = vueloJson.getString("ciudadOrigen"),
                ciudadDestino = vueloJson.getString("ciudadDestino"),
                valor = vueloJson.getDouble("valor"),
                horaSalida = horaSalidaStr,
                fecha = fechaVueloParsed
            )

            compras.add(
                Compra(
                    idCompra = json.getInt("idCompra"),
                    vuelo = vuelo,
                    fechaCompra = fechaCompraParsed
                )
            )
        }
        return compras
    }

    @Throws(Exception::class)
    fun registrarCompra(idVuelo: Int, idCliente: Int): Int {
        if (idVuelo <= 0 || idCliente <= 0) {
            throw IllegalArgumentException("ID de vuelo y cliente deben ser válidos")
        }

        val url = "$baseUrl/compra"
        val jsonBody = JSONObject()
            .put("idVuelo", idVuelo)
            .put("idCliente", idCliente)
            .toString()
        val request = Request.Builder()
            .url(url)
            .post(jsonBody.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            throw Exception("Error al registrar compra: ${response.code} - $errorBody")
        }

        return 1 // El servicio REST no devuelve un ID, retornamos 1 como en el cliente de escritorio
    }
}

// Extensión para convertir String a MediaType (necesario para OkHttp)
fun String.toMediaType(): okhttp3.MediaType? {
    return this.toMediaTypeOrNull()
}