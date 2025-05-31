using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class MainForm : Form
    {
        private readonly ViajecitosController _controlador;

        public MainForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            // Configurar eventos
            btnSearchFlights.Click += (s, e) => OpenSearchForm();
            btnViewPurchases.Click += (s, e) => OpenPurchasesForm();
            btnLogin.Click += (s, e) => OpenLoginForm();
            btnRegister.Click += (s, e) => OpenRegisterForm();
            // Habilitar btnViewPurchases si hay usuario autenticado
            try { _controlador.GetIdClienteAutenticado(); btnViewPurchases.Enabled = true; } catch { }
            // Efectos de hover
            btnSearchFlights.MouseEnter += (s, e) => btnSearchFlights.BackColor = Color.LightBlue;
            btnSearchFlights.MouseLeave += (s, e) => btnSearchFlights.BackColor = Color.FromArgb(191, 219, 254);
            btnViewPurchases.MouseEnter += (s, e) => btnViewPurchases.BackColor = Color.LightBlue;
            btnViewPurchases.MouseLeave += (s, e) => btnViewPurchases.BackColor = Color.FromArgb(191, 219, 254);
            btnLogin.MouseEnter += (s, e) => btnLogin.BackColor = Color.LightBlue;
            btnLogin.MouseLeave += (s, e) => btnLogin.BackColor = Color.FromArgb(191, 219, 254);
            btnRegister.MouseEnter += (s, e) => btnRegister.BackColor = Color.LightBlue;
            btnRegister.MouseLeave += (s, e) => btnRegister.BackColor = Color.FromArgb(191, 219, 254);
        }

        private void OpenSearchForm() { new SearchFlightsForm(_controlador).Show(); Hide(); }
        private void OpenPurchasesForm() { new PurchasesForm(_controlador).Show(); Hide(); }
        private void OpenLoginForm() { new LoginForm().Show(); Hide(); }
        private void OpenRegisterForm() { new RegisterForm().Show(); Hide(); }
    }
}