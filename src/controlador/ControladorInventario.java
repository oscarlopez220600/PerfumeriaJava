package controlador;

import dao.PerfumeDAO;
import modelo.Perfume;
import vista.VistaInventario;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorInventario {

    private VistaInventario vista;
    private PerfumeDAO dao = new PerfumeDAO();

    public ControladorInventario(VistaInventario vista) {

        this.vista = vista;

        cargarInventario();
    }

    private void cargarInventario() {

        DefaultTableModel model =
                (DefaultTableModel) vista.tabla.getModel();

        model.setRowCount(0);

        List<Perfume> lista = dao.listar();

        for (Perfume p : lista) {

            model.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getMarca(),
                    p.getCantidad(),
                    p.getPrecio()
            });

        }

    }
}