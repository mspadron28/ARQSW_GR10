package ec.edu.monster.conuni

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.controlador.ViajecitosController
import ec.edu.monster.servicio.ViajecitosService

import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme

class LoginActivity : ComponentActivity() {

    private lateinit var controller: ViajecitosController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos el controlador con el servicio
        val service = ViajecitosService()
        controller = ViajecitosController(service)

        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10_RESTTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Viajecitos SA",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF46535D) // color de fondo del encabezado
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Pantalla de login con validación
                    LoginScreen(controller = controller, innerPadding = innerPadding)
                }
            }
        }
    }
}
@Composable
fun LoginScreen(controller: ViajecitosController, innerPadding: PaddingValues) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current // Obtener el contexto aquí

    // Estructura de la pantalla de login
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0xFFF9FAFB)) // Fondo
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título y subtítulo
        Text(
            text = "Iniciar Sesión",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Encuentra y compra tus boletos de avión de forma fácil y segura.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Mostrar error si existe
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color(0xFFDC2626),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Cambia los TextFields a OutlinedTextFields y actualiza los colores
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3B82F6),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF3B82F6),
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3B82F6),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF3B82F6),
                unfocusedLabelColor = Color.Gray
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de iniciar sesión
        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    errorMessage = "Usuario y contraseña son requeridos."
                    Log.e("LoginActivity", "Usuario o contraseña vacíos")
                    return@Button
                }

                Log.d("LoginActivity", "Iniciando sesión con usuario: $username")

                controller.iniciarSesion(username.trim(), password.trim()) { usuario ->
                    if (usuario != null) {
                        // Log cuando la autenticación sea exitosa
                        Log.d("LoginActivity", "Inicio de sesión exitoso para el usuario: $username")
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                        // Navegar a MainActivity después de autenticarse
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        // Finish the login activity to prevent going back to it
                        (context as? LoginActivity)?.finish()
                    } else {
                        // Log cuando la autenticación falle
                        Log.e("LoginActivity", "Error: Usuario o contraseña incorrectos")
                        errorMessage = "Usuario o contraseña incorrectos."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
