package ec.edu.monster.controlador;

import ec.edu.monster.ws.Cliente;
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
 * Servlet for handling user registration requests.
 * @author MATIAS
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final ViajecitosService viajecitosService = new ViajecitosService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String documentoIdentidad = request.getParameter("documentoIdentidad");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String claveUsuario = request.getParameter("claveUsuario");

            Cliente cliente = viajecitosService.registrarCliente(nombre, email, documentoIdentidad);
            Usuario usuario = viajecitosService.registrarUsuario(cliente.getIdCliente(), nombreUsuario, claveUsuario);

            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrarse: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}