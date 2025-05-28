package ec.edu.monster.conuni.rest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme
import ec.edu.monster.controlador.AppControlador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class RetiroActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10_RESTTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Registrar Retiro", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF1E272E)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    RetiroScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigate = { destination ->
                            startActivity(Intent(this@RetiroActivity, destination))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetiroScreen(modifier: Modifier = Modifier, onNavigate: (Class<*>) -> Unit) {
    var cuenta by remember { mutableStateOf("") }
    var importe by remember { mutableStateOf("") }
    var codEmp by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var costo by remember { mutableStateOf(0.0) }
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
            text = "Registrar Retiro",
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

        TextField(
            value = importe,
            onValueChange = { importe = it },
            label = { Text("Importe", color = Color(0xFFB0BEC5)) },
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

        TextField(
            value = codEmp,
            onValueChange = { codEmp = it },
            label = { Text("Código Empleado", color = Color(0xFFB0BEC5)) },
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
                        val importeValue = importe.toDoubleOrNull() ?: throw Exception("Importe inválido")
                        if (importeValue <= 0) throw Exception("Importe debe ser mayor que 0")
                        costo = withContext(Dispatchers.IO) {
                            controlador.obtenerCostoMovimiento(cuenta)
                        }
                        if (costo == -1.0) throw Exception("Error al obtener costo")
                        withContext(Dispatchers.IO) {
                            controlador.registrarRetiro(cuenta, importeValue, codEmp)
                        }
                        mensaje = "Retiro registrado exitosamente. Costo: ${String.format("%.2f", costo)}"
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
            Text("Retirar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun RetiroScreenPreview() {
    CONUNI_CLIMOV_GR10_RESTTheme {
        RetiroScreen(onNavigate = {})
    }
}