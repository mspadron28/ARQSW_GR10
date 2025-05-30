package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import ec.edu.monster.ws.Vuelo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FlightResultFrame extends JFrame {

    private final ViajecitosController controlador;
    private final Vuelo vuelo;

    public FlightResultFrame(ViajecitosController controlador, Vuelo vuelo) {
        this.controlador = controlador;
        this.vuelo = vuelo;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Resultado");
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Result Card
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBackground(Color.WHITE);
        GridBagConstraints resultGbc = new GridBagConstraints();
        resultGbc.insets = new Insets(15, 15, 15, 15);
        resultGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFormTitle = new JLabel("Resultado de la Búsqueda");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 24));
        lblFormTitle.setForeground(Color.BLACK);
        resultGbc.gridx = 0;
        resultGbc.gridy = 0;
        resultGbc.gridwidth = 2;
        resultPanel.add(lblFormTitle, resultGbc);

        if (vuelo != null) {
            JLabel lblId = new JLabel("Vuelo ID: " + vuelo.getIdVuelo());
            lblId.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 1;
            resultGbc.gridwidth = 2;
            resultPanel.add(lblId, resultGbc);

            JLabel lblOrigen = new JLabel("Origen: " + vuelo.getCiudadOrigen());
            lblOrigen.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 2;
            resultPanel.add(lblOrigen, resultGbc);

            JLabel lblDestino = new JLabel("Destino: " + vuelo.getCiudadDestino());
            lblDestino.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 3;
            resultPanel.add(lblDestino, resultGbc);

            JLabel lblValor = new JLabel("Valor: $" + String.format("%.2f", vuelo.getValor()));
            lblValor.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 4;
            resultPanel.add(lblValor, resultGbc);

            JLabel lblHora = new JLabel("Hora de Salida: " + vuelo.getHoraSalida());
            lblHora.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 5;
            resultPanel.add(lblHora, resultGbc);

            try {
                int idCliente = controlador.getIdClienteAutenticado();
                JButton btnComprar = createStyledButton("Comprar Boleto");
                btnComprar.addActionListener(e -> comprarBoleto(idCliente));
                resultGbc.gridx = 0;
                resultGbc.gridy = 6;
                resultGbc.gridwidth = 2;
                resultPanel.add(btnComprar, resultGbc);
            } catch (Exception ex) {
                JLabel lblLogin = new JLabel("Inicie sesión para comprar");
                lblLogin.setFont(new Font("Roboto", Font.PLAIN, 14));
                lblLogin.setForeground(Color.RED);
                lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lblLogin.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        new LoginFrame().setVisible(true);
                        dispose();
                    }
                });
                resultGbc.gridx = 0;
                resultGbc.gridy = 6;
                resultGbc.gridwidth = 2;
                resultPanel.add(lblLogin, resultGbc);
            }
        } else {
            JLabel lblNoVuelo = new JLabel("Vuelo no Disponible");
            lblNoVuelo.setForeground(Color.RED);
            lblNoVuelo.setFont(new Font("Roboto", Font.PLAIN, 16));
            resultGbc.gridx = 0;
            resultGbc.gridy = 1;
            resultGbc.gridwidth = 2;
            resultPanel.add(lblNoVuelo, resultGbc);
        }

        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        linkPanel.setOpaque(false);
        JButton btnBack = createLinkButton("Volver a Buscar", e -> openSearchFrame());
        linkPanel.add(btnBack);
        resultGbc.gridx = 0;
        resultGbc.gridy = 7;
        resultGbc.gridwidth = 2;
        resultPanel.add(linkPanel, resultGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPanel.add(resultPanel, gbc);

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

    private void comprarBoleto(int idCliente) {
        try {
            if (controlador.registrarCompra(vuelo.getIdVuelo(), idCliente)) {
                JOptionPane.showMessageDialog(this, "Compra realizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                openSearchFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openSearchFrame() {
        new SearchFlightsFrame(controlador).setVisible(true);
        dispose();
    }
}