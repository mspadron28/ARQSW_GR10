package ec.edu.monster.conuni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.modelo.Factura

class InvoiceDetailsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val factura = intent.getSerializableExtra("factura") as? Factura
        if (factura == null) {
            finish()
            return
        }

        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Detalles de Factura", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                            navigationIcon = { IconButton(onClick = { finish() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White) } },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1), titleContentColor = Color.White)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    InvoiceDetailsScreen(modifier = Modifier.padding(innerPadding), factura = factura)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceDetailsScreen(modifier: Modifier = Modifier, factura: Factura) {
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
                Text("Detalles de Factura", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 16.dp))
                Divider(modifier = Modifier.fillMaxWidth(), color = Color(0xFF0288D1), thickness = 1.dp)
                Text("Información General", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Número:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("${factura.numeroFactura}", fontSize = 16.sp) }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Fecha Emisión:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("${factura.fechaEmision}", fontSize = 16.sp) }
                Divider(modifier = Modifier.fillMaxWidth(), color = Color(0xFF0288D1), thickness = 1.dp)
                Text("Resumen", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Subtotal:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("$${factura.subtotal}", fontSize = 16.sp) }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Descuento:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("$${factura.descuento}", fontSize = 16.sp) }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("IVA:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("$${factura.iva}", fontSize = 16.sp) }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("Total:", fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("$${factura.total}", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                Divider(modifier = Modifier.fillMaxWidth(), color = Color(0xFF0288D1), thickness = 1.dp)
                factura.detallesFactura?.let { detalles ->
                    if (detalles.isNotEmpty()) {
                        Text("Detalles de Vuelos", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                            items(detalles) { detalle ->
                                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F5), contentColor = Color(0xFF212121))) {
                                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text("Vuelo ID: ${detalle.idVuelo}", fontSize = 14.sp)
                                        Text("Cantidad: ${detalle.cantidad}", fontSize = 14.sp)
                                        Text("Total: $${detalle.total}", fontSize = 14.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}