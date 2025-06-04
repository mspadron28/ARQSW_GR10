package ec.edu.monster.modelo

import java.util.*

data class Factura(
    val idFactura: Int,
    val numeroFactura: String,
    val fechaEmision: Date,
    val idEmpleado: Int,
    val idCliente: Int,
    val idMetodoPago: Int,
    val subtotal: Double,
    val descuento: Double,
    val iva: Double,
    val total: Double,
    val empleado: Empleado,
    val cliente: Cliente,
    val metodoPago: MetodoPago,
    val detallesFactura: List<DetalleFactura>
)
