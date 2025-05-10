package ec.edu.monster.vista;

import ec.edu.monster.controlador.AppControlador;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author MATIAS
 */
public class LoginVista extends javax.swing.JFrame {

    private AppControlador controlador;

    public LoginVista() {
        initComponents();
        this.setLocationRelativeTo(null);
        controlador = new AppControlador();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar por defecto
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        txtUsuario = new JTextField();
        txtContraseña = new JPasswordField();
        btnIniciarSesión = new JButton();

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        headerPanel.setPreferredSize(new Dimension(0, 50));

        JLabel headerLabel = new JLabel("Inicio de sesión");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel contenedor principal con márgenes
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel principal con padding interno
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xf6f7f6));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel welcomeLabel = new JLabel("Bienvenido");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(welcomeLabel, gbc);

        JLabel subtextLabel = new JLabel("Por favor, ingresa tus credenciales");
        subtextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtextLabel.setForeground(new Color(0x666666));
        subtextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(subtextLabel, gbc);

        JLabel avatarLabel = new JLabel();
        try {
            ImageIcon avatarIcon = new ImageIcon(getClass().getResource("/images/sulley.png"));
            Image scaledImage = avatarIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            avatarLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            avatarLabel.setText("(Avatar no disponible)");
        }
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 2;
        mainPanel.add(avatarLabel, gbc);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(userLabel, gbc);

        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(new LineBorder(new Color(0xb1c5c7), 1));
        txtUsuario.setPreferredSize(new Dimension(250, 30));
        txtUsuario.setMaximumSize(new Dimension(300, 30));
        gbc.gridy = 4;
        mainPanel.add(txtUsuario, gbc);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 5;
        mainPanel.add(passwordLabel, gbc);

        txtContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtContraseña.setBorder(new LineBorder(new Color(0xb1c5c7), 1));
        txtContraseña.setPreferredSize(new Dimension(250, 30));
        txtContraseña.setMaximumSize(new Dimension(300, 30));
        gbc.gridy = 6;
        mainPanel.add(txtContraseña, gbc);

        btnIniciarSesión.setText("Iniciar Sesión");
        btnIniciarSesión.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciarSesión.setBackground(new Color(0x202224));
        btnIniciarSesión.setForeground(Color.WHITE);
        btnIniciarSesión.setPreferredSize(new Dimension(250, 40));
        btnIniciarSesión.setMaximumSize(new Dimension(300, 40));
        btnIniciarSesión.setFocusPainted(false);
        btnIniciarSesión.addActionListener(evt -> btnIniciarSesiónActionPerformed(evt));
        gbc.gridy = 7;
        mainPanel.add(btnIniciarSesión, gbc);

        containerPanel.add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(containerPanel, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(400, 500));
    }

    private void btnIniciarSesiónActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String user = txtUsuario.getText();
            String password = new String(txtContraseña.getPassword());

            if (user.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = controlador.login(user, password);

            if (success) {
                this.dispose();
                new CONUNIVista().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new LoginVista().setVisible(true));
    }

    private javax.swing.JButton btnIniciarSesión;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtContraseña;
}