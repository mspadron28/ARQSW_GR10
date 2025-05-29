package ec.edu.monster.servicio;

import ec.edu.monster.db.AccesoDB;
import ec.edu.monster.modelo.Cliente;
import ec.edu.monster.modelo.Compra;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.Vuelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servicio para operaciones de Viajecitos SA.
 * @author MATIAS
 */
public class ViajecitosService {

    private Connection getConnection() throws SQLException {
        return AccesoDB.getConnection();
    }

    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, Date fecha) throws SQLException {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida " +
                    "FROM Vuelo WHERE ciudad_origen = ? AND ciudad_destino = ? AND DATE(hora_salida) = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ciudadOrigen);
            ps.setString(2, ciudadDestino);
            ps.setDate(3, new java.sql.Date(fecha.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vuelo vuelo = new Vuelo();
                    vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                    vuelo.setCiudadOrigen(rs.getString("ciudad_origen"));
                    vuelo.setCiudadDestino(rs.getString("ciudad_destino"));
                    vuelo.setValor(rs.getDouble("valor"));
                    vuelo.setHoraSalida(rs.getTimestamp("hora_salida"));
                    lista.add(vuelo);
                }
            }
        }
        return lista;
    }

    public Vuelo obtenerVueloMasCaro(String ciudadOrigen, String ciudadDestino, Date fecha) throws SQLException {
        String sql = "SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida " +
                    "FROM Vuelo WHERE ciudad_origen = ? AND ciudad_destino = ? AND DATE(hora_salida) = ? " +
                    "ORDER BY valor DESC LIMIT 1";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ciudadOrigen);
            ps.setString(2, ciudadDestino);
            ps.setDate(3, new java.sql.Date(fecha.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vuelo vuelo = new Vuelo();
                    vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                    vuelo.setCiudadOrigen(rs.getString("ciudad_origen"));
                    vuelo.setCiudadDestino(rs.getString("ciudad_destino"));
                    vuelo.setValor(rs.getDouble("valor"));
                    vuelo.setHoraSalida(rs.getTimestamp("hora_salida"));
                    return vuelo;
                }
            }
        }
        return null;
    }

    public void registrarCompra(int idVuelo, int idCliente) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Validar vuelo
            PreparedStatement ps = conn.prepareStatement("SELECT id_vuelo FROM Vuelo WHERE id_vuelo = ?");
            ps.setInt(1, idVuelo);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Vuelo no válido.");
            }

            // Validar cliente
            ps = conn.prepareStatement("SELECT id_cliente FROM Cliente WHERE id_cliente = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Cliente no válido.");
            }

            // Registrar compra
            ps = conn.prepareStatement(
                    "INSERT INTO Compra (id_vuelo, id_cliente, fecha_compra) VALUES (?, ?, NOW())");
            ps.setInt(1, idVuelo);
            ps.setInt(2, idCliente);
            ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Cliente registrarCliente(String nombre, String email, String documentoIdentidad) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Verificar si el email o documento ya existen
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id_cliente FROM Cliente WHERE email = ? OR documento_identidad = ?");
            ps.setString(1, email);
            ps.setString(2, documentoIdentidad);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("Email o documento de identidad ya registrado.");
            }

            // Registrar cliente
            ps = conn.prepareStatement(
                    "INSERT INTO Cliente (nombre, email, documento_identidad) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, documentoIdentidad);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int idCliente = rs.next() ? rs.getInt(1) : 0;

            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            cliente.setDocumentoIdentidad(documentoIdentidad);

            conn.commit();
            return cliente;
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Usuario registrarUsuario(int idCliente, String nombreUsuario, String claveUsuario) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Verificar cliente
            PreparedStatement ps = conn.prepareStatement("SELECT id_cliente FROM Cliente WHERE id_cliente = ?");
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Cliente no válido.");
            }

            // Verificar nombre de usuario
            ps = conn.prepareStatement("SELECT id_usuario FROM Usuario WHERE nombre_usuario = ?");
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("Nombre de usuario ya registrado.");
            }

            // Registrar usuario
            ps = conn.prepareStatement(
                    "INSERT INTO Usuario (id_cliente, nombre_usuario, clave_usuario, estado_usuario) VALUES (?, ?, ?, 'ACTIVO')",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCliente);
            ps.setString(2, nombreUsuario);
            ps.setString(3, claveUsuario); // En una aplicación real, hashear la contraseña
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int idUsuario = rs.next() ? rs.getInt(1) : 0;

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setIdCliente(idCliente);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setClaveUsuario(claveUsuario);
            usuario.setEstadoUsuario("ACTIVO");

            conn.commit();
            return usuario;
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Usuario iniciarSesion(String nombreUsuario, String claveUsuario) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();

            // Validar credenciales
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id_usuario, id_cliente, nombre_usuario, clave_usuario, estado_usuario " +
                    "FROM Usuario WHERE nombre_usuario = ? AND clave_usuario = ? AND estado_usuario = 'ACTIVO'");
            ps.setString(1, nombreUsuario);
            ps.setString(2, claveUsuario); // En una aplicación real, comparar contraseña hasheada
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Credenciales inválidas o usuario inactivo.");
            }

            // Crear objeto Usuario
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setIdCliente(rs.getInt("id_cliente"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setClaveUsuario(rs.getString("clave_usuario"));
            usuario.setEstadoUsuario(rs.getString("estado_usuario"));

            return usuario;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new SQLException("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}