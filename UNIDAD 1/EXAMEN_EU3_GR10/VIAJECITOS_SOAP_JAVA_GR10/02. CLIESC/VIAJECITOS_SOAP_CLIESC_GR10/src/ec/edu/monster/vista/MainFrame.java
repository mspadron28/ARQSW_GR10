package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private final ViajecitosController controlador;

    public MainFrame(ViajecitosController controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600); // Tamaño fijo similar al contenedor del JSP
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
        gbc.fill = GridBagConstraints.BOTH;

        // Menu Card
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);
        GridBagConstraints menuGbc = new GridBagConstraints();
        menuGbc.insets = new Insets(15, 15, 15, 15);
        menuGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMenuTitle = new JLabel("Bienvenido a Viajecitos SA");
        lblMenuTitle.setFont(new Font("Roboto", Font.BOLD, 24));
        lblMenuTitle.setForeground(Color.BLACK);
        menuGbc.gridx = 0;
        menuGbc.gridy = 0;
        menuPanel.add(lblMenuTitle, menuGbc);

        JButton btnSearchFlights = createStyledButton("Buscar Vuelos");
        btnSearchFlights.addActionListener(e -> openSearchFrame());
        menuGbc.gridx = 0;
        menuGbc.gridy = 1;
        menuPanel.add(btnSearchFlights, menuGbc);

        JButton btnViewPurchases = createStyledButton("Ver Mis Compras");
        btnViewPurchases.addActionListener(e -> openPurchasesFrame());
        btnViewPurchases.setEnabled(false);
        try {
            controlador.getIdClienteAutenticado();
            btnViewPurchases.setEnabled(true);
        } catch (Exception ignored) {}
        menuGbc.gridx = 0;
        menuGbc.gridy = 2;
        menuPanel.add(btnViewPurchases, menuGbc);

        JButton btnLogin = createStyledButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> openLoginFrame());
        menuGbc.gridx = 0;
        menuGbc.gridy = 3;
        menuPanel.add(btnLogin, menuGbc);

        JButton btnRegister = createStyledButton("Registrarse");
        btnRegister.addActionListener(e -> openRegisterFrame());
        menuGbc.gridx = 0;
        menuGbc.gridy = 4;
        menuPanel.add(btnRegister, menuGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPanel.add(menuPanel, gbc);

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