package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Vista para verificar el saldo de una cuenta.
 * @author MATIAS
 */
public class VerificarSaldoView extends JFrame {

    private final EurekaController controlador;
    private final MainApp mainApp;
    private JTextField txtCuenta;
    private JLabel lblSaldo;

    public VerificarSaldoView(MainApp mainApp) {
        this.mainApp = mainApp;
        controlador = new EurekaController();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Verificar Saldo");
        mainApp.setVisible(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("Verificar Saldo");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0xf6f7f6));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cuenta
        JLabel lblCuenta = new JLabel("Cuenta:");
        lblCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(lblCuenta, gbc);

        txtCuenta = new JTextField(10);
        txtCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuenta.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(txtCuenta, gbc);

        // Saldo
        JLabel lblSaldoText = new JLabel("Saldo:");
        lblSaldoText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblSaldoText, gbc);

        lblSaldo = new JLabel("0.00");
        lblSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSaldo.setBorder(new LineBorder(Color.BLACK, 1));
        lblSaldo.setPreferredSize(new Dimension(120, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(lblSaldo, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xf6f7f6));

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVerificar.setBackground(new Color(0x202224));
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setPreferredSize(new Dimension(120, 30));
        btnVerificar.addActionListener(e -> verificarSaldo());
        buttonPanel.add(btnVerificar);

        JButton btnReturn = new JButton("Volver al Menú");
        btnReturn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReturn.setBackground(new Color(0x202224));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setPreferredSize(new Dimension(150, 30));
        btnReturn.addActionListener(e -> returnToMain());
        buttonPanel.add(btnReturn);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(buttonPanel, gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void verificarSaldo() {
        try {
            String cuenta = txtCuenta.getText().trim();
            if (cuenta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un número de cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double saldo = controlador.verificarSaldo(cuenta);
            lblSaldo.setText(String.format("%.2f", saldo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al verificar el saldo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnToMain() {
        mainApp.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VerificarSaldoView(new MainApp()).setVisible(true));
    }
}