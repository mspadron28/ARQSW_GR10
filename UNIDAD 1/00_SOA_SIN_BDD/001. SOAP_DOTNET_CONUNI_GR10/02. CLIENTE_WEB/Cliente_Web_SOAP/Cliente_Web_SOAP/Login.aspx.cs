using System;
using System.Web.UI;

namespace Cliente_Web_SOAP
{
    public partial class Login : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            // Validar usuario
            bool isValidUser = ValidateUser(txtUsername.Text, txtPassword.Text);

            if (isValidUser)
            {
                // Redirigir a la página principal si la validación es exitosa
                Response.Redirect("Default.aspx");
            }
            else
            {
                // Mostrar el modal de validación si la validación falla
                ScriptManager.RegisterStartupScript(this, GetType(), "showValidationModal", "showValidationModal();", true);
            }
        }

        private bool ValidateUser(string username, string password)
        {
            // Validar las credenciales quemadas
            return username == "MasterMonster" && password == "Monster9";
        }
    }
}
