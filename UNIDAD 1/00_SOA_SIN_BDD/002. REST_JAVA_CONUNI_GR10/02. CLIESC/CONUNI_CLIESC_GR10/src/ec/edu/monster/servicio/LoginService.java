package ec.edu.monster.servicio;

import ec.edu.monster.modelo.LoginRequest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service class to handle login operations with the CONUNI REST web service.
 * @author MATIAS
 */
public class LoginService {

    private static final String BASE_URL = "http://10.9.6.218:8080/CONUNI_REST_GR10/api/conuni";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public LoginService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
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