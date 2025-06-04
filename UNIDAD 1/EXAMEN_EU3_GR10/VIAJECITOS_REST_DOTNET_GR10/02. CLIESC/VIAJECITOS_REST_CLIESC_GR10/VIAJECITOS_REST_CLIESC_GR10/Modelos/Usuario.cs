namespace VIAJECITOS_REST_CLIESC_GR10.Modelos
{
    public class Usuario
    {
        public int IdUsuario { get; set; }
        public int IdCliente { get; set; }
        public string NombreUsuario { get; set; }
        public string ClaveUsuario { get; set; }
        public string EstadoUsuario { get; set; }
        public Cliente Cliente { get; set; }
    }
}