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
            this.Size = new Size(650, 350);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(250, 250, 250);

            // Title
            lblTitle = new Label
            {
                Text = "Buscar Vuelos",
                Font = new Font("Segoe UI", 20, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138),
                Location = new Point(30, 20),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 68),
                Location = new Point(30, 60),
                Size = new Size(580, 30),
                AutoSize = false,
                Visible = false,
                TextAlign = ContentAlignment.MiddleCenter,
                Font = new Font("Segoe UI", 10, FontStyle.Regular)
            };
            this.Controls.Add(lblError);

            // Origen Label and TextBox
            var lblOrigen = new Label
            {
                Text = "Origen",
                Location = new Point(30, 100),
                AutoSize = true,
                Font = new Font("Segoe UI", 10, FontStyle.Regular),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblOrigen);

            txtCiudadOrigen = new TextBox
            {
                Location = new Point(30, 125),
                Width = 220,
                Font = new Font("Segoe UI", 12),
                PlaceholderText = "Ej. Quito"
            };
            this.Controls.Add(txtCiudadOrigen);

            // Destino Label and TextBox
            var lblDestino = new Label
            {
                Text = "Destino",
                Location = new Point(280, 100),
                AutoSize = true,
                Font = new Font("Segoe UI", 10, FontStyle.Regular),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblDestino);

            txtCiudadDestino = new TextBox
            {
                Location = new Point(280, 125),
                Width = 220,
                Font = new Font("Segoe UI", 12),
                PlaceholderText = "Ej. Guayaquil"
            };
            this.Controls.Add(txtCiudadDestino);

            // Fecha Label and DateTimePicker
            var lblFecha = new Label
            {
                Text = "Fecha",
                Location = new Point(30, 175),
                AutoSize = true,
                Font = new Font("Segoe UI", 10, FontStyle.Regular),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblFecha);

            dtpFecha = new DateTimePicker
            {
                Location = new Point(30, 200),
                Width = 220,
                Format = DateTimePickerFormat.Short,
                MinDate = DateTime.Today,
                Font = new Font("Segoe UI", 12)
            };
            this.Controls.Add(dtpFecha);

            // Buscar Button
            btnBuscar = new Button
            {
                Text = "Buscar",
                Location = new Point(280, 195),
                Size = new Size(220, 40),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Segoe UI", 14, FontStyle.Bold),
                Cursor = Cursors.Hand
            };
            btnBuscar.FlatAppearance.BorderSize = 0;
            btnBuscar.Click += async (s, e) => await BuscarVuelosAsync();
            btnBuscar.MouseEnter += (s, e) => btnBuscar.BackColor = Color.FromArgb(30, 58, 138);
            btnBuscar.MouseLeave += (s, e) => btnBuscar.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnBuscar);

            // Volver Button
            btnVolver = new Button
            {
                Text = "Volver",
                Location = new Point(30, 260),
                Size = new Size(470, 40),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(220, 38, 38),
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Segoe UI", 14, FontStyle.Bold),
                Cursor = Cursors.Hand
            };
            btnVolver.FlatAppearance.BorderSize = 1;
            btnVolver.FlatAppearance.BorderColor = Color.FromArgb(220, 38, 38);
            btnVolver.Click += (s, e) => VolverAlMain();
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

        private async Task BuscarVuelosAsync()
        {
            lblError.Visible = false;

            // Validaciones simples replicando cliente web "required"
            if (string.IsNullOrWhiteSpace(txtCiudadOrigen.Text))
            {
                MostrarError("La ciudad de origen es requerida.");
                return;
            }

            if (string.IsNullOrWhiteSpace(txtCiudadDestino.Text))
            {
                MostrarError("La ciudad de destino es requerida.");
                return;
            }

            if (dtpFecha.Value.Date < DateTime.Today)
            {
                MostrarError("La fecha es requerida y debe ser válida.");
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

                // Aquí puedes abrir otro formulario para mostrar resultados, por ejemplo
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
