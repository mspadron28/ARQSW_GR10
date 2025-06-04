package ec.edu.monster.modelo

import java.io.Serializable
import java.math.BigDecimal

data class DetalleFactura(
    val idDetalleFactura: Int,
    val idFactura: Int,
    val idVuelo: Int,
    val cantidad: Int,
    val valorUnitario: BigDecimal,
    val total: BigDecimal,
    val vuelo: Vuelo? = null,
    val factura: Factura? = null
) : Serializable