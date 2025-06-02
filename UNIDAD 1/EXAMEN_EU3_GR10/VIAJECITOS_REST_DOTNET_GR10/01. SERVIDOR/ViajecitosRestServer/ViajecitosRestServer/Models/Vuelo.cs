namespace ViajecitosRestServer.Models
{
    public class Vuelo
    {
        public int IdVuelo { get; set; }
        public string CiudadOrigen { get; set; } = string.Empty;
        public string CiudadDestino { get; set; } = string.Empty;
        public decimal Valor { get; set; }
        public DateTime HoraSalida { get; set; }
    }
}
