namespace CONUNI_CLIWEB_GR10.Modelo
{
    public class ConversionModel
    {
        public double InputValue { get; set; }
        public string ConversionType { get; set; }
        public double? Result { get; set; }

        public List<string> ConversionTypes { get; set; }

        public ConversionModel()
        {
            ConversionTypes = new List<string>
        {
            "pulgadasACentimetros",
            "centimetrosAPulgadas",
            "metrosAPies",
            "piesAMetros",
            "metrosAYardas",
            "yardasAMetros"
        };
        }
    }


}