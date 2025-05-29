package ec.edu.monster.controlador;

import ec.edu.monster.ws.Usuario;
import ec.edu.monster.servicio.ViajecitosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling user login requests.
 * @author MATIAS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final ViajecitosService viajecitosService = new ViajecitosService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombreUsuario = request.getParameter("nombreUsuario");
            String claveUsuario = request.getParameter("claveUsuario");
            Usuario usuario = viajecitosService.iniciarSesion(nombreUsuario, claveUsuario);
            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Credenciales inválidas");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}