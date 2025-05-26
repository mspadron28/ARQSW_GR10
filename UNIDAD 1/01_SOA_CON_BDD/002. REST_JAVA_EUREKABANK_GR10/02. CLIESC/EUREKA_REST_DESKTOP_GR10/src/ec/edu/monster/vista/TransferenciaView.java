package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Vista para registrar una transferencia entre cuentas.
 * @author MATIAS
 */
public class TransferenciaView extends JFrame {

    private final EurekaController controlador;
    private final MainApp mainApp;
    private JTextField txtCuentaOrigen;
    private JTextField txtCuentaDestino;
    private JTextField txtImporte;
    private JComboBox<String> cbxCodEmp;

    public TransferenciaView(MainApp mainApp) {
        this.mainApp = mainApp;
        controlador = new EurekaController();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Registrar Transferencia");
        mainApp.setVisible(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("Registrar Transferencia");
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

        // Cuenta Origen
        JLabel lblCuentaOrigen = new JLabel("Cuenta Origen:");
        lblCuentaOrigen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(lblCuentaOrigen, gbc);

        txtCuentaOrigen = new JTextField(10);
        txtCuentaOrigen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuentaOrigen.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(txtCuentaOrigen, gbc);

        // Cuenta Destino
        JLabel lblCuentaDestino = new JLabel("Cuenta Destino:");
        lblCuentaDestino.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblCuentaDestino, gbc);

        txtCuentaDestino = new JTextField(10);
        txtCuentaDestino.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuentaDestino.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(txtCuentaDestino, gbc);

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

        // Código de empleado
        JLabel lblCodEmp = new JLabel("Código de Empleado:");
        lblCodEmp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(lblCodEmp, gbc);

        String[] codigos = new String[14];
        for (int i = 1; i <= 14; i++) {
            codigos[i-1] = String.format("%04d", i);
        }
        cbxCodEmp = new JComboBox<>(codigos);
        cbxCodEmp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbxCodEmp.setPreferredSize(new Dimension(120, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(cbxCodEmp, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xf6f7f6));

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTransferir.setBackground(new Color(0x202224));
        btnTransferir.setForeground(Color.WHITE);
        btnTransferir.setPreferredSize(new Dimension(120, 30));
        btnTransferir.addActionListener(e -> realizarTransferencia());
        buttonPanel.add(btnTransferir);

        JButton btnReturn = new JButton("Volver al Menú");
        btnReturn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReturn.setBackground(new Color(0x202224));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setPreferredSize(new Dimension(150, 30));
        btnReturn.addActionListener(e -> returnToMain());
        buttonPanel.add(btnReturn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(buttonPanel, gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void realizarTransferencia() {
        try {
            String cuentaOrigen = txtCuentaOrigen.getText().trim();
            String cuentaDestino = txtCuentaDestino.getText().trim();
            String importeText = txtImporte.getText().trim().replace(",", ".");
            String codEmp = (String) cbxCodEmp.getSelectedItem();

            if (cuentaOrigen.isEmpty() || cuentaDestino.isEmpty() || importeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double importe = Double.parseDouble(importeText);
            if (importe <= 0) {
                JOptionPane.showMessageDialog(this, "El importe debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double costo = controlador.obtenerCostoMovimiento(cuentaOrigen);
            int confirm = JOptionPane.showConfirmDialog(this,
                String.format("El costo del movimiento es: %.2f\n¿Desea continuar?", costo),
                "Confirmar Transferencia", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            controlador.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            JOptionPane.showMessageDialog(this, "Transferencia realizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtCuentaOrigen.setText("");
            txtCuentaDestino.setText("");
            txtImporte.setText("");
            cbxCodEmp.setSelectedIndex(0);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un importe válido (use punto como separador decimal).", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la transferencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnToMain() {
        mainApp.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TransferenciaView(new MainApp()).setVisible(true));
    }
}