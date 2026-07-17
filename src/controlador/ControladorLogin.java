package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import vista.VistaLogin;
import vista.VistaMenuPrincipal;

import javax.swing.*;

public class ControladorLogin {

    private final VistaLogin vista;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        init();
    }

    private void init() {

        vista.btnIngresar.addActionListener(e -> login());

        vista.btnCancelar.addActionListener(e -> System.exit(0));

    }

    private void login() {

        String user = vista.txtUsuario.getText().trim();
        String pass = new String(vista.txtPassword.getPassword());

        Usuario u = usuarioDAO.login(user, pass);

        if (u != null) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Bienvenido " + u.getUsername()
            );

            vista.dispose();

            VistaMenuPrincipal menu =
                    new VistaMenuPrincipal();

            new ControladorMenuPrincipal(menu);

            menu.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    vista,
                    "Credenciales incorrectas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

}