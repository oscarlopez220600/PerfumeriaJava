package main;

import controlador.ControladorLogin;
import vista.VistaLogin;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaLogin vl = new VistaLogin();
                new ControladorLogin(vl);
                vl.setVisible(true);
            }
        });
    }
}
