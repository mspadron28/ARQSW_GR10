using System.ServiceModel;

namespace espe.edu.ec.monster.modelo
{
    [ServiceContract]
    public interface IConversionService
    {
        [OperationContract]
        double pulgadasACentimetros(double pulgadas);

        [OperationContract]
        double centimetrosAPulgadas(double centimetros);

        [OperationContract]
        double metrosAPies(double metros);

        [OperationContract]
        double piesAMetros(double pies);

        [OperationContract]
        double metrosAYardas(double metros);

        [OperationContract]
        double yardasAMetros(double yardas);

        [OperationContract]
        bool Login(string usuario, string contraseña);
    }
}
