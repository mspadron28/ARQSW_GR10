using System;
using System.Drawing;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class InvoiceDetailsForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly Factura _factura;

        private Label lblTitle;
        private Label lblFacturaNumero;
        private Label lblCliente;
        private Label lblDocumento;
        private Label lblEmail;
        private Label lblMetodoPago;
        private Label lblFechaEmision;
        private Label lblDetalles;
        private Label lblResumen;
        private Button btnImprimir;
        private Button btnInicio;

        private TextBox txtDetalles;
        private TextBox txtResumen;

        public InvoiceDetailsForm(ViajecitosController controlador, Factura factura)
        {
            _controlador = controlador;
            _factura = factura;

            InitializeComponent();
            MostrarDatosFactura();
        }

        private void InitializeComponent()
        {
            this.Text = "Detalles de Factura - Viajecitos SA";
            this.Size = new Size(900, 600);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.White;

            lblTitle = new Label
            {
                Text = "Factura",
                Font = new Font("Segoe UI", 24, FontStyle.Bold),
                Location = new Point(20, 20),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            lblFacturaNumero = new Label
            {
                Text = "Número: ",
                Font = new Font("Segoe UI", 12, FontStyle.Regular),
                Location = new Point(20, 70),
                AutoSize = true
            };
            this.Controls.Add(lblFacturaNumero);

            lblCliente = new Label { Location = new Point(20, 100), AutoSize = true };
            this.Controls.Add(lblCliente);

            lblDocumento = new Label { Location = new Point(20, 125), AutoSize = true };
            this.Controls.Add(lblDocumento);

            lblEmail = new Label { Location = new Point(20, 150), AutoSize = true };
            this.Controls.Add(lblEmail);

            lblMetodoPago = new Label { Location = new Point(20, 175), AutoSize = true };
            this.Controls.Add(lblMetodoPago);

            lblFechaEmision = new Label { Location = new Point(20, 200), AutoSize = true };
            this.Controls.Add(lblFechaEmision);

            lblDetalles = new Label
            {
                Text = "Detalles de la Compra:",
                Font = new Font("Segoe UI", 14, FontStyle.Bold),
                Location = new Point(20, 240),
                AutoSize = true
            };
            this.Controls.Add(lblDetalles);

            txtDetalles = new TextBox
            {
                Location = new Point(20, 270),
                Size = new Size(840, 150),
                Multiline = true,
                ReadOnly = true,
                ScrollBars = ScrollBars.Vertical,
                Font = new Font("Consolas", 10)
            };
            this.Controls.Add(txtDetalles);

            lblResumen = new Label
            {
                Text = "Resumen:",
                Font = new Font("Segoe UI", 14, FontStyle.Bold),
                Location = new Point(20, 430),
                AutoSize = true
            };
            this.Controls.Add(lblResumen);

            txtResumen = new TextBox
            {
                Location = new Point(20, 460),
                Size = new Size(840, 60),
                Multiline = true,
                ReadOnly = true,
                Font = new Font("Segoe UI", 12, FontStyle.Bold)
            };
            this.Controls.Add(txtResumen);

            btnImprimir = new Button
            {
                Text = "Imprimir Factura",
                Location = new Point(600, 530),
                Size = new Size(130, 35),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnImprimir.FlatAppearance.BorderSize = 0;
            btnImprimir.Click += BtnImprimir_Click;
            this.Controls.Add(btnImprimir);

            btnInicio = new Button
            {
                Text = "Inicio",
                Location = new Point(740, 530),
                Size = new Size(120, 35),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(30, 58, 138),
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnInicio.FlatAppearance.BorderSize = 1;
            btnInicio.FlatAppearance.BorderColor = Color.FromArgb(30, 58, 138);
            btnInicio.Click += BtnInicio_Click;
            this.Controls.Add(btnInicio);
        }

        private void MostrarDatosFactura()
        {
            lblFacturaNumero.Text = $"Número: {_factura?.NumeroFactura ?? "N/A"}";

            var cliente = _factura?.Cliente;
            lblCliente.Text = $"Cliente: {cliente?.Nombre ?? "N/A"}";
            lblDocumento.Text = $"Documento: {cliente?.DocumentoIdentidad ?? "N/A"}";
            lblEmail.Text = $"Email: {cliente?.Email ?? "N/A"}";

            string metodoPago = _factura?.IdMetodoPago switch
            {
                1 => "Tarjeta de Crédito",
                2 => "Efectivo",
                3 => "Tarjeta de Débito",
                _ => "Desconocido"
            };
            lblMetodoPago.Text = $"Método de Pago: {metodoPago}";

            lblFechaEmision.Text = $"Fecha de Emisión: {_factura?.FechaEmision.ToString("yyyy-MM-dd HH:mm:ss") ?? "N/A"}";

            // Detalles
            if (_factura?.DetallesFactura != null)
            {
                var detallesTexto = "";
                foreach (var d in _factura.DetallesFactura)
                {
                    var vueloInfo = d.Vuelo != null ? $"{d.Vuelo.CiudadOrigen} a {d.Vuelo.CiudadDestino} ({d.Vuelo.HoraSalida:yyyy-MM-dd HH:mm})" : "N/A";
                    detallesTexto += $"Vuelo: {vueloInfo}\r\nCantidad: {d.Cantidad} | Valor Unitario: {d.ValorUnitario:C} | Total: {d.Total:C}\r\n\r\n";
                }
                txtDetalles.Text = detallesTexto;
            }
            else
            {
                txtDetalles.Text = "No hay detalles para esta factura.";
            }

            // Resumen
            txtResumen.Text =
                $"Subtotal: {_factura?.Subtotal.ToString("C") ?? "N/A"}\r\n" +
                $"Descuento: {_factura?.Descuento.ToString("C") ?? "N/A"}\r\n" +
                $"IVA (15%): {_factura?.Iva.ToString("C") ?? "N/A"}\r\n" +
                $"Total: {_factura?.Total.ToString("C") ?? "N/A"}";
        }

        private void BtnImprimir_Click(object sender, EventArgs e)
        {
            // Simple print dialog (or could export to PDF, etc)
            MessageBox.Show("Funcionalidad de impresión no implementada. Imprime desde la aplicación web.", "Imprimir", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void BtnInicio_Click(object sender, EventArgs e)
        {
            var mainForm = new MainForm(_controlador);
            mainForm.Show();
            this.Hide();
        }
    }
}
