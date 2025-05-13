package ec.edu.monster.controlador

import ec.edu.monster.servicio.CONUNIService

class AppControlador {
    private val service = CONUNIService()

    suspend fun login(usuario: String, contraseña: String): Boolean {
        return service.login(usuario, contraseña)
    }

    suspend fun pulgadasACentimetros(pulgadas: Double): Double {
        return service.pulgadasACentimetros(pulgadas)
    }

    suspend fun centimetrosAPulgadas(centimetros: Double): Double {
        return service.centimetrosAPulgadas(centimetros)
    }

    suspend fun metrosAPies(metros: Double): Double {
        return service.metrosAPies(metros)
    }

    suspend fun piesAMetros(pies: Double): Double {
        return service.piesAMetros(pies)
    }

    suspend fun metrosAYardas(metros: Double): Double {
        return service.metrosAYardas(metros)
    }

    suspend fun yardasAMetros(yardas: Double): Double {
        return service.yardasAMetros(yardas)
    }
}