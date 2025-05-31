namespace VIAJECITOS_REST_CLIESC_GR10.Modelos
{
    public class Vuelo
    {
        public int IdVuelo { get; set; }
        public string CiudadOrigen { get; set; }
        public string CiudadDestino { get; set; }
        public DateTime Fecha { get; set; }
        public DateTime HoraSalida { get; set; }
        public double Valor { get; set; }
    }
}