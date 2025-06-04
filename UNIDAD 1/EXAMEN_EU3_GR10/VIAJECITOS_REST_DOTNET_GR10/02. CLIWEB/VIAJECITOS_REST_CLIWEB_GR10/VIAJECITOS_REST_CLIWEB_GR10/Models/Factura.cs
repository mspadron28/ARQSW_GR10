using ViajecitosRestServer.Models;

namespace VIAJECITOS_REST_CLIWEB_GR10.Models
{
    public class Factura
    {
        public int IdFactura { get; set; }
        public string NumeroFactura { get; set; }
        public DateTime FechaEmision { get; set; }
        public int IdEmpleado { get; set; }
        public int IdCliente { get; set; }
        public int IdMetodoPago { get; set; }
        public decimal Subtotal { get; set; }
        public decimal Descuento { get; set; }
        public decimal Iva { get; set; }
        public decimal Total { get; set; }
        public Empleado Empleado { get; set; }
        public Cliente Cliente { get; set; }
        public MetodoPago MetodoPago { get; set; }
        public List<DetalleFactura> DetallesFactura { get; set; }
    }
}
