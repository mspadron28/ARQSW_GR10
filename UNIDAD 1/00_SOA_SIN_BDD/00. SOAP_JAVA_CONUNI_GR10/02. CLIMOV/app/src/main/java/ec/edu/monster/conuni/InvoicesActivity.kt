package ec.edu.monster.conuni

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.modelo.Cliente
import ec.edu.monster.modelo.ClienteFacturas
import ec.edu.monster.modelo.Factura
import ec.edu.monster.servicio.ViajecitosService
import android.os.AsyncTask
import android.widget.Toast

class InvoicesActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Consultar Facturas", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                            navigationIcon = { IconButton(onClick = { finish() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White) } },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1), titleContentColor = Color.White)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    InvoicesScreen(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}

@Composable
fun InvoicesScreen(modifier: Modifier = Modifier, activity: ComponentActivity) {
    var clientes by remember { mutableStateOf<List<Cliente>>(emptyList()) }
    var facturas by remember { mutableStateOf<List<ClienteFacturas>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedClientId by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        isLoading = true
        object : AsyncTask<Void, Void, Pair<Pair<List<Cliente>, List<ClienteFacturas>>?, String?>>() {
            override fun doInBackground(vararg params: Void?) = try {
                val service = ViajecitosService()
                Pair(Pair(service.obtenerTodosClientes(), service.obtenerTodasFacturasPorCliente()), null)
            } catch (e: Exception) { Pair(null, e.message) }
            override fun onPostExecute(result: Pair<Pair<List<Cliente>, List<ClienteFacturas>>?, String?>) {
                isLoading = false
                if (result?.first != null) {
                    clientes = result.first!!.first
                    facturas = result.first!!.second
                    if (clientes.isEmpty() && facturas.isEmpty()) errorMessage = "No hay clientes ni facturas"
                } else {
                    errorMessage = result?.second ?: "Error al cargar datos"
                }
            }
        }.execute()
    }

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
                Text("Seleccionar Cliente o Ver Todas", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 16.dp))
                if (isLoading) CircularProgressIndicator(modifier = Modifier.size(48.dp), color = Color(0xFF0288D1))
                else if (errorMessage != null) Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE), contentColor = Color(0xFFD32F2F))) { Text(errorMessage!!, fontSize = 14.sp, modifier = Modifier.padding(12.dp), textAlign = TextAlign.Center) }
                else {
                    LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 200.dp)) {
                        item { Text("Clientes:", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 8.dp)) }
                        items(clientes) { cliente ->
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { selectedClientId = cliente.idCliente }, shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F5), contentColor = Color(0xFF212121))) {
                                Text("ID: ${cliente.idCliente} - ${cliente.nombre}", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                            }
                        }
                        item { Button(onClick = { selectedClientId = null }, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) { Text("Ver Todas las Facturas", fontSize = 16.sp) } }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val filteredFacturas = if (selectedClientId != null) facturas.filter { it.clienteId == selectedClientId } else facturas
                    if (filteredFacturas.isNotEmpty()) {
                        Card(modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xFF0288D1), RoundedCornerShape(8.dp)), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Cliente", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                Text("Nº Factura", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                Text("Total", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                            }
                        }
                        LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
                            items(filteredFacturas) { clienteFacturas ->
                                clienteFacturas.facturas.forEach { factura ->
                                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp).clickable {
                                        context.startActivity(Intent(context, InvoiceDetailsActivity::class.java).apply { putExtra("factura", factura) })
                                    }, shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F5), contentColor = Color(0xFF212121))) {
                                        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(clienteFacturas.nombre, fontSize = 14.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                            Text("#${factura.numeroFactura}", fontSize = 14.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                            Text("$${factura.total}", fontSize = 14.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                        }
                                    }
                                }
                            }
                        }
                    } else if (selectedClientId != null) Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F5), contentColor = Color(0xFF616161))) { Text("No hay facturas para este cliente", fontSize = 14.sp, modifier = Modifier.padding(12.dp), textAlign = TextAlign.Center) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { activity.finish() }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                    Text("Volver al Menú", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}