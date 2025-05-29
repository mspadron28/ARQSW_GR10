package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Compra;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.ViajecitosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for displaying a client's purchases.
 * @author MATIAS
 */
@WebServlet(name = "ComprasClienteServlet", urlPatterns = {"/ComprasClienteServlet"})
public class ComprasClienteServlet extends HttpServlet {

    private final ViajecitosService viajecitosService = new ViajecitosService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            request.setAttribute("error", "Debes iniciar sesión para ver tus compras.");
            request.getRequestDispatcher("compras.jsp").forward(request, response);
            return;
        }

        try {
            int idCliente = usuario.getIdCliente();
            List<Compra> compras = viajecitosService.obtenerComprasCliente(idCliente);
            request.setAttribute("compras", compras);
            request.getRequestDispatcher("compras.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener las compras: " + e.getMessage());
            request.getRequestDispatcher("compras.jsp").forward(request, response);
        }
    }
}