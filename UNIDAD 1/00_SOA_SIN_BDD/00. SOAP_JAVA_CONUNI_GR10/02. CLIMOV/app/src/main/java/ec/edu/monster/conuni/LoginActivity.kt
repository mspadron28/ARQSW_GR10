package ec.edu.monster.conuni

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.modelo.Usuario
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class LoginActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Bienvenido a Viajecitos SA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1), titleContentColor = Color.White)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var nombreUsuario by remember { mutableStateOf("") }
    var claveUsuario by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F5))
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
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
                Text("Iniciar Sesión", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 16.dp))
                OutlinedTextField(
                    value = nombreUsuario,
                    onValueChange = { nombreUsuario = it },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161))
                )
                OutlinedTextField(
                    value = claveUsuario,
                    onValueChange = { claveUsuario = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF0288D1), unfocusedBorderColor = Color(0xFF616161))
                )
                errorMessage?.let {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE), contentColor = Color(0xFFD32F2F))
                    ) {
                        Text(it, fontSize = 14.sp, modifier = Modifier.padding(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (nombreUsuario.isEmpty() || claveUsuario.isEmpty()) {
                            errorMessage = "Por favor, complete todos los campos"
                            return@Button
                        }
                        if (nombreUsuario.length < 4 || claveUsuario.length < 6) {
                            errorMessage = "El usuario debe tener al menos 4 caracteres y la contraseña al menos 6"
                            return@Button
                        }
                        isLoading = true
                        errorMessage = null
                        object : AsyncTask<Void, Void, Pair<Usuario?, String?>>() {
                            override fun doInBackground(vararg params: Void?) = try {
                                Pair(AppControlador().login(nombreUsuario, claveUsuario), null)
                            } catch (e: Exception) {
                                Pair(null, e.message)
                            }

                            override fun onPostExecute(result: Pair<Usuario?, String?>) {
                                isLoading = false
                                val (usuario, error) = result
                                if (usuario != null) {
                                    context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
                                        .edit()
                                        .putInt("idCliente", usuario.idCliente)
                                        .putString("usuario", usuario.nombreUsuario)
                                        .apply()
                                    Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                                    context.startActivity(Intent(context, MainMenuActivity::class.java))
                                    (context as? ComponentActivity)?.finish()
                                } else {
                                    errorMessage = error ?: "Usuario o contraseña incorrectos"
                                }
                            }
                        }.execute()
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text("Iniciar Sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { (context as? ComponentActivity)?.finish() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Salir", color = Color(0xFF0288D1), fontSize = 14.sp)
                }
            }
        }
    }
}