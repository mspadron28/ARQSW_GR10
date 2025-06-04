package ec.edu.monster.modelo

import java.io.Serializable
import java.math.BigDecimal

data class Factura(
    val idFactura: Int,
    val numeroFactura: String,
    val fechaEmision: String,
    val idEmpleado: Int,
    val idCliente: Int,
    val idMetodoPago: Int,
    val subtotal: BigDecimal,
    val descuento: BigDecimal,
    val iva: BigDecimal,
    val total: BigDecimal,
    val empleado: Empleado? = null,
    val cliente: Cliente? = null,
    val metodoPago: MetodoPago? = null,
    val detallesFactura: List<DetalleFactura>? = emptyList()
) : Serializable