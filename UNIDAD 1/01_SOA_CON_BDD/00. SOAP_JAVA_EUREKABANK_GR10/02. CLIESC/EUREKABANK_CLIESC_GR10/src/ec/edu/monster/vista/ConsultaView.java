package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import ec.edu.monster.ws.Movimiento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Vista para consultar los movimientos de una cuenta.
 *
 * @author MATIAS
 */
public class ConsultaView extends JFrame {

    private final EurekaController controlador;
    private final MainApp mainApp;
    private JTextField txtCuenta;
    private JTable reporte;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ConsultaView(MainApp mainApp) {
        this.mainApp = mainApp;
        controlador = new EurekaController();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Movimientos");
        // Hide MainApp when this view opens
        mainApp.setVisible(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x46535d));
        headerPanel.setPreferredSize(new Dimension(0, 50));
        JLabel headerLabel = new JLabel("Consulta de Movimientos");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xf6f7f6));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel de entrada
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(new Color(0xf6f7f6));
        JLabel lblCuenta = new JLabel("Cuenta:");
        lblCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuenta = new JTextField(10);
        txtCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuenta.setBorder(new LineBorder(Color.BLACK, 1));
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConsultar.setBackground(new Color(0x202224));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setPreferredSize(new Dimension(120, 30));
        btnConsultar.addActionListener(e -> consultarMovimientos());
        inputPanel.add(lblCuenta);
        inputPanel.add(txtCuenta);
        inputPanel.add(btnConsultar);

        // Botón de retorno
        JButton btnReturn = new JButton("Volver al Menú");
        btnReturn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReturn.setBackground(new Color(0x202224));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setPreferredSize(new Dimension(150, 30));
        btnReturn.addActionListener(e -> returnToMain());
        inputPanel.add(btnReturn);

        // Panel de tabla
        reporte = new JTable();
        reporte.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CUENTA", "NROMOV", "FECHA", "TIPO", "ACCION", "IMPORTE"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        reporte.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        reporte.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(reporte);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 1));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void consultarMovimientos() {
        try {
            // Dato
            String cuenta = txtCuenta.getText().trim();
            if (cuenta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un número de cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Proceso
            List<Movimiento> lista = controlador.consultarMovimientos(cuenta);

            // Reporte
            DefaultTableModel tabla = (DefaultTableModel) reporte.getModel();
            tabla.setRowCount(0);
            for (Movimiento r : lista) {
            
                Object[] rowData = {
                    r.getCuenta(),
                    r.getNroMov(),
                    r.getFecha(),
                    r.getTipo(),
                    r.getAccion(),
                    r.getImporte()
                };
                tabla.addRow(rowData);
            }
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron movimientos para la cuenta.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar movimientos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnToMain() {
        mainApp.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultaView(new MainApp()).setVisible(true));
    }
}
