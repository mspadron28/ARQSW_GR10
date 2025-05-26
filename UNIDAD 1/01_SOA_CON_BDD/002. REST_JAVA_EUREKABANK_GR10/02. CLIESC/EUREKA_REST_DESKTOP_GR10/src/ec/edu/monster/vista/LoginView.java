package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Vista para el inicio de sesión de EurekaBank.
 * @author MATIAS
 */
public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciarSesion;
    private EurekaController controller;

    public LoginView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("EurekaBank - Inicio de Sesión");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar por defecto
        controller = new EurekaController();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600, 600));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("EurekaBank - Inicio de Sesión");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Main container panel with margins
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Main panel with form
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xf6f7f6));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0; // Distribuir espacio vertical

        // Welcome message
        JLabel welcomeLabel = new JLabel("Bienvenido a EurekaBank");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(welcomeLabel, gbc);

        JLabel subtextLabel = new JLabel("Ingresa tus credenciales");
        subtextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtextLabel.setForeground(new Color(0x666666));
        subtextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(subtextLabel, gbc);

        // Avatar image
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

        // Username field
        JLabel userLabel = new JLabel("Usuario");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(userLabel, gbc);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(new LineBorder(new Color(0xb1c5c7), 1));
        txtUsuario.setPreferredSize(new Dimension(300, 40));
        txtUsuario.setMaximumSize(new Dimension(350, 40));
        gbc.gridy = 4;
        mainPanel.add(txtUsuario, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 5;
        mainPanel.add(passwordLabel, gbc);

        txtContraseña = new JPasswordField();
        txtContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtContraseña.setBorder(new LineBorder(new Color(0xb1c5c7), 1));
        txtContraseña.setPreferredSize(new Dimension(300, 40));
        txtContraseña.setMaximumSize(new Dimension(350, 40));
        gbc.gridy = 6;
        mainPanel.add(txtContraseña, gbc);

        // Login button
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciarSesion.setBackground(new Color(0x202224));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setPreferredSize(new Dimension(300, 50));
        btnIniciarSesion.setMaximumSize(new Dimension(350, 50));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.addActionListener(this::btnIniciarSesionActionPerformed);
        gbc.gridy = 7;
        mainPanel.add(btnIniciarSesion, gbc);

        containerPanel.add(mainPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        add(containerPanel, BorderLayout.CENTER);

        pack();
    }

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtContraseña.getPassword()).trim();

            if (usuario.isEmpty() || clave.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = controller.iniciarSesion(usuario, clave);
            if (success) {
                this.dispose();
                SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}