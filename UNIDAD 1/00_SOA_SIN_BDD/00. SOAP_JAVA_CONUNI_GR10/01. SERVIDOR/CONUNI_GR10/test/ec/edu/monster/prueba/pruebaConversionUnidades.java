package ec.edu.monster.prueba;

import ec.edu.monster.servicio.ConversionService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ConversionService unit conversion functionality.
 * @author MATIAS
 */
public class pruebaConversionUnidades {

    private final ConversionService conversionService = new ConversionService();
    private static final double DELTA = 0.0001;

    @Test
    public void testPulgadasACentimetros() {
        double resultado = conversionService.pulgadasACentimetros(1.0);
        assertEquals("1 pulgada debería ser 2.54 centímetros", 2.54, resultado, DELTA);
    }

    @Test
    public void testCentimetrosAPulgadas() {
        double resultado = conversionService.centimetrosAPulgadas(2.54);
        assertEquals("2.54 centímetros deberían ser 1 pulgada", 1.0, resultado, DELTA);
    }

    @Test
    public void testMetrosAPies() {
        double resultado = conversionService.metrosAPies(1.0);
        assertEquals("1 metro debería ser 3.28084 pies", 3.28084, resultado, DELTA);
    }

    @Test
    public void testPiesAMetros() {
        double resultado = conversionService.piesAMetros(3.28084);
        assertEquals("3.28084 pies deberían ser 1 metro", 1.0, resultado, DELTA);
    }

    @Test
    public void testMetrosAYardas() {
        double resultado = conversionService.metrosAYardas(1.0);
        assertEquals("1 metro debería ser 1.09361 yardas", 1.09361, resultado, DELTA);
    }

    @Test
    public void testYardasAMetros() {
        double resultado = conversionService.yardasAMetros(1.09361);
        assertEquals("1.09361 yardas deberían ser 1 metro", 1.0, resultado, DELTA);
    }

    @Test
    public void testConversionCero() {
        double resultado = conversionService.pulgadasACentimetros(0.0);
        assertEquals("Cero pulgadas deberían ser cero centímetros", 0.0, resultado, DELTA);
    }
}