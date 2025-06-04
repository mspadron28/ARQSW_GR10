package ec.edu.monster.modelo

import java.io.Serializable
import java.util.Date

data class Compra(
    val idCompra: Int,
    val idVuelo: Int,
    val idCliente: Int,
    val vuelo: Vuelo? = null,
    val cliente: Cliente? = null,
    val fechaCompra: Date
) : Serializable