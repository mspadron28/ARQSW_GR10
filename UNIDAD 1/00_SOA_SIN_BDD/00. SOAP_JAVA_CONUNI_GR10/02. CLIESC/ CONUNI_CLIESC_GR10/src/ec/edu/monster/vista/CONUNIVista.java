/*
 * Click nargs://.netbeans/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nargs://.netbeans/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.monster.vista;

import ec.edu.monster.controlador.AppControlador;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author MATIAS
 */
public class CONUNIVista extends javax.swing.JFrame {
    private AppControlador controlador;
    private JComboBox<String> conversionSelector;
    private JTextField inputField;
    private JLabel resultLabel;

    /**
     * Creates new form CONUNIVista
     */
    public CONUNIVista() {
        initComponents();
        this.setLocationRelativeTo(null);
        controlador = new AppControlador();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar por defecto
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        headerPanel.setPreferredSize(new Dimension(0, 50)); // Altura fija, ancho adaptable

        JLabel headerLabel = new JLabel("Conversor de Unidades JAVA SOAP");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel contenedor principal con márgenes
        JPanel containerPanel = new JPanel(new GridLayout(1, 2, 5, 20));
        containerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        containerPanel.setBackground(new Color(0xf6f7f6));

        // Columna izquierda: Sección visual y emocional
        // Envolver leftPanel en un panel para centrar verticalmente
        JPanel leftPanelWrapper = new JPanel(new GridBagLayout());
        leftPanelWrapper.setBackground(new Color(0xf6f7f6));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0xf6f7f6));
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 10, 10, 10);
        gbcLeft.anchor = GridBagConstraints.CENTER;

        // Pregunta en negrita
        JLabel questionLabel = new JLabel("¿Qué unidad requieres convertir?");
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 0.0;
        leftPanel.add(questionLabel, gbcLeft);

        // Imagen de Sulley con laptop
        JLabel sulleyLabel = new JLabel();
        try {
            ImageIcon sulleyIcon = new ImageIcon(getClass().getResource("/images/sulleyconuni.png"));
            Image scaledImage = sulleyIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Aumentar tamaño a 300x300
            sulleyLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            sulleyLabel.setText("(Imagen no disponible)");
        }
        sulleyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbcLeft.gridy = 1;
        leftPanel.add(sulleyLabel, gbcLeft);

        // Añadir leftPanel al wrapper para centrar verticalmente
        GridBagConstraints gbcLeftWrapper = new GridBagConstraints();
        gbcLeftWrapper.gridx = 0;
        gbcLeftWrapper.gridy = 0;
        gbcLeftWrapper.weightx = 1.0;
        gbcLeftWrapper.weighty = 1.0;
        gbcLeftWrapper.anchor = GridBagConstraints.CENTER;
        leftPanelWrapper.add(leftPanel, gbcLeftWrapper);

        // Columna derecha: Área funcional
        // Envolver rightPanel en un panel para centrar verticalmente
        JPanel rightPanelWrapper = new JPanel(new GridBagLayout());
        rightPanelWrapper.setBackground(new Color(0xf6f7f6));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0xb1c5c7));
        rightPanel.setBorder(new LineBorder(Color.BLACK, 1));
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(300, 400)); // Tamaño fijo para el contenedor
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 5, 5, 5);
        gbcRight.anchor = GridBagConstraints.CENTER;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        // Label "Selecciona el tipo de cambio"
        JLabel selectorLabel = new JLabel("Selecciona el tipo de cambio");
        selectorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.weightx = 0.0;
        rightPanel.add(selectorLabel, gbcRight);

        // Selector desplegable
        String[] conversionOptions = {
            "Pulgadas a Centímetros",
            "Centímetros a Pulgadas",
            "Metros a Pies",
            "Pies a Metros",
            "Metros a Yardas",
            "Yardas a Metros"
        };
        conversionSelector = new JComboBox<>(conversionOptions);
        conversionSelector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        conversionSelector.setBorder(new LineBorder(Color.BLACK, 1));
        conversionSelector.setPreferredSize(new Dimension(200, 30));
        conversionSelector.setMaximumSize(new Dimension(250, 30));
        gbcRight.gridy = 1;
        rightPanel.add(conversionSelector, gbcRight);

        // Campo de entrada
        JLabel inputLabel = new JLabel("Ingresa el valor");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbcRight.gridy = 2;
        rightPanel.add(inputLabel, gbcRight);

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(new LineBorder(Color.BLACK, 1));
        inputField.setPreferredSize(new Dimension(200, 30));
        inputField.setMaximumSize(new Dimension(250, 30));
        gbcRight.gridy = 3;
        rightPanel.add(inputField, gbcRight);

        // Botón de conversión
        JButton convertButton = new JButton("Convertir");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        convertButton.setBackground(new Color(0x202224));
        convertButton.setForeground(Color.WHITE);
        convertButton.setPreferredSize(new Dimension(200, 40));
        convertButton.setMaximumSize(new Dimension(250, 40));
        convertButton.setFocusPainted(false);
        convertButton.addActionListener(evt -> convertButtonActionPerformed(evt));
        gbcRight.gridy = 4;
        rightPanel.add(convertButton, gbcRight);

        // Campo de resultado
        JLabel resultTextLabel = new JLabel("Resultado");
        resultTextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbcRight.gridy = 5;
        rightPanel.add(resultTextLabel, gbcRight);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setBorder(new LineBorder(Color.BLACK, 1));
        resultLabel.setPreferredSize(new Dimension(200, 30));
        resultLabel.setMaximumSize(new Dimension(250, 30));
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.WHITE);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbcRight.gridy = 6;
        rightPanel.add(resultLabel, gbcRight);

        // Añadir rightPanel al wrapper para centrar verticalmente
        GridBagConstraints gbcRightWrapper = new GridBagConstraints();
        gbcRightWrapper.gridx = 0;
        gbcRightWrapper.gridy = 0;
        gbcRightWrapper.weightx = 1.0;
        gbcRightWrapper.weighty = 1.0;
        gbcRightWrapper.anchor = GridBagConstraints.CENTER;
        rightPanelWrapper.add(rightPanel, gbcRightWrapper);

        // Añadir columnas al contenedor principal
        containerPanel.add(leftPanelWrapper);
        containerPanel.add(rightPanelWrapper);

        // Configuración del marco
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conversor de Unidades");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0xf6f7f6));
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(containerPanel, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(800, 500));
    }

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Obtener el valor de entrada
            double value = Double.parseDouble(inputField.getText());

            // Validar que el valor sea no negativo
            if (value < 0) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor no negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la opción seleccionada
            String selectedOption = (String) conversionSelector.getSelectedItem();
            double result = 0;
            String unit = "";

            // Realizar la conversión según la opción seleccionada
            switch (selectedOption) {
                case "Pulgadas a Centímetros":
                    result = controlador.pulgadasACentimetros(value);
                    unit = "cm";
                    break;
                case "Centímetros a Pulgadas":
                    result = controlador.centimetrosAPulgadas(value);
                    unit = "pulgadas";
                    break;
                case "Metros a Pies":
                    result = controlador.metrosAPies(value);
                    unit = "pies";
                    break;
                case "Pies a Metros":
                    result = controlador.piesAMetros(value);
                    unit = "m";
                    break;
                case "Metros a Yardas":
                    result = controlador.metrosAYardas(value);
                    unit = "yardas";
                    break;
                case "Yardas a Metros":
                    result = controlador.yardasAMetros(value);
                    unit = "m";
                    break;
                default:
                    throw new IllegalArgumentException("Opción de conversión no válida");
            }

            // Mostrar el resultado
            resultLabel.setText(String.format("%.2f %s", result, unit));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la conversión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CONUNIVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CONUNIVista().setVisible(true));
    }

    // Variables declaration
    // No se necesitan las variables originales ya que se redefinen en initComponents
}