package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import ec.edu.monster.ws.Compra;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;

public class PurchasesFrame extends JFrame {

    private final ViajecitosController controlador;
    private JTable table;
    private JLabel lblError;
    // Paleta de colores profesional con tonos pastel
    private final Color HEADER_START = new Color(163, 191, 250); // Azul pastel #A3BFFA
    private final Color HEADER_END = new Color(191, 219, 254); // Celeste pastel #BFDBFE
    private final Color BACKGROUND = new Color(241, 245, 249); // Gris claro #F1F5F9
    private final Color TABLE_BG = new Color(243, 244, 246); // Gris claro para tabla
    private final Color TEXT_DARK = new Color(31, 41, 55); // Gris oscuro para texto
    private final Color WHITE = Color.WHITE;
    private final Color SHADOW = new Color(0, 0, 0, 50); // Sombra suave
    private final Color ERROR_RED = new Color(220, 38, 38); // Rojo suave para errores

    public PurchasesFrame(ViajecitosController controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Mis Compras");
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
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Contenedor de tabla
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(WHITE);
        Border outerBorder = new LineBorder(new Color(209, 213, 219), 1, true); // Borde gris suave
        Border innerBorder = new EmptyBorder(30, 30, 30, 30); // Padding interno
        Border shadowBorder = new CompoundBorder(
            new LineBorder(SHADOW, 4, true), // Sombra suave
            new CompoundBorder(outerBorder, innerBorder)
        );
        tablePanel.setBorder(shadowBorder); // Borde con sombra
        tablePanel.setMaximumSize(new Dimension(800, Integer.MAX_VALUE)); // Ancho limitado para tabla
        GridBagConstraints tableGbc = new GridBagConstraints();
        tableGbc.insets = new Insets(12, 12, 12, 12);
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 1.0;

        JLabel lblFormTitle = new JLabel("Mis Compras");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        lblFormTitle.setForeground(TEXT_DARK);
        tableGbc.gridx = 0;
        tableGbc.gridy = 0;
        tableGbc.gridwidth = 2;
        tablePanel.add(lblFormTitle, tableGbc);

        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(ERROR_RED);
        lblError.setFont(new Font("Roboto", Font.PLAIN, 14));
        tableGbc.gridx = 0;
        tableGbc.gridy = 1;
        tablePanel.add(lblError, tableGbc);

        table = new JTable();
        table.setFont(new Font("Roboto", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(TABLE_BG); // Fondo gris claro
        table.setForeground(TEXT_DARK);
        table.setBorder(new LineBorder(new Color(147, 197, 253), 1, true)); // Borde azul pastel
        table.setGridColor(new Color(209, 213, 219)); // Líneas de cuadrícula grises
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        header.setBackground(HEADER_START); // Gradiente en encabezado
        header.setForeground(WHITE);
        header.setBorder(new LineBorder(new Color(147, 197, 253), 1, true)); // Borde azul pastel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new CompoundBorder(
            new LineBorder(new Color(147, 197, 253), 1, true), // Borde azul pastel
            new EmptyBorder(8, 8, 8, 8) // Padding interno
        ));
        tableGbc.gridx = 0;
        tableGbc.gridy = 2;
        tableGbc.gridwidth = 2;
        tablePanel.add(scrollPane, tableGbc);

        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        linkPanel.setOpaque(false);
        JButton btnBack = createLinkButton("Volver al Inicio", e -> openSearchFrame());
        linkPanel.add(btnBack);
        tableGbc.gridx = 0;
        tableGbc.gridy = 3;
        tableGbc.gridwidth = 2;
        tablePanel.add(linkPanel, tableGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(tablePanel, gbc);

        add(contentPanel, BorderLayout.CENTER);

        loadPurchases();
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

    private void loadPurchases() {
        try {
            int idCliente = controlador.getIdClienteAutenticado();
            List<Compra> compras = controlador.obtenerComprasCliente(idCliente);
            if (compras == null || compras.isEmpty()) {
                DefaultTableModel emptyModel = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"ID Compra", "Vuelo", "Valor", "Fecha Compra", "Hora Salida"}
                );
                table.setModel(emptyModel);
                table.setForeground(Color.BLACK); // Texto negro
                JOptionPane.showMessageDialog(this, "No tienes compras registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID Compra", "Vuelo", "Valor", "Fecha Compra", "Hora Salida"}, 0);
                for (Compra compra : compras) {
                    model.addRow(new Object[]{
                        compra.getIdCompra(),
                        compra.getVuelo().getCiudadOrigen() + " a " + compra.getVuelo().getCiudadDestino(),
                        String.format("$%.2f", compra.getVuelo().getValor()),
                        compra.getFechaCompra(),
                        compra.getVuelo().getHoraSalida()
                    });
                }
                table.setModel(model);
                table.setForeground(Color.BLACK); // Texto negro
            }
            // También aseguramos que el encabezado sea negro si lo deseas:
            table.getTableHeader().setForeground(Color.BLACK);
        } catch (Exception ex) {
            lblError.setText("Error: " + ex.getMessage());
            DefaultTableModel errorModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Compra", "Vuelo", "Valor", "Fecha Compra", "Hora Salida"}
            );
            table.setModel(errorModel);
            table.setForeground(Color.BLACK); // Texto negro
            table.getTableHeader().setForeground(Color.BLACK);
        }
    }

    private void openSearchFrame() {
        new SearchFlightsFrame(controlador).setVisible(true);
        dispose();
    }
}