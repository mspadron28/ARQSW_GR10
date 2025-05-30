package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SearchFlightsFrame extends JFrame {

    private final ViajecitosController controlador;
    private JComboBox<String> cbOrigen, cbDestino;
    private JSpinner dateSpinner;
    private JButton btnViewPurchases;
    private JLabel lblError;

    public SearchFlightsFrame(ViajecitosController controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Buscar Vuelos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("images/background.jpg");
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Navigation Bar
        JPanel navPanel = createNavPanel();
        backgroundPanel.add(navPanel, BorderLayout.NORTH);

        // Main Content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;

        // Form Card
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 230));
        formPanel.setBorder(new LineBorder(new Color(226, 232, 240), 2, true));
        formPanel.setPreferredSize(new Dimension(600, 400));
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(15, 15, 15, 15);
        formGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFormTitle = new JLabel("Buscar Vuelos", SwingConstants.CENTER);
        lblFormTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblFormTitle.setForeground(new Color(30, 58, 138));
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 3;
        formPanel.add(lblFormTitle, formGbc);

        btnViewPurchases = createStyledButton("Ver Mis Compras", new Color(255, 182, 193), "icons/ticket.png");
        btnViewPurchases.addActionListener(e -> openPurchasesFrame());
        btnViewPurchases.setVisible(false);
        try {
            controlador.getIdClienteAutenticado();
            btnViewPurchases.setVisible(true);
        } catch (Exception ignored) {}
        formGbc.gridx = 2;
        formGbc.gridy = 0;
        formGbc.gridwidth = 1;
        formPanel.add(btnViewPurchases, formGbc);

        JLabel lblOrigen = new JLabel("Ciudad Origen:");
        lblOrigen.setFont(new Font("SansSerif", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.gridwidth = 1;
        formPanel.add(lblOrigen, formGbc);

        String[] ciudades = {"Selecciona una ciudad", "Bogotá", "Medellín", "Buenos Aires", "Córdoba", "Quito", "Guayaquil", "Cali", "Cartagena", "Mendoza"};
        cbOrigen = new JComboBox<>(ciudades);
        cbOrigen.setFont(new Font("SansSerif", Font.PLAIN, 16));
        formGbc.gridx = 1;
        formGbc.gridy = 1;
        formGbc.gridwidth = 2;
        formPanel.add(cbOrigen, formGbc);

        JLabel lblDestino = new JLabel("Ciudad Destino:");
        lblDestino.setFont(new Font("SansSerif", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.gridwidth = 1;
        formPanel.add(lblDestino, formGbc);

        cbDestino = new JComboBox<>(ciudades);
        cbDestino.setFont(new Font("SansSerif", Font.PLAIN, 16));
        formGbc.gridx = 1;
        formGbc.gridy = 2;
        formGbc.gridwidth = 2;
        formPanel.add(cbDestino, formGbc);

        JLabel lblFecha = new JLabel("Fecha de Viaje:");
        lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 16));
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        formGbc.gridwidth = 1;
        formPanel.add(lblFecha, formGbc);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        formGbc.gridx = 1;
        formGbc.gridy = 3;
        formGbc.gridwidth = 2;
        formPanel.add(dateSpinner, formGbc);

        JButton btnSearch = createStyledButton("Buscar", new Color(249, 115, 22), "icons/search.png");
        btnSearch.addActionListener(e -> searchFlights());
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        formGbc.gridwidth = 3;
        formPanel.add(btnSearch, formGbc);

        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(new Color(220, 38, 38));
        lblError.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formGbc.gridx = 0;
        formGbc.gridy = 5;
        formGbc.gridwidth = 3;
        formPanel.add(lblError, formGbc);

        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        linkPanel.setOpaque(false);
        JButton btnLogin = createLinkButton("Iniciar Sesión", e -> openLoginFrame());
        JButton btnRegister = createLinkButton("Registrarse", e -> openRegisterFrame());
        linkPanel.add(btnLogin);
        linkPanel.add(btnRegister);
        formGbc.gridx = 0;
        formGbc.gridy = 6;
        formGbc.gridwidth = 3;
        formPanel.add(linkPanel, formGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPanel.add(formPanel, gbc);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = createFooterPanel();
        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createNavPanel() {
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(new Color(30, 58, 138));
        navPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblLogo = new JLabel(new ImageIcon("images/logo.png"));
        navPanel.add(lblLogo, BorderLayout.WEST);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        JLabel lblUser = new JLabel("Invitado");
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 16));
        userPanel.add(lblUser);
        try {
            lblUser.setText("Usuario: " + controlador.getIdClienteAutenticado());
        } catch (Exception ignored) {}
        navPanel.add(userPanel, BorderLayout.EAST);

        return navPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(15, 23, 42));
        footerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        JLabel lblFooter = new JLabel("© 2025 Viajecitos SA | Contacto: info@viajecitossa.com | Síguenos en redes sociales");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerPanel.add(lblFooter);
        return footerPanel;
    }

    private JButton createStyledButton(String text, Color bgColor, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorder(new LineBorder(bgColor, 2, true));
        button.setPreferredSize(new Dimension(200, 50));
        if (iconPath != null) {
            button.setIcon(new ImageIcon(iconPath));
        }
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private JButton createLinkButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setForeground(new Color(45, 212, 191));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(94, 234, 212));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(45, 212, 191));
            }
        });
        return button;
    }

    private void searchFlights() {
        try {
            String origen = (String) cbOrigen.getSelectedItem();
            String destino = (String) cbDestino.getSelectedItem();
            if (origen.equals("Selecciona una ciudad") || destino.equals("Selecciona una ciudad")) {
                lblError.setText("Seleccione ciudades válidas.");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(((java.util.Date) dateSpinner.getValue()));
            new FlightResultFrame(controlador, controlador.obtenerVueloMasCaro(origen, destino, fecha)).setVisible(true);
            dispose();
        } catch (Exception ex) {
            lblError.setText("Error: " + ex.getMessage());
        }
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