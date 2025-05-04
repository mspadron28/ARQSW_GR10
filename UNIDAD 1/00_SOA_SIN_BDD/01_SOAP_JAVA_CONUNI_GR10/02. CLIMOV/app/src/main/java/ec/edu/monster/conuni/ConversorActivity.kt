package ec.edu.monster.conuni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class ConversorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConversorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversorScreen(modifier: Modifier = Modifier) {
    var pulgadas by remember { mutableStateOf("") }
    var centimetros by remember { mutableStateOf("") }
    var resultadoCentimetros by remember { mutableStateOf("") }
    var resultadoPulgadas by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.Main)
    val controlador = AppControlador()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Conversor de Unidades", fontSize = 24.sp)

        // Pulgadas a Centímetros
        Text(text = "Pulgadas a Centímetros", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = pulgadas,
                onValueChange = { pulgadas = it },
                label = { Text("Pulgadas") },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            Button(
                onClick = {
                    scope.launch {
                        try {
                            val p = pulgadas.toDoubleOrNull()
                            if (p != null && p >= 0) {
                                val result = withContext(Dispatchers.IO) {
                                    controlador.pulgadasACentimetros(p)
                                }
                                resultadoCentimetros = String.format("%.2f cm", result)
                            } else {
                                Toast.makeText(context, "Ingrese un valor no negativo", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Convertir")
            }
        }
        Text(text = resultadoCentimetros, fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))

        // Centímetros a Pulgadas
        Text(text = "Centímetros a Pulgadas", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = centimetros,
                onValueChange = { centimetros = it },
                label = { Text("Centímetros") },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            Button(
                onClick = {
                    scope.launch {
                        try {
                            val c = centimetros.toDoubleOrNull()
                            if (c != null && c >= 0) {
                                val result = withContext(Dispatchers.IO) {
                                    controlador.centimetrosAPulgadas(c)
                                }
                                resultadoPulgadas = String.format("%.2f pulgadas", result)
                            } else {
                                Toast.makeText(context, "Ingrese un valor no negativo", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Convertir")
            }
        }
        Text(text = resultadoPulgadas, fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ConversorScreenPreview() {
    CONUNI_CLIMOV_GR10Theme {
        ConversorScreen()
    }
}