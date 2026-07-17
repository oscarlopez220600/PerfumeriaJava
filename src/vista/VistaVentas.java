package vista;

import modelo.Cliente;
import modelo.Perfume;

import javax.swing.*;
import java.awt.*;

public class VistaVentas extends JFrame {

    public JComboBox<Cliente> cmbClientes =
            new JComboBox<>();

    public JComboBox<Perfume> cmbPerfumes =
            new JComboBox<>();

    public JTextField txtCantidad =
            new JTextField("1");

    public JLabel lblPrecio =
            new JLabel("$0.00");

    public JLabel lblTotal =
            new JLabel("$0.00");

    public JButton btnCalcular =
            new JButton("Calcular");

    public JButton btnRegistrar =
            new JButton("Registrar Venta");

    public VistaVentas() {

        setTitle("Ventas");
        setSize(700,500);
        setLocationRelativeTo(null);

        getContentPane().setBackground(
                new Color(15,15,15)
        );

        JLabel titulo =
                new JLabel(
                        "REGISTRAR VENTA",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        titulo.setForeground(
                new Color(212,175,55)
        );

        JPanel panel = new JPanel();

        panel.setBackground(
                new Color(15,15,15)
        );

        panel.setLayout(
                new GridLayout(
                        0,
                        2,
                        10,
                        10
                )
        );

        JLabel lblCliente =
                new JLabel("Cliente");

        JLabel lblPerfume =
                new JLabel("Perfume");

        JLabel lblCantidadTexto =
                new JLabel("Cantidad");

        JLabel lblPrecioTexto =
                new JLabel("Precio");

        JLabel lblTotalTexto =
                new JLabel("Total");

        Color dorado =
                new Color(212,175,55);

        lblCliente.setForeground(dorado);
        lblPerfume.setForeground(dorado);
        lblCantidadTexto.setForeground(dorado);
        lblPrecioTexto.setForeground(dorado);
        lblTotalTexto.setForeground(dorado);

        lblPrecio.setForeground(Color.WHITE);
        lblTotal.setForeground(Color.WHITE);

        panel.add(lblCliente);
        panel.add(cmbClientes);

        panel.add(lblPerfume);
        panel.add(cmbPerfumes);

        panel.add(lblPrecioTexto);
        panel.add(lblPrecio);

        panel.add(lblCantidadTexto);
        panel.add(txtCantidad);

        panel.add(lblTotalTexto);
        panel.add(lblTotal);

        panel.add(btnCalcular);
        panel.add(btnRegistrar);

        add(titulo, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }
}