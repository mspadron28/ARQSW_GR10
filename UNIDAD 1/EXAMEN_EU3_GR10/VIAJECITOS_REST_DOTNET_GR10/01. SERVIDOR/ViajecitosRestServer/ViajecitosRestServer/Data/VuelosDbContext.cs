using Microsoft.EntityFrameworkCore;
using ViajecitosRestServer.Models;

namespace ViajecitosRestServer.Data
{
    public class VuelosDbContext : DbContext
    {
        public VuelosDbContext(DbContextOptions<VuelosDbContext> options)
            : base(options)
        {
        }

        public DbSet<Vuelo> Vuelos { get; set; }
        public DbSet<Cliente> Clientes { get; set; }
        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Compra> Compras { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Mapeo de la tabla Vuelo
            modelBuilder.Entity<Vuelo>()
                .ToTable("Vuelo")
                .HasKey(v => v.IdVuelo);

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.IdVuelo)
                .HasColumnName("id_vuelo");

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.CiudadOrigen)
                .HasColumnName("ciudad_origen")
                .IsRequired();

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.CiudadDestino)
                .HasColumnName("ciudad_destino")
                .IsRequired();

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.Valor)
                .HasColumnName("valor")
                .HasColumnType("decimal(7,2)")
                .IsRequired();

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.HoraSalida)
                .HasColumnName("hora_salida")
                .IsRequired();

            // Mapeo de la tabla Cliente
            modelBuilder.Entity<Cliente>()
                .ToTable("Cliente")
                .HasKey(c => c.IdCliente);

            modelBuilder.Entity<Cliente>()
                .Property(c => c.IdCliente)
                .HasColumnName("id_cliente");

            modelBuilder.Entity<Cliente>()
                .Property(c => c.Nombre)
                .HasColumnName("nombre")
                .IsRequired();

            modelBuilder.Entity<Cliente>()
                .Property(c => c.Email)
                .HasColumnName("email")
                .IsRequired();

            modelBuilder.Entity<Cliente>()
                .Property(c => c.DocumentoIdentidad)
                .HasColumnName("documento_identidad")
                .IsRequired();

            // Mapeo de la tabla Usuario
            modelBuilder.Entity<Usuario>()
                .ToTable("Usuario")
                .HasKey(u => u.IdUsuario);

            modelBuilder.Entity<Usuario>()
                .Property(u => u.IdUsuario)
                .HasColumnName("id_usuario");

            modelBuilder.Entity<Usuario>()
                .Property(u => u.IdCliente)
                .HasColumnName("id_cliente")
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .Property(u => u.NombreUsuario)
                .HasColumnName("nombre_usuario")
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .Property(u => u.ClaveUsuario)
                .HasColumnName("clave_usuario")
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .Property(u => u.EstadoUsuario)
                .HasColumnName("estado_usuario")
                .IsRequired();

            // Mapeo de la tabla Compra
            modelBuilder.Entity<Compra>()
                .ToTable("Compra")
                .HasKey(c => c.IdCompra);

            modelBuilder.Entity<Compra>()
                .Property(c => c.IdCompra)
                .HasColumnName("id_compra");

            modelBuilder.Entity<Compra>()
                .Property(c => c.IdVuelo)
                .HasColumnName("id_vuelo")
                .IsRequired();

            modelBuilder.Entity<Compra>()
                .Property(c => c.IdCliente)
                .HasColumnName("id_cliente")
                .IsRequired();

            modelBuilder.Entity<Compra>()
                .Property(c => c.FechaCompra)
                .HasColumnName("fecha_compra")
                .IsRequired();

            // Relaciones
            modelBuilder.Entity<Compra>()
                .HasOne(c => c.Vuelo)
                .WithMany()
                .HasForeignKey(c => c.IdVuelo);

            modelBuilder.Entity<Compra>()
                .HasOne(c => c.Cliente)
                .WithMany()
                .HasForeignKey(c => c.IdCliente);

            modelBuilder.Entity<Usuario>()
                .HasOne(u => u.Cliente)
                .WithMany()
                .HasForeignKey(u => u.IdCliente);
        }
    }
}