package ec.edu.monster.conuni

import android.os.Bundle
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.controlador.ViajecitosController
import ec.edu.monster.servicio.ViajecitosService
import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme
import java.text.SimpleDateFormat
import java.util.*

class SearchFlightsActivity : ComponentActivity() {

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
                                    "Buscar Vuelos",
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
                    // Pantalla de búsqueda de vuelos
                    SearchFlightsScreen(controller = controller, innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun SearchFlightsScreen(controller: ViajecitosController, innerPadding: PaddingValues) {
    var originCity by remember { mutableStateOf("") }
    var destinationCity by remember { mutableStateOf("") }
    var flightDate by remember { mutableStateOf<TextFieldValue>(TextFieldValue(SimpleDateFormat("dd/MM/yyyy").format(Date()))) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Estructura de la pantalla de búsqueda de vuelos
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
            text = "Buscar Vuelos",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Encuentra tus vuelos con facilidad y seguridad.",
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

        // Ciudad de origen
        OutlinedTextField(
            value = originCity,
            onValueChange = { originCity = it },
            label = { Text("Ciudad Origen") },
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

        // Ciudad de destino
        OutlinedTextField(
            value = destinationCity,
            onValueChange = { destinationCity = it },
            label = { Text("Ciudad Destino") },
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

        // Fecha de vuelo
        OutlinedTextField(
            value = flightDate,
            onValueChange = { flightDate = it },
            label = { Text("Fecha de Vuelo") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3B82F6),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF3B82F6),
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de buscar vuelos
        Button(
            onClick = {
                if (originCity.isEmpty() || destinationCity.isEmpty()) {
                    errorMessage = "Las ciudades de origen y destino son requeridas."
                    return@Button
                }
                if (flightDate.text.isEmpty()) {
                    errorMessage = "La fecha es requerida."
                    return@Button
                }

                controller.buscarVuelos(originCity.trim(), destinationCity.trim(), flightDate.text.trim()) { vuelos ->
                    if (vuelos != null && vuelos.isNotEmpty()) {
                        Toast.makeText(context, "Vuelos encontrados", Toast.LENGTH_SHORT).show()
                        // Lógica para mostrar los resultados de los vuelos
                    } else {
                        errorMessage = "No se encontraron vuelos."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Buscar Vuelos",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
