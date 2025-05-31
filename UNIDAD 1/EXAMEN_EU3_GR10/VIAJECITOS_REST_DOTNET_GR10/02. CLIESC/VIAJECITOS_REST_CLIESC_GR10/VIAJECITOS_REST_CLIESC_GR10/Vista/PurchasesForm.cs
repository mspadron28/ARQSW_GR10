using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public partial class PurchasesForm : Form
    {
        private readonly ViajecitosController _controlador;

        public PurchasesForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            Load += PurchasesForm_Load;
            btnBack.Click += (s, e) => OpenSearchForm();
            // Efectos de hover
            btnBack.MouseEnter += (s, e) => btnBack.ForeColor = Color.DarkBlue;
            btnBack.MouseLeave += (s, e) => btnBack.ForeColor = Color.FromArgb(59, 130, 246);
        }

        private async void PurchasesForm_Load(object sender, EventArgs e)
        {
            await LoadPurchases();
        }

        private async Task LoadPurchases()
        {
            try
            {
                int idCliente = _controlador.GetIdClienteAutenticado();
                var compras = await _controlador.ObtenerComprasCliente(idCliente);
                if (compras == null || compras.Count == 0)
                {
                    table.Rows.Clear();
                    MessageBox.Show("No tienes compras registradas.", "Información", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    table.Rows.Clear();
                    foreach (var compra in compras)
                    {
                        table.Rows.Add(
                            compra.IdCompra,
                            $"{compra.Vuelo.CiudadOrigen} a {compra.Vuelo.CiudadDestino}",
                            $"${compra.Vuelo.Valor:F2}",
                            compra.FechaCompra.ToString("yyyy-MM-dd HH:mm:ss"),
                            compra.Vuelo.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss")
                        );
                    }
                }
            }
            catch (Exception ex)
            {
                lblError.Text = $"Error: {ex.Message}";
                table.Rows.Clear();
            }
        }

        private void OpenSearchForm()
        {
            new SearchFlightsForm(_controlador).Show();
            Hide();
        }
    }
}