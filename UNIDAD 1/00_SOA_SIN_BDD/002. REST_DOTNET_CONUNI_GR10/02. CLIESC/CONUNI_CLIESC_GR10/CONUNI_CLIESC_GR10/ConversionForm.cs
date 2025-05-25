using System;
using System.Net.Http;
using System.Text.Json;
using System.Windows.Forms;

namespace CONUNI_CLIESC_GR10
{
    public partial class ConversionForm : Form
    {
        private static readonly HttpClient client = new HttpClient(); private const string BaseUrl = "http://10.40.23.154:5000/api/Conversion/";
        public ConversionForm()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized; // Maximizar el formulario
            this.FormBorderStyle = FormBorderStyle.FixedSingle; // Evitar redimensionar manualmente
            this.MaximizeBox = true; // Mantener botón de maximizar
            this.MinimumSize = new Size(600, 600); // Tamaño mínimo
            this.StartPosition = FormStartPosition.CenterScreen; // Centrar al abrir
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
                string url = string.Empty;

                switch (selectedOption)
                {
                    case "Pulgadas a Centímetros":
                        url = $"{BaseUrl}pulgadas-a-centimetros?pulgadas={inputValue}";
                        inputUnit = "in";
                        outputUnit = "cm";
                        break;
                    case "Centímetros a Pulgadas":
                        url = $"{BaseUrl}centimetros-a-pulgadas?centimetros={inputValue}";
                        inputUnit = "cm";
                        outputUnit = "in";
                        break;
                    case "Metros a Pies":
                        url = $"{BaseUrl}metros-a-pies?metros={inputValue}";
                        inputUnit = "m";
                        outputUnit = "ft";
                        break;
                    case "Pies a Metros":
                        url = $"{BaseUrl}pies-a-metros?pies={inputValue}";
                        inputUnit = "ft";
                        outputUnit = "m";
                        break;
                    case "Metros a Yardas":
                        url = $"{BaseUrl}metros-a-yardas?metros={inputValue}";
                        inputUnit = "m";
                        outputUnit = "yd";
                        break;
                    case "Yardas a Metros":
                        url = $"{BaseUrl}yardas-a-metros?yardas={inputValue}";
                        inputUnit = "yd";
                        outputUnit = "m";
                        break;
                }

                HttpResponseMessage response = await client.GetAsync(url);
                response.EnsureSuccessStatusCode();
                string responseBody = await response.Content.ReadAsStringAsync();
                resultValue = JsonSerializer.Deserialize<double>(responseBody);

                resultLabel.Text = $"{inputValue} {inputUnit} = {resultValue:F5} {outputUnit}.";
            }
            catch (HttpRequestException ex)
            {
                MessageBox.Show($"Ocurrió un error al realizar la conversión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void BtnSalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void conversionSelector_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void inputField_TextChanged(object sender, EventArgs e)
        {

        }

        private void resultLabel_Click(object sender, EventArgs e)
        {

        }

        private void sulleyLabel_Click(object sender, EventArgs e)
        {

        }

        private void containerPanel_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}