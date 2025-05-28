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

class SaldoActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Verificar Saldo", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF46535D)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SaldoScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigate = { destination ->
                            startActivity(Intent(this@SaldoActivity, destination))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaldoScreen(modifier: Modifier = Modifier, onNavigate: (Class<*>) -> Unit) {
    var cuenta by remember { mutableStateOf("") }
    var saldo by remember { mutableStateOf<String?>(null) }
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
            text = "Verificar Saldo",
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

        Button(
            onClick = {
                object : AsyncTask<Void, Void, Pair<Double?, Exception?>>() {
                    override fun doInBackground(vararg params: Void?): Pair<Double?, Exception?> {
                        return try {
                            val controlador = AppControlador()
                            val saldoValue = controlador.verificarSaldo(cuenta)
                            if (saldoValue == -1.0) throw Exception("Error al verificar saldo")
                            Pair(saldoValue, null)
                        } catch (e: Exception) {
                            Pair(null, e)
                        }
                    }

                    override fun onPostExecute(result: Pair<Double?, Exception?>) {
                        if (result.first != null) {
                            saldo = String.format("%.2f", result.first)
                            errorMessage = null
                        } else {
                            errorMessage = "Error: ${result.second?.message}"
                            saldo = null
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
            Text("Verificar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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

        saldo?.let {
            Text(
                text = "Saldo: $it",
                color = Color(0xFF66BB6A),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SaldoScreenPreview() {
    CONUNI_CLIMOV_GR10Theme {
        SaldoScreen(onNavigate = {})
    }
}