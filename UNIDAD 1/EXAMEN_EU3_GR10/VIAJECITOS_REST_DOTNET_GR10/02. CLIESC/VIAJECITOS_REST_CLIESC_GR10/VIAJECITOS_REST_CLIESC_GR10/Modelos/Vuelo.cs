using System;

namespace VIAJECITOS_REST_CLIESC_GR10.Modelos
{
    public class Vuelo
    {
        public int IdVuelo { get; set; }
        public string CiudadOrigen { get; set; }
        public string CiudadDestino { get; set; }
        public decimal Valor { get; set; }
        public DateTime HoraSalida { get; set; }
    }
}