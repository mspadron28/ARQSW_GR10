namespace ViajecitosRestServer.Models
{
    public class Vuelo
    {
        public int IdVuelo { get; set; }
        public string CiudadOrigen { get; set; } // Cambiado a no nullable
        public string CiudadDestino { get; set; } // Cambiado a no nullable
        public decimal Valor { get; set; } // Cambiado a decimal
        public DateTime HoraSalida { get; set; } // Cambiado a no nullable
    }
}