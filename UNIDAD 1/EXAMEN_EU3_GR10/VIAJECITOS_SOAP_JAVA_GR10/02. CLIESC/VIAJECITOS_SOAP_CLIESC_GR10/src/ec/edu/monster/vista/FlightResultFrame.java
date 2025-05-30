package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import ec.edu.monster.ws.Vuelo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FlightResultFrame extends JFrame {

    private final ViajecitosController controlador;
    private final Vuelo vuelo;
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

    public FlightResultFrame(ViajecitosController controlador, Vuelo vuelo) {
        this.controlador = controlador;
        this.vuelo = vuelo;
        initComponents();
    }

    private void initComponents() {
        setTitle("Viajecitos SA - Resultado");
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

        // Contenedor de resultados
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBackground(WHITE);
        Border outerBorder = new LineBorder(new Color(209, 213, 219), 1, true); // Borde gris suave
        Border innerBorder = new EmptyBorder(30, 30, 30, 30); // Padding interno
        Border shadowBorder = new CompoundBorder(
            new LineBorder(SHADOW, 4, true), // Sombra suave
            new CompoundBorder(outerBorder, innerBorder)
        );
        resultPanel.setBorder(shadowBorder); // Borde con sombra
        resultPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // Ancho limitado como formulario
        GridBagConstraints resultGbc = new GridBagConstraints();
        resultGbc.insets = new Insets(12, 12, 12, 12);
        resultGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFormTitle = new JLabel("Resultado de la Búsqueda");
        lblFormTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        lblFormTitle.setForeground(TEXT_DARK);
        resultGbc.gridx = 0;
        resultGbc.gridy = 0;
        resultGbc.gridwidth = 2;
        resultPanel.add(lblFormTitle, resultGbc);

        if (vuelo != null) {
            JLabel lblId = new JLabel("Vuelo ID: " + vuelo.getIdVuelo());
            lblId.setFont(new Font("Roboto", Font.PLAIN, 16));
            lblId.setForeground(TEXT_DARK);
            resultGbc.gridx = 0;
            resultGbc.gridy = 1;
            resultGbc.gridwidth = 2;
            resultPanel.add(lblId, resultGbc);

            JLabel lblOrigen = new JLabel("Origen: " + vuelo.getCiudadOrigen());
            lblOrigen.setFont(new Font("Roboto", Font.PLAIN, 16));
            lblOrigen.setForeground(TEXT_DARK);
            resultGbc.gridx = 0;
            resultGbc.gridy = 2;
            resultPanel.add(lblOrigen, resultGbc);

            JLabel lblDestino = new JLabel("Destino: " + vuelo.getCiudadDestino());
            lblDestino.setFont(new Font("Roboto", Font.PLAIN, 16));
            lblDestino.setForeground(TEXT_DARK);
            resultGbc.gridx = 0;
            resultGbc.gridy = 3;
            resultPanel.add(lblDestino, resultGbc);

            JLabel lblValor = new JLabel("Valor: $" + String.format("%.2f", vuelo.getValor()));
            lblValor.setFont(new Font("Roboto", Font.PLAIN, 16));
            lblValor.setForeground(TEXT_DARK);
            resultGbc.gridx = 0;
            resultGbc.gridy = 4;
            resultPanel.add(lblValor, resultGbc);

            JLabel lblHora = new JLabel("Hora de Salida: " + vuelo.getHoraSalida());
            lblHora.setFont(new Font("Roboto", Font.PLAIN, 16));
            lblHora.setForeground(TEXT_DARK);
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
                lblLogin.setForeground(ERROR_RED);
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
            lblNoVuelo.setForeground(ERROR_RED);
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