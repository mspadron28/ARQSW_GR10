using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class SearchResultsForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly List<Vuelo> _vuelos;

        private Label lblTitle;
        private Button btnNuevaBusqueda;
        private Button btnComprar;
        private Button btnVolver;
        private Label lblError;
        private Panel pnlHighlight;
        private Label lblHighlightTitle;
        private Label lblHighlightInfo;

        private DataGridView dgvVuelos;

        public SearchResultsForm(ViajecitosController controlador, List<Vuelo> vuelos)
        {
            _controlador = controlador;
            _vuelos = vuelos ?? new List<Vuelo>();

            InitializeComponent();
            CargarDatos();
        }

        private void InitializeComponent()
        {
            // Form properties
            this.Text = "Vuelos Disponibles - Viajecitos SA";
            this.Size = new Size(850, 500); // Initial size, but will be maximized
            this.WindowState = FormWindowState.Maximized; // Maximize on startup
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(250, 250, 250); // --background-color: #fafafa

            // Header panel
            var headerPanel = new Panel
            {
                Dock = DockStyle.Top,
                Height = 150,
                BackColor = Color.FromArgb(163, 191, 250) // Consistent with other forms
            };
            this.Controls.Add(headerPanel);

            var headerText = new Label
            {
                Text = "Viajecitos SA",
                Font = new Font("Inter", 26, FontStyle.Bold),
                ForeColor = Color.White,
                AutoSize = true
            };
            headerPanel.Controls.Add(headerText);

            var subHeaderText = new Label
            {
                Text = "Encuentra y compra tus boletos de avión de forma fácil y segura",
                Font = new Font("Inter", 12, FontStyle.Regular),
                ForeColor = Color.White,
                AutoSize = true
            };
            headerPanel.Controls.Add(subHeaderText);

            // Center content dynamically
            int contentWidth = 800; // Max-width from web
            int leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

            // Title
            lblTitle = new Label
            {
                Text = "Vuelos Disponibles",
                Font = new Font("Inter", 20, FontStyle.Bold), // 1.25rem = ~20px
                ForeColor = Color.FromArgb(17, 24, 39), // --text-color: #111827
                Location = new Point(leftMargin + 20, 15 + 150),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            // Nueva Búsqueda Button
            btnNuevaBusqueda = new Button
            {
                Text = "Nueva Búsqueda",
                Location = new Point(leftMargin + 650, 15 + 150),
                Size = new Size(130, 35), // Adjusted to fit text and match web style
                BackColor = Color.FromArgb(59, 130, 246), // --primary-color: #3b82f6
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                Cursor = Cursors.Hand
            };
            btnNuevaBusqueda.FlatAppearance.BorderSize = 0;
            btnNuevaBusqueda.Click += BtnNuevaBusqueda_Click;
            btnNuevaBusqueda.MouseEnter += (s, e) => btnNuevaBusqueda.BackColor = Color.FromArgb(37, 99, 235); // #2563eb on hover
            btnNuevaBusqueda.MouseLeave += (s, e) => btnNuevaBusqueda.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnNuevaBusqueda);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 44), // --error-color: #ef4444
                Location = new Point(leftMargin + 20, 60 + 150),
                Size = new Size(780, 25),
                Visible = false,
                Font = new Font("Inter", 12, FontStyle.Regular), // 0.75rem = ~12px
                TextAlign = ContentAlignment.MiddleCenter
            };
            this.Controls.Add(lblError);

            // Highlight Panel
            pnlHighlight = new Panel
            {
                Location = new Point(leftMargin + 20, 90 + 150),
                Size = new Size(790, 70),
                BackColor = Color.FromArgb(239, 246, 255), // --highlight-bg: #eff6ff
                BorderStyle = BorderStyle.FixedSingle,
                Visible = false
            };
            this.Controls.Add(pnlHighlight);

            lblHighlightTitle = new Label
            {
                Text = "Vuelo con Mayor Costo",
                Font = new Font("Inter", 16, FontStyle.Bold), // 1rem = ~16px
                ForeColor = Color.FromArgb(59, 130, 246), // --primary-color: #3b82f6
                Location = new Point(10, 10),
                AutoSize = true
            };
            pnlHighlight.Controls.Add(lblHighlightTitle);

            lblHighlightInfo = new Label
            {
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                ForeColor = Color.FromArgb(17, 24, 39), // --text-color: #111827
                Location = new Point(10, 35),
                AutoSize = true
            };
            pnlHighlight.Controls.Add(lblHighlightInfo);

            // DataGridView
            dgvVuelos = new DataGridView
            {
                Location = new Point(leftMargin + 20, 170 + 150),
                Size = new Size(790, 220),
                ReadOnly = true,
                AllowUserToAddRows = false,
                AllowUserToDeleteRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells, // Changed to ensure text fits
                BackgroundColor = Color.White,
                BorderStyle = BorderStyle.FixedSingle,
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                ForeColor = Color.FromArgb(17, 24, 39), // --text-color: #111827
                GridColor = Color.FromArgb(229, 231, 235), // --border-color: #e5e7eb
                RowHeadersVisible = false,
                RowTemplate = { Height = 40 } // Increased row height to ensure text visibility
            };
            // Header style
            dgvVuelos.ColumnHeadersDefaultCellStyle = new DataGridViewCellStyle
            {
                BackColor = Color.White,
                ForeColor = Color.FromArgb(107, 114, 128), // #6b7280
                Font = new Font("Inter", 12, FontStyle.Bold), // 0.75rem = ~12px
                Alignment = DataGridViewContentAlignment.MiddleLeft,
                Padding = new Padding(5)
            };
            // Row style on hover
            dgvVuelos.RowsDefaultCellStyle = new DataGridViewCellStyle
            {
                BackColor = Color.White,
                SelectionBackColor = Color.FromArgb(249, 250, 251), // #f9fafb on hover
                SelectionForeColor = Color.FromArgb(17, 24, 39),
                Padding = new Padding(5)
            };
            this.Controls.Add(dgvVuelos);

            // Comprar Button
            btnComprar = new Button
            {
                Text = "Comprar",
                Location = new Point(leftMargin + 550, 410 + 150),
                Size = new Size(120, 40),
                BackColor = Color.FromArgb(59, 130, 246), // --primary-color: #3b82f6
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                Cursor = Cursors.Hand
            };
            btnComprar.FlatAppearance.BorderSize = 0;
            btnComprar.Click += BtnComprar_Click;
            btnComprar.MouseEnter += (s, e) => btnComprar.BackColor = Color.FromArgb(37, 99, 235); // #2563eb on hover
            btnComprar.MouseLeave += (s, e) => btnComprar.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnComprar);

            // Volver Button
            btnVolver = new Button
            {
                Text = "Volver",
                Location = new Point(leftMargin + 680, 410 + 150),
                Size = new Size(120, 40),
                BackColor = Color.FromArgb(107, 114, 128), // .action-button.secondary: #6b7280
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                Cursor = Cursors.Hand
            };
            btnVolver.FlatAppearance.BorderSize = 0;
            btnVolver.Click += BtnVolver_Click;
            btnVolver.MouseEnter += (s, e) => btnVolver.BackColor = Color.FromArgb(75, 85, 99); // #4b5563 on hover
            btnVolver.MouseLeave += (s, e) => btnVolver.BackColor = Color.FromArgb(107, 114, 128);
            this.Controls.Add(btnVolver);

            // Handle form resize
            this.Resize += (s, e) =>
            {
                leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

                // Update positions
                headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
                subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
                lblTitle.Location = new Point(leftMargin + 20, lblTitle.Location.Y);
                btnNuevaBusqueda.Location = new Point(leftMargin + 650, btnNuevaBusqueda.Location.Y);
                lblError.Location = new Point(leftMargin + 20, lblError.Location.Y);
                pnlHighlight.Location = new Point(leftMargin + 20, pnlHighlight.Location.Y);
                dgvVuelos.Location = new Point(leftMargin + 20, dgvVuelos.Location.Y);
                btnComprar.Location = new Point(leftMargin + 550, btnComprar.Location.Y);
                btnVolver.Location = new Point(leftMargin + 680, btnVolver.Location.Y);
            };

            // Initial alignment of header text
            headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
            subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
        }

        private void CargarDatos()
        {
            if (_vuelos == null || _vuelos.Count == 0)
            {
                lblError.Text = "No se encontraron vuelos.";
                lblError.Visible = true;
                dgvVuelos.Visible = false;
                pnlHighlight.Visible = false;
                btnComprar.Enabled = false;
                return;
            }

            lblError.Visible = false;
            dgvVuelos.Visible = true;
            btnComprar.Enabled = true;

            // Destacar vuelo con mayor costo
            var highest = _vuelos.OrderByDescending(v => v.Valor).First();
            pnlHighlight.Visible = true;
            lblHighlightInfo.Text =
                $"ID: {highest.IdVuelo} | Origen: {highest.CiudadOrigen} | Destino: {highest.CiudadDestino} | Valor: {highest.Valor:C} | Fecha: {highest.HoraSalida:yyyy-MM-dd} | Hora: {highest.HoraSalida:HH:mm}";

            // Mostrar otros vuelos en DataGridView excluyendo el destacado
            var otros = _vuelos.Where(v => v.IdVuelo != highest.IdVuelo).OrderByDescending(v => v.Valor).ToList();

            dgvVuelos.Columns.Clear();
            dgvVuelos.Rows.Clear();

            // Definir columnas
            dgvVuelos.Columns.Add("IdVuelo", "ID Vuelo");
            dgvVuelos.Columns.Add("CiudadOrigen", "Origen");
            dgvVuelos.Columns.Add("CiudadDestino", "Destino");
            dgvVuelos.Columns.Add("Valor", "Valor");
            dgvVuelos.Columns.Add("FechaSalida", "Fecha de Salida");
            dgvVuelos.Columns.Add("HoraSalida", "Hora de Salida");

            // Formatear columnas
            dgvVuelos.Columns["Valor"].DefaultCellStyle.Format = "C";
            dgvVuelos.Columns["FechaSalida"].DefaultCellStyle.Format = "yyyy-MM-dd";
            dgvVuelos.Columns["HoraSalida"].DefaultCellStyle.Format = "HH:mm";

            foreach (var vuelo in otros)
            {
                dgvVuelos.Rows.Add(
                    vuelo.IdVuelo,
                    vuelo.CiudadOrigen,
                    vuelo.CiudadDestino,
                    vuelo.Valor,
                    vuelo.HoraSalida.ToString("yyyy-MM-dd"),
                    vuelo.HoraSalida.ToString("HH:mm")
                );
            }
        }

        private void BtnNuevaBusqueda_Click(object sender, EventArgs e)
        {
            var searchForm = new SearchFlightsForm(_controlador);
            searchForm.Show();
            this.Hide();
        }

        private void BtnComprar_Click(object sender, EventArgs e)
        {
            var purchaseForm = new PurchaseForm(_controlador, _vuelos);
            purchaseForm.Show();
            this.Hide();
        }

        private void BtnVolver_Click(object sender, EventArgs e)
        {
            var mainForm = new MainForm(_controlador);
            mainForm.Show();
            this.Hide();
        }
    }
}