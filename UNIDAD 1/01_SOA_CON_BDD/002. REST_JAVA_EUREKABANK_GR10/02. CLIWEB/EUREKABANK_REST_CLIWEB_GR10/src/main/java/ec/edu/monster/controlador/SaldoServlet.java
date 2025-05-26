package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet para verificar saldo.
 * @author MATIAS
 */
@WebServlet("/SaldoServlet")
public class SaldoServlet extends HttpServlet {
    private final EurekaService service = new EurekaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cuenta = request.getParameter("cuenta");

        if (cuenta == null || cuenta.trim().isEmpty()) {
            request.setAttribute("error", "Ingrese un número de cuenta.");
            request.getRequestDispatcher("saldo.jsp").forward(request, response);
            return;
        }

        try {
            double saldo = service.verificarSaldo(cuenta);
            request.setAttribute("saldo", String.format("%.2f", saldo));
            request.getRequestDispatcher("saldo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al verificar el saldo: " + e.getMessage());
            request.getRequestDispatcher("saldo.jsp").forward(request, response);
        }
    }
}