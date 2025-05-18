package ec.edu.monster.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConversorServlet", urlPatterns = {"/convertir"})
public class ConversorServlet extends HttpServlet {

    private final AppControlador controlador = new AppControlador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String valor = request.getParameter("valor");

        // Validar que los parámetros no sean nulos ni vacíos
        if (accion == null || valor == null || valor.trim().isEmpty()) {
            request.setAttribute("error", "Por favor, seleccione una conversión e ingrese un valor válido.");
            request.getRequestDispatcher("/conversor.jsp").forward(request, response);
            return;
        }

        // Guardar el tipo de conversión seleccionado para preseleccionarlo en el JSP
        request.setAttribute("selectedAccion", accion);

        try {
            // Normalizar el valor: reemplazar coma por punto
            valor = valor.trim().replace(",", ".");
            double inputValue = Double.parseDouble(valor);

            // Validar que el valor sea no negativo
            if (inputValue < 0) {
                request.setAttribute("error", "Por favor, ingrese un valor no negativo.");
                request.getRequestDispatcher("/conversor.jsp").forward(request, response);
                return;
            }

            double result = 0;
            String resultado = "";

            switch (accion) {
                case "pulgadasACentimetros":
                    result = controlador.pulgadasACentimetros(inputValue);
                    resultado = String.format("%.2f in = %.2f cm.", inputValue, result);
                    break;

                case "centimetrosAPulgadas":
                    result = controlador.centimetrosAPulgadas(inputValue);
                    resultado = String.format("%.2f cm = %.2f in.", inputValue, result);
                    break;

                case "metrosAPies":
                    result = controlador.metrosAPies(inputValue);
                    resultado = String.format("%.2f m = %.2f ft.", inputValue, result);
                    break;

                case "piesAMetros":
                    result = controlador.piesAMetros(inputValue);
                    resultado = String.format("%.2f ft = %.2f m.", inputValue, result);
                    break;

                case "metrosAYardas":
                    result = controlador.metrosAYardas(inputValue);
                    resultado = String.format("%.2f m = %.2f yd.", inputValue, result);
                    break;

                case "yardasAMetros":
                    result = controlador.yardasAMetros(inputValue);
                    resultado = String.format("%.2f yd = %.2f m.", inputValue, result);
                    break;

                default:
                    request.setAttribute("error", "Acción no reconocida.");
                    request.getRequestDispatcher("/conversor.jsp").forward(request, response);
                    return;
            }

            request.setAttribute("resultado", resultado);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Por favor, ingrese un número válido (use punto como separador decimal).");
        } catch (Exception e) {
            request.setAttribute("error", "Error al realizar la conversión: " + e.getMessage());
        }

        request.getRequestDispatcher("/conversor.jsp").forward(request, response);
    }
}