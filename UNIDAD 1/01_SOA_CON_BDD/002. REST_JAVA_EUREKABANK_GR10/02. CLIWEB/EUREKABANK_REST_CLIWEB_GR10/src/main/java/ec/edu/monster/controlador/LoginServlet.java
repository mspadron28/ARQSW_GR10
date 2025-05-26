package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet para manejar el inicio de sesión.
 * @author MATIAS
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final EurekaService service = new EurekaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if (usuario == null || clave == null || usuario.trim().isEmpty() || clave.trim().isEmpty()) {
            request.setAttribute("error", "Complete todos los campos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        try {
            if (service.iniciarSesion(usuario, clave) != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("main.jsp");
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}