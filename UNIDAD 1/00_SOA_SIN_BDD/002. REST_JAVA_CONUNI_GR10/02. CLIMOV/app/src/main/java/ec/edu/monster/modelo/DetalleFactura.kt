package ec.edu.monster.modelo

data class DetalleFactura(
    val idDetalleFactura: Int,
    val idFactura: Int,
    val idVuelo: Int,
    val cantidad: Int,
    val valorUnitario: Double,
    val total: Double,
    val vuelo: Vuelo
)