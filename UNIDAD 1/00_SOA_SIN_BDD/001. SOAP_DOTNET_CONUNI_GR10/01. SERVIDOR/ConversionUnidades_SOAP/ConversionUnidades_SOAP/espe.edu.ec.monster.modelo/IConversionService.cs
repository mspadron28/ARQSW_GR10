using System.ServiceModel;

namespace espe.edu.ec.monster.modelo
{
    [ServiceContract]
    public interface IConversionService
    {
        [OperationContract]
        double CentimetersToFeet(double centimeters);

        [OperationContract]
        double FeetToCentimeters(double feet);

        [OperationContract]
        double MetersToYards(double meters);

        [OperationContract]
        double YardsToMeters(double yards);

        [OperationContract]
        double InchesToCentimeters(double inches);

        [OperationContract]
        double CentimetersToInches(double centimeters);
    }
}
