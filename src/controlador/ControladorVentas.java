package controlador;

import dao.ClienteDAO;
import dao.PerfumeDAO;
import dao.VentaDAO;

import modelo.Cliente;
import modelo.Perfume;
import modelo.Venta;

import vista.VistaVentas;

import javax.swing.JOptionPane;

public class ControladorVentas {

    private VistaVentas vista;

    private ClienteDAO clienteDAO =
            new ClienteDAO();

    private PerfumeDAO perfumeDAO =
            new PerfumeDAO();

    private VentaDAO ventaDAO =
            new VentaDAO();

    public ControladorVentas(VistaVentas vista) {

        this.vista = vista;

        cargarClientes();
        cargarPerfumes();

        eventos();
    }

    private void cargarClientes() {

        vista.cmbClientes.removeAllItems();

        for (Cliente c : clienteDAO.listar()) {

            vista.cmbClientes.addItem(c);

        }
    }

    private void cargarPerfumes() {

        vista.cmbPerfumes.removeAllItems();

        for (Perfume p : perfumeDAO.listar()) {

            vista.cmbPerfumes.addItem(p);

        }
    }

    private void eventos() {

        vista.cmbPerfumes.addActionListener(e -> {

            Perfume p =
                    (Perfume) vista.cmbPerfumes.getSelectedItem();

            if (p != null) {

                vista.lblPrecio.setText(
                        "$" + p.getPrecio()
                );

            }

        });

        vista.btnCalcular.addActionListener(e -> {

            calcularTotal();

        });

        vista.btnRegistrar.addActionListener(e -> {

            registrarVenta();

        });
    }

    private void calcularTotal() {

        try {

            Perfume perfume =
                    (Perfume) vista.cmbPerfumes.getSelectedItem();

            if (perfume == null) return;

            int cantidad =
                    Integer.parseInt(
                            vista.txtCantidad.getText()
                    );

            double total =
                    perfume.getPrecio() * cantidad;

            vista.lblTotal.setText(
                    "$" + total
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Cantidad inválida"
            );
        }
    }

    private void registrarVenta() {

        try {

            Cliente cliente =
                    (Cliente) vista.cmbClientes.getSelectedItem();

            Perfume perfume =
                    (Perfume) vista.cmbPerfumes.getSelectedItem();

            if(cliente == null || perfume == null){

                JOptionPane.showMessageDialog(
                        vista,
                        "Seleccione cliente y perfume"
                );

                return;
            }

            int cantidad =
                    Integer.parseInt(
                            vista.txtCantidad.getText()
                    );

            if(cantidad <= 0){

                JOptionPane.showMessageDialog(
                        vista,
                        "Cantidad inválida"
                );

                return;
            }

            if(cantidad > perfume.getCantidad()){

                JOptionPane.showMessageDialog(
                        vista,
                        "No hay suficiente inventario.\n\nStock disponible: "
                                + perfume.getCantidad()
                );

                return;
            }

            double total =
                    perfume.getPrecio() * cantidad;

            Venta venta =
                    new Venta();

            venta.setClienteId(
                    cliente.getId()
            );

            venta.setPerfumeId(
                    perfume.getId()
            );

            venta.setCantidad(
                    cantidad
            );

            venta.setTotal(
                    total
            );

            boolean guardado =
                    ventaDAO.registrar(
                            venta
                    );

            if(guardado){

                int nuevoStock =
                        perfume.getCantidad()
                                - cantidad;

                perfumeDAO.actualizarStock(
                        perfume.getId(),
                        nuevoStock
                );

                JOptionPane.showMessageDialog(
                        vista,
                        "Venta registrada correctamente\n\nTotal: $" + total
                );

                vista.txtCantidad.setText("1");

                vista.lblTotal.setText("$0.00");

                cargarPerfumes();

            }else{

                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo registrar la venta"
                );

            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al registrar venta"
            );
        }
    }
}