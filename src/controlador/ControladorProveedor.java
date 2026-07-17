package controlador;

import dao.ProveedorDAO;
import modelo.Proveedor;
import vista.VistaProveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ControladorProveedor {
    private final VistaProveedor vista;
    private final ProveedorDAO dao = new ProveedorDAO();

    public ControladorProveedor(VistaProveedor vista) {
        this.vista = vista;
        init();
    }

    private void init() {
        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnConsultar.addActionListener(e -> cargarTabla());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila >= 0) {
                    vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                    vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
                    vista.txtTelefono.setText(vista.tabla.getValueAt(fila, 2) != null ? vista.tabla.getValueAt(fila, 2).toString() : "");
                    vista.txtEmail.setText(vista.tabla.getValueAt(fila, 3) != null ? vista.tabla.getValueAt(fila, 3).toString() : "");
                    vista.txtDireccion.setText(vista.tabla.getValueAt(fila, 4) != null ? vista.tabla.getValueAt(fila, 4).toString() : "");
                }
            }
        });
    }

    public void mostrar() {
        vista.setVisible(true);
        cargarTabla();
    }

    private void guardar() {
        Proveedor p = new Proveedor();
        p.setNombre(vista.txtNombre.getText().trim());
        p.setTelefono(vista.txtTelefono.getText().trim());
        p.setEmail(vista.txtEmail.getText().trim());
        p.setDireccion(vista.txtDireccion.getText().trim());
        if (p.getNombre().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre es obligatorio");
            return;
        }
        if (dao.crear(p)) {
            JOptionPane.showMessageDialog(vista, "Proveedor guardado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un proveedor de la tabla");
            return;
        }
        Proveedor p = new Proveedor();
        p.setId(Integer.parseInt(vista.txtId.getText()));
        p.setNombre(vista.txtNombre.getText().trim());
        p.setTelefono(vista.txtTelefono.getText().trim());
        p.setEmail(vista.txtEmail.getText().trim());
        p.setDireccion(vista.txtDireccion.getText().trim());
        if (dao.actualizar(p)) {
            JOptionPane.showMessageDialog(vista, "Proveedor actualizado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un proveedor");
            return;
        }
        int id = Integer.parseInt(vista.txtId.getText());
        int op = JOptionPane.showConfirmDialog(vista, "¿Eliminar proveedor " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(vista, "Eliminado");
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarTabla() {
        List<Proveedor> lista = dao.listar();
        DefaultTableModel model = (DefaultTableModel) vista.tabla.getModel();
        model.setRowCount(0);
        for (Proveedor p : lista) {
            model.addRow(new Object[]{p.getId(), p.getNombre(), p.getTelefono(), p.getEmail(), p.getDireccion()});
        }
    }

    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtEmail.setText("");
        vista.txtDireccion.setText("");
    }
}

