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
            this.Text = "Vuelos Disponibles - Viajecitos SA";
            this.Size = new Size(850, 500);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.White;

            lblTitle = new Label
            {
                Text = "Vuelos Disponibles",
                Font = new Font("Segoe UI", 20, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138),
                Location = new Point(20, 15),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            btnNuevaBusqueda = new Button
            {
                Text = "Nueva Búsqueda",
                Location = new Point(650, 15),
                Size = new Size(160, 35),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnNuevaBusqueda.FlatAppearance.BorderSize = 0;
            btnNuevaBusqueda.Click += BtnNuevaBusqueda_Click;
            btnNuevaBusqueda.MouseEnter += (s, e) => btnNuevaBusqueda.BackColor = Color.FromArgb(30, 58, 138);
            btnNuevaBusqueda.MouseLeave += (s, e) => btnNuevaBusqueda.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnNuevaBusqueda);

            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 68),
                Location = new Point(20, 60),
                Size = new Size(780, 25),
                Visible = false,
                Font = new Font("Segoe UI", 10, FontStyle.Regular),
                TextAlign = ContentAlignment.MiddleCenter
            };
            this.Controls.Add(lblError);

            pnlHighlight = new Panel
            {
                Location = new Point(20, 90),
                Size = new Size(790, 70),
                BackColor = Color.FromArgb(239, 246, 255),
                BorderStyle = BorderStyle.FixedSingle,
                Visible = false
            };
            this.Controls.Add(pnlHighlight);

            lblHighlightTitle = new Label
            {
                Text = "Vuelo con Mayor Costo",
                Font = new Font("Segoe UI", 12, FontStyle.Bold),
                ForeColor = Color.FromArgb(59, 130, 246),
                Location = new Point(10, 10),
                AutoSize = true
            };
            pnlHighlight.Controls.Add(lblHighlightTitle);

            lblHighlightInfo = new Label
            {
                Font = new Font("Segoe UI", 10, FontStyle.Regular),
                Location = new Point(10, 35),
                AutoSize = true
            };
            pnlHighlight.Controls.Add(lblHighlightInfo);

            dgvVuelos = new DataGridView
            {
                Location = new Point(20, 170),
                Size = new Size(790, 220),
                ReadOnly = true,
                AllowUserToAddRows = false,
                AllowUserToDeleteRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
                BackgroundColor = Color.White,
                BorderStyle = BorderStyle.FixedSingle
            };
            this.Controls.Add(dgvVuelos);

            btnComprar = new Button
            {
                Text = "Comprar",
                Location = new Point(550, 410),
                Size = new Size(120, 40),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnComprar.FlatAppearance.BorderSize = 0;
            btnComprar.Click += BtnComprar_Click;
            btnComprar.MouseEnter += (s, e) => btnComprar.BackColor = Color.FromArgb(30, 58, 138);
            btnComprar.MouseLeave += (s, e) => btnComprar.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnComprar);

            btnVolver = new Button
            {
                Text = "Volver",
                Location = new Point(680, 410),
                Size = new Size(130, 40),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(220, 38, 38),
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnVolver.FlatAppearance.BorderSize = 1;
            btnVolver.FlatAppearance.BorderColor = Color.FromArgb(220, 38, 38);
            btnVolver.Click += BtnVolver_Click;
            btnVolver.MouseEnter += (s, e) =>
            {
                btnVolver.BackColor = Color.FromArgb(220, 38, 38);
                btnVolver.ForeColor = Color.White;
            };
            btnVolver.MouseLeave += (s, e) =>
            {
                btnVolver.BackColor = Color.White;
                btnVolver.ForeColor = Color.FromArgb(220, 38, 38);
            };
            this.Controls.Add(btnVolver);
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
            // Abrir formulario de compra con todos los vuelos actuales para seleccionar cantidad y cliente
        
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
