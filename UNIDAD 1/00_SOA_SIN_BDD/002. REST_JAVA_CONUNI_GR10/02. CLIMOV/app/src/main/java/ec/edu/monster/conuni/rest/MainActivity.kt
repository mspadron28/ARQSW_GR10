package ec.edu.monster.conuni

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.controlador.ViajecitosController
import ec.edu.monster.servicio.ViajecitosService
import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme

class MainActivity : ComponentActivity() {

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
                ) { innerPadding -> // Aquí es donde se pasa innerPadding
                    // Pantalla principal con opciones
                    MainScreen(controller = controller, onNavigate = { route ->
                        navigateTo(route) // Navegar al destino correspondiente
                    }, innerPadding = innerPadding) // Pasamos innerPadding al componente MainScreen
                }
            }
        }
    }

    // Función para navegar entre pantallas
    private fun navigateTo(route: String) {
        when(route) {
            "login" -> startActivity(Intent(this, LoginActivity::class.java))
            "searchFlights" -> startActivity(Intent(this, SearchFlightsActivity::class.java))
            //"selectClient" -> startActivity(Intent(this, SelectClientActivity::class.java))
            //"allInvoices" -> startActivity(Intent(this, AllInvoicesActivity::class.java))
            else -> Toast.makeText(this, "Pantalla no disponible", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(controller: ViajecitosController, onNavigate: (String) -> Unit, innerPadding: PaddingValues) {
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Verificar si el usuario está autenticado
    val isAuthenticated = controller.usuarioAutenticado != null

    // Estructura de la pantalla principal
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Viajecitos SA",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA3BFFA)
                ),
                modifier = Modifier.height(150.dp).shadow(4.dp)
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)) // Color de fondo
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .shadow(2.dp, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a Viajecitos SA",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A), // Color de texto
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            if (isAuthenticated) {
                // Si el usuario está autenticado, mostramos el mensaje y las opciones
                Text(
                    text = "Hola, ${controller.usuarioAutenticado?.nombreUsuario}!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Botón para buscar vuelos
                    NavButton(
                        text = "Buscar Vuelos",
                        onClick = { onNavigate("searchFlights") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Botón para cerrar sesión
                    NavButton(
                        text = "Cerrar Sesión",
                        onClick = {
                            // Limpiar el usuario autenticado y refrescar la pantalla
                            controller.usuarioAutenticado = null
                            onNavigate("login")
                        },
                        backColor = Color(0xFFDC2626),
                        textColor = Color(0xFFDC2626),
                        primary = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                // Si no hay usuario autenticado, mostrar botón para iniciar sesión
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NavButton(
                        text = "Iniciar Sesión",
                        onClick = { onNavigate("login") },
                        primary = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun NavButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backColor: Color = Color.White,
    textColor: Color = Color(0xFF3B82F6),
    primary: Boolean = false
) {
    val buttonColor = if (primary) Color(0xFF3B82F6) else backColor
    val contentColor = if (primary) Color.White else textColor
    val borderColor = if (primary) Color(0xFF3B82F6) else textColor

    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
