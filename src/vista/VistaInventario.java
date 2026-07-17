package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaInventario extends JFrame {

    public JTable tabla = new JTable();

    public VistaInventario() {

        setTitle("Inventario");
        setSize(900,600);
        setLocationRelativeTo(null);

        getContentPane().setBackground(
                new Color(15,15,15)
        );

        JLabel titulo = new JLabel(
                "INVENTARIO DE PERFUMES",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        titulo.setForeground(
                new Color(212,175,55)
        );

        add(titulo, BorderLayout.NORTH);

        tabla.setModel(
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                                "ID",
                                "Perfume",
                                "Marca",
                                "Cantidad",
                                "Precio"
                        }
                )
        );

        add(
                new JScrollPane(tabla),
                BorderLayout.CENTER
        );
    }
}