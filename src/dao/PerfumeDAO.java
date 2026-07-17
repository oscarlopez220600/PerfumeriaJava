package dao;

import dao.interfaces.IGenericoDAO;
import modelo.Perfume;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerfumeDAO implements IGenericoDAO<Perfume> {

    @Override
    public boolean crear(Perfume p) {
        String sql = "INSERT INTO perfumes (nombre, marca, precio, codigo, cantidad, proveedor_id, categoria, genero, disponible, descripcion, aroma) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMarca());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getCodigo());
            ps.setInt(5, p.getCantidad());
            if (p.getProveedorId() == null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, p.getProveedorId());
            ps.setString(7, p.getCategoria());
            ps.setString(8, p.getGenero());
            ps.setBoolean(9, p.isDisponible());
            ps.setString(10, p.getDescripcion());
            ps.setString(11, p.getAroma());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean actualizar(Perfume p) {
        String sql = "UPDATE perfumes SET nombre=?, marca=?, precio=?, codigo=?, cantidad=?, proveedor_id=?, categoria=?, genero=?, disponible=?, descripcion=?, aroma=? WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMarca());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getCodigo());
            ps.setInt(5, p.getCantidad());
            if (p.getProveedorId() == null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, p.getProveedorId());
            ps.setString(7, p.getCategoria());
            ps.setString(8, p.getGenero());
            ps.setBoolean(9, p.isDisponible());
            ps.setString(10, p.getDescripcion());
            ps.setString(11, p.getAroma());
            ps.setInt(12, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM perfumes WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Perfume obtenerPorId(int id) {
        String sql = "SELECT * FROM perfumes WHERE id=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Perfume> listar() {
        String sql = "SELECT * FROM perfumes ORDER BY id DESC";
        List<Perfume> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public Perfume obtenerPorCodigo(String codigo) {
        String sql = "SELECT * FROM perfumes WHERE codigo=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Perfume map(ResultSet rs) throws SQLException {
        Perfume p = new Perfume();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecio(rs.getDouble("precio"));
        p.setCodigo(rs.getString("codigo"));
        p.setCantidad(rs.getInt("cantidad"));
        int prov = rs.getInt("proveedor_id");
        p.setProveedorId(rs.wasNull() ? null : prov);
        p.setCategoria(rs.getString("categoria"));
        p.setGenero(rs.getString("genero"));
        p.setDisponible(rs.getBoolean("disponible"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setAroma(rs.getString("aroma"));
        return p;
    }
    public boolean actualizarStock(int idPerfume, int nuevaCantidad) {

    String sql =
            "UPDATE perfumes SET cantidad=? WHERE id=?";

    try (
            Connection cn = Conexion.getConnection();
            PreparedStatement ps =
                    cn.prepareStatement(sql)
    ) {

        ps.setInt(1, nuevaCantidad);
        ps.setInt(2, idPerfume);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;
    }
}
