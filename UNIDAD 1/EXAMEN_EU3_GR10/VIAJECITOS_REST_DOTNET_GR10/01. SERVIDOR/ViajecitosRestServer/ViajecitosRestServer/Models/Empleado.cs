namespace ViajecitosRestServer.Models
{
    public class Empleado
    {
        public int IdEmpleado { get; set; }
        public int IdUsuario { get; set; }
        public string Nombre { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public string DocumentoIdentidad { get; set; } = string.Empty;
        public string Estado { get; set; } = string.Empty;
        public Usuario? Usuario { get; set; }
    }
}
