package ec.edu.monster.modelo

import java.io.Serializable

data class ClienteFacturas(
    var clienteId: Int = 0,
    var nombre: String = "",
    var email: String = "",
    var documentoIdentidad: String = "",
    var facturas: List<Factura> = emptyList()
) : Serializable