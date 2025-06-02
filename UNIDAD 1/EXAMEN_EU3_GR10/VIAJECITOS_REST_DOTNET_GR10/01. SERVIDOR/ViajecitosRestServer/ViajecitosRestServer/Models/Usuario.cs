namespace ViajecitosRestServer.Models
{
    public class Usuario
    {
        public int IdUsuario { get; set; }
        public string NombreUsuario { get; set; } = string.Empty;
        public string ClaveUsuario { get; set; } = string.Empty;
        public string EstadoUsuario { get; set; } = string.Empty;
    }
}
