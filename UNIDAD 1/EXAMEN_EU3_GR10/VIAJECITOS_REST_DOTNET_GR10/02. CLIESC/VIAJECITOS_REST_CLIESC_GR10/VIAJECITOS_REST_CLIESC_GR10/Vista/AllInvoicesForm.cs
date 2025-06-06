﻿using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    public class AllInvoicesForm : Form
    {
        private readonly ViajecitosController _controlador;
        private DataGridView dgvFacturas;

        public AllInvoicesForm(ViajecitosController controlador)
        {
            _controlador = controlador;
            InitializeComponent();
            _ = CargarFacturasAsync();
        }

        private void InitializeComponent()
        {
            this.Text = "Todas las Facturas";
            this.Size = new Size(900, 600);
            this.StartPosition = FormStartPosition.CenterScreen;

            // Label for the title
            Label lblTitle = new Label
            {
                Text = "Todas las Facturas",
                Font = new Font("Segoe UI", 14, FontStyle.Bold),
                AutoSize = true,
                Location = new Point(0, 20) // Initial position, will be centered below
            };
            this.Controls.Add(lblTitle);

            // DataGridView for displaying all invoices
            dgvFacturas = new DataGridView
            {
                AllowUserToAddRows = false,
                SelectionMode = DataGridViewSelectionMode.FullRowSelect,
                MultiSelect = false,
                RowHeadersVisible = false,
                Font = new Font("Segoe UI", 10),
                Size = new Size(850, 500) // Fixed size to allow centering
            };
            this.Controls.Add(dgvFacturas);

            // Center the title and DataGridView
            lblTitle.Location = new Point((this.ClientSize.Width - lblTitle.Width) / 2, 20);
            dgvFacturas.Location = new Point((this.ClientSize.Width - dgvFacturas.Width) / 2, 60);

            // Add columns to DataGridView
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "Cliente", HeaderText = "Cliente", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "NumeroFactura", HeaderText = "Número de Factura", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "Fecha", HeaderText = "Fecha", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewTextBoxColumn { Name = "Total", HeaderText = "Total", ReadOnly = true });
            dgvFacturas.Columns.Add(new DataGridViewButtonColumn { Name = "Accion", HeaderText = "Acción", Text = "Ver Detalles", UseColumnTextForButtonValue = true });

            dgvFacturas.CellClick += DgvFacturas_CellClick;

            // Adjust positions when the form is resized
            this.Resize += (s, e) =>
            {
                lblTitle.Location = new Point((this.ClientSize.Width - lblTitle.Width) / 2, 20);
                dgvFacturas.Location = new Point((this.ClientSize.Width - dgvFacturas.Width) / 2, 60);
            };
        }
        private async Task CargarFacturasAsync()
        {
            try
            {
                // Obtener la lista de facturas de clientes
                var clientesFacturas = await _controlador.ObtenerTodasFacturasPorClienteAsync();

                dgvFacturas.Rows.Clear();

                // Iteramos a través de cada cliente en la lista
                foreach (var clienteFacturas in clientesFacturas.OrderBy(c => c.Nombre))
                {
                    // Mostramos los detalles del cliente
                    dgvFacturas.Rows.Add(clienteFacturas.Nombre, "", "", "", "", ""); // Inicializamos con la información del cliente

                    // Iteramos por cada factura del cliente
                    foreach (var factura in clienteFacturas.Facturas.OrderBy(f => f.FechaEmision))
                    {
                        // Añadimos una fila por cada factura
                        dgvFacturas.Rows.Add("", factura.NumeroFactura, factura.FechaEmision.ToString("yyyy-MM-dd HH:mm:ss"), factura.Total.ToString("C"), "",
                            "Ver Detalles");
                    }
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

                // Buscar la factura completa usando el número de factura
                var facturas = await _controlador.ObtenerTodasFacturasPorClienteAsync();

                // Buscar la factura seleccionada por el número de factura
                var facturaSeleccionada = facturas
                    .SelectMany(f => f.Facturas)
                    .FirstOrDefault(f => f.NumeroFactura == numeroFactura);

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
