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
    public class PurchaseForm : Form
    {
        private readonly ViajecitosController _controlador;
        private readonly List<Vuelo> _vuelosDisponibles;

        // UI controls
        private DataGridView dgvVuelos;
        private ComboBox cbClientes;
        private Button btnToggleNewClient;
        private Panel pnlNewClient;
        private TextBox txtNewClientNombre;
        private TextBox txtNewClientEmail;
        private TextBox txtNewClientDocumento;
        private Button btnRegisterClient;

        private ComboBox cbMetodosPago;
        private NumericUpDown nudDescuento;

        private Button btnConfirmarCompra;
        private Button btnVolver;

        private Label lblError;
        private TextBox txtErrorDetalles;
        private FlowLayoutPanel flpDetalles;

        // Para manejar detalles de compra: vueloId -> cantidad
        private Dictionary<int, int> detallesCompra = new Dictionary<int, int>();

        public PurchaseForm(ViajecitosController controlador, List<Vuelo> vuelos)
        {
            _controlador = controlador;
            _vuelosDisponibles = vuelos ?? new List<Vuelo>();

            InitializeComponent();
            _ = CargarClientesAsync();
            CargarMetodosPago();
            CargarVuelos();
        }

        private void InitializeComponent()
        {
            this.Text = "Compra Boleto - Viajecitos SA";
            this.WindowState = FormWindowState.Maximized;
            this.BackColor = Color.White;

            // Layout principal
            var mainLayout = new TableLayoutPanel
            {
                Dock = DockStyle.Fill,
                ColumnCount = 1,
                RowCount = 6,
                Padding = new Padding(10),
                AutoScroll = true,
            };
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 30));   // lblError
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 40));    // dgvVuelos
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 35));   // Clientes + toggle
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 170));  // Nuevo cliente panel
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 80));   // Pago, descuento, botones
            mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 60));    // Detalles compra + error extendido
            this.Controls.Add(mainLayout);

            // lblError
            lblError = new Label
            {
                ForeColor = Color.FromArgb(239, 68, 68),
                Dock = DockStyle.Fill,
                Visible = false,
                Font = new Font("Segoe UI", 11, FontStyle.Bold),
                TextAlign = ContentAlignment.MiddleCenter
            };
            mainLayout.Controls.Add(lblError, 0, 0);

            // DataGridView vuelos
            dgvVuelos = new DataGridView
            {
                Dock = DockStyle.Fill,
                AllowUserToAddRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                RowHeadersVisible = false,
                Font = new Font("Segoe UI", 10)
            };
            mainLayout.Controls.Add(dgvVuelos, 0, 1);

            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "IdVuelo", HeaderText = "ID", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Origen", HeaderText = "Origen", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Destino", HeaderText = "Destino", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Valor", HeaderText = "Valor", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Fecha", HeaderText = "Fecha", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Hora", HeaderText = "Hora", ReadOnly = true });
            dgvVuelos.Columns.Add(new DataGridViewTextBoxColumn { Name = "Cantidad", HeaderText = "Cant.", ReadOnly = false, Width = 70 });
            dgvVuelos.Columns.Add(new DataGridViewButtonColumn { Name = "Accion", HeaderText = "Acción", Text = "Añadir", UseColumnTextForButtonValue = true, Width = 80 });

            dgvVuelos.CellClick += DgvVuelos_CellClick;
            dgvVuelos.EditingControlShowing += DgvVuelos_EditingControlShowing;

            // Panel clientes + botón toggle usando FlowLayoutPanel para correcto layout
            var clientePanel = new Panel { Dock = DockStyle.Fill };
            mainLayout.Controls.Add(clientePanel, 0, 2);

            var flpCliente = new FlowLayoutPanel
            {
                Dock = DockStyle.Fill,
                FlowDirection = FlowDirection.LeftToRight,
                WrapContents = false,
                Padding = new Padding(0),
                AutoSize = false
            };
            clientePanel.Controls.Add(flpCliente);

            var lblCliente = new Label
            {
                Text = "Seleccionar Cliente",
                Font = new Font("Segoe UI", 10),
                AutoSize = true,
                TextAlign = ContentAlignment.MiddleLeft,
                Margin = new Padding(0, 5, 10, 0)
            };
            flpCliente.Controls.Add(lblCliente);

            cbClientes = new ComboBox
            {
                Size = new Size(350, 25),
                DropDownStyle = ComboBoxStyle.DropDownList,
                Margin = new Padding(0, 0, 10, 0)
            };
            flpCliente.Controls.Add(cbClientes);

            btnToggleNewClient = new Button
            {
                Text = "Crear Nuevo Cliente",
                Size = new Size(160, 25),
                BackColor = Color.FromArgb(107, 114, 128),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand,
                Margin = new Padding(0)
            };
            btnToggleNewClient.FlatAppearance.BorderSize = 0;
            btnToggleNewClient.Click += BtnToggleNewClient_Click;
            flpCliente.Controls.Add(btnToggleNewClient);

            // Panel nuevo cliente (oculto)
            pnlNewClient = new Panel
            {
                Location = new Point(0, 0),
                Size = new Size(540, 160),
                BorderStyle = BorderStyle.FixedSingle,
                Visible = false
            };
            mainLayout.Controls.Add(pnlNewClient, 0, 3);

            var lblNombre = new Label { Text = "Nombre", Location = new Point(10, 10), AutoSize = true };
            pnlNewClient.Controls.Add(lblNombre);
            txtNewClientNombre = new TextBox { Location = new Point(10, 30), Width = 520 };
            pnlNewClient.Controls.Add(txtNewClientNombre);

            var lblEmail = new Label { Text = "Email", Location = new Point(10, 60), AutoSize = true };
            pnlNewClient.Controls.Add(lblEmail);
            txtNewClientEmail = new TextBox { Location = new Point(10, 80), Width = 520 };
            pnlNewClient.Controls.Add(txtNewClientEmail);

            var lblDocumento = new Label { Text = "Documento de Identidad", Location = new Point(10, 110), AutoSize = true };
            pnlNewClient.Controls.Add(lblDocumento);
            txtNewClientDocumento = new TextBox { Location = new Point(10, 130), Width = 520 };
            pnlNewClient.Controls.Add(txtNewClientDocumento);

            btnRegisterClient = new Button
            {
                Text = "Registrar Cliente",
                Location = new Point(390, 100),
                Size = new Size(140, 40),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnRegisterClient.FlatAppearance.BorderSize = 0;
            btnRegisterClient.Click += BtnRegisterClient_Click;
            pnlNewClient.Controls.Add(btnRegisterClient);

            // Panel pago, descuento, botones
            var pagoPanel = new Panel { Dock = DockStyle.Fill };
            mainLayout.Controls.Add(pagoPanel, 0, 4);

            var lblMetodoPago = new Label
            {
                Text = "Método de Pago",
                Location = new Point(0, 5),
                Font = new Font("Segoe UI", 10),
                AutoSize = true
            };
            pagoPanel.Controls.Add(lblMetodoPago);

            cbMetodosPago = new ComboBox
            {
                Location = new Point(0, 30),
                Size = new Size(240, 25),
                DropDownStyle = ComboBoxStyle.DropDownList
            };
            pagoPanel.Controls.Add(cbMetodosPago);

            var lblDescuento = new Label
            {
                Text = "Descuento (%)",
                Location = new Point(260, 5),
                Font = new Font("Segoe UI", 10),
                AutoSize = true
            };
            pagoPanel.Controls.Add(lblDescuento);

            nudDescuento = new NumericUpDown
            {
                Location = new Point(260, 30),
                Minimum = 0,
                Maximum = 100,
                DecimalPlaces = 2,
                Increment = 0.5M,
                Size = new Size(100, 25),
                Value = 0
            };
            pagoPanel.Controls.Add(nudDescuento);

            btnConfirmarCompra = new Button
            {
                Text = "Confirmar Compra",
                Location = new Point(400, 25),
                Size = new Size(180, 35),
                BackColor = Color.FromArgb(59, 130, 246),
                ForeColor = Color.White,
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnConfirmarCompra.FlatAppearance.BorderSize = 0;
            btnConfirmarCompra.Click += async (s, e) => await ConfirmarCompraAsync();
            pagoPanel.Controls.Add(btnConfirmarCompra);

            btnVolver = new Button
            {
                Text = "Volver",
                Location = new Point(600, 25),
                Size = new Size(180, 35),
                BackColor = Color.White,
                ForeColor = Color.FromArgb(220, 38, 38),
                FlatStyle = FlatStyle.Flat,
                Cursor = Cursors.Hand
            };
            btnVolver.FlatAppearance.BorderSize = 1;
            btnVolver.FlatAppearance.BorderColor = Color.FromArgb(220, 38, 38);
            btnVolver.Click += BtnVolver_Click;
            pagoPanel.Controls.Add(btnVolver);

            // Panel detalles compra y textbox error extendido (en Row 5)
            var detallesErrorPanel = new Panel { Dock = DockStyle.Fill, AutoScroll = true };
            mainLayout.Controls.Add(detallesErrorPanel, 0, 5);

            var lblDetalles = new Label
            {
                Text = "Detalles de la Compra",
                Font = new Font("Segoe UI", 11, FontStyle.Bold),
                Location = new Point(0, 0),
                AutoSize = true
            };
            detallesErrorPanel.Controls.Add(lblDetalles);

            flpDetalles = new FlowLayoutPanel
            {
                Location = new Point(0, 25),
                Size = new Size(860, 140),
                AutoScroll = true,
                BorderStyle = BorderStyle.FixedSingle,
                Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right
            };
            detallesErrorPanel.Controls.Add(flpDetalles);

            txtErrorDetalles = new TextBox
            {
                Location = new Point(0, 175),
                Size = new Size(860, 120),
                Multiline = true,
                ScrollBars = ScrollBars.Vertical,
                ReadOnly = true,
                ForeColor = Color.DarkRed,
                Visible = false,
                Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom
            };
            detallesErrorPanel.Controls.Add(txtErrorDetalles);
        }

        private void CargarVuelos()
        {
            dgvVuelos.Rows.Clear();
            foreach (var vuelo in _vuelosDisponibles.OrderByDescending(v => v.Valor))
            {
                dgvVuelos.Rows.Add(
                    vuelo.IdVuelo,
                    vuelo.CiudadOrigen,
                    vuelo.CiudadDestino,
                    vuelo.Valor.ToString("C"),
                    vuelo.HoraSalida.ToString("yyyy-MM-dd"),
                    vuelo.HoraSalida.ToString("HH:mm"),
                    "1",
                    "Añadir"
                );
            }
        }

        private async Task CargarClientesAsync()
        {
            try
            {
                var clientes = await _controlador.ObtenerTodosClientesAsync();
                cbClientes.Items.Clear();
                cbClientes.Items.Add(new ComboboxItem { Text = "Seleccione un cliente", Value = null });
                foreach (var c in clientes)
                    cbClientes.Items.Add(new ComboboxItem { Text = $"{c.Nombre} ({c.DocumentoIdentidad})", Value = c.IdCliente });
                cbClientes.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
                MostrarError($"Error cargando clientes: {ex.Message}", true, ex.ToString());
            }
        }

        private void CargarMetodosPago()
        {
            var metodos = new List<(int Id, string Nombre)>
            {
                (1, "Tarjeta de Crédito"),
                (2, "Efectivo"),
                (3, "Tarjeta de Débito")
            };
            cbMetodosPago.Items.Clear();
            cbMetodosPago.Items.Add(new ComboboxItem { Text = "Seleccione un método", Value = null });
            foreach (var m in metodos)
                cbMetodosPago.Items.Add(new ComboboxItem { Text = m.Nombre, Value = m.Id });
            cbMetodosPago.SelectedIndex = 0;
        }

        private void BtnToggleNewClient_Click(object sender, EventArgs e)
        {
            pnlNewClient.Visible = !pnlNewClient.Visible;
        }

        private async void BtnRegisterClient_Click(object sender, EventArgs e)
        {
            var nombre = txtNewClientNombre.Text.Trim();
            var email = txtNewClientEmail.Text.Trim();
            var documento = txtNewClientDocumento.Text.Trim();

            if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(documento))
            {
                MostrarError("Todos los campos del nuevo cliente son requeridos.");
                return;
            }

            try
            {
                var nuevoCliente = await _controlador.RegistrarClienteAsync(nombre, email, documento);
                if (nuevoCliente != null)
                {
                    MostrarError("Cliente registrado exitosamente.", isError: false);
                    await CargarClientesAsync();

                    for (int i = 0; i < cbClientes.Items.Count; i++)
                    {
                        if ((cbClientes.Items[i] as ComboboxItem)?.Value is int id && id == nuevoCliente.IdCliente)
                        {
                            cbClientes.SelectedIndex = i;
                            break;
                        }
                    }
                    pnlNewClient.Visible = false;
                    txtNewClientNombre.Text = "";
                    txtNewClientEmail.Text = "";
                    txtNewClientDocumento.Text = "";
                }
                else
                {
                    MostrarError("Error al registrar cliente.");
                }
            }
            catch (Exception ex)
            {
                MostrarError($"Error registrando cliente: {ex.Message}", true, ex.ToString());
            }
        }

        private void DgvVuelos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0 || e.ColumnIndex != dgvVuelos.Columns["Accion"].Index) return;

            var row = dgvVuelos.Rows[e.RowIndex];
            if (!int.TryParse(row.Cells["Cantidad"].Value?.ToString(), out int cantidad) || cantidad < 1)
            {
                MostrarError("La cantidad debe ser al menos 1.");
                return;
            }

            if (!int.TryParse(row.Cells["IdVuelo"].Value?.ToString(), out int idVuelo))
            {
                MostrarError("ID de vuelo inválido.");
                return;
            }

            if (detallesCompra.ContainsKey(idVuelo))
                detallesCompra[idVuelo] += cantidad;
            else
                detallesCompra[idVuelo] = cantidad;

            ActualizarDetallesCompraUI();
            MostrarError($"Se añadieron {cantidad} boletos al vuelo {idVuelo}.", isError: false);
        }

        private void ActualizarDetallesCompraUI()
        {
            flpDetalles.Controls.Clear();

            foreach (var kvp in detallesCompra)
            {
                int idVuelo = kvp.Key;
                int cantidad = kvp.Value;

                var vuelo = _vuelosDisponibles.FirstOrDefault(v => v.IdVuelo == idVuelo);
                string textoVuelo = vuelo != null
                    ? $"Vuelo {idVuelo} ({vuelo.CiudadOrigen} -> {vuelo.CiudadDestino}) - Cantidad: {cantidad}"
                    : $"Vuelo {idVuelo} - Cantidad: {cantidad}";

                var panelDetalle = new Panel
                {
                    Width = flpDetalles.Width - 25,
                    Height = 30,
                    Tag = idVuelo
                };

                var lblDetalle = new Label
                {
                    Text = textoVuelo,
                    Location = new Point(5, 5),
                    AutoSize = true
                };
                panelDetalle.Controls.Add(lblDetalle);

                var btnEliminar = new Button
                {
                    Text = "Eliminar",
                    Size = new Size(70, 23),
                    Location = new Point(panelDetalle.Width - 75, 2),
                    Tag = idVuelo
                };
                btnEliminar.Click += BtnEliminarDetalle_Click;
                panelDetalle.Controls.Add(btnEliminar);

                flpDetalles.Controls.Add(panelDetalle);
            }
        }

        private void BtnEliminarDetalle_Click(object sender, EventArgs e)
        {
            if (sender is Button btn && btn.Tag is int idVuelo)
            {
                detallesCompra.Remove(idVuelo);
                ActualizarDetallesCompraUI();
                MostrarError($"Eliminado detalle del vuelo {idVuelo}.", isError: false);
            }
        }

        private void DgvVuelos_EditingControlShowing(object sender, DataGridViewEditingControlShowingEventArgs e)
        {
            if (dgvVuelos.CurrentCell.ColumnIndex == dgvVuelos.Columns["Cantidad"].Index)
            {
                var tb = e.Control as TextBox;
                if (tb != null)
                {
                    tb.KeyPress -= Tb_KeyPress;
                    tb.KeyPress += Tb_KeyPress;
                }
            }
        }

        private void Tb_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
                e.Handled = true;
        }

        private async Task ConfirmarCompraAsync()
        {
            lblError.Visible = false;
            txtErrorDetalles.Visible = false;

            if (detallesCompra.Count == 0)
            {
                MostrarError("Debe añadir al menos un vuelo para confirmar la compra.");
                return;
            }

            if (cbClientes.SelectedItem is not ComboboxItem clienteItem || clienteItem.Value == null)
            {
                MostrarError("Debe seleccionar un cliente.");
                return;
            }

            if (cbMetodosPago.SelectedItem is not ComboboxItem metodoItem || metodoItem.Value == null)
            {
                MostrarError("Debe seleccionar un método de pago.");
                return;
            }

            btnConfirmarCompra.Enabled = false;

            try
            {
                var numeroFactura = $"FAC{DateTime.Now:yyyyMMddHHmmss}";
                var idCliente = (int)clienteItem.Value;
                var idMetodoPago = (int)metodoItem.Value;
                decimal descuento = nudDescuento.Value;

                var detalles = detallesCompra.Select(kvp => (IdVuelo: kvp.Key, Cantidad: kvp.Value)).ToList();

                const int idEmpleadoFijo = 1; // Valor quemado como pediste

                var factura = await _controlador.CrearFacturaAsync(numeroFactura, idEmpleadoFijo, idCliente, idMetodoPago, descuento, detalles);

                if (factura != null)
                {
                    MessageBox.Show($"Compra realizada con éxito. Número de factura: {factura.NumeroFactura}", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    var invoiceForm = new InvoiceDetailsForm(_controlador, factura);
                    invoiceForm.Show();
                    this.Hide();
                }
                else
                {
                    MostrarError("Error al crear la factura.");
                }
            }
            catch (Exception ex)
            {
                MostrarError($"Error al confirmar compra: {ex.Message}", true, ex.ToString());
            }
            finally
            {
                btnConfirmarCompra.Enabled = true;
            }
        }

        private void MostrarError(string mensaje, bool isError = true, string detalles = null)
        {
            lblError.Text = mensaje;
            lblError.ForeColor = isError ? Color.FromArgb(239, 68, 68) : Color.FromArgb(34, 197, 94);
            lblError.Visible = true;

            if (isError && !string.IsNullOrEmpty(detalles))
            {
                txtErrorDetalles.Text = detalles;
                txtErrorDetalles.Visible = true;
            }
            else
            {
                txtErrorDetalles.Text = "";
                txtErrorDetalles.Visible = false;
            }
        }

        private void BtnVolver_Click(object sender, EventArgs e)
        {
            var searchResults = new SearchResultsForm(_controlador, _vuelosDisponibles);
            searchResults.Show();
            this.Hide();
        }

        private class ComboboxItem
        {
            public string Text { get; set; }
            public object Value { get; set; }
            public override string ToString() => Text;
        }
    }
}
