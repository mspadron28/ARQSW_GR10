namespace ViajecitosRestServer.Models
{
    public class Usuario
    {
        public int IdUsuario { get; set; }
        public int IdCliente { get; set; }
        public string NombreUsuario { get; set; } // Cambiado a no nullable
        public string ClaveUsuario { get; set; } // Cambiado a no nullable
        public string EstadoUsuario { get; set; } // Cambiado a no nullable
        public Cliente? Cliente { get; set; }
    }
}