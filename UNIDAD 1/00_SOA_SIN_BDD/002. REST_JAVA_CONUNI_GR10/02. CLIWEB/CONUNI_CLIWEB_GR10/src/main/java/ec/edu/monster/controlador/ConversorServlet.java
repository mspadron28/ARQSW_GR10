package ec.edu.monster.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet to handle conversion requests.
 * @author MATIAS
 */
@WebServlet(name = "ConversorServlet", urlPatterns = {"/convertir"})
public class ConversorServlet extends HttpServlet {

    private final AppControlador controlador = new AppControlador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String valor = request.getParameter("valor");

        if (accion == null || valor == null || valor.trim().isEmpty()) {
            request.setAttribute("error", "Por favor, seleccione una conversión e ingrese un valor válido.");
            request.getRequestDispatcher("/conversor.jsp").forward(request, response);
            return;
        }

        // Guardar el tipo de conversión seleccionado para preseleccionarlo en el JSP
        request.setAttribute("selectedAccion", accion);

        try {
            double inputValue = Double.parseDouble(valor.trim());
            double result = 0;
            String unit = "";

            if (inputValue < 0) {
                request.setAttribute("error", "Por favor, ingrese un valor no negativo.");
                request.getRequestDispatcher("/conversor.jsp").forward(request, response);
                return;
            }

            switch (accion) {
                case "pulgadasACentimetros":
                    result = controlador.pulgadasACentimetros(inputValue);
                    unit = "centímetros";
                    break;

                case "centimetrosAPulgadas":
                    result = controlador.centimetrosAPulgadas(inputValue);
                    unit = "pulgadas";
                    break;

                case "metrosAPies":
                    result = controlador.metrosAPies(inputValue);
                    unit = "pies";
                    break;

                case "piesAMetros":
                    result = controlador.piesAMetros(inputValue);
                    unit = "metros";
                    break;

                case "metrosAYardas":
                    result = controlador.metrosAYardas(inputValue);
                    unit = "yardas";
                    break;

                case "yardasAMetros":
                    result = controlador.yardasAMetros(inputValue);
                    unit = "metros";
                    break;

                default:
                    request.setAttribute("error", "Acción no reconocida.");
                    request.getRequestDispatcher("/conversor.jsp").forward(request, response);
                    return;
            }

            request.setAttribute("resultado", String.format("%.2f %s", result, unit));

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Por favor, ingrese un número válido.");
        } catch (Exception e) {
            request.setAttribute("error", "Error al realizar la conversión: " + e.getMessage());
        }

        request.getRequestDispatcher("/conversor.jsp").forward(request, response);
    }
}