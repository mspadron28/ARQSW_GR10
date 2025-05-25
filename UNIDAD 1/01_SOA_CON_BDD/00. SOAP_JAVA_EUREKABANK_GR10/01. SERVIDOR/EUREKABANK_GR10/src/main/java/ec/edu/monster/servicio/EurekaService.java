package ec.edu.monster.servicio;

import ec.edu.monster.db.AccesoDB;
import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.Usuario;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para operaciones bancarias de EurekaBank.
 * @author MATIAS
 */
public class EurekaService {

    private Connection getConnection() throws SQLException {
        return AccesoDB.getConnection();
    }

    public List<Movimiento> leerMovimientos(String cuenta) throws SQLException {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.chr_cuencodigo, m.int_movinumero, m.dtt_movifecha, m.chr_tipocodigo, " +
                    "t.vch_tipoaccion, m.dec_moviimporte " +
                    "FROM Movimiento m JOIN TipoMovimiento t ON m.chr_tipocodigo = t.chr_tipocodigo " +
                    "WHERE m.chr_cuencodigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cuenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movimiento mov = new Movimiento();
                    mov.setCuenta(rs.getString("chr_cuencodigo"));
                    mov.setNroMov(rs.getInt("int_movinumero"));
                    mov.setFecha(rs.getDate("dtt_movifecha"));
                    mov.setTipo(rs.getString("chr_tipocodigo"));
                    mov.setAccion(rs.getString("vch_tipoaccion"));
                    mov.setImporte(rs.getDouble("dec_moviimporte"));
                    lista.add(mov);
                }
            }
        }
        return lista;
    }

    public void registrarDeposito(String cuenta, double importe, String codEmp) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Validar cuenta
            PreparedStatement ps = conn.prepareStatement(
                "SELECT dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO'");
            ps.setString(1, cuenta);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Cuenta no válida o inactiva.");
            }
            String moneCodigo = rs.getString("chr_monecodigo");

            // Obtener costo de movimiento
            ps = conn.prepareStatement("SELECT dec_costimporte FROM CostoMovimiento WHERE chr_monecodigo = ?");
            ps.setString(1, moneCodigo);
            rs = ps.executeQuery();
            double costo = rs.next() ? rs.getDouble("dec_costimporte") : 0.0;

            // Obtener próximo número de movimiento
            ps = conn.prepareStatement("SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = ?");
            ps.setString(1, cuenta);
            rs = ps.executeQuery();
            int moviNumero = rs.next() ? rs.getInt(1) : 1;

            // Registrar movimiento de depósito
            ps = conn.prepareStatement(
                "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                "VALUES (?, ?, CURDATE(), ?, '003', ?, NULL)");
            ps.setString(1, cuenta);
            ps.setInt(2, moviNumero);
            ps.setString(3, codEmp);
            ps.setDouble(4, importe);
            ps.executeUpdate();

            // Registrar costo, si aplica
            if (costo > 0) {
                ps = conn.prepareStatement(
                    "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                    "VALUES (?, ?, CURDATE(), ?, '010', ?, NULL)");
                ps.setString(1, cuenta);
                ps.setInt(2, moviNumero + 1);
                ps.setString(3, codEmp);
                ps.setDouble(4, costo);
                ps.executeUpdate();
            }

            // Actualizar cuenta
            ps = conn.prepareStatement(
                "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + ? - ?, int_cuencontmov = int_cuencontmov + ? WHERE chr_cuencodigo = ?");
            ps.setDouble(1, importe);
            ps.setDouble(2, costo);
            ps.setInt(3, costo > 0 ? 2 : 1);
            ps.setString(4, cuenta);
            ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void registrarRetiro(String cuenta, double importe, String codEmp) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Validar cuenta
            PreparedStatement ps = conn.prepareStatement(
                "SELECT dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO'");
            ps.setString(1, cuenta);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Cuenta no válida o inactiva.");
            }
            double saldo = rs.getDouble("dec_cuensaldo");
            String moneCodigo = rs.getString("chr_monecodigo");

            // Obtener costo de movimiento
            ps = conn.prepareStatement("SELECT dec_costimporte FROM CostoMovimiento WHERE chr_monecodigo = ?");
            ps.setString(1, moneCodigo);
            rs = ps.executeQuery();
            double costo = rs.next() ? rs.getDouble("dec_costimporte") : 0.0;

            // Validar saldo
            if (saldo < importe + costo) {
                throw new Exception("Saldo insuficiente.");
            }

            // Obtener próximo número de movimiento
            ps = conn.prepareStatement("SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = ?");
            ps.setString(1, cuenta);
            rs = ps.executeQuery();
            int moviNumero = rs.next() ? rs.getInt(1) : 1;

            // Registrar movimiento de retiro
            ps = conn.prepareStatement(
                "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                "VALUES (?, ?, CURDATE(), ?, '004', ?, NULL)");
            ps.setString(1, cuenta);
            ps.setInt(2, moviNumero);
            ps.setString(3, codEmp);
            ps.setDouble(4, importe);
            ps.executeUpdate();

            // Registrar costo, si aplica
            if (costo > 0) {
                ps = conn.prepareStatement(
                    "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                    "VALUES (?, ?, CURDATE(), ?, '010', ?, NULL)");
                ps.setString(1, cuenta);
                ps.setInt(2, moviNumero + 1);
                ps.setString(3, codEmp);
                ps.setDouble(4, costo);
                ps.executeUpdate();
            }

            // Actualizar cuenta
            ps = conn.prepareStatement(
                "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - ? - ?, int_cuencontmov = int_cuencontmov + ? WHERE chr_cuencodigo = ?");
            ps.setDouble(1, importe);
            ps.setDouble(2, costo);
            ps.setInt(3, costo > 0 ? 2 : 1);
            ps.setString(4, cuenta);
            ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Validar cuentas
            PreparedStatement ps = conn.prepareStatement(
                "SELECT chr_cuencodigo, dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo IN (?, ?) AND vch_cuenestado = 'ACTIVO'");
            ps.setString(1, cuentaOrigen);
            ps.setString(2, cuentaDestino);
            ResultSet rs = ps.executeQuery();
            Map<String, Double> saldos = new HashMap<>();
            Map<String, String> monedas = new HashMap<>();
            while (rs.next()) {
                saldos.put(rs.getString("chr_cuencodigo"), rs.getDouble("dec_cuensaldo"));
                monedas.put(rs.getString("chr_cuencodigo"), rs.getString("chr_monecodigo"));
            }
            if (!saldos.containsKey(cuentaOrigen) || !saldos.containsKey(cuentaDestino)) {
                throw new Exception("Cuenta(s) no válida(s) o inactiva(s).");
            }
            if (!monedas.get(cuentaOrigen).equals(monedas.get(cuentaDestino))) {
                throw new Exception("Las cuentas deben usar la misma moneda.");
            }
            if (saldos.get(cuentaOrigen) < importe) {
                throw new Exception("Saldo insuficiente en cuenta origen.");
            }

            // Obtener costo
            ps = conn.prepareStatement("SELECT dec_costimporte FROM CostoMovimiento WHERE chr_monecodigo = ?");
            ps.setString(1, monedas.get(cuentaOrigen));
            rs = ps.executeQuery();
            double costo = rs.next() ? rs.getDouble("dec_costimporte") : 0.0;
            if (saldos.get(cuentaOrigen) < importe + costo) {
                throw new Exception("Saldo insuficiente para cubrir costo de movimiento.");
            }

            // Obtener números de movimiento
            ps = conn.prepareStatement("SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = ?");
            ps.setString(1, cuentaOrigen);
            rs = ps.executeQuery();
            int moviOrigen = rs.next() ? rs.getInt(1) : 1;

            ps.setString(1, cuentaDestino);
            rs = ps.executeQuery();
            int moviDestino = rs.next() ? rs.getInt(1) : 1;

            // Registrar movimiento salida
            ps = conn.prepareStatement(
                "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                "VALUES (?, ?, CURDATE(), ?, '009', ?, ?)");
            ps.setString(1, cuentaOrigen);
            ps.setInt(2, moviOrigen);
            ps.setString(3, codEmp);
            ps.setDouble(4, importe);
            ps.setString(5, cuentaDestino);
            ps.executeUpdate();

            // Registrar movimiento ingreso
            ps = conn.prepareStatement(
                "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                "VALUES (?, ?, CURDATE(), ?, '008', ?, ?)");
            ps.setString(1, cuentaDestino);
            ps.setInt(2, moviDestino);
            ps.setString(3, codEmp);
            ps.setDouble(4, importe);
            ps.setString(5, cuentaOrigen);
            ps.executeUpdate();

            // Registrar costo, si aplica
            if (costo > 0) {
                ps = conn.prepareStatement(
                    "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                    "VALUES (?, ?, CURDATE(), ?, '010', ?, NULL)");
                ps.setString(1, cuentaOrigen);
                ps.setInt(2, moviOrigen + 1);
                ps.setString(3, codEmp);
                ps.setDouble(4, costo);
                ps.executeUpdate();
            }

            // Actualizar cuentas
            ps = conn.prepareStatement(
                "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - ? - ?, int_cuencontmov = int_cuencontmov + ? WHERE chr_cuencodigo = ?");
            ps.setDouble(1, importe);
            ps.setDouble(2, costo);
            ps.setInt(3, costo > 0 ? 2 : 1);
            ps.setString(4, cuentaOrigen);
            ps.executeUpdate();

            ps = conn.prepareStatement(
                "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + ?, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = ?");
            ps.setDouble(1, importe);
            ps.setString(2, cuentaDestino);
            ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public double verificarSaldo(String cuenta) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT dec_cuensaldo FROM Cuenta WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO'")) {
            ps.setString(1, cuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("dec_cuensaldo");
                } else {
                    throw new Exception("Cuenta no válida o inactiva.");
                }
            }
        }
    }
    
    public double obtenerCostoMovimiento(String cuenta) throws Exception {
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(
             "SELECT cm.dec_costimporte " +
             "FROM Cuenta c JOIN CostoMovimiento cm ON c.chr_monecodigo = cm.chr_monecodigo " +
             "WHERE c.chr_cuencodigo = ? AND c.vch_cuenestado = 'ACTIVO'")) {
        ps.setString(1, cuenta);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("dec_costimporte");
            } else {
                throw new Exception("Cuenta no válida o inactiva.");
            }
        }
    }
}
    
    public Usuario iniciarSesion(String usuario, String clave) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Validar credenciales
            PreparedStatement ps = conn.prepareStatement(
                "SELECT chr_emplcodigo, vch_emplusuario, vch_emplclave, vch_emplestado " +
                "FROM Usuario WHERE vch_emplusuario = ? AND vch_emplclave = ? AND vch_emplestado = 'ACTIVO'");
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Credenciales inválidas o usuario inactivo.");
            }

            // Crear objeto Usuario
            Usuario user = new Usuario();
            user.setCodigo(rs.getString("chr_emplcodigo"));
            user.setUsuario(rs.getString("vch_emplusuario"));
            user.setClave(rs.getString("vch_emplclave"));
            user.setEstado(rs.getString("vch_emplestado"));

            // Registrar sesión en LOGSESSION
            String ip = "127.0.0.1"; // IP por defecto, puedes obtener la real si es necesario
            String hostname = "localhost"; // Hostname por defecto
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();
                hostname = inetAddress.getHostName();
            } catch (Exception e) {
                // Ignorar errores de red, usar valores por defecto
            }

            ps = conn.prepareStatement(
                "INSERT INTO LOGSESSION (chr_emplcodigo, fec_ingreso, ip, hostname) " +
                "VALUES (?, NOW(), ?, ?)");
            ps.setString(1, user.getCodigo());
            ps.setString(2, ip);
            ps.setString(3, hostname);
            ps.executeUpdate();

            conn.commit();
            return user;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }
}