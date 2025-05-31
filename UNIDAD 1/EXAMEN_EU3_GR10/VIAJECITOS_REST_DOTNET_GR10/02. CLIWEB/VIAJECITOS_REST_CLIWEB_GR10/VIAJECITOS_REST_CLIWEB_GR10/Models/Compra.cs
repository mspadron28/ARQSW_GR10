namespace VIAJECITOS_REST_CLIWEB_GR10.Models
{
    public class Compra
    {
        public int IdCompra { get; set; }
        public Vuelo Vuelo { get; set; }
        public DateTime FechaCompra { get; set; }
    }
}