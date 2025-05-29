package ec.edu.monster.controlador;

import ec.edu.monster.servicio.ViajecitosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling ticket purchase requests.
 * @author MATIAS
 */
@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {

    private final ViajecitosService viajecitosService = new ViajecitosService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVuelo = Integer.parseInt(request.getParameter("idVuelo"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            viajecitosService.registrarCompra(idVuelo, idCliente);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar la compra: " + e.getMessage());
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }
}