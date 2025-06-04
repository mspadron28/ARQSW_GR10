package ec.edu.monster.modelo

data class Usuario(
    val idUsuario: Int,
    val idCliente: Int,
    val nombreUsuario: String,
    val claveUsuario: String,
    val estadoUsuario: String,
    val cliente: Cliente
)