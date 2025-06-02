namespace ViajecitosRestServer.Models
{
    public class Cliente
    {
        public int IdCliente { get; set; }
        public string Nombre { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public string DocumentoIdentidad { get; set; } = string.Empty;
    }
}
