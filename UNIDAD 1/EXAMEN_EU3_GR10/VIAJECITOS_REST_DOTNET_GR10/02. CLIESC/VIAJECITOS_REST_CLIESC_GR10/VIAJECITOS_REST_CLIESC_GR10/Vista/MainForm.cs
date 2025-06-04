using System;
using System.Drawing;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class MainForm : Form
    {
        private readonly ViajecitosController _controlador;

        public MainForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
        }

        private void InitializeComponent()
        {
            // Form setup
            this.Text = "Viajecitos SA - Inicio";
            this.Size = new Size(700, 450);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(249, 250, 251);

            // Title Label
            var lblWelcome = new Label
            {
                Text = "Bienvenido a Viajecitos SA",
                Font = new Font("Segoe UI", 22, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138),
                AutoSize = true,
                Location = new Point(180, 30)
            };
            this.Controls.Add(lblWelcome);

            // Subtitle Label
            var lblSubtitle = new Label
            {
                Text = "Encuentra y compra tus boletos de avión de forma fácil y segura.",
                Font = new Font("Segoe UI", 14),
                ForeColor = Color.FromArgb(31, 41, 55),
                AutoSize = true,
                Location = new Point(140, 75)
            };
            this.Controls.Add(lblSubtitle);

            // Panel to hold navigation buttons
            var panelButtons = new FlowLayoutPanel
            {
                Location = new Point(100, 130),
                Size = new Size(500, 250),
                FlowDirection = FlowDirection.LeftToRight,
                WrapContents = true,
                AutoSize = false,
                Padding = new Padding(10)
            };
            this.Controls.Add(panelButtons);

            if (_controlador.UsuarioAutenticado != null)
            {
                // Greeting label if user is logged in
                var lblGreeting = new Label
                {
                    Text = $"Hola, {_controlador.UsuarioAutenticado.NombreUsuario}!",
                    Font = new Font("Segoe UI", 16, FontStyle.Bold),
                    ForeColor = Color.FromArgb(30, 58, 138),
                    AutoSize = true,
                    Margin = new Padding(0, 0, 0, 20)
                };
                panelButtons.Controls.Add(lblGreeting);

                // Add buttons visible to logged users
                panelButtons.Controls.Add(CreateNavButton("Buscar Vuelos", BtnBuscarVuelos_Click));
                panelButtons.Controls.Add(CreateNavButton("Facturas de un Cliente", BtnFacturasCliente_Click));
                panelButtons.Controls.Add(CreateNavButton("Todas las Facturas", BtnTodasFacturas_Click));
                panelButtons.Controls.Add(CreateNavButton("Cerrar Sesión", BtnLogout_Click, Color.FromArgb(220, 38, 38)));
            }
            else
            {
                // Only "Iniciar Sesión" button if no user is logged in
                panelButtons.Controls.Add(CreateNavButton("Iniciar Sesión", BtnLogin_Click, primary: true));
            }
        }

        private Button CreateNavButton(string text, EventHandler onClick, Color? backColor = null, bool primary = false)
        {
            var btn = new Button
            {
                Text = text,
                Size = new Size(180, 45),
                Margin = new Padding(10),
                BackColor = backColor ?? Color.White,
                ForeColor = primary ? Color.White : Color.FromArgb(59, 130, 246),
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Segoe UI", 12, FontStyle.Regular),
                Cursor = Cursors.Hand
            };
            btn.FlatAppearance.BorderColor = primary ? Color.FromArgb(59, 130, 246) : btn.ForeColor;
            btn.FlatAppearance.BorderSize = 1;
            btn.Click += onClick;

            btn.MouseEnter += (s, e) =>
            {
                btn.BackColor = primary ? Color.FromArgb(30, 58, 138) : Color.FromArgb(59, 130, 246);
                btn.ForeColor = Color.White;
            };
            btn.MouseLeave += (s, e) =>
            {
                btn.BackColor = backColor ?? Color.White;
                btn.ForeColor = primary ? Color.White : Color.FromArgb(59, 130, 246);
            };

            return btn;
        }

        private void BtnBuscarVuelos_Click(object sender, EventArgs e)
        {
           var searchForm = new SearchFlightsForm(_controlador);
            searchForm.Show();
            this.Hide();
        }

        private void BtnFacturasCliente_Click(object sender, EventArgs e)
        {
            var selectClientForm = new SelectClientForm(_controlador);
            selectClientForm.Show();
            this.Hide();
        }

        private void BtnTodasFacturas_Click(object sender, EventArgs e)
        {
            var allInvoicesForm = new AllInvoicesForm(_controlador);
            allInvoicesForm.Show();
            this.Hide();
        }

        private void BtnLogout_Click(object sender, EventArgs e)
        {
            // Clear authenticated user by reflection
            typeof(ViajecitosController)
                .GetField("_usuarioAutenticado", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance)
                ?.SetValue(_controlador, null);

            // Rebuild UI to show "Iniciar Sesión" only
            this.Controls.Clear();
            InitializeComponent();
        }

        private void BtnLogin_Click(object sender, EventArgs e)
        {
            var loginForm = new LoginForm(_controlador);
            loginForm.Show();
            this.Hide();
        }
    }
}
