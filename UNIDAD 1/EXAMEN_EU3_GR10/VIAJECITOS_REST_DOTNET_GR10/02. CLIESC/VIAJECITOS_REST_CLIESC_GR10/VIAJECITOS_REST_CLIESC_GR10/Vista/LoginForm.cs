using System;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class LoginForm : Form
    {
        private readonly ViajecitosController _controlador;

        private Label lblTitle;
        private Label lblError;
        private Label lblUsuario;
        private Label lblClave;
        private TextBox txtUsuario;
        private TextBox txtClave;
        private Button btnLogin;
        private LinkLabel linkVolver;

        public LoginForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
        }

        private void InitializeComponent()
        {
            // Form properties
            this.Text = "Iniciar Sesión - Viajecitos SA";
            this.Size = new Size(450, 320);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.White;

            // Title Label
            lblTitle = new Label
            {
                Text = "Iniciar Sesión",
                Font = new Font("Segoe UI", 20, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138),
                Location = new Point(130, 20),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(220, 38, 38),
                Location = new Point(40, 60),
                AutoSize = true,
                MaximumSize = new Size(370, 0),
                Visible = false
            };
            this.Controls.Add(lblError);

            // Usuario Label
            lblUsuario = new Label
            {
                Text = "Usuario",
                Location = new Point(40, 90),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblUsuario);

            // Usuario TextBox
            txtUsuario = new TextBox
            {
                Location = new Point(40, 110),
                Width = 370,
                Font = new Font("Segoe UI", 10)
            };
            this.Controls.Add(txtUsuario);

            // Clave Label
            lblClave = new Label
            {
                Text = "Contraseña",
                Location = new Point(40, 150),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblClave);

            // Clave TextBox
            txtClave = new TextBox
            {
                Location = new Point(40, 170),
                Width = 370,
                Font = new Font("Segoe UI", 10),
                UseSystemPasswordChar = true
            };
            this.Controls.Add(txtClave);

            // Login Button
            btnLogin = new Button
            {
                Text = "Iniciar Sesión",
                Location = new Point(40, 210),
                Width = 370,
                Height = 40,
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Segoe UI", 12, FontStyle.Bold),
                Cursor = Cursors.Hand
            };
            btnLogin.FlatAppearance.BorderSize = 0;
            btnLogin.Click += async (s, e) => await LoginAsync();
            btnLogin.MouseEnter += (s, e) => btnLogin.BackColor = Color.FromArgb(30, 58, 138);
            btnLogin.MouseLeave += (s, e) => btnLogin.BackColor = Color.FromArgb(59, 130, 246);
            this.Controls.Add(btnLogin);

            // LinkLabel Volver
            linkVolver = new LinkLabel
            {
                Text = "Volver",
                Location = new Point(40, 260),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                LinkColor = Color.FromArgb(59, 130, 246),
                Cursor = Cursors.Hand
            };
            linkVolver.Click += (s, e) => OpenMainForm();
            this.Controls.Add(linkVolver);
        }

        private async Task LoginAsync()
        {
            lblError.Visible = false;

            if (string.IsNullOrWhiteSpace(txtUsuario.Text))
            {
                ShowError("El usuario es requerido.");
                return;
            }
            if (string.IsNullOrWhiteSpace(txtClave.Text))
            {
                ShowError("La contraseña es requerida.");
                return;
            }

            try
            {
                bool success = await _controlador.IniciarSesionAsync(txtUsuario.Text.Trim(), txtClave.Text.Trim());
                if (success)
                {
                    MessageBox.Show("Inicio de sesión exitoso.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    OpenMainForm();
                }
                else
                {
                    ShowError("Usuario o contraseña incorrectos.");
                }
            }
            catch (Exception ex)
            {
                ShowError($"Error: {ex.Message}");
            }
        }

        private void ShowError(string message)
        {
            lblError.Text = message;
            lblError.Visible = true;
        }

        private void OpenMainForm()
        {
            var mainForm = new MainForm(_controlador);
            mainForm.Show();
            this.Hide();
        }
    }
}
