package ec.edu.monster.modelo

import java.io.Serializable

data class Empleado(
    val idEmpleado: Int,
    val nombre: String,
    val email: String
) : Serializable