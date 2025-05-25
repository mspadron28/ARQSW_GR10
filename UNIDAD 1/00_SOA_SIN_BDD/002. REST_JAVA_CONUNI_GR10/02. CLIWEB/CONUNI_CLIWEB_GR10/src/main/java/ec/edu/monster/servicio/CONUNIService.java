package ec.edu.monster.servicio;

import ec.edu.monster.modelo.LoginRequest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service class to interact with the CONUNI REST web service.
 * @author MATIAS
 */
public class CONUNIService {

    private static final String BASE_URL = "http://10.9.6.218:8080/CONUNI_REST_GR10/api/conuni";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public CONUNIService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public double pulgadasACentimetros(double pulgadas) throws Exception {
        String url = BASE_URL + "/pulgadas-a-centimetros?pulgadas=" + pulgadas;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        String url = BASE_URL + "/centimetros-a-pulgadas?centimetros=" + centimetros;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public double metrosAPies(double metros) throws Exception {
        String url = BASE_URL + "/metros-a-pies?metros=" + metros;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public double piesAMetros(double pies) throws Exception {
        String url = BASE_URL + "/pies-a-metros?pies=" + pies;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public double metrosAYardas(double metros) throws Exception {
        String url = BASE_URL + "/metros-a-yardas?metros=" + metros;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public double yardasAMetros(double yardas) throws Exception {
        String url = BASE_URL + "/yardas-a-metros?yardas=" + yardas;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Double.class);
        } else {
            throw new Exception("Error en la conversión: " + response.statusCode());
        }
    }

    public boolean login(String usuario, String contraseña) throws Exception {
        String url = BASE_URL + "/login";
        LoginRequest loginRequest = new LoginRequest(usuario, contraseña);
        String jsonBody = mapper.writeValueAsString(loginRequest);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return true;
        } else if (response.statusCode() == 401) {
            return false;
        } else {
            throw new Exception("Error en el login: " + response.statusCode());
        }
    }
}