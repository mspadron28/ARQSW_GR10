using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class SelectClientForm : Form
    {
        private readonly ViajecitosController _controlador;
        private ComboBox cbClientes;
        private Button btnVerFacturas;
        private Label lblError;

        public SelectClientForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            _ = CargarClientesAsync();
        }

        private void InitializeComponent()
        {
            this.Text = "Seleccionar Cliente";
            this.Size = new Size(400, 200);
            this.StartPosition = FormStartPosition.CenterScreen;

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 68),
                Location = new Point(20, 20),
                AutoSize = true,
                Visible = false
            };
            this.Controls.Add(lblError);

            // ComboBox para clientes
            cbClientes = new ComboBox
            {
                Location = new Point(20, 60),
                Size = new Size(350, 25),
                DropDownStyle = ComboBoxStyle.DropDownList
            };
            this.Controls.Add(cbClientes);

            // Botón para ver facturas
            btnVerFacturas = new Button
            {
                Text = "Ver Facturas",
                Location = new Point(20, 100),
                Size = new Size(350, 35),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat
            };
            btnVerFacturas.FlatAppearance.BorderSize = 0;
            btnVerFacturas.Click += BtnVerFacturas_Click;
            this.Controls.Add(btnVerFacturas);
        }

        private async Task CargarClientesAsync()
        {
            try
            {
                var clientes = await _controlador.ObtenerTodosClientesAsync();
                cbClientes.Items.Clear();
                cbClientes.Items.Add("Seleccionar un cliente");

                // Crear una lista de clientes con sus ID
                foreach (var cliente in clientes)
                {
                    cbClientes.Items.Add(new { IdCliente = cliente.IdCliente, Nombre = cliente.Nombre });
                }
                cbClientes.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
                lblError.Text = "Error cargando clientes: " + ex.Message;
                lblError.Visible = true;
            }
        }


        private void BtnVerFacturas_Click(object sender, EventArgs e)
        {
            if (cbClientes.SelectedIndex == 0)
            {
                MessageBox.Show("Por favor, seleccione un cliente.");
                return;
            }

            // Obtener el cliente seleccionado con su ID
            var clienteSeleccionado = (dynamic)cbClientes.SelectedItem;
            var clienteId = clienteSeleccionado.IdCliente;

            // Pasar el ID del cliente a la siguiente vista
            var facturasForm = new FacturasClienteForm(_controlador, clienteId);
            facturasForm.Show();
            this.Hide();
        }
    }
}
