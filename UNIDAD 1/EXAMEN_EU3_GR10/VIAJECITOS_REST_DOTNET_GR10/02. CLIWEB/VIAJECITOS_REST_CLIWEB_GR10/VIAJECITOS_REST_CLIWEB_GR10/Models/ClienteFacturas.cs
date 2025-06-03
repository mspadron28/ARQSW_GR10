namespace VIAJECITOS_REST_CLIWEB_GR10.Models
{
    public class ClienteFacturas
    {
        public int ClienteId { get; set; }
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string DocumentoIdentidad { get; set; }
        public List<Factura> Facturas { get; set; }
    }
}
