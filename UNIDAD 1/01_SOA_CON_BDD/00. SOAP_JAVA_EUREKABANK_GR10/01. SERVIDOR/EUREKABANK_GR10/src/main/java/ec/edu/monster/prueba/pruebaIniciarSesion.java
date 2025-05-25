package ec.edu.monster.prueba;

import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.EurekaService;

/**
 * Clase para probar el inicio de sesión en la base de datos.
 * @author MATIAS
 */
public class pruebaIniciarSesion {
    
    public static void main(String[] args) {
        try {
            // DATOS
            String usuario = "MONSTER";
            String clave = "MONSTER9";
            
            // PROCESO
            EurekaService service = new EurekaService();
            Usuario user = service.iniciarSesion(usuario, clave);
            
            // REPORTE
            System.out.println("Inicio de sesión exitoso.");
            System.out.println("Código Empleado: " + user.getCodigo());
            System.out.println("Usuario: " + user.getUsuario());
            System.out.println("Estado: " + user.getEstado());
            
        } catch (Exception e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
}