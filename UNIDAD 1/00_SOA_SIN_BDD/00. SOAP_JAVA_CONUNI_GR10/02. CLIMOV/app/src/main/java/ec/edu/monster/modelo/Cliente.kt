package ec.edu.monster.modelo

import java.io.Serializable

data class Cliente(
    val idCliente: Int,
    val nombre: String,
    val email: String,
    val documentoIdentidad: String
) : Serializable