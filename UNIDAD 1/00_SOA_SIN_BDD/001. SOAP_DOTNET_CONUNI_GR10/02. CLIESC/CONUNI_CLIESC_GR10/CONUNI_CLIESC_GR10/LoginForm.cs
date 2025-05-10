using System;
using System.Windows.Forms;
using ConversionServiceReference;

namespace CONUNI_CLIESC_GR10
{
    public partial class LoginForm : Form
    {
        private ConversionServiceClient client;

        public LoginForm()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized; // Maximizar el formulario
            this.FormBorderStyle = FormBorderStyle.FixedSingle; // Evitar redimensionar manualmente
            this.MaximizeBox = true; // Mantener botón de maximizar
            this.MinimumSize = new Size(600, 600); // Tamaño mínimo
            this.StartPosition = FormStartPosition.CenterScreen; // Centrar al abrir
            client = new ConversionServiceClient();
        }

        private void BtnIniciarSesión_Click(object sender, EventArgs e)
        {
            string user = txtUsuario.Text.Trim();
            string password = txtContraseña.Text;

            if (string.IsNullOrWhiteSpace(user) || string.IsNullOrWhiteSpace(password)) // Corrección a IsNullOrWhiteSpace
            {
                MessageBox.Show("Por favor, complete todos los campos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            bool success = client.LoginAsync(user, password).Result; // Uso de .Result para sincronización
            if (success)
            {
                this.Hide();
                using (var conversionForm = new ConversionForm()) // Uso de using para manejo de recursos
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

        private void LoginForm_Load(object sender, EventArgs e)
        {
            // Configuraciones adicionales pueden ir aquí si es necesario
        }
    }
}