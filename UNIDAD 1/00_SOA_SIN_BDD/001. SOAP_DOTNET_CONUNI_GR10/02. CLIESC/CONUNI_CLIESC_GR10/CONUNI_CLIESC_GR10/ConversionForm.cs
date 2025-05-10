using System;
using System.Windows.Forms;
using ConversionServiceReference;

namespace CONUNI_CLIESC_GR10
{
    public partial class ConversionForm : Form
    {
        private ConversionServiceClient client;

        public ConversionForm()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized; // Maximizar el formulario
            this.FormBorderStyle = FormBorderStyle.FixedSingle; // Evitar redimensionar manualmente
            this.MaximizeBox = true; // Mantener botón de maximizar
            this.MinimumSize = new Size(600, 600); // Tamaño mínimo
            this.StartPosition = FormStartPosition.CenterScreen; // Centrar al abrir
            client = new ConversionServiceClient();
        }

        private void ConversionForm_Load(object sender, EventArgs e)
        {
            // Rellenar el ComboBox con opciones de conversión
            conversionSelector.Items.AddRange(new string[]
            {
                "Pulgadas a Centímetros",
                "Centímetros a Pulgadas",
                "Metros a Pies",
                "Pies a Metros",
                "Metros a Yardas",
                "Yardas a Metros"
            });
            conversionSelector.SelectedIndex = 0; // Selecciona la primera opción por defecto
        }

        private async void ConvertButton_Click(object sender, EventArgs e)
        {
            double inputValue;
            if (string.IsNullOrWhiteSpace(inputField.Text))
            {
                MessageBox.Show("Por favor, ingrese un valor para convertir.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (!double.TryParse(inputField.Text, out inputValue))
            {
                MessageBox.Show("Por favor, ingrese un valor numérico válido.", "Error de entrada", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (inputValue < 0)
            {
                MessageBox.Show("Por favor, ingrese un valor no negativo.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                double resultValue = 0;
                string inputUnit = string.Empty;
                string outputUnit = string.Empty;
                string selectedOption = conversionSelector.SelectedItem.ToString();

                using (client = new ConversionServiceClient())
                {
                    switch (selectedOption)
                    {
                        case "Pulgadas a Centímetros":
                            resultValue = await client.pulgadasACentimetrosAsync(inputValue);
                            inputUnit = "in";
                            outputUnit = "cm";
                            break;
                        case "Centímetros a Pulgadas":
                            resultValue = await client.centimetrosAPulgadasAsync(inputValue);
                            inputUnit = "cm";
                            outputUnit = "in";
                            break;
                        case "Metros a Pies":
                            resultValue = await client.metrosAPiesAsync(inputValue);
                            inputUnit = "m";
                            outputUnit = "ft";
                            break;
                        case "Pies a Metros":
                            resultValue = await client.piesAMetrosAsync(inputValue);
                            inputUnit = "ft";
                            outputUnit = "m";
                            break;
                        case "Metros a Yardas":
                            resultValue = await client.metrosAYardasAsync(inputValue);
                            inputUnit = "m";
                            outputUnit = "yd";
                            break;
                        case "Yardas a Metros":
                            resultValue = await client.yardasAMetrosAsync(inputValue);
                            inputUnit = "yd";
                            outputUnit = "m";
                            break;
                    }
                }

                resultLabel.Text = $"{inputValue} {inputUnit} = {resultValue:F5} {outputUnit}.";
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ocurrió un error al llamar al servicio de conversión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void BtnSalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}