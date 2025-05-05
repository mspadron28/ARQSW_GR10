using System;
using Cliente_Web_SOAP.ConversionUnidades;

namespace Cliente_Web_SOAP
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnConvert_Click(object sender, EventArgs e)
        {
            double input;
            if (double.TryParse(txtInput.Text, out input))
            {
                var client = new ConversionServiceClient(); // Asegúrate de usar el nombre correcto del cliente del servicio
                double result = 0;

                switch (ddlConversionType.SelectedValue)
                {
                    case "CentimetersToFeet":
                        result = client.CentimetersToFeet(input);
                        break;
                    case "FeetToCentimeters":
                        result = client.FeetToCentimeters(input);
                        break;
                    case "MetersToYards":
                        result = client.MetersToYards(input);
                        break;
                    case "YardsToMeters":
                        result = client.YardsToMeters(input);
                        break;
                    case "InchesToCentimeters":
                        result = client.InchesToCentimeters(input);
                        break;
                    case "CentimetersToInches":
                        result = client.CentimetersToInches(input);
                        break;
                }

                lblResult.Text = result.ToString();
                client.Close();
            }
            else
            {
                lblResult.Text = "Por favor, ingrese un valor numérico válido.";
            }
        }
    }
}
