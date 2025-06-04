package ec.edu.monster.modelo

import java.io.Serializable
import java.math.BigDecimal

data class Vuelo(
    val idVuelo: Int,
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val valor: BigDecimal,
    val horaSalida: String
) : Serializable