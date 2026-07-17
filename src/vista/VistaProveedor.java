package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaProveedor extends JFrame {
    public JTextField txtId = new JTextField();
    public JTextField txtNombre = new JTextField();
    public JTextField txtTelefono = new JTextField();
    public JTextField txtEmail = new JTextField();
    public JTextField txtDireccion = new JTextField();

    public JButton btnGuardar = new JButton("Guardar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnConsultar = new JButton("Consultar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JTable tabla = new JTable();

    public VistaProveedor() {
        setTitle("Proveedores");
        setSize(800,600);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(0,2,5,5));
        txtId.setVisible(false);

        form.add(new JLabel("ID"));
        form.add(txtId);
        form.add(new JLabel("Nombre"));
        form.add(txtNombre);
        form.add(new JLabel("Teléfono"));
        form.add(txtTelefono);
        form.add(new JLabel("Email"));
        form.add(txtEmail);
        form.add(new JLabel("Dirección"));
        form.add(txtDireccion);

        form.add(btnGuardar);
        form.add(btnActualizar);
        form.add(btnEliminar);
        form.add(btnConsultar);
        form.add(btnLimpiar);

        add(form, BorderLayout.NORTH);

        tabla.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID","Nombre","Teléfono","Email","Dirección"}
        ));
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
