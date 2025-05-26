package ec.edu.monster.modelo

data class TransferenciaRequest(
    val cuentaOrigen: String,
    val cuentaDestino: String,
    val importe: Double,
    val codEmp: String
)