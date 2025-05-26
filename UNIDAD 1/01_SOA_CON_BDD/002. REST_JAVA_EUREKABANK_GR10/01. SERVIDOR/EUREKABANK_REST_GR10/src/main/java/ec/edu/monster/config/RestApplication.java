package ec.edu.monster.config;

   import jakarta.ws.rs.ApplicationPath;
   import jakarta.ws.rs.core.Application;
   import java.util.HashSet;
   import java.util.Set;

   @ApplicationPath("/api")
   public class RestApplication extends Application {
       @Override
       public Set<Class<?>> getClasses() {
           Set<Class<?>> resources = new HashSet<>();
           resources.add(ec.edu.monster.controlador.EurekaRestResource.class);
           return resources;
       }
   }