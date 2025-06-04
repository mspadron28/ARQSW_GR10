using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class FacturasClienteForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly int _clienteId;
        private DataGridView dgvFacturas;

        public FacturasClienteForm(ViajecitosController controlador, int clienteId)
        {
            _controlador = controlador;
            _clienteId = clienteId;
            InitializeComponent();
            _ = CargarFacturasAsync();
        }

        private void InitializeComponent()
        {
            this.Text = "Facturas del Cliente";
            this.Size = new Size(900, 600);
            this.StartPosition = FormStartPosition.CenterScreen;

            // DataGridView de facturas
            dgvFacturas = new DataGridView
            {
                Dock = DockStyle.Fill,
                AllowUserToAddRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                RowHeadersVisible = false,
                Font = new Font("Segoe UI", 10)
            };
            this.Controls.Add(dgvFacturas);

            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "NumeroFactura", HeaderText = "Número de Factura", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "Fecha", HeaderText = "Fecha", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "Total", HeaderText = "Total", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewButtonColumn { Name = "Accion", HeaderText = "Acción", Text = "Ver Detalles", UseColumnTextForButtonValue = true });

            dgvFacturas.CellClick += DgvFacturas_CellClick;
        }

        private async Task CargarFacturasAsync()
        {
            try
            {
                // Obtener las facturas del cliente con el idCliente proporcionado
                var facturas = await _controlador.ObtenerFacturasClienteAsync(_clienteId);
                dgvFacturas.Rows.Clear();

                // Mostrar las facturas en el DataGridView
                foreach (var factura in facturas)
                {
                    dgvFacturas.Rows.Add(factura.NumeroFactura, factura.FechaEmision.ToString("yyyy-MM-dd HH:mm:ss"), factura.Total.ToString("C"));
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error cargando facturas: " + ex.Message);
            }
        }

        private async void DgvFacturas_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && e.ColumnIndex == dgvFacturas.Columns["Accion"].Index)
            {
                var numeroFactura = dgvFacturas.Rows[e.RowIndex].Cells["NumeroFactura"].Value.ToString();

                // Obtener la factura completa usando el número de factura
                var facturas = await _controlador.ObtenerFacturasClienteAsync(_clienteId);

                var facturaSeleccionada = facturas.FirstOrDefault(f => f.NumeroFactura == numeroFactura);

                if (facturaSeleccionada != null)
                {
                    var detallesForm = new InvoiceDetailsForm(_controlador, facturaSeleccionada);
                    detallesForm.Show();
                    this.Hide();
                }
                else
                {
                    MessageBox.Show("Factura no encontrada.");
                }
            }
        }

    }
}
