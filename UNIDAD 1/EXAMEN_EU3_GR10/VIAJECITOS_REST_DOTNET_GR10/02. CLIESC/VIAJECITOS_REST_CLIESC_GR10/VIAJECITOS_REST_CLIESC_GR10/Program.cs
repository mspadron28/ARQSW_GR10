using System;
using System.Windows.Forms;
using VIAJECITOS_REST_CLIESC_GR10.Controlador;
using VIAJECITOS_REST_CLIESC_GR10.Vista;

namespace VIAJECITOS_REST_CLIESC_GR10
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            var controlador = new ViajecitosController();
            Application.Run(new LoginForm(controlador));
        }
    }
}
