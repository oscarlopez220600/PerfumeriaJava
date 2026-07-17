package controlador;

import dao.PerfumeDAO;
import dao.ProveedorDAO;
import modelo.Perfume;
import modelo.Proveedor;
import vista.VistaCliente;
import vista.VistaPerfume;
import vista.VistaProveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ControladorPerfume {
    private final VistaPerfume vista;
    private final PerfumeDAO dao = new PerfumeDAO();
    private final ProveedorDAO proveedorDAO = new ProveedorDAO();

    public ControladorPerfume(VistaPerfume vista) {
        this.vista = vista;
        init();
    }

    public void iniciar() {
        cargarProveedores();
        cargarTabla();
    }

    private void init() {
        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnConsultar.addActionListener(e -> cargarTabla());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.menuSalir.addActionListener(e -> System.exit(0));
        vista.menuProveedores.addActionListener(e -> {
            VistaProveedor vp = new VistaProveedor();
            ControladorProveedor cp = new ControladorProveedor(vp);
            cp.mostrar();
        });
        vista.menuClientes.addActionListener(e -> {
            VistaCliente vc = new VistaCliente();
            ControladorCliente cc = new ControladorCliente(vc);
            cc.mostrar();
        });
        vista.menuLogout.addActionListener(e -> {
            vista.dispose();
            new ControladorLogin(new vista.VistaLogin()); // volver a login
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila >= 0) {
                    vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                    vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
                    vista.txtMarca.setText(vista.tabla.getValueAt(fila, 2).toString());
                    vista.txtPrecio.setText(vista.tabla.getValueAt(fila, 3).toString());
                    vista.txtCodigo.setText(vista.tabla.getValueAt(fila, 4).toString());
                    vista.txtCantidad.setText(vista.tabla.getValueAt(fila, 5).toString());

                    // Proveedor
                    Object provIdObj = vista.tabla.getValueAt(fila, 6);
                    if (provIdObj != null) {
                        int provId = Integer.parseInt(provIdObj.toString());
                        seleccionarProveedorEnCombo(provId);
                    } else {
                        vista.cmbProveedor.setSelectedIndex(-1);
                    }

                    vista.cmbCategoria.setSelectedItem(vista.tabla.getValueAt(fila, 7));
                    String genero = vista.tabla.getValueAt(fila, 8).toString();
                    if ("Mujer".equalsIgnoreCase(genero)) vista.rbMujer.setSelected(true);
                    else vista.rbHombre.setSelected(true);

                    boolean dispo = "true".equalsIgnoreCase(vista.tabla.getValueAt(fila, 9).toString())
                            || "1".equals(vista.tabla.getValueAt(fila, 9).toString());
                    vista.chkDisponible.setSelected(dispo);

                    String aroma = vista.tabla.getValueAt(fila, 10) != null ? vista.tabla.getValueAt(fila, 10).toString() : null;
                    if (aroma != null) vista.listaAromas.setSelectedValue(aroma, true);
                }
            }
        });
    }

    private void seleccionarProveedorEnCombo(int provId) {
        ComboBoxModel<Object> model = vista.cmbProveedor.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Object item = model.getElementAt(i);
            if (item instanceof Proveedor) {
                if (((Proveedor) item).getId() == provId) {
                    vista.cmbProveedor.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void cargarProveedores() {
        vista.cmbProveedor.removeAllItems();
        List<Proveedor> proveedores = proveedorDAO.listar();
        for (Proveedor p : proveedores) {
            vista.cmbProveedor.addItem(p);
        }
        vista.cmbProveedor.setSelectedIndex(proveedores.isEmpty() ? -1 : 0);
    }

    private void guardar() {
        Perfume p = leerFormulario();
        if (p == null) return;
        if (dao.crear(p)) {
            JOptionPane.showMessageDialog(vista, "Perfume guardado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro");
            return;
        }
        Perfume p = leerFormulario();
        if (p == null) return;
        p.setId(Integer.parseInt(vista.txtId.getText()));
        if (dao.actualizar(p)) {
            JOptionPane.showMessageDialog(vista, "Perfume actualizado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro");
            return;
        }
        int id = Integer.parseInt(vista.txtId.getText());
        int op = JOptionPane.showConfirmDialog(vista, "¿Eliminar perfume " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
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

    private Perfume leerFormulario() {
        try {
            String nombre = vista.txtNombre.getText().trim();
            String marca = vista.txtMarca.getText().trim();
            double precio = Double.parseDouble(vista.txtPrecio.getText().trim());
            String codigo = vista.txtCodigo.getText().trim();
            int cantidad = Integer.parseInt(vista.txtCantidad.getText().trim());
            Proveedor proveedor = (Proveedor) vista.cmbProveedor.getSelectedItem();
            Integer proveedorId = proveedor != null ? proveedor.getId() : null;
            String categoria = (String) vista.cmbCategoria.getSelectedItem();
            String genero = vista.rbMujer.isSelected() ? "Mujer" : "Hombre";
            boolean disponible = vista.chkDisponible.isSelected();
            String descripcion = vista.txtDescripcion.getText().trim();
            String aroma = vista.listaAromas.getSelectedValue();

            if (nombre.isEmpty() || marca.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Nombre, Marca y Código son obligatorios");
                return null;
            }

            Perfume p = new Perfume();
            p.setNombre(nombre);
            p.setMarca(marca);
            p.setPrecio(precio);
            p.setCodigo(codigo);
            p.setCantidad(cantidad);
            p.setProveedorId(proveedorId);
            p.setCategoria(categoria);
            p.setGenero(genero);
            p.setDisponible(disponible);
            p.setDescripcion(descripcion);
            p.setAroma(aroma);
            return p;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio y Cantidad deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void cargarTabla() {
        List<Perfume> lista = dao.listar();
        DefaultTableModel model = (DefaultTableModel) vista.tabla.getModel();
        model.setRowCount(0);
        for (Perfume p : lista) {
            model.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getMarca(), p.getPrecio(), p.getCodigo(),
                    p.getCantidad(), p.getProveedorId(), p.getCategoria(), p.getGenero(),
                    p.isDisponible(), p.getAroma()
            });
        }
    }

    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtMarca.setText("");
        vista.txtPrecio.setText("");
        vista.txtCodigo.setText("");
        vista.txtCantidad.setText("");
        vista.cmbProveedor.setSelectedIndex(-1);
        vista.cmbCategoria.setSelectedIndex(0);
        vista.rbHombre.setSelected(true);
        vista.chkDisponible.setSelected(false);
        vista.txtDescripcion.setText("");
        vista.listaAromas.clearSelection();
        vista.tabla.clearSelection();
    }
}
