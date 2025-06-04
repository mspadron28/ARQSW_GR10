package ec.edu.monster.modelo

import java.io.Serializable

data class Usuario(
    val idUsuario: Int,
    val idCliente: Int,
    val nombreUsuario: String,
    val claveUsuario: String,
    val estadoUsuario: String,
    val cliente: Cliente? = null
) : Serializable