package vista;

import javax.swing.*;
import java.awt.*;

public class VistaMenuPrincipal extends JFrame {

    public JButton btnPerfumes = new JButton(" CATÁLOGO");
    public JButton btnClientes = new JButton(" CLIENTES");
    public JButton btnProveedores = new JButton(" PROVEEDORES");
    public JButton btnInventario = new JButton(" INVENTARIO");
    public JButton btnVentas = new JButton(" VENTAS");
    public JButton btnInstagram = new JButton(" INSTAGRAM");
    public JButton btnSalir = new JButton(" CERRAR SESIÓN");

    public VistaMenuPrincipal() {

        setTitle("L.R Perfumes");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel();
        fondo.setLayout(null);
        fondo.setBackground(new Color(15, 15, 15));

        try {

            ImageIcon icono = new ImageIcon(
                    getClass().getResource("/Imagenes/logo.jpeg")
            );

            Image img = icono.getImage().getScaledInstance(
                    250,
                    250,
                    Image.SCALE_SMOOTH
            );

            JLabel logo = new JLabel(new ImageIcon(img));
            logo.setBounds(375, 20, 250, 250);

            fondo.add(logo);

        } catch (Exception e) {

            JLabel tituloLogo = new JLabel(
                    "L.R PERFUMES",
                    SwingConstants.CENTER
            );

            tituloLogo.setBounds(
                    250,
                    50,
                    500,
                    50
            );

            tituloLogo.setForeground(
                    new Color(212, 175, 55)
            );

            tituloLogo.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            36
                    )
            );

            fondo.add(tituloLogo);
        }

        JLabel titulo = new JLabel(
                "SISTEMA DE GESTIÓN",
                SwingConstants.CENTER
        );

        titulo.setBounds(
                250,
                260,
                500,
                40
        );

        titulo.setForeground(
                new Color(212, 175, 55)
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        fondo.add(titulo);

        configurarBoton(btnPerfumes, 180, 340);
        configurarBoton(btnClientes, 520, 340);

        configurarBoton(btnProveedores, 180, 440);
        configurarBoton(btnInventario, 520, 440);

        configurarBoton(btnVentas, 180, 540);
        configurarBoton(btnInstagram, 520, 540);

        fondo.add(btnPerfumes);
        fondo.add(btnClientes);
        fondo.add(btnProveedores);
        fondo.add(btnInventario);
        fondo.add(btnVentas);
        fondo.add(btnInstagram);

        btnSalir.setBounds(
                350,
                670,
                300,
                55
        );

        btnSalir.setBackground(
                new Color(120, 20, 20)
        );

        btnSalir.setForeground(Color.WHITE);

        btnSalir.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        btnSalir.setFocusPainted(false);

        fondo.add(btnSalir);

        add(fondo);
    }

    private void configurarBoton(
            JButton boton,
            int x,
            int y
    ) {

        boton.setBounds(
                x,
                y,
                280,
                65
        );

        boton.setBackground(
                new Color(212, 175, 55)
        );

        boton.setForeground(Color.BLACK);

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        boton.setFocusPainted(false);

        boton.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );
    }
}