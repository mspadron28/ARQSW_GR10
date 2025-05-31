using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class SearchFlightsForm : Form
    {
        private readonly ViajecitosController _controlador;

        public SearchFlightsForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            // Configurar eventos
            btnSearch.Click += async (s, e) => await SearchFlights();
            btnViewPurchases.Click += (s, e) => OpenPurchasesForm();
            btnLogin.Click += (s, e) => OpenLoginForm();
            btnRegister.Click += (s, e) => OpenRegisterForm();
            // Habilitar btnViewPurchases si hay usuario autenticado
            try { _controlador.GetIdClienteAutenticado(); btnViewPurchases.Visible = true; lblUser.Text = $"Usuario: {_controlador.GetIdClienteAutenticado()}"; } catch { }
            // Efectos de hover
            btnSearch.MouseEnter += (s, e) => btnSearch.BackColor = Color.LightBlue;
            btnSearch.MouseLeave += (s, e) => btnSearch.BackColor = Color.FromArgb(191, 219, 254);
            btnViewPurchases.MouseEnter += (s, e) => btnViewPurchases.BackColor = Color.LightBlue;
            btnViewPurchases.MouseLeave += (s, e) => btnViewPurchases.BackColor = Color.FromArgb(191, 219, 254);
            btnLogin.MouseEnter += (s, e) => btnLogin.ForeColor = Color.DarkBlue;
            btnLogin.MouseLeave += (s, e) => btnLogin.ForeColor = Color.FromArgb(59, 130, 246);
            btnRegister.MouseEnter += (s, e) => btnRegister.ForeColor = Color.DarkBlue;
            btnRegister.MouseLeave += (s, e) => btnRegister.ForeColor = Color.FromArgb(59, 130, 246);
        }

        private async Task SearchFlights()
        {
            try
            {
                string origen = cbOrigen.SelectedItem?.ToString();
                string destino = cbDestino.SelectedItem?.ToString();
                if (origen == "Selecciona una ciudad" || destino == "Selecciona una ciudad")
                {
                    lblError.Text = "Seleccione ciudades válidas.";
                    return;
                }
                string fecha = datePicker.Value.ToString("yyyy-MM-dd");
                var vuelo = await _controlador.ObtenerVueloMasCaro(origen, destino, fecha);
                new FlightResultForm(_controlador, vuelo).Show();
                Hide();
            }
            catch (Exception ex)
            {
                lblError.Text = $"Error: {ex.Message}";
            }
        }

        private void OpenPurchasesForm() { new PurchasesForm(_controlador).Show(); Hide(); }
        private void OpenLoginForm() { new LoginForm().Show(); Hide(); }
        private void OpenRegisterForm() { new RegisterForm().Show(); Hide(); }
    }
}