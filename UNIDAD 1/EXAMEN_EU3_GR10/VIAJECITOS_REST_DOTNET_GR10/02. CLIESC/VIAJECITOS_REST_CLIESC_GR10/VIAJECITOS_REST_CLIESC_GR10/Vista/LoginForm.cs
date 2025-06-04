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
            this.Size = new Size(600, 600); // Initial size, but will be maximized
            this.WindowState = FormWindowState.Maximized; // Maximize on startup
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.White;

            // Header (con un color y texto simulado similar a lo solicitado)
            var headerPanel = new Panel
            {
                Dock = DockStyle.Top,
                Height = 150,
                BackColor = Color.FromArgb(163, 191, 250) // Azul pastel claro (header)
            };
            this.Controls.Add(headerPanel);

            var headerText = new Label
            {
                Text = "Viajecitos SA",
                Font = new Font("Segoe UI", 26, FontStyle.Bold),
                ForeColor = Color.White,
                AutoSize = true
            };
            headerPanel.Controls.Add(headerText);

            var subHeaderText = new Label
            {
                Text = "Encuentra y compra tus boletos de avión de forma fácil y segura",
                Font = new Font("Segoe UI", 12, FontStyle.Regular),
                ForeColor = Color.White,
                AutoSize = true
            };
            headerPanel.Controls.Add(subHeaderText);

            // Center the form controls
            int formWidth = this.ClientSize.Width;
            int controlWidth = 500;
            int leftMargin = (formWidth - controlWidth) / 2; // Calculate left margin to center controls

            // Title Label (el texto "Iniciar Sesión")
            lblTitle = new Label
            {
                Text = "Iniciar Sesión",
                Font = new Font("Segoe UI", 20, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138),
                Location = new Point(leftMargin, 220),
                AutoSize = true
            };
            this.Controls.Add(lblTitle);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(220, 38, 38),
                Location = new Point(leftMargin, 260),
                AutoSize = true,
                MaximumSize = new Size(controlWidth, 0),
                Visible = false
            };
            this.Controls.Add(lblError);

            // Usuario Label
            lblUsuario = new Label
            {
                Text = "Usuario",
                Location = new Point(leftMargin, 290),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblUsuario);

            // Usuario TextBox
            txtUsuario = new TextBox
            {
                Location = new Point(leftMargin, 310),
                Width = controlWidth,
                Font = new Font("Segoe UI", 10)
            };
            this.Controls.Add(txtUsuario);

            // Clave Label
            lblClave = new Label
            {
                Text = "Contraseña",
                Location = new Point(leftMargin, 350),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                ForeColor = Color.FromArgb(31, 41, 55)
            };
            this.Controls.Add(lblClave);

            // Clave TextBox
            txtClave = new TextBox
            {
                Location = new Point(leftMargin, 370),
                Width = controlWidth,
                Font = new Font("Segoe UI", 10),
                UseSystemPasswordChar = true
            };
            this.Controls.Add(txtClave);

            // Login Button
            btnLogin = new Button
            {
                Text = "Iniciar Sesión",
                Location = new Point(leftMargin, 410),
                Width = controlWidth,
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
                Location = new Point(leftMargin, 460),
                AutoSize = true,
                Font = new Font("Segoe UI", 10),
                LinkColor = Color.FromArgb(59, 130, 246),
                Cursor = Cursors.Hand
            };
            linkVolver.Click += (s, e) => OpenMainForm();
            this.Controls.Add(linkVolver);

            // Handle form resize to keep header text aligned to the right
            this.Resize += (s, e) =>
            {
                formWidth = this.ClientSize.Width;
                leftMargin = (formWidth - controlWidth) / 2;

                // Update positions of form controls
                lblTitle.Location = new Point(leftMargin, lblTitle.Location.Y);
                lblError.Location = new Point(leftMargin, lblError.Location.Y);
                lblUsuario.Location = new Point(leftMargin, lblUsuario.Location.Y);
                txtUsuario.Location = new Point(leftMargin, txtUsuario.Location.Y);
                lblClave.Location = new Point(leftMargin, lblClave.Location.Y);
                txtClave.Location = new Point(leftMargin, txtClave.Location.Y);
                btnLogin.Location = new Point(leftMargin, btnLogin.Location.Y);
                linkVolver.Location = new Point(leftMargin, linkVolver.Location.Y);

                // Align header text to the right
                headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
                subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
            };

            // Initial alignment of header text
            headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
            subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
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