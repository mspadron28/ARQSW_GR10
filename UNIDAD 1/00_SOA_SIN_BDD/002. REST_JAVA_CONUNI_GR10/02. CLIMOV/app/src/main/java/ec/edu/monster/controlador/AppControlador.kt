package ec.edu.monster.controlador

import ec.edu.monster.modelo.Movimiento
import ec.edu.monster.modelo.Usuario
import ec.edu.monster.servicio.EurekaService

class AppControlador {
    private val service = EurekaService()

    suspend fun login(usuario: String, clave: String): Usuario? {
        return service.login(usuario, clave)
    }

    suspend fun traerMovimientos(cuenta: String): List<Movimiento> {
        return service.traerMovimientos(cuenta)
    }

    suspend fun registrarDeposito(cuenta: String, importe: Double, codEmp: String) {
        service.registrarDeposito(cuenta, importe, codEmp)
    }

    suspend fun registrarRetiro(cuenta: String, importe: Double, codEmp: String) {
        service.registrarRetiro(cuenta, importe, codEmp)
    }

    suspend fun realizarTransferencia(cuentaOrigen: String, cuentaDestino: String, importe: Double, codEmp: String) {
        service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp)
    }

    suspend fun verificarSaldo(cuenta: String): Double {
        return service.verificarSaldo(cuenta)
    }

    suspend fun obtenerCostoMovimiento(cuenta: String): Double {
        return service.obtenerCostoMovimiento(cuenta)
    }
}