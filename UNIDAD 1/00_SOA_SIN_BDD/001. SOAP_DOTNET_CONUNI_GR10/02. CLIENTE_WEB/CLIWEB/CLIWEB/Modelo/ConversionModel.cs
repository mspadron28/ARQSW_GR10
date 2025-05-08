namespace CLIWEB.Modelo
{
    public class ConversionModel
    {
        public double InputValue { get; set; }
        public string ConversionType { get; set; }
        public double? Result { get; set; }

        public List<string> ConversionTypes => new List<string>
        {
            "CentimetersToFeet",
            "FeetToCentimeters",
            "MetersToYards",
            "YardsToMeters",
            "InchesToCentimeters",
            "CentimetersToInches"
        };
    }
}