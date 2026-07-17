package dao;

import modelo.Venta;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VentaDAO {

    public boolean registrar(Venta venta) {

        String sql =
                "INSERT INTO ventas(cliente_id, perfume_id, cantidad, total) VALUES (?,?,?,?)";

        try (
                Connection cn = Conexion.getConnection();
                PreparedStatement ps =
                        cn.prepareStatement(sql)
        ) {

            ps.setInt(1, venta.getClienteId());
            ps.setInt(2, venta.getPerfumeId());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getTotal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}