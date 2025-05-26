package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import ec.edu.monster.modelo.Movimiento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para consultar movimientos.
 * @author MATIAS
 */
@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
    private final EurekaService service = new EurekaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cuenta = request.getParameter("cuenta");

        if (cuenta == null || cuenta.trim().isEmpty()) {
            request.setAttribute("error", "Ingrese un número de cuenta.");
            request.getRequestDispatcher("consulta.jsp").forward(request, response);
            return;
        }

        try {
            List<Movimiento> movimientos = service.traerMovimientos(cuenta);
            request.setAttribute("movimientos", movimientos);
            if (movimientos.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron movimientos para la cuenta.");
            }
            request.getRequestDispatcher("consulta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar movimientos: " + e.getMessage());
            request.getRequestDispatcher("consulta.jsp").forward(request, response);
        }
    }
}