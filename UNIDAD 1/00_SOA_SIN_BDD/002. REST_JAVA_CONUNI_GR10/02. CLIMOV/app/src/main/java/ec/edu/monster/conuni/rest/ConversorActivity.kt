package ec.edu.monster.conuni.rest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.edu.monster.conuni.rest.ui.theme.CONUNI_CLIMOV_GR10_RESTTheme
import ec.edu.monster.controlador.AppControlador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class ConversorActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CONUNI_CLIMOV_GR10_RESTTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Inicio de Sesión", color = Color.White, fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF46535D)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { innerPadding ->
                    ConversorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversorScreen(modifier: Modifier = Modifier) {
    var valor by remember { mutableStateOf("") }
    var tipoConversion by remember { mutableStateOf("pulgadasACentimetros") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.Main)
    val controlador = AppControlador()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFB1C5C7), shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sulleyconuni),
            contentDescription = "Sulley",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "¿Qué unidad requieres convertir?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Selector de conversión
        var isExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            TextField(
                value = when (tipoConversion) {
                    "pulgadasACentimetros" -> "Pulgadas a Centímetros"
                    "centimetrosAPulgadas" -> "Centímetros a Pulgadas"
                    "metrosAPies" -> "Metros a Pies"
                    "piesAMetros" -> "Pies a Metros"
                    "metrosAYardas" -> "Metros a Yardas"
                    "yardasAMetros" -> "Yardas a Metros"
                    else -> "Seleccione una conversión"
                },
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de conversión") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(bottom = 8.dp)
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Pulgadas a Centímetros") },
                    onClick = {
                        tipoConversion = "pulgadasACentimetros"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Centímetros a Pulgadas") },
                    onClick = {
                        tipoConversion = "centimetrosAPulgadas"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Metros a Pies") },
                    onClick = {
                        tipoConversion = "metrosAPies"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Pies a Metros") },
                    onClick = {
                        tipoConversion = "piesAMetros"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Metros a Yardas") },
                    onClick = {
                        tipoConversion = "metrosAYardas"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Yardas a Metros") },
                    onClick = {
                        tipoConversion = "yardasAMetros"
                        isExpanded = false
                    }
                )
            }
        }

        // Campo de entrada
        TextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Ingresa el valor") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Botón de conversión
        Button(
            onClick = {
                scope.launch {
                    try {
                        val value = valor.toDoubleOrNull()
                        if (value != null && value >= 0) {
                            val result = withContext(Dispatchers.IO) {
                                when (tipoConversion) {
                                    "pulgadasACentimetros" -> controlador.pulgadasACentimetros(value)
                                    "centimetrosAPulgadas" -> controlador.centimetrosAPulgadas(value)
                                    "metrosAPies" -> controlador.metrosAPies(value)
                                    "piesAMetros" -> controlador.piesAMetros(value)
                                    "metrosAYardas" -> controlador.metrosAYardas(value)
                                    "yardasAMetros" -> controlador.yardasAMetros(value)
                                    else -> throw Exception("Tipo de conversión no válido")
                                }
                            }
                            resultado = String.format("%.2f %s", result, when (tipoConversion) {
                                "pulgadasACentimetros" -> "cm"
                                "centimetrosAPulgadas" -> "pulgadas"
                                "metrosAPies" -> "pies"
                                "piesAMetros" -> "metros"
                                "metrosAYardas" -> "yardas"
                                "yardasAMetros" -> "metros"
                                else -> ""
                            })
                        } else {
                            Toast.makeText(context, "Ingrese un valor no negativo", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(4.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFF202224))
        ) {
            Text("Convertir", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        // Resultado (siempre visible)
        Text(
            text = resultado,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConversorScreenPreview() {
    CONUNI_CLIMOV_GR10_RESTTheme {
        ConversorScreen()
    }
}