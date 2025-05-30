package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import ec.edu.monster.ws.Compra;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PurchasesFrame extends JFrame {

    private final ViajecitosController controlador;
    private JTable table;
    private JLabel lblError;

    public PurchasesFrame(ViajecitosController controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Mis Compras");
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
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Table Card
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.WHITE);
        GridBagConstraints tableGbc = new GridBagConstraints();
        tableGbc.insets = new Insets(15, 15, 15, 15);
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 1.0;

        JLabel lblFormTitle = new JLabel("Mis Compras");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 24));
        lblFormTitle.setForeground(Color.BLACK);
        tableGbc.gridx = 0;
        tableGbc.gridy = 0;
        tableGbc.gridwidth = 2;
        tablePanel.add(lblFormTitle, tableGbc);

        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Roboto", Font.PLAIN, 14));
        tableGbc.gridx = 0;
        tableGbc.gridy = 1;
        tablePanel.add(lblError, tableGbc);

        table = new JTable();
        table.setFont(new Font("Roboto", Font.PLAIN, 14));
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        header.setBackground(Color.BLACK); // Ajustado a negro para simular el estilo del JSP
        header.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
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

    private void loadPurchases() {
        try {
            int idCliente = controlador.getIdClienteAutenticado();
            List<Compra> compras = controlador.obtenerComprasCliente(idCliente);
            if (compras == null || compras.isEmpty()) {
                table.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"ID Compra", "Vuelo", "Valor", "Fecha Compra", "Hora Salida"}
                ));
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
            }
        } catch (Exception ex) {
            lblError.setText("Error: " + ex.getMessage());
            table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Compra", "Vuelo", "Valor", "Fecha Compra", "Hora Salida"}
            ));
        }
    }

    private void openSearchFrame() {
        new SearchFlightsFrame(controlador).setVisible(true);
        dispose();
    }
}