package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.EurekaService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/eureka")
public class EurekaRestResource {

    private final EurekaService eurekaService;

    public EurekaRestResource() {
        this.eurekaService = new EurekaService();
    }

    @GET
    @Path("/movimientos/{cuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leerMovimientos(@PathParam("cuenta") String cuenta) {
        try {
            if (cuenta == null || cuenta.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El número de cuenta no puede estar vacío."))
                        .build();
            }
            List<Movimiento> lista = eurekaService.leerMovimientos(cuenta);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al obtener movimientos: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/deposito")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDeposito(DepositoRequest request) {
        try {
            if (request == null || request.getCuenta() == null || request.getCuenta().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El número de cuenta no puede estar vacío."))
                        .build();
            }
            if (request.getImporte() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El importe debe ser mayor que 0."))
                        .build();
            }
            if (request.getCodEmp() == null || request.getCodEmp().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El código de empleado no puede estar vacío."))
                        .build();
            }
            eurekaService.registrarDeposito(request.getCuenta(), request.getImporte(), request.getCodEmp());
            return Response.ok(new ResponseMessage("Depósito registrado exitosamente.")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al registrar depósito: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/retiro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarRetiro(RetiroRequest request) {
        try {
            if (request == null || request.getCuenta() == null || request.getCuenta().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El número de cuenta no puede estar vacío."))
                        .build();
            }
            if (request.getImporte() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El importe debe ser mayor que 0."))
                        .build();
            }
            if (request.getCodEmp() == null || request.getCodEmp().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El código de empleado no puede estar vacío."))
                        .build();
            }
            eurekaService.registrarRetiro(request.getCuenta(), request.getImporte(), request.getCodEmp());
            return Response.ok(new ResponseMessage("Retiro registrado exitosamente.")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al registrar retiro: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/transferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarTransferencia(TransferenciaRequest request) {
        try {
            if (request == null || request.getCuentaOrigen() == null || request.getCuentaOrigen().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("La cuenta de origen no puede estar vacía."))
                        .build();
            }
            if (request.getCuentaDestino() == null || request.getCuentaDestino().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("La cuenta de destino no puede estar vacía."))
                        .build();
            }
            if (request.getCuentaOrigen().equals(request.getCuentaDestino())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("Las cuentas de origen y destino deben ser diferentes."))
                        .build();
            }
            if (request.getImporte() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El importe debe ser mayor que 0."))
                        .build();
            }
            if (request.getCodEmp() == null || request.getCodEmp().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El código de empleado no puede estar vacío."))
                        .build();
            }
            eurekaService.realizarTransferencia(
                    request.getCuentaOrigen(),
                    request.getCuentaDestino(),
                    request.getImporte(),
                    request.getCodEmp()
            );
            return Response.ok(new ResponseMessage("Transferencia realizada exitosamente.")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al realizar transferencia: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/saldo/{cuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarSaldo(@PathParam("cuenta") String cuenta) {
        try {
            if (cuenta == null || cuenta.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El número de cuenta no puede estar vacío."))
                        .build();
            }
            double saldo = eurekaService.verificarSaldo(cuenta);
            return Response.ok(new SaldoResponse(saldo)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al verificar saldo: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/costo/{cuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCostoMovimiento(@PathParam("cuenta") String cuenta) {
        try {
            if (cuenta == null || cuenta.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El número de cuenta no puede estar vacío."))
                        .build();
            }
            double costo = eurekaService.obtenerCostoMovimiento(cuenta);
            return Response.ok(new CostoResponse(costo)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al obtener costo: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(LoginRequest request) {
        try {
            if (request == null || request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("El nombre de usuario no puede estar vacío."))
                        .build();
            }
            if (request.getClave() == null || request.getClave().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ResponseMessage("La clave no puede estar vacía."))
                        .build();
            }
            Usuario user = eurekaService.iniciarSesion(request.getUsername(), request.getClave());
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseMessage("Error al iniciar sesión: " + e.getMessage()))
                    .build();
        }
    }

    // Request and Response classes
    public static class DepositoRequest {
        private String cuenta;
        private double importe;
        private String codEmp;

        public String getCuenta() {
            return cuenta;
        }

        public void setCuenta(String cuenta) {
            this.cuenta = cuenta;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public String getCodEmp() {
            return codEmp;
        }

        public void setCodEmp(String codEmp) {
            this.codEmp = codEmp;
        }
    }

    public static class RetiroRequest {
        private String cuenta;
        private double importe;
        private String codEmp;

        public String getCuenta() {
            return cuenta;
        }

        public void setCuenta(String cuenta) {
            this.cuenta = cuenta;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public String getCodEmp() {
            return codEmp;
        }

        public void setCodEmp(String codEmp) {
            this.codEmp = codEmp;
        }
    }

    public static class TransferenciaRequest {
        private String cuentaOrigen;
        private String cuentaDestino;
        private double importe;
        private String codEmp;

        public String getCuentaOrigen() {
            return cuentaOrigen;
        }

        public void setCuentaOrigen(String cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
        }

        public String getCuentaDestino() {
            return cuentaDestino;
        }

        public void setCuentaDestino(String cuentaDestino) {
            this.cuentaDestino = cuentaDestino;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public String getCodEmp() {
            return codEmp;
        }

        public void setCodEmp(String codEmp) {
            this.codEmp = codEmp;
        }
    }

    public static class LoginRequest {
        private String username;
        private String clave;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }
    }

    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class SaldoResponse {
        private double saldo;

        public SaldoResponse(double saldo) {
            this.saldo = saldo;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
    }

    public static class CostoResponse {
        private double costo;

        public CostoResponse(double costo) {
            this.costo = costo;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }
    }
}