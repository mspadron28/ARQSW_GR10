package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet para registrar depósitos.
 * @author MATIAS
 */
@WebServlet("/DepositoServlet")
public class DepositoServlet extends HttpServlet {
    private final EurekaService service = new EurekaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cuenta = request.getParameter("cuenta");
        String importeText = request.getParameter("importe");
        String codEmp = request.getParameter("codEmp");

        if (cuenta == null || importeText == null || codEmp == null ||
            cuenta.trim().isEmpty() || importeText.trim().isEmpty() || codEmp.trim().isEmpty()) {
            request.setAttribute("error", "Complete todos los campos.");
            request.getRequestDispatcher("deposito.jsp").forward(request, response);
            return;
        }

        try {
            double importe = Double.parseDouble(importeText.replace(",", "."));
            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor que 0.");
                request.getRequestDispatcher("deposito.jsp").forward(request, response);
                return;
            }

            service.registrarDeposito(cuenta, importe, codEmp);
            request.setAttribute("mensaje", "Depósito registrado exitosamente.");
            request.getRequestDispatcher("deposito.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Ingrese un importe válido (use punto como separador decimal).");
            request.getRequestDispatcher("deposito.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar el depósito: " + e.getMessage());
            request.getRequestDispatcher("deposito.jsp").forward(request, response);
        }
    }
}