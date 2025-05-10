package ec.edu.monster.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures the JAX-RS application.
 * @author MATIAS
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {
    // No additional configuration needed for basic setup
}