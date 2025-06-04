package ec.edu.monster.conuni

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.modelo.DetalleFactura
import ec.edu.monster.modelo.Vuelo
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.servicio.ViajecitosService
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class SearchFlightsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
        val usuario = sharedPref.getString("usuario", null)

        if (usuario == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Buscar Vuelos y Comprar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1), titleContentColor = Color.White)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SearchFlightsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFlightsScreen(modifier: Modifier = Modifier) {
    var ciudadOrigen by remember { mutableStateOf("") }
    var ciudadDestino by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var vuelos by remember { mutableStateOf<List<Vuelo>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var expandedVueloId by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
    val idCliente = sharedPref.getInt("idCliente", 0)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { timeZone = TimeZone.getTimeZone("GMT-05:00") }
    val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply { timeZone = TimeZone.getTimeZone("GMT-05:00") }
    val timeFormat = SimpleDateFormat("HH:mm", Locale.US)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F5))
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color(0xFF212121))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Buscar Vuelos y Comprar", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 16.dp))
                OutlinedTextField(value = ciudadOrigen, onValueChange = { ciudadOrigen = it }, label = { Text("Ciudad Origen") }, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161)))
                OutlinedTextField(value = ciudadDestino, onValueChange = { ciudadDestino = it }, label = { Text("Ciudad Destino") }, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161)))
                OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha (yyyy-MM-dd)") }, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161)))
                Button(onClick = {
                    if (ciudadOrigen.isNotEmpty() && ciudadDestino.isNotEmpty() && fecha.isNotEmpty()) {
                        try {
                            val parsedDate = dateFormat.parse(fecha)
                            if (parsedDate != null) {
                                isLoading = true
                                errorMessage = null
                                expandedVueloId = null
                                object : AsyncTask<Void, Void, Pair<List<Vuelo>, String?>>() {
                                    override fun doInBackground(vararg params: Void?) = try {
                                        val controlador = AppControlador()
                                        val trimmedOrigen = ciudadOrigen.trim()
                                        val trimmedDestino = ciudadDestino.trim()
                                        val rawData = controlador.buscarVuelos(trimmedOrigen, trimmedDestino, parsedDate)
                                        println("Parsed Vuelos: $rawData") // Debug log
                                        Pair(rawData, null)
                                    } catch (e: Exception) {
                                        println("Error in AsyncTask: ${e.message}") // Error log
                                        Pair(emptyList(), e.message)
                                    }
                                    override fun onPostExecute(result: Pair<List<Vuelo>, String?>) {
                                        isLoading = false
                                        vuelos = result.first
                                        errorMessage = result.second ?: if (vuelos.isEmpty()) "No se encontraron vuelos" else null
                                    }
                                }.execute()
                            } else {
                                errorMessage = "Fecha inválida. Usa el formato yyyy-MM-dd"
                            }
                        } catch (e: Exception) {
                            errorMessage = "Fecha inválida. Usa el formato yyyy-MM-dd"
                        }
                    } else {
                        errorMessage = "Por favor, completa todos los campos"
                    }
                }, modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White), enabled = !isLoading) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(modifier = Modifier.width(8.dp)); Text("Buscar Vuelos", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                }
                if (isLoading) CircularProgressIndicator(modifier = Modifier.size(48.dp), color = Color(0xFF0288D1))
                else if (errorMessage != null) Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE), contentColor = Color(0xFFD32F2F))) { Text(errorMessage!!, fontSize = 14.sp, modifier = Modifier.padding(12.dp), textAlign = TextAlign.Center) }
                else if (vuelos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Vuelos Disponibles", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.align(Alignment.Start))
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
                        items(vuelos) { vuelo ->
                            val isExpanded = expandedVueloId == vuelo.idVuelo
                            val horaSalidaTime = try {
                                val parsedDate = isoDateFormat.parse(vuelo.horaSalida)
                                timeFormat.format(parsedDate ?: Date())
                            } catch (e: Exception) {
                                "N/A"
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { expandedVueloId = if (isExpanded) null else vuelo.idVuelo },
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color(0xFF212121))
                            ) {
                                Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            Text("Origen", fontSize = 12.sp, color = Color.Gray)
                                            Text(vuelo.ciudadOrigen, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            Text("Destino", fontSize = 12.sp, color = Color.Gray)
                                            Text(vuelo.ciudadDestino, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            Text("Valor", fontSize = 12.sp, color = Color.Gray)
                                            Text("$${vuelo.valor}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            Text("Hora Salida", fontSize = 12.sp, color = Color.Gray)
                                            Text(horaSalidaTime, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Icon(
                                            if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                            contentDescription = if (isExpanded) "Contraer" else "Expandir",
                                            tint = Color(0xFF0288D1),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    if (isExpanded) PurchaseSection(vuelo, idCliente, { context.startActivity(Intent(context, InvoicesActivity::class.java).apply { putExtra("idCliente", idCliente) }); (context as? ComponentActivity)?.finish() })
                                }
                            }
                        }
                    }
                } else if (!isLoading && vuelos.isEmpty() && errorMessage == null) Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F5), contentColor = Color(0xFF616161))) { Text("No se encontraron vuelos", fontSize = 14.sp, modifier = Modifier.padding(12.dp), textAlign = TextAlign.Center) }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { context.startActivity(Intent(context, MainMenuActivity::class.java)); (context as? ComponentActivity)?.finish() }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                    Text("Volver al Menú", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseSection(vuelo: Vuelo, idClient: Int, onPurchaseSuccess: () -> Unit) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var cantidad by remember { mutableStateOf(1) }
    var metodoPago by remember { mutableStateOf("Tarjeta de Crédito") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.White, RoundedCornerShape(8.dp)).padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50), contentColor = Color.White)) {
            Text("Comprar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }

    if (showDialog) AlertDialog(onDismissRequest = { showDialog = false }, title = { Text("Confirmar Compra", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1)) }, text = {
        Column {
            Text("Vuelo: ${vuelo.ciudadOrigen} → ${vuelo.ciudadDestino}", fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text("Valor por boleto: $${vuelo.valor}", fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(value = cantidad.toString(), onValueChange = { cantidad = it.toIntOrNull() ?: 1; if (cantidad <= 0) cantidad = 1 }, label = { Text("Cantidad de boletos") }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), shape = RoundedCornerShape(12.dp), colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161)))
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                OutlinedTextField(value = metodoPago, onValueChange = {}, label = { Text("Método de Pago") }, modifier = Modifier.fillMaxWidth().menuAnchor(), readOnly = true, trailingIcon = { Icon(if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, contentDescription = null, tint = Color(0xFF0288D1)) }, shape = RoundedCornerShape(12.dp), colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161)))
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) { listOf("Tarjeta de Crédito", "Tarjeta de Débito", "Efectivo").forEach { option -> DropdownMenuItem(text = { Text(option) }, onClick = { metodoPago = option; expanded = false }) } }
            }
            errorMessage?.let { Text(it, color = Color(0xFFD32F2F), fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp)) }
        }
    }, confirmButton = {
        Button(onClick = {
            if (idClient <= 0) { errorMessage = "Error: No se encontró el ID del cliente"; return@Button }
            isLoading = true
            errorMessage = null
            object : AsyncTask<Void, Void, Pair<ec.edu.monster.modelo.Factura?, String?>>() {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun doInBackground(vararg params: Void?) = try {
                    val detalles = listOf(DetalleFactura(idDetalleFactura = 0, idFactura = 0, idVuelo = vuelo.idVuelo, cantidad = cantidad, valorUnitario = vuelo.valor, total = vuelo.valor.multiply(BigDecimal(cantidad))))
                    val numeroFactura = "FACT-${System.currentTimeMillis()}"
                    val metodoPagoId = when (metodoPago) { "Tarjeta de Crédito" -> 1; "Tarjeta de Débito" -> 2; "Efectivo" -> 3; else -> 1 }
                    Pair(ViajecitosService().crearFactura(numeroFactura, 1, idClient, metodoPagoId, BigDecimal.ZERO, detalles), null)
                } catch (e: Exception) { Pair(null, e.message) }
                override fun onPostExecute(result: Pair<ec.edu.monster.modelo.Factura?, String?>) {
                    isLoading = false
                    if (result.first != null) { Toast.makeText(context, "Compra realizada con éxito", Toast.LENGTH_SHORT).show(); showDialog = false; onPurchaseSuccess() } else { errorMessage = result.second ?: "Error al realizar la compra" }
                }
            }.execute()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50), contentColor = Color.White), enabled = !isLoading) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp) else Text("Confirmar")
        }
    }, dismissButton = { TextButton(onClick = { showDialog = false }) { Text("Cancelar", color = Color(0xFF0288D1)) } })
}