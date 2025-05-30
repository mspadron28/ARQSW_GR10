package viajecitos_soap_cliesc_gr10;

import ec.edu.monster.vista.MainFrame;
import ec.edu.monster.controlador.ViajecitosController;
import javax.swing.UIManager;

/**
 * Main class for Viajecitos SA desktop client.
 *
 * @author MATIAS
 */
public class VIAJECITOS_SOAP_CLIESC_GR10 {

    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch main frame
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame(new ViajecitosController()).setVisible(true);
        });
    }
}