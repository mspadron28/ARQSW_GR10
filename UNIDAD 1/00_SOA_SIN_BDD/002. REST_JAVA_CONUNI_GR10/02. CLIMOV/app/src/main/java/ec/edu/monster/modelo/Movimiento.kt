package ec.edu.monster.modelo

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Movimiento(
    val cuenta: String,
    @SerializedName("nroMov") val nroMov: Int,
    val fecha: Date,
    val tipo: String,
    val accion: String,
    val importe: Double
)