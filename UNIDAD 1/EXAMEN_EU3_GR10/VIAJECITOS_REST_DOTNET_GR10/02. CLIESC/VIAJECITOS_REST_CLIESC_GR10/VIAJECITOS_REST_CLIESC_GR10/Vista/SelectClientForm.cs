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
        private Label lblTitle;
        private Panel panelContainer;

        public SelectClientForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            _ = CargarClientesAsync();
        }

        private void InitializeComponent()
        {
            this.Text = "Seleccionar Cliente";
            this.Size = new Size(450, 300);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(249, 250, 251); // --background-color: #f9fafb

            // Container panel for a clean, card-like layout
            panelContainer = new Panel
            {
                Size = new Size(400, 250),
                BackColor = Color.White,
                BorderStyle = BorderStyle.FixedSingle, // Subtle border to mimic box-shadow
                Location = new Point(0, 0)
            };
            this.Controls.Add(panelContainer);

            // Center the panel
            panelContainer.Location = new Point((this.ClientSize.Width - panelContainer.Width) / 2, 25);

            // Title label
            lblTitle = new Label
            {
                Text = "Seleccionar Cliente",
                Font = new Font("Inter", 14, FontStyle.Bold),
                ForeColor = Color.FromArgb(30, 58, 138), // --primary-color: #1e3a8a
                AutoSize = true,
                Location = new Point(0, 20)
            };
            panelContainer.Controls.Add(lblTitle);

            // Error Label
            lblError = new Label
            {
                ForeColor = Color.FromArgb(220, 38, 38), // --error-color: #dc2626
                BackColor = Color.FromArgb(254, 242, 242), // #fef2f2
                Font = new Font("Inter", 9),
                AutoSize = true,
                Visible = false,
                Padding = new Padding(12),
                Location = new Point(0, 60)
            };
            panelContainer.Controls.Add(lblError);

            // ComboBox para clientes
            cbClientes = new ComboBox
            {
                Location = new Point(25, 100),
                Size = new Size(350, 30),
                DropDownStyle = ComboBoxStyle.DropDownList,
                Font = new Font("Inter", 10),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(31, 41, 55), // --text-color: #1f2937
                FlatStyle = FlatStyle.Flat
            };
            cbClientes.DropDownHeight = 200; // Increased dropdown height for better visibility
            panelContainer.Controls.Add(cbClientes);

            // Botón para ver facturas
            btnVerFacturas = new Button
            {
                Text = "Ver Facturas",
                Location = new Point(25, 150),
                Size = new Size(350, 40),
                BackColor = Color.FromArgb(59, 130, 246), // --secondary-color: #3b82f6
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Font = new Font("Inter", 10, FontStyle.Bold)
            };
            btnVerFacturas.FlatAppearance.BorderSize = 0;
            btnVerFacturas.FlatAppearance.MouseOverBackColor = Color.FromArgb(30, 58, 138); // --primary-color: #1e3a8a on hover
            panelContainer.Controls.Add(btnVerFacturas);

            // Hover effect for button text
            btnVerFacturas.MouseEnter += (s, e) =>
            {
                btnVerFacturas.ForeColor = Color.White;
            };
            btnVerFacturas.MouseLeave += (s, e) =>
            {
                btnVerFacturas.ForeColor = Color.White;
            };
            btnVerFacturas.Click += BtnVerFacturas_Click;

            // Center elements within panel
            lblTitle.Location = new Point((panelContainer.Width - lblTitle.Width) / 2, 20);
            lblError.Location = new Point((panelContainer.Width - lblError.Width) / 2, 60);

            // Adjust positions on resize
            this.Resize += (s, e) =>
            {
                panelContainer.Location = new Point((this.ClientSize.Width - panelContainer.Width) / 2, 25);
                lblTitle.Location = new Point((panelContainer.Width - lblTitle.Width) / 2, 20);
                lblError.Location = new Point((panelContainer.Width - lblError.Width) / 2, 60);
            };
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