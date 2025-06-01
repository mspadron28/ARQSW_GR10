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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.modelo.Vuelo
import android.widget.Toast
import ec.edu.monster.modelo.VueloSerializable
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vuelo = intent.getParcelableExtra<VueloSerializable>("vuelo")
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Detalles del Vuelo",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    startActivity(Intent(this@ResultActivity, MainActivity::class.java))
                                }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Volver",
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0288D1),
                                titleContentColor = Color.White
                            )
                        )
                    },
                    bottomBar = {
                        if (vuelo != null) {
                            val context = LocalContext.current
                            val sharedPref = context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
                            val idCliente = sharedPref.getInt("idCliente", 0)
                            val usuario = sharedPref.getString("usuario", null)
                            var isLoading by remember { mutableStateOf(false) }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF5F6F5))
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                if (usuario != null && idCliente > 0) {
                                    Button(
                                        onClick = {
                                            isLoading = true
                                            object : AsyncTask<Void, Void, Pair<Int?, Exception?>>() {
                                                override fun doInBackground(vararg params: Void?): Pair<Int?, Exception?> {
                                                    return try {
                                                        val controlador = AppControlador()
                                                        val idCompra = controlador.registrarCompra(vuelo.idVuelo, idCliente)
                                                        Pair(idCompra, null)
                                                    } catch (e: Exception) {
                                                        Pair(null, e)
                                                    }
                                                }

                                                override fun onPostExecute(result: Pair<Int?, Exception?>) {
                                                    isLoading = false
                                                    val (idCompra, error) = result
                                                    if (idCompra != null) {
                                                        Toast.makeText(context, "Compra registrada con ID: $idCompra", Toast.LENGTH_SHORT).show()
                                                        startActivity(Intent(context, MainActivity::class.java))
                                                    } else {
                                                        Toast.makeText(context, "Error: ${error?.message ?: "Error al registrar compra"}", Toast.LENGTH_LONG).show()
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
                                            Text(
                                                text = "Confirmar Compra",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            context.startActivity(Intent(context, LoginActivity::class.java))
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(56.dp)
                                            .border(1.dp, Color(0xFF0288D1), RoundedCornerShape(12.dp)),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color(0xFF0288D1)
                                        )
                                    ) {
                                        Text(
                                            text = "Iniciar Sesión para Comprar",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ResultScreen(
                        modifier = Modifier.padding(innerPadding),
                        vuelo = vuelo?.let {
                            Vuelo(it.idVuelo, it.ciudadOrigen, it.ciudadDestino, it.valor, it.horaSalida, Date(it.fecha))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    vuelo: Vuelo?
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F5))
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = vuelo != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if (vuelo != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
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
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "${vuelo.ciudadOrigen} → ${vuelo.ciudadDestino}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0288D1),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        FlightDetailRow(
                            icon = Icons.Default.LocationOn,
                            label = "Origen",
                            value = vuelo.ciudadOrigen
                        )
                        FlightDetailRow(
                            icon = Icons.Default.LocationOn,
                            label = "Destino",
                            value = vuelo.ciudadDestino
                        )
                        FlightDetailRow(
                            icon = Icons.Default.Schedule,
                            label = "Hora de Salida",
                            value = vuelo.horaSalida
                        )
                        FlightDetailRow(
                            icon = Icons.Default.AirplaneTicket,
                            label = "Fecha",
                            value = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(vuelo.fecha)
                        )
                        FlightDetailRow(
                            icon = Icons.Default.MonetizationOn,
                            label = "Precio",
                            value = "$${String.format(Locale.US, "%.2f", vuelo.valor)}"
                        )
                    }
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .shadow(8.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3F3),
                        contentColor = Color(0xFFD32F2F)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AirplaneTicket,
                            contentDescription = null,
                            tint = Color(0xFFD32F2F)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Vuelo no disponible",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun FlightDetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF0288D1),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF616161),
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF212121)
        )
    }
}