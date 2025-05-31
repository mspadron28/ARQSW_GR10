using System;

namespace VIAJECITOS_REST_CLICON_GR10.Modelos
{
    public class Compra
    {
        public int IdCompra { get; set; }
        public int IdVuelo { get; set; }
        public int IdCliente { get; set; }
        public DateTime FechaCompra { get; set; }
        public Vuelo Vuelo { get; set; }
        public Cliente Cliente { get; set; }
    }
}