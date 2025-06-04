package ec.edu.monster.modelo

import java.util.*

data class Vuelo(
    val idVuelo: Int,
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val valor: Double,
    val horaSalida: String,
    val fecha: Date
)