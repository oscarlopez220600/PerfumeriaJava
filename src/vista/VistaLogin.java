package vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {

    public JTextField txtUsuario = new JTextField();
    public JPasswordField txtPassword = new JPasswordField();

    public JButton btnIngresar = new JButton("INGRESAR");
    public JButton btnCancelar = new JButton("SALIR");

    public VistaLogin() {

        setTitle("L.R Perfumes");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(15,15,15));

        // LOGO
        try {

            ImageIcon icono = new ImageIcon(
                    getClass().getResource("/Imagenes/logo.jpeg")
            );

            Image imagen = icono.getImage()
                    .getScaledInstance(
                            300,
                            300,
                            Image.SCALE_SMOOTH
                    );

            JLabel lblLogo =
                    new JLabel(new ImageIcon(imagen));

            lblLogo.setBounds(150,20,300,300);

            panel.add(lblLogo);

        } catch(Exception e){

            JLabel lblLogo =
                    new JLabel("L.R PERFUMES");

            lblLogo.setForeground(
                    new Color(212,175,55)
            );

            lblLogo.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            32
                    )
            );

            lblLogo.setBounds(150,100,300,50);

            panel.add(lblLogo);
        }

        JLabel lblUsuario =
                new JLabel("Usuario");

        lblUsuario.setForeground(Color.WHITE);

        lblUsuario.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        lblUsuario.setBounds(100,340,100,30);

        panel.add(lblUsuario);

        txtUsuario.setBounds(
                100,
                370,
                400,
                40
        );

        txtUsuario.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        panel.add(txtUsuario);

        JLabel lblPassword =
                new JLabel("Contraseña");

        lblPassword.setForeground(Color.WHITE);

        lblPassword.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        lblPassword.setBounds(
                100,
                430,
                150,
                30
        );

        panel.add(lblPassword);

        txtPassword.setBounds(
                100,
                460,
                400,
                40
        );

        txtPassword.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        panel.add(txtPassword);

        btnIngresar.setBounds(
                100,
                540,
                180,
                50
        );

        btnIngresar.setBackground(
                new Color(212,175,55)
        );

        btnIngresar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        panel.add(btnIngresar);

        btnCancelar.setBounds(
                320,
                540,
                180,
                50
        );

        btnCancelar.setBackground(
                Color.LIGHT_GRAY
        );

        btnCancelar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        panel.add(btnCancelar);

        add(panel);
    }
}