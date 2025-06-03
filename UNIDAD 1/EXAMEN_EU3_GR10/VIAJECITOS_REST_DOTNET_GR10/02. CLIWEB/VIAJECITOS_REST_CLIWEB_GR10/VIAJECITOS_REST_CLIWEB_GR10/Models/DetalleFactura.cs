namespace VIAJECITOS_REST_CLIWEB_GR10.Models
{
    public class DetalleFactura
    {
        public int IdDetalleFactura { get; set; }
        public int IdFactura { get; set; }
        public int IdVuelo { get; set; }
        public int Cantidad { get; set; }
        public decimal ValorUnitario { get; set; }
        public decimal Total { get; set; }
        public Vuelo Vuelo { get; set; }
    }
}
