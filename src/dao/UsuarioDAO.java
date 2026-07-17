package dao;

import modelo.Usuario;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String usuario, String password) {

        try {

            Connection cn = Conexion.getConnection();

            System.out.println("Conexión exitosa a MySQL");

            String sql = "SELECT * FROM usuarios";

            PreparedStatement ps = cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("------------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("USERNAME: " + rs.getString("username"));
                System.out.println("PASSWORD: " + rs.getString("password"));

                if (rs.getString("username").equals(usuario)
                        && rs.getString("password").equals(password)) {

                    Usuario u = new Usuario();

                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));

                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}