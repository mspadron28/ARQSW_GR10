package ec.edu.monster.conuni

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Receipt
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
import ec.edu.monster.conuni.ui.theme.CONUNI_CLIMOV_GR10Theme

class MainMenuActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("viajecitos", Context.MODE_PRIVATE)
        val usuario = sharedPref.getString("usuario", null)

        if (usuario == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Menú Principal - Viajecitos SA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1), titleContentColor = Color.White)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MenuScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MenuScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

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
                Text("Menú Principal", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1), modifier = Modifier.padding(bottom = 16.dp))
                Button(onClick = { context.startActivity(Intent(context, SearchFlightsActivity::class.java)) }, modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                    Row(verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.Flight, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(modifier = Modifier.width(8.dp)); Text("Buscar Vuelos y Comprar", fontSize = 16.sp) }
                }
                Button(onClick = { context.startActivity(Intent(context, InvoicesActivity::class.java)) }, modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                    Row(verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.Receipt, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(modifier = Modifier.width(8.dp)); Text("Consultar Facturas", fontSize = 16.sp) }
                }
                Button(onClick = {
                    context.getSharedPreferences("viajecitos", Context.MODE_PRIVATE).edit().remove("usuario").apply()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                    (context as? ComponentActivity)?.finish()
                }, modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 8.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1), contentColor = Color.White)) {
                    Row(verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp)); Spacer(modifier = Modifier.width(8.dp)); Text("Cerrar Sesión", fontSize = 16.sp) }
                }
            }
        }
    }
}