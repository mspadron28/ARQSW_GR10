namespace ViajecitosRestServer.Models
{
    public class Compra
    {
        public int IdCompra { get; set; }
        public int IdVuelo { get; set; }
        public int IdCliente { get; set; }
        public DateTime FechaCompra { get; set; } // Cambiado a no nullable
        public Vuelo? Vuelo { get; set; }
        public Cliente? Cliente { get; set; }
    }
}