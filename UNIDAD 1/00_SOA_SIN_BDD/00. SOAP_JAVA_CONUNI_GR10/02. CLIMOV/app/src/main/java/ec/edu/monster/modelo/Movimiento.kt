package ec.edu.monster.modelo

import java.util.Date

data class Movimiento(
    val cuenta: String,
    val nroMov: Int,
    val fecha: Date,
    val tipo: String,
    val accion: String,
    val importe: Double
)