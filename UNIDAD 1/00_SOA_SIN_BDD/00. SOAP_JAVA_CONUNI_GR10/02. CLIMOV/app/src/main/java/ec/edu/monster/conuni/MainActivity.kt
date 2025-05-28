package ec.edu.monster.conuni

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import android.widget.Toast
import androidx.compose.ui.tooling.preview.Preview
import ec.edu.monster.modelo.Usuario

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Inicio de Sesión", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF46535D)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLoginSuccess = {
                            startActivity(Intent(this@MainActivity, MenuActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFB1C5C7), shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a EurekaBank",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.sulley),
            contentDescription = "Sulley Avatar",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(150.dp)
        )
        Text(
            text = "Por favor, ingresa tus credenciales",
            fontSize = 14.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
        )

        Button(
            onClick = {
                object : AsyncTask<Void, Void, Pair<Usuario?, Exception?>>() {
                    override fun doInBackground(vararg params: Void?): Pair<Usuario?, Exception?> {
                        return try {
                            val controlador = AppControlador()
                            val user = controlador.login(usuario, clave)
                            Pair(user, null)
                        } catch (e: Exception) {
                            Pair(null, e)
                        }
                    }

                    override fun onPostExecute(result: Pair<Usuario?, Exception?>) {
                        val (user, error) = result
                        if (user != null && user.estado == "ACTIVO") {
                            onLoginSuccess()
                        } else if (error != null) {
                            Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Usuario o contraseña incorrectos o estado no activo", Toast.LENGTH_SHORT).show()
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
            Text("Iniciar Sesión", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CONUNI_CLIMOV_GR10Theme {
        LoginScreen {}
    }
}