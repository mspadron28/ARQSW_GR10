package ec.edu.monster.servicio;

/**
 * Service class for unit conversions.
 * @author MATIAS
 */
public class ConversionService {

    /**
     * Converts inches to centimeters.
     * @param pulgadas the value in inches
     * @return the value in centimeters
     */
    public double pulgadasACentimetros(double pulgadas) {
        return pulgadas * 2.54;
    }

    /**
     * Converts centimeters to inches.
     * @param centimetros the value in centimeters
     * @return the value in inches
     */
    public double centimetrosAPulgadas(double centimetros) {
        return centimetros / 2.54;
    }
}