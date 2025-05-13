package ec.edu.monster.modelo

data class LoginRequest(
    val usuario: String,
    val contraseña: String
)