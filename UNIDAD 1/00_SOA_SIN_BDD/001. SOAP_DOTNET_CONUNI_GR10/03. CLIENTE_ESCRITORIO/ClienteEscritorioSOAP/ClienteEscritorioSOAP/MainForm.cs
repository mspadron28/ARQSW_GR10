using MaterialSkin.Controls;
using System;
using System.Drawing;
using System.Windows.Forms;
using ClienteEscritorioSOAP.ConversionService;

namespace ClienteEscritorioSOAP
{
    public partial class MainForm : MaterialForm
    {
        private ConversionServiceClient client;

        public MainForm()
        {
            InitializeComponent();
            var skinManager = MaterialSkin.MaterialSkinManager.Instance;
            skinManager.AddFormToManage(this);
            skinManager.Theme = MaterialSkin.MaterialSkinManager.Themes.LIGHT;
            skinManager.ColorScheme = new MaterialSkin.ColorScheme(
                MaterialSkin.Primary.Blue800, MaterialSkin.Primary.Blue900,
                MaterialSkin.Primary.Blue500, MaterialSkin.Accent.LightBlue200,
                MaterialSkin.TextShade.WHITE
            );

            client = new ConversionServiceClient(); // Inicializar el cliente del servicio SOAP
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            // Rellenar el ComboBox con opciones de conversión
            ddlConversionType.Items.Add("Centímetros a Pulgadas");
            ddlConversionType.Items.Add("Pulgadas a Centímetros");
            ddlConversionType.Items.Add("Metros a Pies");
            ddlConversionType.Items.Add("Pies a Metros");
            ddlConversionType.SelectedIndex = 0; // Selecciona la primera opción por defecto
        }

        private void btnConvert_Click(object sender, EventArgs e)
        {
            double inputValue;
            if (double.TryParse(txtInput.Text, out inputValue))
            {
                double resultValue = 0;
                string inputUnit = string.Empty;
                string outputUnit = string.Empty;

                try
                {
                    switch (ddlConversionType.SelectedItem.ToString())
                    {
                        case "Centímetros a Pulgadas":
                            resultValue = client.CentimetersToInches(inputValue);
                            inputUnit = "cm";
                            outputUnit = "in";
                            break;
                        case "Pulgadas a Centímetros":
                            resultValue = client.InchesToCentimeters(inputValue);
                            inputUnit = "in";
                            outputUnit = "cm";
                            break;
                        case "Metros a Pies":
                            resultValue = client.MetersToYards(inputValue);
                            inputUnit = "m";
                            outputUnit = "yd";
                            break;
                        case "Pies a Metros":
                            resultValue = client.YardsToMeters(inputValue);
                            inputUnit = "yd";
                            outputUnit = "m";
                            break;
                    }

                    lblResult.Text = $"{inputValue} {inputUnit} es equivalente a {resultValue:F5} {outputUnit}.";
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Ocurrió un error al llamar al servicio de conversión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                MessageBox.Show("Por favor, ingrese un valor numérico válido.", "Error de entrada", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
