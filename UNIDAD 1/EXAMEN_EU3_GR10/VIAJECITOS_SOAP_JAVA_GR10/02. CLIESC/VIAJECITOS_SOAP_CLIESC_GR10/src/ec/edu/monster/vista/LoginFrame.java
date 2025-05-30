package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {

    private final ViajecitosController controlador;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JLabel lblError;
    // Paleta de colores profesional con tonos pastel
    private final Color HEADER_START = new Color(163, 191, 250); // Azul pastel #A3BFFA
    private final Color HEADER_END = new Color(191, 219, 254); // Celeste pastel #BFDBFE
    private final Color BACKGROUND = new Color(241, 245, 249); // Gris claro #F1F5F9
    private final Color BUTTON_BG = new Color(191, 219, 254); // Celeste pastel para botones
    private final Color BUTTON_HOVER = new Color(147, 197, 253); // Azul pastel claro para hover
    private final Color TEXT_DARK = new Color(31, 41, 55); // Gris oscuro para texto
    private final Color WHITE = Color.WHITE;
    private final Color SHADOW = new Color(0, 0, 0, 50); // Sombra suave
    private final Color ERROR_RED = new Color(220, 38, 38); // Rojo suave para errores

    public LoginFrame() {
        controlador = new ViajecitosController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar para escalabilidad
        setLayout(new BorderLayout(20, 20)); // Espaciado consistente
        getContentPane().setBackground(BACKGROUND);

        // Encabezado con gradiente pastel
        JPanel headerPanel = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, HEADER_START, getWidth(), 0, HEADER_END));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Padding refinado
        JLabel lblHeaderImage = new JLabel(new ImageIcon("images/travel-agency.jpg"));
        lblHeaderImage.setPreferredSize(new Dimension(300, 100)); // Tamaño consistente
        headerPanel.add(lblHeaderImage, BorderLayout.WEST);

        JPanel headerTextPanel = new JPanel();
        headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.Y_AXIS));
        headerTextPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Viajecitos SA");
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 36)); // Más grande para impacto
        lblTitle.setForeground(WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblSubtitle = new JLabel("Encuentra y compra tus boletos de avión de forma fácil y segura");
        lblSubtitle.setFont(new Font("Roboto", Font.PLAIN, 20)); // Legible y claro
        lblSubtitle.setForeground(WHITE);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerTextPanel.add(lblTitle);
        headerTextPanel.add(lblSubtitle);
        headerPanel.add(headerTextPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Contenido principal
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40)); // Padding amplio
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;

        // Contenedor de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        Border outerBorder = new LineBorder(new Color(209, 213, 219), 1, true); // Borde gris suave
        Border innerBorder = new EmptyBorder(30, 30, 30, 30); // Padding interno
        Border shadowBorder = new CompoundBorder(
            new LineBorder(SHADOW, 4, true), // Sombra suave
            new CompoundBorder(outerBorder, innerBorder)
        );
        formPanel.setBorder(shadowBorder); // Borde con sombra
        formPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // Ancho limitado como formulario
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(12, 12, 12, 12);
        formGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFormTitle = new JLabel("Iniciar Sesión");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        lblFormTitle.setForeground(TEXT_DARK);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formPanel.add(lblFormTitle, formGbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        lblUsuario.setForeground(TEXT_DARK);
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.gridwidth = 1;
        formPanel.add(lblUsuario, formGbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setBackground(new Color(243, 244, 246)); // Fondo gris claro
        txtUsuario.setForeground(TEXT_DARK);
        txtUsuario.setBorder(new CompoundBorder(
            new LineBorder(new Color(147, 197, 253), 1, true), // Borde azul pastel
            new EmptyBorder(8, 8, 8, 8) // Padding interno
        ));
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.gridwidth = 2;
        formPanel.add(txtUsuario, formGbc);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setFont(new Font("Roboto", Font.PLAIN, 16));
        lblClave.setForeground(TEXT_DARK);
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        formGbc.gridwidth = 1;
        formPanel.add(lblClave, formGbc);

        txtClave = new JPasswordField(20);
        txtClave.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtClave.setBackground(new Color(243, 244, 246)); // Fondo gris claro
        txtClave.setForeground(TEXT_DARK);
        txtClave.setBorder(new CompoundBorder(
            new LineBorder(new Color(147, 197, 253), 1, true), // Borde azul pastel
            new EmptyBorder(8, 8, 8, 8) // Padding interno
        ));
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
        lblError.setForeground(ERROR_RED);
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
        button.setBackground(BUTTON_BG); // Celeste pastel
        button.setForeground(Color.BLACK); // Texto negro
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        Border outer = new LineBorder(new Color(147, 197, 253), 1, true); // Borde azul pastel claro
        Border inner = new EmptyBorder(14, 28, 14, 28); // Padding amplio
        button.setBorder(new CompoundBorder(outer, inner));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER); // Hover más oscuro
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BG);
            }
        });
        return button;
    }

    private JButton createLinkButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setForeground(new Color(59, 130, 246)); // Azul moderno para enlaces
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(29, 78, 216)); // Azul más oscuro para hover
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(59, 130, 246));
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