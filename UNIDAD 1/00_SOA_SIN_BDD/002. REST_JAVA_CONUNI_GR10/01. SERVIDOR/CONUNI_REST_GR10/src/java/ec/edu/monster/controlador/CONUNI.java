package ec.edu.monster.controlador;

import ec.edu.monster.modelo.LoginRequest;
import ec.edu.monster.servicio.ConversionService;
import ec.edu.monster.servicio.LoginService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * RESTful resource for unit conversions and login.
 * @author MATIAS
 */
@Path("conuni")
public class CONUNI {

    private final ConversionService conversionService = new ConversionService();
    private final LoginService loginService = new LoginService();

    @GET
    @Path("pulgadas-a-centimetros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pulgadasACentimetros(@QueryParam("pulgadas") double pulgadas) {
        double result = conversionService.pulgadasACentimetros(pulgadas);
        return Response.ok(result).build();
    }

    @GET
    @Path("centimetros-a-pulgadas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response centimetrosAPulgadas(@QueryParam("centimetros") double centimetros) {
        double result = conversionService.centimetrosAPulgadas(centimetros);
        return Response.ok(result).build();
    }

    @GET
    @Path("metros-a-pies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response metrosAPies(@QueryParam("metros") double metros) {
        double result = conversionService.metrosAPies(metros);
        return Response.ok(result).build();
    }

    @GET
    @Path("pies-a-metros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response piesAMetros(@QueryParam("pies") double pies) {
        double result = conversionService.piesAMetros(pies);
        return Response.ok(result).build();
    }

    @GET
    @Path("metros-a-yardas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response metrosAYardas(@QueryParam("metros") double metros) {
        double result = conversionService.metrosAYardas(metros);
        return Response.ok(result).build();
    }

    @GET
    @Path("yardas-a-metros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response yardasAMetros(@QueryParam("yardas") double yardas) {
        double result = conversionService.yardasAMetros(yardas);
        return Response.ok(result).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        boolean authenticated = loginService.autenticar(loginRequest.getUsuario(), loginRequest.getContraseña());
        if (authenticated) {
            return Response.ok("Login successful").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }

 
}