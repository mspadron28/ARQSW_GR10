using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VIAJECITOS_REST_CLICON_GR10.Modelos
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
