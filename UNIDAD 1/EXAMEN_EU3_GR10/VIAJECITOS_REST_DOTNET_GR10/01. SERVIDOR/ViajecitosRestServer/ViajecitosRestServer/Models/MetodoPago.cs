namespace ViajecitosRestServer.Models
{
    public class MetodoPago
    {
        public int IdMetodoPago { get; set; }
        public string NombreMetodo { get; set; } = string.Empty;
        public string Descripcion { get; set; } = string.Empty;
    }
}
