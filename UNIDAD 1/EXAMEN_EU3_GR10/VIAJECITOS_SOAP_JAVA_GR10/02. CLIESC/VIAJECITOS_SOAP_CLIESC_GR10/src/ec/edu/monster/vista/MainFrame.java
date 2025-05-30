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

public class MainFrame extends JFrame {

    private final ViajecitosController controlador;
    // Paleta de colores profesional con tonos pastel
    private final Color HEADER_START = new Color(163, 191, 250); // Azul pastel #A3BFFA
    private final Color HEADER_END = new Color(191, 219, 254); // Celeste pastel #BFDBFE
    private final Color BACKGROUND = new Color(241, 245, 249); // Gris claro #F1F5F9
    private final Color BUTTON_BG = new Color(191, 219, 254); // Celeste pastel para botones
    private final Color BUTTON_HOVER = new Color(147, 197, 253); // Azul pastel claro para hover
    private final Color TEXT_DARK = new Color(31, 41, 55); // Gris oscuro para texto
    private final Color WHITE = Color.WHITE;
    private final Color SHADOW = new Color(0, 0, 0, 50); // Sombra suave

    public MainFrame(ViajecitosController controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA");
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

        JLabel lblFormTitle = new JLabel("Bienvenido a Viajecitos SA");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        lblFormTitle.setForeground(TEXT_DARK);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formPanel.add(lblFormTitle, formGbc);

        JButton btnSearchFlights = createStyledButton("Buscar Vuelos");
        btnSearchFlights.addActionListener(e -> openSearchFrame());
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.weightx = 1.0;
        formPanel.add(btnSearchFlights, formGbc);

        JButton btnViewPurchases = createStyledButton("Ver Mis Compras");
        btnViewPurchases.addActionListener(e -> openPurchasesFrame());
        btnViewPurchases.setEnabled(false);
        try {
            controlador.getIdClienteAutenticado();
            btnViewPurchases.setEnabled(true);
        } catch (Exception ignored) {}
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formPanel.add(btnViewPurchases, formGbc);

        JButton btnLogin = createStyledButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> openLoginFrame());
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        formPanel.add(btnLogin, formGbc);

        JButton btnRegister = createStyledButton("Registrarse");
        btnRegister.addActionListener(e -> openRegisterFrame());
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        formPanel.add(btnRegister, formGbc);

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

    private void openSearchFrame() {
        new SearchFlightsFrame(controlador).setVisible(true);
        dispose();
    }

    private void openPurchasesFrame() {
        new PurchasesFrame(controlador).setVisible(true);
        dispose();
    }

    private void openLoginFrame() {
        new LoginFrame().setVisible(true);
        dispose();
    }

    private void openRegisterFrame() {
        new RegisterFrame().setVisible(true);
        dispose();
    }
}