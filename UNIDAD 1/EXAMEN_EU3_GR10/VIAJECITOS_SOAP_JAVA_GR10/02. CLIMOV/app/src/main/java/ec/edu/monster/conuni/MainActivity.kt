package ec.edu.monster.conuni

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.modelo.Vuelo
import android.widget.Toast
import ec.edu.monster.modelo.VueloSerializable
import java.text.SimpleDateFormat
import java.util.*

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
                            title = {
                                Text(
                                    "Viajecitos SA",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0288D1),
                                titleContentColor = Color.White
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    FlightSearchScreen(
                        modifier = Modifier.padding(innerPadding),
                        onFlightFound = { vuelo ->
                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            val vueloSerializable = VueloSerializable(
                                idVuelo = vuelo.idVuelo,
                                ciudadOrigen = vuelo.ciudadOrigen,
                                ciudadDestino = vuelo.ciudadDestino,
                                valor = vuelo.valor,
                                horaSalida = vuelo.horaSalida,
                                fecha = vuelo.fecha.time
                            )
                            intent.putExtra("vuelo", vueloSerializable)
                            startActivity(intent)
                        },
                        onViewPurchases = {
                            startActivity(Intent(this@MainActivity, PurchasesActivity::class.java))
                        },
                        onLogin = {
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        },
                        onRegister = {}
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    modifier: Modifier = Modifier,
    onFlightFound: (Vuelo) -> Unit,
    onViewPurchases: () -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    val context = LocalContext.current
    var ciudadOrigen by remember { mutableStateOf("") }
    var ciudadDestino by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var expandedOrigen by remember { mutableStateOf(false) }
    var expandedDestino by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val ciudades = listOf(
        "Bogotá" to "BOG",
        "Medellín" to "MDE",
        "Buenos Aires" to "BUE",
        "Córdoba" to "COR",
        "Quito" to "UIO",
        "Guayaquil" to "GYE",
        "Cali" to "CLO",
        "Cartagena" to "CTG",
        "Mendoza" to "MDZ"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F5))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color(0xFF212121)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Buscar Vuelos",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0288D1),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "Encuentra y compra tus boletos de avión",
                    fontSize = 16.sp,
                    color = Color(0xFF616161),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = expandedOrigen,
                    onExpandedChange = { expandedOrigen = !expandedOrigen },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = ciudadOrigen,
                        onValueChange = { ciudadOrigen = it },
                        label = { Text("Ciudad Origen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF0288D1),
                            unfocusedBorderColor = Color(0xFF616161)
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedOrigen,
                        onDismissRequest = { expandedOrigen = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        ciudades.forEach { (nombre, codigo) ->
                            DropdownMenuItem(
                                text = { Text("$codigo - $nombre", fontSize = 16.sp) },
                                onClick = {
                                    ciudadOrigen = nombre
                                    expandedOrigen = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandedDestino,
                    onExpandedChange = { expandedDestino = !expandedDestino },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = ciudadDestino,
                        onValueChange = { ciudadDestino = it },
                        label = { Text("Ciudad Destino") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF0288D1),
                            unfocusedBorderColor = Color(0xFF616161)
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedDestino,
                        onDismissRequest = { expandedDestino = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        ciudades.forEach { (nombre, codigo) ->
                            DropdownMenuItem(
                                text = { Text("$codigo - $nombre", fontSize = 16.sp) },
                                onClick = {
                                    ciudadDestino = nombre
                                    expandedDestino = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha de Viaje (YYYY-MM-DD)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF0288D1),
                        unfocusedBorderColor = Color(0xFF616161)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (ciudadOrigen.isEmpty() || ciudadDestino.isEmpty() || fecha.isEmpty()) {
                            Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        isLoading = true
                        object : AsyncTask<Void, Void, Pair<Vuelo?, Exception?>>() {
                            override fun doInBackground(vararg params: Void?): Pair<Vuelo?, Exception?> {
                                return try {
                                    val controlador = AppControlador()
                                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                                    val parsedDate = dateFormat.parse(fecha)
                                    val vuelo = controlador.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, parsedDate)
                                    Pair(vuelo, null)
                                } catch (e: Exception) {
                                    Pair(null, e)
                                }
                            }

                            override fun onPostExecute(result: Pair<Vuelo?, Exception?>) {
                                isLoading = false
                                val (vuelo, error) = result
                                if (vuelo != null) {
                                    onFlightFound(vuelo)
                                } else {
                                    Toast.makeText(context, "Error: ${error?.message ?: "Vuelo no disponible"}", Toast.LENGTH_LONG).show()
                                }
                            }
                        }.execute()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0288D1),
                        contentColor = Color.White
                    ),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Buscar Vuelo",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val usuario = context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE).getString("usuario", null)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color(0xFF212121)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (usuario != null) {
                    Text(
                        text = "Bienvenido, $usuario",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0288D1),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Button(
                        onClick = onViewPurchases,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0288D1),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Ver Mis Compras",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text(
                        text = "Accede a tu cuenta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0288D1),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = onLogin,
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .padding(end = 8.dp)
                                .border(1.dp, Color(0xFF0288D1), RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color(0xFF0288D1)
                            )
                        ) {
                            Text(
                                text = "Iniciar Sesión",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Button(
                            onClick = onRegister,
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .padding(start = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0288D1),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Registrarse",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}