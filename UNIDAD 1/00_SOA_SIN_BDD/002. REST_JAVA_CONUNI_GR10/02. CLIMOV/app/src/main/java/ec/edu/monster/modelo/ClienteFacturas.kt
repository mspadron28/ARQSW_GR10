package ec.edu.monster.modelo

data class ClienteFacturas(
    val clienteId: Int,
    val nombre: String,
    val email: String,
    val documentoIdentidad: String,
    val facturas: List<Factura>
)