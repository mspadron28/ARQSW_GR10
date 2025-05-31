using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class LoginForm : Form
    {
        private readonly ViajecitosController _controlador;

        public LoginForm()
        {
            _controlador = new ViajecitosController();
            InitializeComponent();
            // Configurar eventos
            btnLogin.Click += async (s, e) => await Login();
            btnRegister.Click += (s, e) => OpenRegisterForm();
            btnBack.Click += (s, e) => OpenMainForm();
            // Efectos de hover
            btnLogin.MouseEnter += (s, e) => btnLogin.BackColor = Color.LightBlue;
            btnLogin.MouseLeave += (s, e) => btnLogin.BackColor = Color.FromArgb(191, 219, 254);
            btnRegister.MouseEnter += (s, e) => btnRegister.ForeColor = Color.DarkBlue;
            btnRegister.MouseLeave += (s, e) => btnRegister.ForeColor = Color.FromArgb(59, 130, 246);
            btnBack.MouseEnter += (s, e) => btnBack.ForeColor = Color.DarkBlue;
            btnBack.MouseLeave += (s, e) => btnBack.ForeColor = Color.FromArgb(59, 130, 246);
        }

        private async Task Login()
        {
            try
            {
                if (await _controlador.IniciarSesion(txtUsuario.Text, txtClave.Text))
                {
                    MessageBox.Show("Inicio de sesión exitoso.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    OpenMainForm();
                }
                else
                {
                    lblError.Text = "Usuario o contraseña incorrectos.";
                }
            }
            catch (Exception ex)
            {
                lblError.Text = $"Error: {ex.Message}";
            }
        }

        private void OpenRegisterForm()
        {
            new RegisterForm().Show();
            Hide();
        }

        private void OpenMainForm()
        {
            new MainForm(_controlador).Show();
            Hide();
        }
    }
}