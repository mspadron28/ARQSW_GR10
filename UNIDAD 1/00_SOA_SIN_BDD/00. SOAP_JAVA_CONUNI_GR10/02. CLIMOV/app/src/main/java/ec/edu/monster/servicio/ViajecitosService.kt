package ec.edu.monster.servicio

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import ec.edu.monster.modelo.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.math.BigDecimal

class ViajecitosService {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val baseUrl = "http://10.40.3.24:5158/api"

    @Throws(Exception::class)
    fun iniciarSesion(nombreUsuario: String, claveUsuario: String): Usuario? {
        if (nombreUsuario.isEmpty() || claveUsuario.isEmpty()) {
            throw IllegalArgumentException("Nombre de usuario y contraseña son requeridos")
        }

        val url = "$baseUrl/login?nombreUsuario=${URLEncoder.encode(nombreUsuario, "UTF-8")}&claveUsuario=${URLEncoder.encode(claveUsuario, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(null, ""))
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            println("Error al iniciar sesión: ${response.code} - $errorBody - ${response.message}")
            throw Exception("Error al iniciar sesión: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)
        return Usuario(
            idUsuario = json.getInt("idUsuario"),
            nombreUsuario = json.getString("nombreUsuario"),
            claveUsuario = json.getString("claveUsuario"),
            estadoUsuario = json.getString("estadoUsuario"),
            idCliente = json.optInt("idCliente", 0)
        )
    }

    @Throws(Exception::class)
    fun obtenerVuelosOrdenados(ciudadOrigen: String, ciudadDestino: String, fecha: Date): List<Vuelo> {
        if (ciudadOrigen.isEmpty() || ciudadDestino.isEmpty()) {
            throw IllegalArgumentException("Ciudad origen y destino no pueden estar vacíos")
        }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US) // Solo enviamos la fecha sin hora ni zona horaria
        val fechaStr = dateFormat.format(fecha)
        val url = "$baseUrl/vuelos?ciudadOrigen=${URLEncoder.encode(ciudadOrigen, "UTF-8")}&ciudadDestino=${URLEncoder.encode(ciudadDestino, "UTF-8")}&fecha=$fechaStr"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al obtener vuelos: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val vuelos = mutableListOf<Vuelo>()
        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)
            vuelos.add(
                Vuelo(
                    idVuelo = json.getInt("idVuelo"),
                    ciudadOrigen = json.getString("ciudadOrigen"),
                    ciudadDestino = json.getString("ciudadDestino"),
                    valor = json.getDouble("valor").toBigDecimal(),
                    horaSalida = json.getString("horaSalida")
                )
            )
        }
        return vuelos
    }

    @Throws(Exception::class)
    fun registrarCliente(nombre: String, email: String, documentoIdentidad: String): Cliente? {
        if (nombre.isEmpty() || email.isEmpty() || documentoIdentidad.isEmpty()) {
            throw IllegalArgumentException("Nombre, email y documento de identidad son requeridos")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw IllegalArgumentException("Formato de email inválido")
        }

        val url = "$baseUrl/cliente?nombre=${URLEncoder.encode(nombre, "UTF-8")}&email=${URLEncoder.encode(email, "UTF-8")}&documentoIdentidad=${URLEncoder.encode(documentoIdentidad, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(null, ""))
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
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
    fun registrarClienteYUsuario(nombre: String, email: String, documentoIdentidad: String, nombreUsuario: String, claveUsuario: String): Usuario? {
        val cliente = registrarCliente(nombre, email, documentoIdentidad) ?: throw Exception("Error al registrar cliente")
        val url = "$baseUrl/usuario?idCliente=${cliente.idCliente}&nombreUsuario=${URLEncoder.encode(nombreUsuario, "UTF-8")}&claveUsuario=${URLEncoder.encode(claveUsuario, "UTF-8")}"
        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(null, ""))
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al registrar usuario: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)
        return Usuario(
            idUsuario = json.getInt("idUsuario"),
            nombreUsuario = json.getString("nombreUsuario"),
            claveUsuario = json.getString("claveUsuario"),
            estadoUsuario = json.getString("estadoUsuario"),
            idCliente = json.optInt("idCliente", 0)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(Exception::class)
    fun crearFactura(numeroFactura: String, idEmpleado: Int, idCliente: Int, idMetodoPago: Int, descuento: BigDecimal, detalles: List<ec.edu.monster.modelo.DetalleFactura>): Factura? {
        if (numeroFactura.isEmpty() || idEmpleado <= 0 || idMetodoPago <= 0) {
            throw IllegalArgumentException("Parámetros de factura inválidos")
        }
        if (detalles.isEmpty()) {
            throw IllegalArgumentException("Debe incluir al menos un detalle de factura")
        }

        val jsonBody = JSONObject().apply {
            put("numeroFactura", numeroFactura)
            put("idEmpleado", idEmpleado)
            put("idCliente", idCliente)
            put("idMetodoPago", idMetodoPago)
            put("descuento", descuento.toDouble())
            put("detalles", JSONArray().apply {
                detalles.forEach { detalle ->
                    put(JSONObject().apply {
                        put("idVuelo", detalle.idVuelo)
                        put("cantidad", detalle.cantidad)
                    })
                }
            })
        }.toString()

        val request = Request.Builder()
            .url("$baseUrl/factura")
            .post(RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonBody))
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al crear factura: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val json = JSONObject(responseBody)

        val empleado = if (json.has("empleado")) {
            val empJson = json.getJSONObject("empleado")
            Empleado(
                idEmpleado = empJson.optInt("idEmpleado", 0),
                nombre = empJson.optString("nombre", ""),
                email = empJson.optString("email", "")
            )
        } else null

        val cliente = if (json.has("cliente")) {
            val cliJson = json.getJSONObject("cliente")
            Cliente(
                idCliente = cliJson.optInt("idCliente", 0),
                nombre = cliJson.optString("nombre", ""),
                email = cliJson.optString("email", ""),
                documentoIdentidad = cliJson.optString("documentoIdentidad", "")
            )
        } else null

        val metodoPago = if (json.has("metodoPago")) {
            val mpJson = json.getJSONObject("metodoPago")
            MetodoPago(
                idMetodoPago = mpJson.optInt("idMetodoPago", 0),
                nombreMetodo = mpJson.optString("nombreMetodo", ""),
                descripcion = mpJson.optString("descripcion", "")
            )
        } else null

        val detallesFactura = if (json.has("detallesFactura")) {
            val detallesArray = json.getJSONArray("detallesFactura")
            val detallesList = mutableListOf<DetalleFactura>()
            for (i in 0 until detallesArray.length()) {
                val detalleJson = detallesArray.getJSONObject(i)
                detallesList.add(
                    DetalleFactura(
                        idDetalleFactura = detalleJson.optInt("idDetalleFactura", 0),
                        idFactura = detalleJson.optInt("idFactura", 0),
                        idVuelo = detalleJson.optInt("idVuelo", 0),
                        cantidad = detalleJson.optInt("cantidad", 0),
                        valorUnitario = detalleJson.optDouble("valorUnitario", 0.0).toBigDecimal(),
                        total = detalleJson.optDouble("total", 0.0).toBigDecimal()
                    )
                )
            }
            detallesList
        } else emptyList()

        return Factura(
            idFactura = json.getInt("idFactura"),
            numeroFactura = json.getString("numeroFactura"),
            fechaEmision = json.getString("fechaEmision"),
            idEmpleado = json.getInt("idEmpleado"),
            idCliente = json.getInt("idCliente"),
            idMetodoPago = json.getInt("idMetodoPago"),
            subtotal = json.getDouble("subtotal").toBigDecimal(),
            descuento = json.getDouble("descuento").toBigDecimal(),
            iva = json.getDouble("iva").toBigDecimal(),
            total = json.getDouble("total").toBigDecimal(),
            empleado = empleado,
            cliente = cliente,
            metodoPago = metodoPago,
            detallesFactura = detallesFactura
        )
    }

    @Throws(Exception::class)
    fun obtenerFacturasCliente(idCliente: Int): List<Factura> {
        val url = "$baseUrl/facturas/$idCliente"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al obtener facturas: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val facturas = mutableListOf<Factura>()
        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)

            val empleado = if (json.has("empleado")) {
                val empJson = json.getJSONObject("empleado")
                Empleado(
                    idEmpleado = empJson.optInt("idEmpleado", 0),
                    nombre = empJson.optString("nombre", ""),
                    email = empJson.optString("email", "")
                )
            } else null

            val cliente = if (json.has("cliente")) {
                val cliJson = json.getJSONObject("cliente")
                Cliente(
                    idCliente = cliJson.optInt("idCliente", 0),
                    nombre = cliJson.optString("nombre", ""),
                    email = cliJson.optString("email", ""),
                    documentoIdentidad = cliJson.optString("documentoIdentidad", "")
                )
            } else null

            val metodoPago = if (json.has("metodoPago")) {
                val mpJson = json.getJSONObject("metodoPago")
                MetodoPago(
                    idMetodoPago = mpJson.optInt("idMetodoPago", 0),
                    nombreMetodo = mpJson.optString("nombreMetodo", ""),
                    descripcion = mpJson.optString("descripcion", "")
                )
            } else null

            val detallesFactura = if (json.has("detallesFactura")) {
                val detallesArray = json.getJSONArray("detallesFactura")
                val detallesList = mutableListOf<DetalleFactura>()
                for (j in 0 until detallesArray.length()) {
                    val detalleJson = detallesArray.getJSONObject(j)
                    detallesList.add(
                        DetalleFactura(
                            idDetalleFactura = detalleJson.optInt("idDetalleFactura", 0),
                            idFactura = detalleJson.optInt("idFactura", 0),
                            idVuelo = detalleJson.optInt("idVuelo", 0),
                            cantidad = detalleJson.optInt("cantidad", 0),
                            valorUnitario = detalleJson.optDouble("valorUnitario", 0.0).toBigDecimal(),
                            total = detalleJson.optDouble("total", 0.0).toBigDecimal()
                        )
                    )
                }
                detallesList
            } else emptyList()

            facturas.add(
                Factura(
                    idFactura = json.optInt("idFactura", 0),
                    numeroFactura = json.optString("numeroFactura", ""),
                    fechaEmision = json.optString("fechaEmision", ""),
                    idEmpleado = json.optInt("idEmpleado", 0),
                    idCliente = json.optInt("idCliente", 0),
                    idMetodoPago = json.optInt("idMetodoPago", 0),
                    subtotal = json.optDouble("subtotal", 0.0).toBigDecimal(),
                    descuento = json.optDouble("descuento", 0.0).toBigDecimal(),
                    iva = json.optDouble("iva", 0.0).toBigDecimal(),
                    total = json.optDouble("total", 0.0).toBigDecimal(),
                    empleado = empleado,
                    cliente = cliente,
                    metodoPago = metodoPago,
                    detallesFactura = detallesFactura
                )
            )
        }
        return facturas
    }

    @Throws(Exception::class)
    fun obtenerTodasFacturasPorCliente(): List<ClienteFacturas> {
        val url = "$baseUrl/facturas"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al obtener facturas: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val result = mutableListOf<ClienteFacturas>()
        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)
            val facturasCliente = mutableListOf<Factura>()
            val facturasArray = json.getJSONArray("facturas")
            for (j in 0 until facturasArray.length()) {
                val facturaJson = facturasArray.getJSONObject(j)

                val empleado = if (facturaJson.has("empleado")) {
                    val empJson = facturaJson.getJSONObject("empleado")
                    Empleado(
                        idEmpleado = empJson.optInt("idEmpleado", 0),
                        nombre = empJson.optString("nombre", ""),
                        email = empJson.optString("email", "")
                    )
                } else null

                val cliente = if (facturaJson.has("cliente")) {
                    val cliJson = facturaJson.getJSONObject("cliente")
                    Cliente(
                        idCliente = cliJson.optInt("idCliente", 0),
                        nombre = cliJson.optString("nombre", ""),
                        email = cliJson.optString("email", ""),
                        documentoIdentidad = cliJson.optString("documentoIdentidad", "")
                    )
                } else null

                val metodoPago = if (facturaJson.has("metodoPago")) {
                    val mpJson = facturaJson.getJSONObject("metodoPago")
                    MetodoPago(
                        idMetodoPago = mpJson.optInt("idMetodoPago", 0),
                        nombreMetodo = mpJson.optString("nombreMetodo", ""),
                        descripcion = mpJson.optString("descripcion", "")
                    )
                } else null

                val detallesFactura = if (facturaJson.has("detallesFactura")) {
                    val detallesArray = facturaJson.getJSONArray("detallesFactura")
                    val detallesList = mutableListOf<DetalleFactura>()
                    for (k in 0 until detallesArray.length()) {
                        val detalleJson = detallesArray.getJSONObject(k)
                        detallesList.add(
                            DetalleFactura(
                                idDetalleFactura = detalleJson.optInt("idDetalleFactura", 0),
                                idFactura = detalleJson.optInt("idFactura", 0),
                                idVuelo = detalleJson.optInt("idVuelo", 0),
                                cantidad = detalleJson.optInt("cantidad", 0),
                                valorUnitario = detalleJson.optDouble("valorUnitario", 0.0).toBigDecimal(),
                                total = detalleJson.optDouble("total", 0.0).toBigDecimal()
                            )
                        )
                    }
                    detallesList
                } else emptyList()

                facturasCliente.add(
                    Factura(
                        idFactura = facturaJson.optInt("idFactura", 0),
                        numeroFactura = facturaJson.optString("numeroFactura", ""),
                        fechaEmision = facturaJson.optString("fechaEmision", ""),
                        idEmpleado = facturaJson.optInt("idEmpleado", 0),
                        idCliente = facturaJson.optInt("idCliente", 0),
                        idMetodoPago = facturaJson.optInt("idMetodoPago", 0),
                        subtotal = facturaJson.optDouble("subtotal", 0.0).toBigDecimal(),
                        descuento = facturaJson.optDouble("descuento", 0.0).toBigDecimal(),
                        iva = facturaJson.optDouble("iva", 0.0).toBigDecimal(),
                        total = facturaJson.optDouble("total", 0.0).toBigDecimal(),
                        empleado = empleado,
                        cliente = cliente,
                        metodoPago = metodoPago,
                        detallesFactura = detallesFactura
                    )
                )
            }
            val clienteFacturas = ClienteFacturas()
            clienteFacturas.clienteId = json.optInt("clienteId", 0)
            clienteFacturas.nombre = json.optString("nombre", "")
            clienteFacturas.email = json.optString("email", "")
            clienteFacturas.documentoIdentidad = json.optString("documentoIdentidad", "")
            clienteFacturas.facturas = facturasCliente
            result.add(clienteFacturas)
        }
        return result
    }

    @Throws(Exception::class)
    fun obtenerTodosClientes(): List<Cliente> {
        val url = "$baseUrl/clientes"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Error desconocido"
            throw Exception("Error al obtener clientes: ${response.code} - $errorBody")
        }

        val responseBody = response.body?.string() ?: throw Exception("Respuesta vacía del servidor")
        val jsonArray = JSONArray(responseBody)
        val clientes = mutableListOf<Cliente>()
        for (i in 0 until jsonArray.length()) {
            val json = jsonArray.getJSONObject(i)
            clientes.add(
                Cliente(
                    idCliente = json.optInt("idCliente", 0),
                    nombre = json.optString("nombre", ""),
                    email = json.optString("email", ""),
                    documentoIdentidad = json.optString("documentoIdentidad", "")
                )
            )
        }
        return clientes
    }
}