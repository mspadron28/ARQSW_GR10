package ec.edu.monster.conuni.rest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.modelo.Movimiento
import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.*

class ConsultaActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10_RESTTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Consulta de Movimientos", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF1E272E)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ConsultaScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigate = { destination ->
                            startActivity(Intent(this@ConsultaActivity, destination))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaScreen(modifier: Modifier = Modifier, onNavigate: (Class<*>) -> Unit) {
    var cuenta by remember { mutableStateOf("") }
    var movimientos by remember { mutableStateOf<List<Movimiento>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var mensaje by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.Main)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFF1E272E), shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Consulta de Movimientos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = cuenta,
            onValueChange = { cuenta = it },
            label = { Text("Cuenta", color = Color(0xFFB0BEC5)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2D3436),
                unfocusedContainerColor = Color(0xFF2D3436),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color(0xFF81D4FA),
                unfocusedIndicatorColor = Color(0xFF78909C)
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    try {
                        val controlador = AppControlador()
                        movimientos = withContext(Dispatchers.IO) {
                            controlador.traerMovimientos(cuenta).sortedByDescending { it.fecha }
                        }
                        if (movimientos.isEmpty()) {
                            mensaje = "No se encontraron movimientos para la cuenta."
                        } else {
                            mensaje = null
                        }
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = "Error: ${e.message}"
                        mensaje = null
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34495E))
        ) {
            Text("Consultar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Button(
            onClick = { onNavigate(MenuActivity::class.java) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34495E))
        ) {
            Text("Volver al Menú", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        errorMessage?.let {
            Text(
                text = it,
                color = Color(0xFFEF5350),
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        mensaje?.let {
            Text(
                text = it,
                color = Color(0xFF66BB6A),
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        if (movimientos.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                item {
                    Text(
                        text = "Movimientos",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
                items(movimientos.size) { index ->
                    MovimientoItem(movimientos[index])
                }
            }
        }
    }
}

@Composable
fun MovimientoItem(movimiento: Movimiento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D3436)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fecha: ${movimiento.fecha}",
                    color = Color(0xFFB0BEC5),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "NroMov: ${movimiento.nroMov}",
                    color = Color(0xFFB0BEC5),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Cuenta: ${movimiento.cuenta}",
                color = Color.White,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "Tipo: ${movimiento.tipo}",
                color = Color(0xFFB0BEC5)
            )
            Text(
                text = "Acción: ${movimiento.accion}",
                color = Color(0xFFB0BEC5)
            )
            Text(
                text = "Importe: ${String.format("%.2f", movimiento.importe)}",
                color = Color(0xFF81D4FA),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultaScreenPreview() {
    CONUNI_CLIMOV_GR10_RESTTheme {
        ConsultaScreen(onNavigate = {})
    }
}