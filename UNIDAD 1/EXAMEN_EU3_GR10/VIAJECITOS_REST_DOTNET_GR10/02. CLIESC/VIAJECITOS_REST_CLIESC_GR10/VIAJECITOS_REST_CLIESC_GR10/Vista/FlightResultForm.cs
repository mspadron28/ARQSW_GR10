using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class FlightResultForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly Vuelo _vuelo;

        public FlightResultForm(ViajecitosController controlador, Vuelo vuelo)
        {
            _controlador = controlador;
            _vuelo = vuelo;
            InitializeComponent();
            Load += FlightResultForm_Load;
        }

        private void FlightResultForm_Load(object sender, EventArgs e)
        {
            if (_vuelo != null)
            {
                lblId.Text = $"Vuelo ID: {_vuelo.IdVuelo}";
                lblOrigen.Text = $"Origen: {_vuelo.CiudadOrigen}";
                lblDestino.Text = $"Destino: {_vuelo.CiudadDestino}";
                lblValor.Text = $"Valor: ${_vuelo.Valor:F2}";
                lblHora.Text = $"Hora de Salida: {_vuelo.HoraSalida}";
                try
                {
                    int idCliente = _controlador.GetIdClienteAutenticado();
                    btnComprar.Visible = true;
                    lblLogin.Visible = false;
                    btnComprar.Click += async (s, e) => await ComprarBoleto(idCliente);
                }
                catch
                {
                    btnComprar.Visible = false;
                    lblLogin.Visible = true;
                    lblLogin.Click += (s, e) => { new LoginForm().Show(); Hide(); };
                }
            }
            else
            {
                lblId.Text = "Vuelo no Disponible";
                lblOrigen.Visible = false;
                lblDestino.Visible = false;
                lblValor.Visible = false;
                lblHora.Visible = false;
                btnComprar.Visible = false;
                lblLogin.Visible = false;
            }
            btnBack.Click += (s, e) => OpenSearchForm();
            // Efectos de hover
            btnComprar.MouseEnter += (s, e) => btnComprar.BackColor = Color.LightBlue;
            btnComprar.MouseLeave += (s, e) => btnComprar.BackColor = Color.FromArgb(191, 219, 254);
            btnBack.MouseEnter += (s, e) => btnBack.ForeColor = Color.DarkBlue;
            btnBack.MouseLeave += (s, e) => btnBack.ForeColor = Color.FromArgb(59, 130, 246);
        }

        private async Task ComprarBoleto(int idCliente)
        {
            try
            {
                if (await _controlador.RegistrarCompra(_vuelo.IdVuelo, idCliente))
                {
                    MessageBox.Show("Compra realizada exitosamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    OpenSearchForm();
                }
                else
                {
                    MessageBox.Show("Error al realizar la compra.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void OpenSearchForm()
        {
            new SearchFlightsForm(_controlador).Show();
            Hide();
        }
    }
}