package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private final ViajecitosController controlador;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JLabel lblError;

    public LoginFrame() {
        controlador = new ViajecitosController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700); // Tamaño fijo similar al contenedor del JSP
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        JLabel lblHeaderImage = new JLabel(new ImageIcon("images/travel-agency.jpg"));
        headerPanel.add(lblHeaderImage, BorderLayout.CENTER);

        JPanel headerTextPanel = new JPanel();
        headerTextPanel.setBackground(Color.BLACK);
        headerTextPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Viajecitos SA");
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        JLabel lblSubtitle = new JLabel("Encuentra y compra tus boletos de avión de forma fácil y segura");
        lblSubtitle.setFont(new Font("Roboto", Font.PLAIN, 16));
        lblSubtitle.setForeground(Color.WHITE);
        headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.Y_AXIS));
        headerTextPanel.add(lblTitle);
        headerTextPanel.add(lblSubtitle);
        headerPanel.add(headerTextPanel, BorderLayout.EAST);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(headerPanel, BorderLayout.NORTH);

        // Main Content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(15, 15, 15, 15);
        formGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFormTitle = new JLabel("Iniciar Sesión");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 24));
        lblFormTitle.setForeground(Color.BLACK);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formPanel.add(lblFormTitle, formGbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.gridwidth = 1;
        formPanel.add(lblUsuario, formGbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.gridwidth = 2;
        formPanel.add(txtUsuario, formGbc);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setFont(new Font("Roboto", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        formGbc.gridwidth = 1;
        formPanel.add(lblClave, formGbc);

        txtClave = new JPasswordField(20);
        txtClave.setFont(new Font("Roboto", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        formGbc.gridwidth = 2;
        formPanel.add(txtClave, formGbc);

        JButton btnLogin = createStyledButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> login());
        formGbc.gridx = 0;
        formGbc.gridy = 5;
        formGbc.gridwidth = 2;
        formPanel.add(btnLogin, formGbc);

        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Roboto", Font.PLAIN, 14));
        formGbc.gridx = 0;
        formGbc.gridy = 6;
        formGbc.gridwidth = 2;
        formPanel.add(lblError, formGbc);

        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        linkPanel.setOpaque(false);
        JButton btnRegister = createLinkButton("Registrarse", e -> openRegisterFrame());
        JButton btnBack = createLinkButton("Volver", e -> openMainFrame());
        linkPanel.add(btnRegister);
        linkPanel.add(btnBack);
        formGbc.gridx = 0;
        formGbc.gridy = 7;
        formGbc.gridwidth = 2;
        formPanel.add(linkPanel, formGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPanel.add(formPanel, gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLUE); // Color azul simple, ajustable según CSS del JSP
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Sin bordes rojos/verdes
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.BLUE);
            }
        });
        return button;
    }

    private JButton createLinkButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setForeground(Color.BLUE); // Color similar a los enlaces del JSP
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.BLUE.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLUE);
            }
        });
        return button;
    }

    private void login() {
        try {
            if (controlador.iniciarSesion(txtUsuario.getText(), new String(txtClave.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                new MainFrame(controlador).setVisible(true);
                dispose();
            } else {
                lblError.setText("Usuario o contraseña incorrectos.");
            }
        } catch (Exception ex) {
            lblError.setText("Error: " + ex.getMessage());
        }
    }

    private void openRegisterFrame() {
        new RegisterFrame().setVisible(true);
        dispose();
    }

    private void openMainFrame() {
        new MainFrame(controlador).setVisible(true);
        dispose();
    }
}