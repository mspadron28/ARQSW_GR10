package ec.edu.monster.servicio;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.DepositoRequest;
import ec.edu.monster.modelo.RetiroRequest;
import ec.edu.monster.modelo.TransferenciaRequest;
import ec.edu.monster.modelo.ResponseMessage;
import ec.edu.monster.modelo.SaldoResponse;
import ec.edu.monster.modelo.CostoResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.monster.modelo.LoginRequest;
import java.util.List;

/**
 * Servicio para consumir las operaciones de la API REST de EurekaBank.
 * @author MATIAS
 */
public class EurekaService {

    private static final String BASE_URL = "http://localhost:8080/EUREKABANK_REST_GR10/api/eureka";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public EurekaService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<Movimiento> traerMovimientos(String cuenta) throws Exception {
        String url = BASE_URL + "/movimientos/" + cuenta;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), mapper.getTypeFactory().constructCollectionType(List.class, Movimiento.class));
        } else {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public void registrarDeposito(String cuenta, double importe, String codEmp) throws Exception {
        String url = BASE_URL + "/deposito";
        DepositoRequest request = new DepositoRequest();
        request.setCuenta(cuenta);
        request.setImporte(importe);
        request.setCodEmp(codEmp);
        String jsonBody = mapper.writeValueAsString(request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public void registrarRetiro(String cuenta, double importe, String codEmp) throws Exception {
        String url = BASE_URL + "/retiro";
        RetiroRequest request = new RetiroRequest();
        request.setCuenta(cuenta);
        request.setImporte(importe);
        request.setCodEmp(codEmp);
        String jsonBody = mapper.writeValueAsString(request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) throws Exception {
        String url = BASE_URL + "/transferencia";
        TransferenciaRequest request = new TransferenciaRequest();
        request.setCuentaOrigen(cuentaOrigen);
        request.setCuentaDestino(cuentaDestino);
        request.setImporte(importe);
        request.setCodEmp(codEmp);
        String jsonBody = mapper.writeValueAsString(request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public double verificarSaldo(String cuenta) throws Exception {
        String url = BASE_URL + "/saldo/" + cuenta;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            SaldoResponse saldoResponse = mapper.readValue(response.body(), SaldoResponse.class);
            return saldoResponse.getSaldo();
        } else {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public double obtenerCostoMovimiento(String cuenta) throws Exception {
        String url = BASE_URL + "/costo/" + cuenta;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            CostoResponse costoResponse = mapper.readValue(response.body(), CostoResponse.class);
            return costoResponse.getCosto();
        } else {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }

    public Usuario iniciarSesion(String usuario, String clave) throws Exception {
        String url = BASE_URL + "/login";
        LoginRequest loginRequest = new LoginRequest(usuario, clave);
        String jsonBody = mapper.writeValueAsString(loginRequest);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Usuario.class);
        } else {
            ResponseMessage error = mapper.readValue(response.body(), ResponseMessage.class);
            throw new Exception(error.getMessage());
        }
    }
}