using System;
using System.Drawing;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class MainForm : Form
    {
        private readonly ViajecitosController _controlador;
        private Label lblGreeting; // Declare lblGreeting as a class-level field

        public MainForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
        }

        private void InitializeComponent()
        {
            // Form setup
            this.Text = "Viajecitos SA - Inicio";
            this.Size = new Size(700, 450); // Initial size, but will be maximized
            this.WindowState = FormWindowState.Maximized; // Maximize on startup
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(249, 250, 251); // --background-color: #f9fafb

            // Header panel
            var headerPanel = new Panel
            {
                Dock = DockStyle.Top,
                Height = 150,
                BackColor = Color.FromArgb(163, 191, 250) // Azul pastel claro, consistent with LoginForm
            };
            this.Controls.Add(headerPanel);

            var headerText = new Label
            {
                Text = "Viajecitos SA",
                Font = new Font("Inter", 26, FontStyle.Bold), // Web uses Inter, fallback to Segoe UI if unavailable
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

            // Content panel to mimic welcome-container
            var contentPanel = new Panel
            {
                Location = new Point(0, 150),
                Size = new Size(this.ClientSize.Width, this.ClientSize.Height - 150),
                BackColor = Color.White, // --white: #ffffff
                BorderStyle = BorderStyle.FixedSingle // Simulate box-shadow
            };
            this.Controls.Add(contentPanel);

            // Center content dynamically
            int contentWidth = 900; // Max-width from web
            int leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2); // Ensure no negative margin

            // Title Label
            var lblWelcome = new Label
            {
                Text = "Bienvenido a Viajecitos SA",
                Font = new Font("Inter", 32, FontStyle.Bold), // 2rem = ~32px
                ForeColor = Color.FromArgb(30, 58, 138), // --primary-color: #1e3a8a
                AutoSize = true,
                Location = new Point(leftMargin, 30)
            };
            contentPanel.Controls.Add(lblWelcome);

            // Subtitle Label
            var lblSubtitle = new Label
            {
                Text = "Encuentra y compra tus boletos de avión de forma fácil y segura.",
                Font = new Font("Inter", 18, FontStyle.Regular), // 1.125rem = ~18px
                ForeColor = Color.FromArgb(31, 41, 55), // --text-color: #1f2937
                AutoSize = true,
                Location = new Point(leftMargin, 75)
            };
            contentPanel.Controls.Add(lblSubtitle);

            // Error label
            var lblError = new Label
            {
                ForeColor = Color.FromArgb(220, 38, 38), // --error-color: #dc2626
                AutoSize = true,
                MaximumSize = new Size(contentWidth, 0),
                Visible = false,
                Location = new Point(leftMargin, 120)
            };
            contentPanel.Controls.Add(lblError);

            // Panel to hold navigation buttons
            var panelButtons = new FlowLayoutPanel
            {
                Size = new Size(contentWidth, 250),
                FlowDirection = FlowDirection.LeftToRight,
                WrapContents = true,
                AutoSize = false,
                Padding = new Padding(10),
                Margin = new Padding(0) // Remove outer margin to control positioning
            };
            contentPanel.Controls.Add(panelButtons);

            if (_controlador.UsuarioAutenticado != null)
            {
                // Initialize greeting label if user is logged in
                lblGreeting = new Label
                {
                    Text = $"Hola, {_controlador.UsuarioAutenticado.NombreUsuario}!",
                    Font = new Font("Inter", 20, FontStyle.Bold), // 1.25rem = ~20px
                    ForeColor = Color.FromArgb(30, 58, 138), // --primary-color: #1e3a8a
                    AutoSize = true,
                    Location = new Point(leftMargin, 160)
                };
                contentPanel.Controls.Add(lblGreeting);

                // Adjust panelButtons location
                panelButtons.Location = new Point(leftMargin, 200);

                // Add buttons visible to logged users
                panelButtons.Controls.Add(CreateNavButton("Buscar Vuelos", BtnBuscarVuelos_Click));
                panelButtons.Controls.Add(CreateNavButton("Facturas de un Cliente", BtnFacturasCliente_Click));
                panelButtons.Controls.Add(CreateNavButton("Todas las Facturas", BtnTodasFacturas_Click));
                panelButtons.Controls.Add(CreateNavButton("Cerrar Sesión", BtnLogout_Click, Color.FromArgb(220, 38, 38), false));
            }
            else
            {
                // Adjust panelButtons location
                panelButtons.Location = new Point(leftMargin, 120);

                // Only "Iniciar Sesión" button if no user is logged in
                panelButtons.Controls.Add(CreateNavButton("Iniciar Sesión", BtnLogin_Click, primary: true));
            }

            // Handle form resize
            this.Resize += (s, e) =>
            {
                contentPanel.Size = new Size(this.ClientSize.Width, this.ClientSize.Height - 150);
                leftMargin = Math.Max(0, (this.ClientSize.Width - contentWidth) / 2);

                // Update positions
                headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
                subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
                lblWelcome.Location = new Point(leftMargin, lblWelcome.Location.Y);
                lblSubtitle.Location = new Point(leftMargin, lblSubtitle.Location.Y);
                lblError.Location = new Point(leftMargin, lblError.Location.Y);
                panelButtons.Location = new Point(leftMargin, _controlador.UsuarioAutenticado != null ? 200 : 120);
                if (lblGreeting != null) // Check if lblGreeting exists
                {
                    lblGreeting.Location = new Point(leftMargin, lblGreeting.Location.Y);
                }
            };

            // Initial alignment of header text
            headerText.Location = new Point(headerPanel.Width - headerText.Width - 20, 30);
            subHeaderText.Location = new Point(headerPanel.Width - subHeaderText.Width - 20, 80);
        }

        private Button CreateNavButton(string text, EventHandler onClick, Color? backColor = null, bool primary = false)
        {
            var btn = new Button
            {
                Text = text,
                Size = new Size(220, 45), // Increased width to 220px to ensure full text visibility
                Margin = new Padding(10), // Gap: 1rem
                BackColor = primary ? Color.FromArgb(59, 130, 246) : backColor ?? Color.White, // --secondary-color or white
                ForeColor = primary ? Color.White : Color.FromArgb(59, 130, 246), // --secondary-color
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 14, FontStyle.Regular), // 0.875rem = ~14px
                Cursor = Cursors.Hand
            };
            btn.FlatAppearance.BorderColor = primary ? Color.FromArgb(59, 130, 246) : (text == "Cerrar Sesión" ? Color.FromArgb(220, 38, 38) : Color.FromArgb(59, 130, 246));
            btn.FlatAppearance.BorderSize = 1;
            btn.Click += onClick;

            btn.MouseEnter += (s, e) =>
            {
                btn.BackColor = primary ? Color.FromArgb(30, 58, 138) : (text == "Cerrar Sesión" ? Color.FromArgb(220, 38, 38) : Color.FromArgb(59, 130, 246));
                btn.ForeColor = Color.White;
            };
            btn.MouseLeave += (s, e) =>
            {
                btn.BackColor = primary ? Color.FromArgb(59, 130, 246) : backColor ?? Color.White;
                btn.ForeColor = primary ? Color.White : (text == "Cerrar Sesión" ? Color.FromArgb(220, 38, 38) : Color.FromArgb(59, 130, 246));
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
            typeof(ViajecitosController)
                .GetField("_usuarioAutenticado", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance)
                ?.SetValue(_controlador, null);

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