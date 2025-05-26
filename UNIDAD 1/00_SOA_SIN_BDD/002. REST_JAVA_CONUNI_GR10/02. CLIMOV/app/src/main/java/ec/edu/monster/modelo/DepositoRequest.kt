package ec.edu.monster.modelo

data class DepositoRequest(
    val cuenta: String,
    val importe: Double,
    val codEmp: String
)