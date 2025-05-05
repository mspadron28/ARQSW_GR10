using System;
using System.Windows.Forms;

namespace ClienteEscritorioSOAP
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string username = txtUsername.Text.Trim();
            string password = txtPassword.Text;

            // Validación de las credenciales quemadas
            if (username == "MasterMonster" && password == "Monster9")
            {
                MessageBox.Show("Inicio de sesión exitoso", "Inicio de Sesión", MessageBoxButtons.OK, MessageBoxIcon.Information);

                // Abrir el formulario principal o realizar otras acciones según necesites
                // Ejemplo: abrir otro formulario
                MainForm form2 = new MainForm();
                this.Hide();
                form2.ShowDialog();
                this.Close();
            }
            else
            {
                MessageBox.Show("Credenciales incorrectas. Por favor, inténtelo nuevamente.", "Inicio de Sesión Fallido", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
