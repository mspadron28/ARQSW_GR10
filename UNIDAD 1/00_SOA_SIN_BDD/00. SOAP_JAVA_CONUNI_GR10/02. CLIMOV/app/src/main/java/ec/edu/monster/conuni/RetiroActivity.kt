package ec.edu.monster.conuni

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import android.widget.Toast
import androidx.compose.ui.tooling.preview.Preview

class RetiroActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Registrar Retiro", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF46535D)
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
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFB1C5C7), shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrar Retiro",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = cuenta,
            onValueChange = { cuenta = it },
            label = { Text("Cuenta") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        TextField(
            value = importe,
            onValueChange = { importe = it },
            label = { Text("Importe") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        TextField(
            value = codEmp,
            onValueChange = { codEmp = it },
            label = { Text("Código Empleado") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        Button(
            onClick = {
                object : AsyncTask<Void, Void, Pair<Double?, Exception?>>() {
                    override fun doInBackground(vararg params: Void?): Pair<Double?, Exception?> {
                        return try {
                            val controlador = AppControlador()
                            val importeValue = importe.toDoubleOrNull() ?: throw Exception("Importe inválido")
                            if (importeValue <= 0) throw Exception("Importe debe ser mayor que 0")
                            val costo = controlador.obtenerCostoMovimiento(cuenta)
                            if (costo == -1.0) throw Exception("Error al obtener costo")
                            controlador.registrarRetiro(cuenta, importeValue, codEmp)
                            Pair(costo, null)
                        } catch (e: Exception) {
                            Pair(null, e)
                        }
                    }

                    override fun onPostExecute(result: Pair<Double?, Exception?>) {
                        if (result.first != null) {
                            mensaje = "Retiro registrado exitosamente. Costo: ${String.format("%.2f", result.first)}"
                            errorMessage = null
                        } else {
                            errorMessage = "Error: ${result.second?.message}"
                            mensaje = null
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
                }.execute()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF202224))
        ) {
            Text("Retirar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Button(
            onClick = { onNavigate(MenuActivity::class.java) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF202224))
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
    CONUNI_CLIMOV_GR10Theme {
        RetiroScreen(onNavigate = {})
    }
}