package dao;

import dao.interfaces.IGenericoDAO;
import modelo.Proveedor;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements IGenericoDAO<Proveedor> {

    @Override
    public boolean crear(Proveedor p) {
        String sql = "INSERT INTO proveedores (nombre, telefono, email, direccion) VALUES (?,?,?,?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE proveedores SET nombre=?, telefono=?, email=?, direccion=? WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getDireccion());
            ps.setInt(5, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Proveedor obtenerPorId(int id) {
        String sql = "SELECT * FROM proveedores WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Proveedor p = new Proveedor();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setEmail(rs.getString("email"));
                    p.setDireccion(rs.getString("direccion"));
                    return p;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Proveedor> listar() {
        String sql = "SELECT * FROM proveedores ORDER BY id DESC";
        List<Proveedor> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}
