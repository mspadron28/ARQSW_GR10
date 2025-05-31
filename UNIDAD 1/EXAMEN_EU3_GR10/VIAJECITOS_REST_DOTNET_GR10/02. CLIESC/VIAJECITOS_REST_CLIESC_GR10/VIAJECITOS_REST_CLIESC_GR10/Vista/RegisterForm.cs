using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class RegisterForm : Form
    {
        private readonly ViajecitosController _controlador;

        public RegisterForm()
        {
            _controlador = new ViajecitosController();
            InitializeComponent();
            // Configurar eventos
            btnRegister.Click += async (s, e) => await Register();
            btnLogin.Click += (s, e) => OpenLoginForm();
            btnBack.Click += (s, e) => OpenMainForm();
            // Efectos de hover
            btnRegister.MouseEnter += (s, e) => btnRegister.BackColor = Color.LightBlue;
            btnRegister.MouseLeave += (s, e) => btnRegister.BackColor = Color.FromArgb(191, 219, 254);
            btnLogin.MouseEnter += (s, e) => btnLogin.ForeColor = Color.DarkBlue;
            btnLogin.MouseLeave += (s, e) => btnLogin.ForeColor = Color.FromArgb(59, 130, 246);
            btnBack.MouseEnter += (s, e) => btnBack.ForeColor = Color.DarkBlue;
            btnBack.MouseLeave += (s, e) => btnBack.ForeColor = Color.FromArgb(59, 130, 246);
        }

        private async Task Register()
        {
            try
            {
                int idCliente = await _controlador.RegistrarCliente(txtNombre.Text, txtEmail.Text, txtDocumento.Text);
                if (await _controlador.RegistrarUsuario(idCliente, txtUsuario.Text, txtClave.Text))
                {
                    MessageBox.Show("Registro exitoso. Por favor, inicie sesión.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    OpenLoginForm();
                }
                else
                {
                    lblError.Text = "Error al registrar el usuario.";
                }
            }
            catch (Exception ex)
            {
                lblError.Text = $"Error: {ex.Message}";
            }
        }

        private void OpenLoginForm() { new LoginForm().Show(); Hide(); }
        private void OpenMainForm() { new MainForm(_controlador).Show(); Hide(); }
    }
}