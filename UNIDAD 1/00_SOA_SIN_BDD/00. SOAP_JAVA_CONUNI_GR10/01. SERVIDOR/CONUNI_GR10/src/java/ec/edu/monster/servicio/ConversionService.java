package ec.edu.monster.servicio;

/**
 * Service class for unit conversions.
 * @author MATIAS
 */
public class ConversionService {

    public double pulgadasACentimetros(double pulgadas) {
        return pulgadas * 2.54;
    }

    public double centimetrosAPulgadas(double centimetros) {
        return centimetros / 2.54;
    }
    
     public double metrosAPies(double metros) {
        return metros * 3.28084;
    }

    public double piesAMetros(double pies) {
        return pies / 3.28084;
    }
        public double metrosAYardas(double metros) {
        return metros * 1.09361;
    }

    public double yardasAMetros(double yardas) {
        return yardas / 1.09361;
    }
}