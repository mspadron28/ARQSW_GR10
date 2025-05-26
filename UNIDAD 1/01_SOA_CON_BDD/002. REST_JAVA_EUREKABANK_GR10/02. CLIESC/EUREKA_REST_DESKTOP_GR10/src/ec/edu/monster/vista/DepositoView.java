package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Vista para registrar un depósito en una cuenta.
 * @author MATIAS
 */
public class DepositoView extends JFrame {

    private final EurekaController controlador;
    private final MainApp mainApp;
    private JTextField txtCodEmp;
    private JTextField txtCuenta;
    private JTextField txtImporte;

    public DepositoView(MainApp mainApp) {
        this.mainApp = mainApp;
        controlador = new EurekaController();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Registrar Depósito");
        mainApp.setVisible(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("Registrar Depósito");
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

        // Código de empleado
        JLabel lblCodEmp = new JLabel("Código de Empleado:");
        lblCodEmp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(lblCodEmp, gbc);

        txtCodEmp = new JTextField(10);
        txtCodEmp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCodEmp.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(txtCodEmp, gbc);

        // Cuenta
        JLabel lblCuenta = new JLabel("Cuenta:");
        lblCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblCuenta, gbc);

        txtCuenta = new JTextField(10);
        txtCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuenta.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(txtCuenta, gbc);

        // Importe
        JLabel lblImporte = new JLabel("Importe:");
        lblImporte.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblImporte, gbc);

        txtImporte = new JTextField(10);
        txtImporte.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtImporte.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(txtImporte, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xf6f7f6));

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDepositar.setBackground(new Color(0x202224));
        btnDepositar.setForeground(Color.WHITE);
        btnDepositar.setPreferredSize(new Dimension(120, 30));
        btnDepositar.addActionListener(e -> registrarDeposito());
        buttonPanel.add(btnDepositar);

        JButton btnReturn = new JButton("Volver al Menú");
        btnReturn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReturn.setBackground(new Color(0x202224));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setPreferredSize(new Dimension(150, 30));
        btnReturn.addActionListener(e -> returnToMain());
        buttonPanel.add(btnReturn);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(buttonPanel, gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void registrarDeposito() {
        try {
            String codEmp = txtCodEmp.getText().trim();
            String cuenta = txtCuenta.getText().trim();
            String importeText = txtImporte.getText().trim().replace(",", ".");

            if (codEmp.isEmpty() || cuenta.isEmpty() || importeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double importe = Double.parseDouble(importeText);
            if (importe <= 0) {
                JOptionPane.showMessageDialog(this, "El importe debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controlador.registrarDeposito(cuenta, importe, codEmp);
            JOptionPane.showMessageDialog(this, "Depósito registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtCodEmp.setText("");
            txtCuenta.setText("");
            txtImporte.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un importe válido (use punto como separador decimal).", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el depósito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnToMain() {
        mainApp.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DepositoView(new MainApp()).setVisible(true));
    }
}