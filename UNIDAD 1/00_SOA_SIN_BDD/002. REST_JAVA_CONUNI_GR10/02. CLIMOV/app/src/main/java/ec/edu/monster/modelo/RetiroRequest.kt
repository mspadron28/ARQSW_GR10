package ec.edu.monster.modelo

data class RetiroRequest(
    val cuenta: String,
    val importe: Double,
    val codEmp: String
)