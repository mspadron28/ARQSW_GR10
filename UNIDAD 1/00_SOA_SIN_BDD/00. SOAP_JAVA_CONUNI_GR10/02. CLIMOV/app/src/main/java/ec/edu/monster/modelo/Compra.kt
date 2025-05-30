package ec.edu.monster.modelo
import java.util.Date
data class Compra(
    val idCompra: Int,
    val vuelo: Vuelo,
    val fechaCompra: Date
)