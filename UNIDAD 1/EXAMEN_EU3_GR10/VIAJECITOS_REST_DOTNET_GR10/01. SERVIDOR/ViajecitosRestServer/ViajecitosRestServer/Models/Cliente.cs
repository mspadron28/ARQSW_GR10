namespace ViajecitosRestServer.Models
{
    public class Cliente
    {
        public int IdCliente { get; set; }
        public string Nombre { get; set; } // Cambiado a no nullable
        public string Email { get; set; } // Cambiado a no nullable
        public string DocumentoIdentidad { get; set; } // Cambiado a no nullable
    }
}