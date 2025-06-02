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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme
import ec.edu.monster.controlador.AppControlador
import ec.edu.monster.modelo.Compra
import android.widget.Toast
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Schedule
import java.text.SimpleDateFormat
import java.util.*

class PurchasesActivity : ComponentActivity() {
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
                                    "Mis Compras",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    startActivity(Intent(this@PurchasesActivity, MainActivity::class.java))
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
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PurchasesScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBack = {
                            startActivity(Intent(this@PurchasesActivity, MainActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchasesScreen(modifier: Modifier = Modifier, onBack: () -> Unit) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
    val idCliente = sharedPref.getInt("idCliente", 0)
    var compras by remember { mutableStateOf<List<Compra>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (idCliente == 0) {
            error = "Por favor, inicia sesión para ver tus compras."
            isLoading = false
            return@LaunchedEffect
        }
        object : AsyncTask<Void, Void, Pair<List<Compra>?, Exception?>>() {
            override fun doInBackground(vararg params: Void?): Pair<List<Compra>?, Exception?> {
                return try {
                    val controlador = AppControlador()
                    val result = controlador.obtenerComprasCliente(idCliente)
                    Pair(result, null)
                } catch (e: Exception) {
                    Pair(null, e)
                }
            }

            override fun onPostExecute(result: Pair<List<Compra>?, Exception?>) {
                compras = result.first
                error = result.second?.message
                isLoading = false
            }
        }.execute()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F5))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp)
                .border(1.dp, Color(0xFF0288D1), RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF0288D1)
            )
        ) {
            Text(
                text = "Volver al Inicio",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp),
                color = Color(0xFF0288D1)
            )
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            when {
                error != null -> {
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
                                contentDescription = "Error",
                                tint = Color(0xFFD32F2F)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = error!!,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(vertical = 8.dp)
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
                }
                compras != null && compras!!.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(compras!!) { compra ->
                            PurchaseCard(compra = compra)
                        }
                    }
                }
                else -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .shadow(8.dp, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF212121)
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
                                contentDescription = "Sin compras",
                                tint = Color(0xFF0288D1)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "No tienes compras registradas.",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PurchaseCard(compra: Compra) {
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
                .padding(16.dp)
        ) {
            Text(
                text = "${compra.vuelo.ciudadOrigen} → ${compra.vuelo.ciudadDestino}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0288D1),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            PurchaseDetailRow(
                icon = Icons.Default.AirplaneTicket,
                label = "ID Compra",
                value = compra.idCompra.toString()
            )
            PurchaseDetailRow(
                icon = Icons.Default.MonetizationOn,
                label = "Valor",
                value = "$${String.format(Locale.US, "%.2f", compra.vuelo.valor)}"
            )
            PurchaseDetailRow(
                icon = Icons.Default.Schedule,
                label = "Fecha Compra",
                value = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(compra.fechaCompra)
            )
            PurchaseDetailRow(
                icon = Icons.Default.Schedule,
                label = "Hora Salida",
                value = compra.vuelo.horaSalida
            )
        }
    }
}

@Composable
fun PurchaseDetailRow(icon: ImageVector, label: String, value: String) {
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
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF212121)
        )
    }
}