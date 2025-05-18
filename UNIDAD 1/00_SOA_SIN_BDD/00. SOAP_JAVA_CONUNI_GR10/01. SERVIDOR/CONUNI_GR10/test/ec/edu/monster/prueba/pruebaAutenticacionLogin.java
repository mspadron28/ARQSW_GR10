package ec.edu.monster.prueba;

import ec.edu.monster.servicio.LoginService;
import org.junit.Test;
import static org.junit.Assert.*;

public class pruebaAutenticacionLogin {

    private final LoginService loginService = new LoginService();

    @Test
    public void testLoginExitoso() {
        boolean resultado = loginService.autenticar("MONSTER", "MONSTER9");
        assertTrue("El login debería ser exitoso con credenciales correctas", resultado);
    }

    @Test
    public void testLoginUsuarioIncorrecto() {
        boolean resultado = loginService.autenticar("USER", "MONSTER9");
        assertFalse("El login debería fallar con usuario incorrecto", resultado);
    }

    @Test
    public void testLoginContrasenaIncorrecta() {
        boolean resultado = loginService.autenticar("MONSTER", "WRONG");
        assertFalse("El login debería fallar con contraseña incorrecta", resultado);
    }

    @Test
    public void testLoginCredencialesVacias() {
        boolean resultado = loginService.autenticar("", "");
        assertFalse("El login debería fallar con credenciales vacías", resultado);
    }
}