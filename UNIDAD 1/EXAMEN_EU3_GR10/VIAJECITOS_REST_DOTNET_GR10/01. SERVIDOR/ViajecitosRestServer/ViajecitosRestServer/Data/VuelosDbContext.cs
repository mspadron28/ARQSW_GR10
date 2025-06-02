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
        public DbSet<Empleado> Empleados { get; set; }
        public DbSet<MetodoPago> MetodosPago { get; set; }
        public DbSet<Factura> Facturas { get; set; }
        public DbSet<DetalleFactura> DetallesFactura { get; set; }

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
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.CiudadDestino)
                .HasColumnName("ciudad_destino")
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Vuelo>()
                .Property(v => v.Valor)
                .HasColumnName("valor")
                .HasColumnType("numeric(7,2)")
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
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Cliente>()
                .Property(c => c.Email)
                .HasColumnName("email")
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Cliente>()
                .Property(c => c.DocumentoIdentidad)
                .HasColumnName("documento_identidad")
                .HasMaxLength(10)
                .IsRequired();

            modelBuilder.Entity<Cliente>()
                .HasIndex(c => c.Email)
                .IsUnique();

            modelBuilder.Entity<Cliente>()
                .HasIndex(c => c.DocumentoIdentidad)
                .IsUnique();

            // Mapeo de la tabla Usuario
            modelBuilder.Entity<Usuario>()
                .ToTable("Usuario")
                .HasKey(u => u.IdUsuario);

            modelBuilder.Entity<Usuario>()
                .Property(u => u.IdUsuario)
                .HasColumnName("id_usuario");

            modelBuilder.Entity<Usuario>()
                .Property(u => u.NombreUsuario)
                .HasColumnName("nombre_usuario")
                .HasMaxLength(50)
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .Property(u => u.ClaveUsuario)
                .HasColumnName("clave_usuario")
                .HasMaxLength(255)
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .Property(u => u.EstadoUsuario)
                .HasColumnName("estado_usuario")
                .HasMaxLength(20)
                .IsRequired();

            modelBuilder.Entity<Usuario>()
                .HasIndex(u => u.NombreUsuario)
                .IsUnique();

            // Mapeo de la tabla Empleado
            modelBuilder.Entity<Empleado>()
                .ToTable("Empleado")
                .HasKey(e => e.IdEmpleado);

            modelBuilder.Entity<Empleado>()
                .Property(e => e.IdEmpleado)
                .HasColumnName("id_empleado");

            modelBuilder.Entity<Empleado>()
                .Property(e => e.IdUsuario)
                .HasColumnName("id_usuario")
                .IsRequired();

            modelBuilder.Entity<Empleado>()
                .Property(e => e.Nombre)
                .HasColumnName("nombre")
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Empleado>()
                .Property(e => e.Email)
                .HasColumnName("email")
                .HasMaxLength(100)
                .IsRequired();

            modelBuilder.Entity<Empleado>()
                .Property(e => e.DocumentoIdentidad)
                .HasColumnName("documento_identidad")
                .HasMaxLength(10)
                .IsRequired();

            modelBuilder.Entity<Empleado>()
                .Property(e => e.Estado)
                .HasColumnName("estado")
                .HasMaxLength(20)
                .IsRequired();

            modelBuilder.Entity<Empleado>()
                .HasIndex(e => e.Email)
                .IsUnique();

            modelBuilder.Entity<Empleado>()
                .HasIndex(e => e.DocumentoIdentidad)
                .IsUnique();

            modelBuilder.Entity<Empleado>()
                .HasOne(e => e.Usuario)
                .WithMany()
                .HasForeignKey(e => e.IdUsuario);

            // Mapeo de la tabla Metodo_Pago
            modelBuilder.Entity<MetodoPago>()
                .ToTable("Metodo_Pago")
                .HasKey(m => m.IdMetodoPago);

            modelBuilder.Entity<MetodoPago>()
                .Property(m => m.IdMetodoPago)
                .HasColumnName("id_metodo_pago");

            modelBuilder.Entity<MetodoPago>()
                .Property(m => m.NombreMetodo)
                .HasColumnName("nombre_metodo")
                .HasMaxLength(50)
                .IsRequired();

            modelBuilder.Entity<MetodoPago>()
                .Property(m => m.Descripcion)
                .HasColumnName("descripcion")
                .HasMaxLength(100)
                .IsRequired();

            // Mapeo de la tabla Factura
            modelBuilder.Entity<Factura>()
                .ToTable("Factura")
                .HasKey(f => f.IdFactura);

            modelBuilder.Entity<Factura>()
                .Property(f => f.IdFactura)
                .HasColumnName("id_factura");

            modelBuilder.Entity<Factura>()
                .Property(f => f.NumeroFactura)
                .HasColumnName("numero_factura")
                .HasMaxLength(20)
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.FechaEmision)
                .HasColumnName("fecha_emision")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.IdEmpleado)
                .HasColumnName("id_empleado")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.IdCliente)
                .HasColumnName("id_cliente")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.IdMetodoPago)
                .HasColumnName("id_metodo_pago")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.Subtotal)
                .HasColumnName("subtotal")
                .HasColumnType("numeric(9,2)")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.Descuento)
                .HasColumnName("descuento")
                .HasColumnType("numeric(9,2)")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.Iva)
                .HasColumnName("iva")
                .HasColumnType("numeric(9,2)")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .Property(f => f.Total)
                .HasColumnName("total")
                .HasColumnType("numeric(9,2)")
                .IsRequired();

            modelBuilder.Entity<Factura>()
                .HasIndex(f => f.NumeroFactura)
                .IsUnique();

            modelBuilder.Entity<Factura>()
                .HasOne(f => f.Empleado)
                .WithMany()
                .HasForeignKey(f => f.IdEmpleado);

            modelBuilder.Entity<Factura>()
                .HasOne(f => f.Cliente)
                .WithMany()
                .HasForeignKey(f => f.IdCliente);

            modelBuilder.Entity<Factura>()
                .HasOne(f => f.MetodoPago)
                .WithMany()
                .HasForeignKey(f => f.IdMetodoPago);

            // Mapeo de la tabla Detalle_Factura
            modelBuilder.Entity<DetalleFactura>()
                .ToTable("Detalle_Factura")
                .HasKey(d => d.IdDetalleFactura);

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.IdDetalleFactura)
                .HasColumnName("id_detalle_factura");

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.IdFactura)
                .HasColumnName("id_factura")
                .IsRequired();

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.IdVuelo)
                .HasColumnName("id_vuelo")
                .IsRequired();

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.Cantidad)
                .HasColumnName("cantidad")
                .IsRequired();

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.ValorUnitario)
                .HasColumnName("valor_unitario")
                .HasColumnType("numeric(7,2)")
                .IsRequired();

            modelBuilder.Entity<DetalleFactura>()
                .Property(d => d.Total)
                .HasColumnName("total")
                .HasColumnType("numeric(9,2)")
                .IsRequired();

            modelBuilder.Entity<DetalleFactura>()
                .HasOne(d => d.Factura)
                .WithMany(f => f.DetallesFactura)
                .HasForeignKey(d => d.IdFactura);

            modelBuilder.Entity<DetalleFactura>()
                .HasOne(d => d.Vuelo)
                .WithMany()
                .HasForeignKey(d => d.IdVuelo);
        }
    }
}