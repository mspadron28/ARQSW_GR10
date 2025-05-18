package ec.edu.monster.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Clase principal para lanzar la aplicación de escritorio.
 * @author MATIAS
 */
public class MainApp extends JFrame {

    public MainApp() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("EurekaBank Cliente de Escritorio");
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("EurekaBank Cliente");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel principal con botones
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xf6f7f6));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton consultaButton = new JButton("Consulta de Movimientos");
        consultaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        consultaButton.setPreferredSize(new Dimension(200, 40));
        consultaButton.addActionListener(this::openConsultaView);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(consultaButton, gbc);

        JButton depositoButton = new JButton("Registrar Depósito");
        depositoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        depositoButton.setPreferredSize(new Dimension(200, 40));
        depositoButton.addActionListener(this::openDepositoView);
        gbc.gridy = 1;
        mainPanel.add(depositoButton, gbc);

        JButton retiroButton = new JButton("Registrar Retiro");
        retiroButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        retiroButton.setPreferredSize(new Dimension(200, 40));
        retiroButton.addActionListener(this::openRetiroView);
        gbc.gridy = 2;
        mainPanel.add(retiroButton, gbc);

        JButton transferenciaButton = new JButton("Realizar Transferencia");
        transferenciaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        transferenciaButton.setPreferredSize(new Dimension(200, 40));
        transferenciaButton.addActionListener(this::openTransferenciaView);
        gbc.gridy = 3;
        mainPanel.add(transferenciaButton, gbc);

        JButton saldoButton = new JButton("Verificar Saldo");
        saldoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saldoButton.setPreferredSize(new Dimension(200, 40));
        saldoButton.addActionListener(this::openVerificarSaldoView);
        gbc.gridy = 4;
        mainPanel.add(saldoButton, gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void openConsultaView(ActionEvent e) {
        ConsultaView consultaView = new ConsultaView(this);
        consultaView.setVisible(true);
    }

    private void openDepositoView(ActionEvent e) {
        DepositoView depositoView = new DepositoView(this);
        depositoView.setVisible(true);
    }

    private void openRetiroView(ActionEvent e) {
        RetiroView retiroView = new RetiroView(this);
        retiroView.setVisible(true);
    }

    private void openTransferenciaView(ActionEvent e) {
        TransferenciaView transferenciaView = new TransferenciaView(this);
        transferenciaView.setVisible(true);
    }

    private void openVerificarSaldoView(ActionEvent e) {
        VerificarSaldoView verificarSaldoView = new VerificarSaldoView(this);
        verificarSaldoView.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}