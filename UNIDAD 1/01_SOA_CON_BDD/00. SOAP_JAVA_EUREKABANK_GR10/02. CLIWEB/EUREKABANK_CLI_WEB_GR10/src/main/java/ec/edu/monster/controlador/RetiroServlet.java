package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RetiroServlet")
public class RetiroServlet extends HttpServlet {
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
            request.getRequestDispatcher("retiro.jsp").forward(request, response);
            return;
        }

        try {
            double importe = Double.parseDouble(importeText.replace(",", "."));
            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor que 0.");
                request.getRequestDispatcher("retiro.jsp").forward(request, response);
                return;
            }

            double costo = service.obtenerCostoMovimiento(cuenta);
            if (costo == -1.0) {
                request.setAttribute("error", "Error al obtener el costo del movimiento.");
                request.getRequestDispatcher("retiro.jsp").forward(request, response);
                return;
            }

            if (service.registrarRetiro(cuenta, importe, codEmp) == 1) {
                request.setAttribute("mensaje", "Retiro registrado exitosamente. Costo: " + costo);
                request.getRequestDispatcher("retiro.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Error al registrar el retiro.");
                request.getRequestDispatcher("retiro.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Ingrese un importe válido (use punto como separador decimal).");
            request.getRequestDispatcher("retiro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar el retiro: " + e.getMessage());
            request.getRequestDispatcher("retiro.jsp").forward(request, response);
        }
    }
}