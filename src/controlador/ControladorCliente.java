package controlador;

import dao.ClienteDAO;
import modelo.Cliente;
import vista.VistaCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ControladorCliente {
    private final VistaCliente vista;
    private final ClienteDAO dao = new ClienteDAO();

    public ControladorCliente(VistaCliente vista) {
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
        Cliente c = new Cliente();
        c.setNombre(vista.txtNombre.getText().trim());
        c.setTelefono(vista.txtTelefono.getText().trim());
        c.setEmail(vista.txtEmail.getText().trim());
        c.setDireccion(vista.txtDireccion.getText().trim());
        if (c.getNombre().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre es obligatorio");
            return;
        }
        if (dao.crear(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente");
            return;
        }
        Cliente c = new Cliente();
        c.setId(Integer.parseInt(vista.txtId.getText()));
        c.setNombre(vista.txtNombre.getText().trim());
        c.setTelefono(vista.txtTelefono.getText().trim());
        c.setEmail(vista.txtEmail.getText().trim());
        c.setDireccion(vista.txtDireccion.getText().trim());
        if (dao.actualizar(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente actualizado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente");
            return;
        }
        int id = Integer.parseInt(vista.txtId.getText());
        int op = JOptionPane.showConfirmDialog(vista, "¿Eliminar cliente " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
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
        List<Cliente> lista = dao.listar();
        DefaultTableModel model = (DefaultTableModel) vista.tabla.getModel();
        model.setRowCount(0);
        for (Cliente c : lista) {
            model.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getEmail(), c.getDireccion()});
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
