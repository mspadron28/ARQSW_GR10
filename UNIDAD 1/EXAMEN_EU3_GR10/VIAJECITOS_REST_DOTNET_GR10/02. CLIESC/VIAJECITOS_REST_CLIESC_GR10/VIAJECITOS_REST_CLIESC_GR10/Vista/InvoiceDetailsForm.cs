using System;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class InvoiceDetailsForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly Factura _factura;

        private Label lblError;
        private Panel headerPanel;
        private DataGridView dgvDetalles;
        private Panel summaryPanel;
        private Button btnImprimir;
        private Button btnInicio;
        private TableLayoutPanel mainLayout;

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
            this.WindowState = FormWindowState.Maximized;
            this.BackColor = Color.FromArgb(255, 255, 255); // --background-color: #ffffff

            int contentWidth = 900; // Max-width from web
            int leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

            // Main container
            mainLayout = new TableLayoutPanel
            {
                Location = new Point(leftMargin, 0),
                Size = new Size(contentWidth, this.ClientSize.Height),
                ColumnCount = 1,
                RowCount = 6,
                Padding = new Padding(24), // 1.5rem padding
                AutoScroll = true,
                BackColor = Color.FromArgb(255, 255, 255), // --white
                BorderStyle = BorderStyle.FixedSingle
            };
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 30));   // Error message
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 120));  // Header
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 120));  // Invoice info
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 50));   // Details
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 150));  // Summary + Footer
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 60));   // Actions
            this.Controls.Add(mainLayout);

            // Error message
            lblError = new Label
            {
                ForeColor = Color.FromArgb(231, 76, 60), // --error-color: #e74c3c
                BackColor = Color.FromArgb(255, 241, 240), // #fff1f0
                Dock = DockStyle.Fill,
                Visible = false,
                Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                TextAlign = ContentAlignment.MiddleCenter,
                Padding = new Padding(8) // 0.5rem
            };
            mainLayout.Controls.Add(lblError, 0, 0);

            // Header panel
            headerPanel = new Panel
            {
                Dock = DockStyle.Fill,
                BorderStyle = BorderStyle.None,
                BackColor = Color.FromArgb(255, 255, 255)
            };
            headerPanel.Paint += (s, e) =>
            {
                using (var pen = new Pen(Color.FromArgb(224, 224, 224), 1)) // --border-color: #e0e0e0
                {
                    e.Graphics.DrawLine(pen, 0, headerPanel.Height - 1, headerPanel.Width, headerPanel.Height - 1);
                }
            };
            mainLayout.Controls.Add(headerPanel, 0, 1);

            var companyDetails = new Label
            {
                Text = "Viajecitos SA\nAv. de los Viajes 123, Quito, Ecuador\nRUC: 1791234567001\nEmail: contacto@viajecitossa.com\nTeléfono: +593 2 123 4567",
                Font = new Font("Inter", 10, FontStyle.Regular), // 0.8rem = ~10px
                ForeColor = Color.FromArgb(51, 51, 51), // --text-color: #333333
                Location = new Point(0, 10),
                AutoSize = true
            };
            companyDetails.Font = new Font(companyDetails.Font.FontFamily, 10, FontStyle.Bold); // First line bold
            headerPanel.Controls.Add(companyDetails);

            var invoiceTitle = new Label
            {
                Text = "FACTURA\nNúmero: ",
                Font = new Font("Inter", 16, FontStyle.Bold), // 1.5rem = ~16px
                ForeColor = Color.FromArgb(44, 62, 80), // --primary-color: #2c3e50
                Location = new Point(headerPanel.Width / 2 - 50, 10),
                AutoSize = true
            };
            headerPanel.Controls.Add(invoiceTitle);

            var logoPlaceholder = new Panel
            {
                Size = new Size(100, 100),
                Location = new Point(headerPanel.Width - 100, 10),
                BackColor = Color.FromArgb(224, 224, 224), // Placeholder for logo
                BorderStyle = BorderStyle.FixedSingle
            };
            headerPanel.Controls.Add(logoPlaceholder);

            // Invoice info
            var infoPanel = new TableLayoutPanel
            {
                Dock = DockStyle.Fill,
                ColumnCount = 4,
                RowCount = 4,
                Padding = new Padding(0, 10, 0, 10),
                CellBorderStyle = TableLayoutPanelCellBorderStyle.None
            };
            infoPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 20));
            infoPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 30));
            infoPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 20));
            infoPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 30));
            mainLayout.Controls.Add(infoPanel, 0, 2);

            var labels = new[]
            {
                new Label { Text = "Facturado a:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },
                new Label { Text = "Fecha de Emisión:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },

                new Label { Text = "Documento:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },
                new Label { Text = "Fecha de Vencimiento:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },

                new Label { Text = "Email:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },
                new Label { Text = "Método de Pago:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },

                new Label { Text = "Atendido por:", Font = new Font("Inter", 11, FontStyle.Bold), AutoSize = true },
                new Label { Text = "Viajecitos SA", Font = new Font("Inter", 11, FontStyle.Regular), AutoSize = true },
                new Label { Text = "", AutoSize = true },
                new Label { Text = "", AutoSize = true }
            };
            for (int i = 0; i < labels.Length; i++)
            {
                infoPanel.Controls.Add(labels[i], i % 4, i / 4);
            }

            // Invoice details
            var detailsPanel = new Panel { Dock = DockStyle.Fill };
            mainLayout.Controls.Add(detailsPanel, 0, 3);

            var lblDetalles = new Label
            {
                Text = "Detalles de la Compra",
                Font = new Font("Inter", 12, FontStyle.Bold), // 1rem = ~12px
                ForeColor = Color.FromArgb(44, 62, 80), // --primary-color: #2c3e50
                Location = new Point(0, 0),
                AutoSize = true
            };
            detailsPanel.Controls.Add(lblDetalles);

            dgvDetalles = new DataGridView
            {
                Location = new Point(0, 30),
                Size = new Size(detailsPanel.Width, detailsPanel.Height - 30),
                AllowUserToAddRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                RowHeadersVisible = false,
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
                Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                ForeColor = Color.FromArgb(51, 51, 51), // --text-color: #333333
                GridColor = Color.FromArgb(224, 224, 224), // --border-color: #e0e0e0
                BackgroundColor = Color.White,
                BorderStyle = BorderStyle.None,
                CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal,
                ColumnHeadersBorderStyle = DataGridViewHeaderBorderStyle.Single
            };
            dgvDetalles.ColumnHeadersDefaultCellStyle = new DataGridViewCellStyle
            {
                BackColor = Color.FromArgb(236, 240, 241), // --table-header-bg: #ecf0f1
                ForeColor = Color.FromArgb(44, 62, 80), // --primary-color: #2c3e50
                Font = new Font("Inter", 10, FontStyle.Bold), // 0.75rem = ~10px
                Alignment = DataGridViewContentAlignment.MiddleLeft,
                Padding = new Padding(8)
            };
            dgvDetalles.RowsDefaultCellStyle = new DataGridViewCellStyle
            {
                BackColor = Color.White,
                SelectionBackColor = Color.FromArgb(248, 249, 250), // --table-row-alt: #f8f9fa
                SelectionForeColor = Color.FromArgb(51, 51, 51),
                Padding = new Padding(8)
            };
            dgvDetalles.AlternatingRowsDefaultCellStyle = new DataGridViewCellStyle
            {
                BackColor = Color.FromArgb(248, 249, 250) // --table-row-alt: #f8f9fa
            };
            detailsPanel.Controls.Add(dgvDetalles);

            // Initialize columns
            dgvDetalles.Columns.Add(new DataGridViewTextBoxColumn
            {
                Name = "Vuelo",
                HeaderText = "Vuelo",
                ReadOnly = true,
                FillWeight = 40
            });
            dgvDetalles.Columns.Add(new DataGridViewTextBoxColumn
            {
                Name = "Cantidad",
                HeaderText = "Cantidad",
                ReadOnly = true,
                FillWeight = 20
            });
            dgvDetalles.Columns.Add(new DataGridViewTextBoxColumn
            {
                Name = "ValorUnitario",
                HeaderText = "Valor Unitario",
                ReadOnly = true,
                FillWeight = 20
            });
            dgvDetalles.Columns.Add(new DataGridViewTextBoxColumn
            {
                Name = "Total",
                HeaderText = "Total",
                ReadOnly = true,
                FillWeight = 20
            });

            // Summary and Footer
            var summaryFooterPanel = new Panel { Dock = DockStyle.Fill };
            mainLayout.Controls.Add(summaryFooterPanel, 0, 4);

            summaryPanel = new Panel
            {
                Size = new Size(250, 80),
                Location = new Point(summaryFooterPanel.Width - 250, 0)
            };
            summaryPanel.Controls.Add(new Label { Text = "Subtotal:", Location = new Point(0, 0), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular) });
            summaryPanel.Controls.Add(new Label { Text = "", Location = new Point(130, 0), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular), TextAlign = ContentAlignment.MiddleRight });
            summaryPanel.Controls.Add(new Label { Text = "Descuento:", Location = new Point(0, 20), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular) });
            summaryPanel.Controls.Add(new Label { Text = "", Location = new Point(130, 20), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular), TextAlign = ContentAlignment.MiddleRight });
            summaryPanel.Controls.Add(new Label { Text = "IVA (15%):", Location = new Point(0, 40), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular) });
            summaryPanel.Controls.Add(new Label { Text = "", Location = new Point(130, 40), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Regular), TextAlign = ContentAlignment.MiddleRight });
            summaryPanel.Controls.Add(new Label { Text = "Total:", Location = new Point(0, 60), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Bold), ForeColor = Color.FromArgb(44, 62, 80) });
            summaryPanel.Controls.Add(new Label { Text = "", Location = new Point(130, 60), Size = new Size(120, 20), Font = new Font("Inter", 11, FontStyle.Bold), ForeColor = Color.FromArgb(44, 62, 80), TextAlign = ContentAlignment.MiddleRight });
            summaryFooterPanel.Controls.Add(summaryPanel);

            var footerPanel = new Panel
            {
                Location = new Point(0, 90),
                Size = new Size(summaryFooterPanel.Width, 60)
            };
            footerPanel.Paint += (s, e) =>
            {
                using (var pen = new Pen(Color.FromArgb(224, 224, 224), 1))
                {
                    e.Graphics.DrawLine(pen, 0, 0, footerPanel.Width, 0);
                }
            };
            footerPanel.Controls.Add(new Label
            {
                Text = "Términos y Condiciones",
                Font = new Font("Inter", 11, FontStyle.Bold), // 0.9rem = ~11px
                ForeColor = Color.FromArgb(44, 62, 80),
                Location = new Point(0, 10),
                AutoSize = true
            });
            footerPanel.Controls.Add(new Label
            {
                Text = "Gracias por su compra. El pago debe realizarse dentro de los 30 días posteriores a la fecha de emisión. Para consultas, contáctenos en contacto@viajecitossa.com.\nLos boletos no son reembolsables una vez emitidos, salvo en casos excepcionales aprobados por Viajecitos SA.",
                Font = new Font("Inter", 9, FontStyle.Regular), // 0.7rem = ~9px
                ForeColor = Color.FromArgb(51, 51, 51),
                Location = new Point(0, 30),
                Size = new Size(footerPanel.Width, 30),
                AutoSize = false
            });
            summaryFooterPanel.Controls.Add(footerPanel);

            // Actions
            var actionsPanel = new FlowLayoutPanel
            {
                Dock = DockStyle.Fill,
                FlowDirection = FlowDirection.LeftToRight,
                WrapContents = false,
                Padding = new Padding(0, 10, 0, 0),
                AutoSize = false,
                BackColor = Color.FromArgb(255, 255, 255)
            };
            mainLayout.Controls.Add(actionsPanel, 0, 5);

            btnImprimir = new Button
            {
                Text = "Imprimir Factura",
                Size = new Size(130, 35),
                BackColor = Color.FromArgb(52, 152, 219), // --secondary-color: #3498db
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                Cursor = Cursors.Hand,
                Margin = new Padding(24, 0, 24, 0) // gap: 1.5rem
            };
            btnImprimir.FlatAppearance.BorderSize = 0;
            btnImprimir.Click += BtnImprimir_Click;
            actionsPanel.Controls.Add(btnImprimir);

            btnInicio = new Button
            {
                Text = "Inicio",
                Size = new Size(120, 35),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(52, 152, 219), // --secondary-color: #3498db
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                Cursor = Cursors.Hand,
                Margin = new Padding(0)
            };
            btnInicio.FlatAppearance.BorderSize = 1;
            btnInicio.FlatAppearance.BorderColor = Color.FromArgb(52, 152, 219);
            btnInicio.Click += BtnInicio_Click;
            actionsPanel.Controls.Add(btnInicio);

            // Handle form resize
            this.Resize += (s, e) =>
            {
                leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);
                mainLayout.Location = new Point(leftMargin, 0);
                mainLayout.Size = new Size(contentWidth, this.ClientSize.Height);
                (headerPanel.Controls[1] as Label).Location = new Point(headerPanel.Width / 2 - 50, 10);
                (headerPanel.Controls[2] as Panel).Location = new Point(headerPanel.Width - 100, 10);
                summaryPanel.Location = new Point((summaryPanel.Parent as Panel).Width - 250, 0);
                (summaryFooterPanel.Controls[1] as Panel).Size = new Size((summaryPanel.Parent as Panel).Width, 60);
                dgvDetalles.Size = new Size(detailsPanel.Width, detailsPanel.Height - 30);
            };
        }

        private void MostrarDatosFactura()
        {
            if (_factura == null || string.IsNullOrEmpty(_factura.NumeroFactura))
            {
                lblError.Text = "No se encontraron detalles de la factura.";
                lblError.Visible = true;
                dgvDetalles.Visible = false;
                var noDetailsLabel = new Label
                {
                    Text = "No hay detalles para esta factura.",
                    Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                    ForeColor = Color.FromArgb(51, 51, 51),
                    BackColor = Color.FromArgb(248, 249, 250), // --table-row-alt: #f8f9fa
                    Location = new Point(0, 30),
                    Size = new Size(dgvDetalles.Width, 40),
                    TextAlign = ContentAlignment.MiddleCenter,
                    Padding = new Padding(8)
                };
                (mainLayout.Controls[3] as Panel).Controls.Add(noDetailsLabel);
                return;
            }

            // Header
            (headerPanel.Controls[1] as Label).Text = $"FACTURA\nNúmero: {_factura.NumeroFactura}";

            // Invoice info
            var infoLabels = (mainLayout.Controls[2] as TableLayoutPanel).Controls.OfType<Label>().ToArray();
            infoLabels[1].Text = _factura.Cliente?.Nombre ?? "N/A";
            infoLabels[3].Text = _factura.FechaEmision.ToString("yyyy-MM-dd HH:mm:ss");
            infoLabels[5].Text = _factura.Cliente?.DocumentoIdentidad ?? "N/A";
            infoLabels[7].Text = _factura.FechaEmision.AddDays(30).ToString("yyyy-MM-dd");
            infoLabels[9].Text = _factura.Cliente?.Email ?? "N/A";
            infoLabels[11].Text = _factura.IdMetodoPago switch
            {
                1 => "Tarjeta de Crédito",
                2 => "Efectivo",
                3 => "Tarjeta de Débito",
                _ => "Desconocido"
            };

            // Detalles
            var detailsPanel = mainLayout.Controls[3] as Panel;
            if (_factura.DetallesFactura != null && _factura.DetallesFactura.Any())
            {
                dgvDetalles.Visible = true;
                dgvDetalles.Rows.Clear();
                foreach (var detalle in _factura.DetallesFactura)
                {
                    var vueloInfo = detalle.Vuelo != null ? $"{detalle.Vuelo.CiudadOrigen} a {detalle.Vuelo.CiudadDestino} ({detalle.Vuelo.HoraSalida:yyyy-MM-dd HH:mm})" : "N/A";
                    dgvDetalles.Rows.Add(vueloInfo, detalle.Cantidad, detalle.ValorUnitario.ToString("C"), detalle.Total.ToString("C"));
                }
                // Ensure DataGridView is visible and properly sized
                dgvDetalles.Size = new Size(detailsPanel.Width, detailsPanel.Height - 30);
                // Remove any existing "no details" label
                var existingLabel = detailsPanel.Controls.OfType<Label>().FirstOrDefault(l => l.Text == "No hay detalles para esta factura.");
                if (existingLabel != null)
                {
                    detailsPanel.Controls.Remove(existingLabel);
                }
            }
            else
            {
                dgvDetalles.Visible = false;
                var noDetailsLabel = new Label
                {
                    Text = "No hay detalles para esta factura.",
                    Font = new Font("Inter", 11, FontStyle.Regular), // 0.85rem = ~11px
                    ForeColor = Color.FromArgb(51, 51, 51),
                    BackColor = Color.FromArgb(248, 249, 250), // --table-row-alt: #f8f9fa
                    Location = new Point(0, 30),
                    Size = new Size(dgvDetalles.Width, 40),
                    TextAlign = ContentAlignment.MiddleCenter,
                    Padding = new Padding(8)
                };
                detailsPanel.Controls.Add(noDetailsLabel);
            }

            // Summary
            var summaryLabels = summaryPanel.Controls.OfType<Label>().ToArray();
            summaryLabels[1].Text = _factura.Subtotal.ToString("C");
            summaryLabels[3].Text = _factura.Descuento.ToString("C");
            summaryLabels[5].Text = _factura.Iva.ToString("C");
            summaryLabels[7].Text = _factura.Total.ToString("C");
        }

        private void BtnImprimir_Click(object sender, EventArgs e)
        {
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