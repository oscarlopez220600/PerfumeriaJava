package controlador;

import vista.*;

import java.awt.Desktop;
import java.net.URI;

public class ControladorMenuPrincipal {

    private final VistaMenuPrincipal vista;

    public ControladorMenuPrincipal(VistaMenuPrincipal vista) {

        this.vista = vista;

        vista.btnPerfumes.addActionListener(e -> {

            VistaPerfume vp = new VistaPerfume();

            ControladorPerfume cp =
                    new ControladorPerfume(vp);

            cp.iniciar();

            vp.setVisible(true);

        });

        vista.btnClientes.addActionListener(e -> {

            VistaCliente vc =
                    new VistaCliente();

            ControladorCliente cc =
                    new ControladorCliente(vc);

            cc.mostrar();

        });

        vista.btnProveedores.addActionListener(e -> {

            VistaProveedor vp =
                    new VistaProveedor();

            ControladorProveedor cp =
                    new ControladorProveedor(vp);

            cp.mostrar();

        });

        vista.btnInventario.addActionListener(e -> {

            VistaInventario vi =
                    new VistaInventario();

            new ControladorInventario(vi);

            vi.setVisible(true);

        });

        vista.btnVentas.addActionListener(e -> {

            VistaVentas vv =
                    new VistaVentas();

            new ControladorVentas(vv);

            vv.setVisible(true);

        });

        vista.btnInstagram.addActionListener(e -> {

            try {

                Desktop.getDesktop().browse(
                        new URI(
                                "https://www.instagram.com/lr.parfums?igsh=NmhicGR2OGM1MGhx&utm_source=qr"
                        )
                );

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        });

        vista.btnSalir.addActionListener(e -> {

            vista.dispose();

            VistaLogin vl =
                    new VistaLogin();

            new ControladorLogin(vl);

            vl.setVisible(true);

        });

    }
}