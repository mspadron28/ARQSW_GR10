using espe.edu.ec.monster.modelo;
using System.ServiceModel;

namespace espe.edu.ec.monster.controlador
{
    // Implementación de la interfaz IConversionService
    public class ConversionService : IConversionService
    {
        public double CentimetersToFeet(double centimeters)
        {
            // Implementación de la conversión de centímetros a pies
            return centimeters * 0.0328084;
        }

        public double FeetToCentimeters(double feet)
        {
            // Implementación de la conversión de pies a centímetros
            return feet / 0.0328084;
        }

        public double MetersToYards(double meters)
        {
            // Implementación de la conversión de metros a yardas
            return meters * 1.09361;
        }

        public double YardsToMeters(double yards)
        {
            // Implementación de la conversión de yardas a metros
            return yards / 1.09361;
        }

        public double InchesToCentimeters(double inches)
        {
            // Implementación de la conversión de pulgadas a centímetros
            return inches * 2.54;
        }

        public double CentimetersToInches(double centimeters)
        {
            // Implementación de la conversión de centímetros a pulgadas
            return centimeters / 2.54;
        }
    }
}
