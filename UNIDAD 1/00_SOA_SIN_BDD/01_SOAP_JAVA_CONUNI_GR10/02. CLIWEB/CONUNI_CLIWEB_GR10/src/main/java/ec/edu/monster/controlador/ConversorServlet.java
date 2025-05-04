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

        try {
            if ("pulgadasACentimetros".equals(accion)) {
                double pulgadas = Double.parseDouble(request.getParameter("pulgadas"));
                if (pulgadas < 0) {
                    request.setAttribute("error", "Por favor, ingrese un valor no negativo.");
                } else {
                    double centimetros = controlador.pulgadasACentimetros(pulgadas);
                    request.setAttribute("resultado", String.format("%.2f centímetros", centimetros));
                }
            } else if ("centimetrosAPulgadas".equals(accion)) {
                double centimetros = Double.parseDouble(request.getParameter("centimetros"));
                if (centimetros < 0) {
                    request.setAttribute("error", "Por favor, ingrese un valor no negativo.");
                } else {
                    double pulgadas = controlador.centimetrosAPulgadas(centimetros);
                    request.setAttribute("resultado", String.format("%.2f pulgadas", pulgadas));
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Por favor, ingrese un número válido.");
        } catch (Exception e) {
            request.setAttribute("error", "Error al realizar la conversión: " + e.getMessage());
        }

        request.getRequestDispatcher("/conversor.jsp").forward(request, response);
    }
}