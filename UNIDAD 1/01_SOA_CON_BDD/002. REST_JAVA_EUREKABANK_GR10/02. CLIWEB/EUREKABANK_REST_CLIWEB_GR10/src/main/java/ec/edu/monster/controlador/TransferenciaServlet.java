package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Servlet para realizar transferencias.
 * @author MATIAS
 */
@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
    private final EurekaService service = new EurekaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cuentaOrigen = request.getParameter("cuentaOrigen");
        String cuentaDestino = request.getParameter("cuentaDestino");
        String importeText = request.getParameter("importe");
        String codEmp = request.getParameter("codEmp");

        if (cuentaOrigen == null || cuentaDestino == null || importeText == null || codEmp == null ||
            cuentaOrigen.trim().isEmpty() || cuentaDestino.trim().isEmpty() || 
            importeText.trim().isEmpty() || codEmp.trim().isEmpty()) {
            request.setAttribute("error", "Complete todos los campos.");
            request.getRequestDispatcher("transferencia.jsp").forward(request, response);
            return;
        }

        try {
            double importe = Double.parseDouble(importeText.replace(",", "."));
            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor que 0.");
                request.getRequestDispatcher("transferencia.jsp").forward(request, response);
                return;
            }

            double costo = service.obtenerCostoMovimiento(cuentaOrigen);
            int confirm = JOptionPane.showConfirmDialog(null,
                String.format("El costo del movimiento es: %.2f\n¿Desea continuar?", costo),
                "Confirmar Transferencia", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            request.setAttribute("mensaje", "Transferencia realizada exitosamente. Costo: " + String.format("%.2f", costo));
            request.getRequestDispatcher("transferencia.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Ingrese un importe válido (use punto como separador decimal).");
            request.getRequestDispatcher("transferencia.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al realizar la transferencia: " + e.getMessage());
            request.getRequestDispatcher("transferencia.jsp").forward(request, response);
        }
    }
}