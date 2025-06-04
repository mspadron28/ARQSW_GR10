package ec.edu.monster.modelo

import java.io.Serializable

data class MetodoPago(
    val idMetodoPago: Int,
    val nombreMetodo: String,
    val descripcion: String
) : Serializable