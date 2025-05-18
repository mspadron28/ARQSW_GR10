using System;
using System.Net.Http;
using System.Text.Json;
using System.Windows.Forms;

namespace CONUNI_CLIESC_GR10
{
    public partial class LoginForm : Form
    {
        private static readonly HttpClient client = new HttpClient(); private const string BaseUrl = "http://localhost:5000/api/Conversion/";
        public LoginForm()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized; // Maximizar el formulario
            this.FormBorderStyle = FormBorderStyle.FixedSingle; // Evitar redimensionar manualmente
            this.MaximizeBox = true; // Mantener botón de maximizar
            this.MinimumSize = new Size(600, 600); // Tamaño mínimo
            this.StartPosition = FormStartPosition.CenterScreen; // Centrar al abrir
        }

        private async void BtnIniciarSesión_Click(object sender, EventArgs e)
        {
            string user = txtUsuario.Text.Trim();
            string password = txtContraseña.Text;

            if (string.IsNullOrWhiteSpace(user) || string.IsNullOrWhiteSpace(password))
            {
                MessageBox.Show("Por favor, complete todos los campos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                string url = $"{BaseUrl}login?usuario={Uri.EscapeDataString(user)}&contraseña={Uri.EscapeDataString(password)}";
                HttpResponseMessage response = await client.GetAsync(url);
                response.EnsureSuccessStatusCode();
                string responseBody = await response.Content.ReadAsStringAsync();
                bool success = JsonSerializer.Deserialize<bool>(responseBody);

                if (success)
                {
                    this.Hide();
                    using (var conversionForm = new ConversionForm())
                    {
                        conversionForm.ShowDialog();
                    }
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Usuario o contraseña incorrectos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (HttpRequestException ex)
            {
                MessageBox.Show($"Error al intentar iniciar sesión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            // Configuraciones adicionales pueden ir aquí si es necesario
        }

        private void txtUsuario_TextChanged(object sender, EventArgs e)
        {

        }
    }
}