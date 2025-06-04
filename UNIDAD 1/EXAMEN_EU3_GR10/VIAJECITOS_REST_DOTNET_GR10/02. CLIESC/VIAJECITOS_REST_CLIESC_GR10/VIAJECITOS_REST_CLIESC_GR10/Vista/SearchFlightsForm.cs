using System;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;
using VIAJECITOS_REST_CLIESC_GR10.Vista;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class SearchFlightsForm : Form
    {
        private readonly ViajecitosController _controlador;

        private Label lblTitle;
        private Label lblError;
        private Label lblOrigen;
        private Label lblDestino;
        private Label lblFecha;

        private TextBox txtCiudadOrigen;
        private TextBox txtCiudadDestino;
        private DateTimePicker dtpFecha;

        private Button btnBuscar;
        private Button btnVolver;

        public SearchFlightsForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
        }

        private void InitializeComponent()
        {
            // Form properties
            this.Text = "Buscar Vuelos - Viajecitos SA";
            this.Size = new Size(650, 350); // Initial size, but will be maximized
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
            int contentWidth = 600; // Max-width from web
            int leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

            // Title
            lblTitle = new Label
            {
                Text = "Buscar Vuelos",
                Font = new Font("Inter", 20, FontStyle.Bold), // 1.25rem = ~20px
                ForeColor = Color.FromArgb(17, 24, 39), // --text-color: #111827
                Location = new Point(leftMargin + 30, 20 + 150),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 44), // --error-color: #ef4444
                Location = new Point(leftMargin + 30, 60 + 150),
                Size = new Size(580, 30),
                AutoSize = false,
                Visible = false,
                TextAlign = ContentAlignment.MiddleCenter,
                Font = new Font("Inter", 12, FontStyle.Regular) // 0.75rem = ~12px
            };
            this.Controls.Add(lblError);

            // Origen Label and TextBox
            lblOrigen = new Label
            {
                Text = "Origen",
                Location = new Point(leftMargin + 30, 100 + 150),
                AutoSize = true,
                Font = new Font("Inter", 12, FontStyle.Bold), // 0.75rem = ~12px
                ForeColor = Color.FromArgb(17, 24, 39) // --text-color: #111827
            };
            this.Controls.Add(lblOrigen);

            txtCiudadOrigen = new TextBox
            {
                Location = new Point(leftMargin + 30, 125 + 150),
                Width = 220,
                Font = new Font("Inter", 16, FontStyle.Regular), // 1rem = ~16px
                PlaceholderText = "Ej. Quito",
                BorderStyle = BorderStyle.FixedSingle,
                BackColor = Color.White,
                ForeColor = Color.Black
            };
            txtCiudadOrigen.TextChanged += (s, e) => ValidateInput(txtCiudadOrigen);
            this.Controls.Add(txtCiudadOrigen);

            // Destino Label and TextBox
            lblDestino = new Label
            {
                Text = "Destino",
                Location = new Point(leftMargin + 280, 100 + 150),
                AutoSize = true,
                Font = new Font("Inter", 12, FontStyle.Bold), // 0.75rem = ~12px
                ForeColor = Color.FromArgb(17, 24, 39) // --text-color: #111827
            };
            this.Controls.Add(lblDestino);

            txtCiudadDestino = new TextBox
            {
                Location = new Point(leftMargin + 280, 125 + 150),
                Width = 220,
                Font = new Font("Inter", 16, FontStyle.Regular), // 1rem = ~16px
                PlaceholderText = "Ej. Guayaquil",
                BorderStyle = BorderStyle.FixedSingle,
                BackColor = Color.White,
                ForeColor = Color.Black
            };
            txtCiudadDestino.TextChanged += (s, e) => ValidateInput(txtCiudadDestino);
            this.Controls.Add(txtCiudadDestino);

            // Fecha Label and DateTimePicker
            lblFecha = new Label
            {
                Text = "Fecha",
                Location = new Point(leftMargin + 30, 175 + 150),
                AutoSize = true,
                Font = new Font("Inter", 12, FontStyle.Bold), // 0.75rem = ~12px
                ForeColor = Color.FromArgb(17, 24, 39) // --text-color: #111827
            };
            this.Controls.Add(lblFecha);

            dtpFecha = new DateTimePicker
            {
                Location = new Point(leftMargin + 30, 200 + 150),
                Width = 220,
                Format = DateTimePickerFormat.Short,
                MinDate = DateTime.Today,
                Font = new Font("Inter", 16, FontStyle.Regular), // 1rem = ~16px
                CalendarFont = new Font("Inter", 12, FontStyle.Regular)
            };
            this.Controls.Add(dtpFecha);

            // Buscar Button
            btnBuscar = new Button
            {
                Text = "Buscar",
                Location = new Point(leftMargin + 280, 195 + 150),
                Size = new Size(220, 40),
                BackColor = Color.FromArgb(59, 130, 246), // --primary-color: #3b82f6
                ForeColor = Color.White, // --white: #ffffff
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 16, FontStyle.Bold), // 1rem = ~16px
                Cursor = Cursors.Hand
            };
            btnBuscar.FlatAppearance.BorderSize = 0;
            btnBuscar.Click += async (s, e) => await BuscarVuelosAsync();
            btnBuscar.MouseEnter += (s, e) => btnBuscar.BackColor = Color.FromArgb(37, 99, 235); // #2563eb on hover
            btnBuscar.MouseLeave += (s, e) => btnBuscar.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnBuscar);

            // Volver Button
            btnVolver = new Button
            {
                Text = "Volver",
                Location = new Point(leftMargin + 30, 260 + 150),
                Size = new Size(470, 40),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(59, 130, 246), // --primary-color: #3b82f6
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 16, FontStyle.Bold), // 1rem = ~16px
                Cursor = Cursors.Hand
            };
            btnVolver.FlatAppearance.BorderSize = 1;
            btnVolver.FlatAppearance.BorderColor = Color.FromArgb(59, 130, 246);
            btnVolver.Click += (s, e) => VolverAlMain();
            btnVolver.MouseEnter += (s, e) =>
            {
                btnVolver.BackColor = Color.FromArgb(59, 130, 246);
                btnVolver.ForeColor = Color.White;
            };
            btnVolver.MouseLeave += (s, e) =>
            {
                btnVolver.BackColor = Color.White;
                btnVolver.ForeColor = Color.FromArgb(59, 130, 246);
            };
            this.Controls.Add(btnVolver);

            // Handle form resize
            this.Resize += (s, e) =>
            {
                leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

                // Update positions
                headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
                subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
                lblTitle.Location = new Point(leftMargin + 30, lblTitle.Location.Y);
                lblError.Location = new Point(leftMargin + 30, lblError.Location.Y);
                lblOrigen.Location = new Point(leftMargin + 30, lblOrigen.Location.Y);
                txtCiudadOrigen.Location = new Point(leftMargin + 30, txtCiudadOrigen.Location.Y);
                lblDestino.Location = new Point(leftMargin + 280, lblDestino.Location.Y);
                txtCiudadDestino.Location = new Point(leftMargin + 280, txtCiudadDestino.Location.Y);
                lblFecha.Location = new Point(leftMargin + 30, lblFecha.Location.Y);
                dtpFecha.Location = new Point(leftMargin + 30, dtpFecha.Location.Y);
                btnBuscar.Location = new Point(leftMargin + 280, btnBuscar.Location.Y);
                btnVolver.Location = new Point(leftMargin + 30, btnVolver.Location.Y);
            };

            // Initial alignment of header text
            headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
            subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
        }

        private void ValidateInput(TextBox textBox)
        {
            if (string.IsNullOrWhiteSpace(textBox.Text))
            {
                textBox.BorderStyle = BorderStyle.FixedSingle;
                textBox.BackColor = Color.FromArgb(254, 242, 242); // Light red background for invalid
            }
            else
            {
                textBox.BorderStyle = BorderStyle.FixedSingle;
                textBox.BackColor = Color.White;
            }
        }

        private async Task BuscarVuelosAsync()
        {
            lblError.Visible = false;

            // Validations
            if (string.IsNullOrWhiteSpace(txtCiudadOrigen.Text))
            {
                MostrarError("La ciudad de origen es requerida.");
                txtCiudadOrigen.BackColor = Color.FromArgb(254, 242, 242);
                return;
            }

            if (string.IsNullOrWhiteSpace(txtCiudadDestino.Text))
            {
                MostrarError("La ciudad de destino es requerida.");
                txtCiudadDestino.BackColor = Color.FromArgb(254, 242, 242);
                return;
            }

            if (dtpFecha.Value.Date < DateTime.Today)
            {
                MostrarError("La fecha es requerida y debe be válida.");
                return;
            }

            try
            {
                var vuelos = await _controlador.BuscarVuelosAsync(txtCiudadOrigen.Text.Trim(), txtCiudadDestino.Text.Trim(), dtpFecha.Value.Date);
                if (vuelos.Count == 0)
                {
                    MostrarError("No se encontraron vuelos disponibles para esos datos.");
                    return;
                }

                var resultadosForm = new SearchResultsForm(_controlador, vuelos);
                resultadosForm.Show();
                this.Hide();
            }
            catch (Exception ex)
            {
                MostrarError($"Error al buscar vuelos: {ex.Message}");
            }
        }

        private void MostrarError(string mensaje)
        {
            lblError.Text = mensaje;
            lblError.Visible = true;
        }

        private void VolverAlMain()
        {
            var mainForm = new MainForm(_controlador);
            mainForm.Show();
            this.Hide();
        }
    }
}