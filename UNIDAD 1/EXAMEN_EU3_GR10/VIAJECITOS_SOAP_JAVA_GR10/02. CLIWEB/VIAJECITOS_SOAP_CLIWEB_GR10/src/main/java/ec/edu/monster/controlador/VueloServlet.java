package ec.edu.monster.controlador;

import ec.edu.monster.ws.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Servlet para manejar solicitudes de búsqueda de vuelos.
 *
 * @author MATIAS
 */
@WebServlet(name = "VueloServlet", urlPatterns = {"/VueloServlet"})
public class VueloServlet extends HttpServlet {

    private final ViajecitosService viajecitosService = new ViajecitosService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Forzar codificación UTF-8 para los parámetros de la solicitud
            request.setCharacterEncoding("UTF-8");

            String ciudadOrigen = request.getParameter("ciudadOrigen");
            String ciudadDestino = request.getParameter("ciudadDestino");
            String fechaStr = request.getParameter("fecha");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            // Convertir Date a XMLGregorianCalendar
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(fecha);
            XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

            Vuelo vuelo = viajecitosService.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, xmlFecha);
            request.setAttribute("vuelo", vuelo);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("vuelo", null);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }
}
