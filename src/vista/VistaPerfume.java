package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPerfume extends JFrame {

    public JTextField txtId = new JTextField();
    public JTextField txtNombre = new JTextField();
    public JTextField txtMarca = new JTextField();
    public JTextField txtPrecio = new JTextField();
    public JTextField txtCodigo = new JTextField();
    public JTextField txtCantidad = new JTextField();

    public JComboBox<Object> cmbProveedor = new JComboBox<>();
    public JComboBox<String> cmbCategoria = new JComboBox<>();

    public JCheckBox chkDisponible = new JCheckBox("Disponible");

    public JRadioButton rbHombre = new JRadioButton("Hombre");
    public JRadioButton rbMujer = new JRadioButton("Mujer");

    public ButtonGroup grupoGenero = new ButtonGroup();

    public JTextArea txtDescripcion = new JTextArea(4,20);

    public JList<String> listaAromas = new JList<>(
            new String[]{
                    "Dulce",
                    "Amaderado",
                    "Cítrico",
                    "Avainillado",
                    "Acuático",
                    "Especiado",
                    "Floral",
                    "Frutal"
            });

    public JButton btnGuardar = new JButton("Guardar");
    public JButton btnConsultar = new JButton("Consultar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JMenuItem menuSalir = new JMenuItem("Salir");
    public JMenuItem menuClientes = new JMenuItem("Clientes");
    public JMenuItem menuProveedores = new JMenuItem("Proveedores");
    public JMenuItem menuLogout = new JMenuItem("Cerrar Sesión");

    public JTable tabla = new JTable();

    public VistaPerfume() {

        setTitle("Sistema de Perfumería");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar barra = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");
        JMenu catalogos = new JMenu("Catálogos");
        JMenu sesion = new JMenu("Sesión");

        archivo.add(menuSalir);

        catalogos.add(menuClientes);
        catalogos.add(menuProveedores);

        sesion.add(menuLogout);

        barra.add(archivo);
        barra.add(catalogos);
        barra.add(sesion);

        setJMenuBar(barra);

        cmbCategoria.addItem("Designer");
        cmbCategoria.addItem("Árabe");
        cmbCategoria.addItem("Nicho");
        cmbCategoria.addItem("Clon");
        cmbCategoria.addItem("EDP");
        cmbCategoria.addItem("EDT");
        cmbCategoria.addItem("PARFUM");

        grupoGenero.add(rbHombre);
        grupoGenero.add(rbMujer);

        txtId.setEditable(false);

        JPanel panelDatos = new JPanel(new GridLayout(0,2,5,5));

        panelDatos.add(new JLabel("ID"));
        panelDatos.add(txtId);

        panelDatos.add(new JLabel("Nombre"));
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Marca"));
        panelDatos.add(txtMarca);

        panelDatos.add(new JLabel("Precio"));
        panelDatos.add(txtPrecio);

        panelDatos.add(new JLabel("Código"));
        panelDatos.add(txtCodigo);

        panelDatos.add(new JLabel("Cantidad"));
        panelDatos.add(txtCantidad);

        panelDatos.add(new JLabel("Proveedor"));
        panelDatos.add(cmbProveedor);

        panelDatos.add(new JLabel("Categoría"));
        panelDatos.add(cmbCategoria);

        JPanel panelGenero = new JPanel();

        panelGenero.add(rbHombre);
        panelGenero.add(rbMujer);

        panelDatos.add(new JLabel("Género"));
        panelDatos.add(panelGenero);

        panelDatos.add(new JLabel("Disponible"));
        panelDatos.add(chkDisponible);

        panelDatos.add(new JLabel("Descripción"));
        panelDatos.add(new JScrollPane(txtDescripcion));

        panelDatos.add(new JLabel("Aroma"));
        panelDatos.add(new JScrollPane(listaAromas));

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnGuardar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Perfumes", panelPrincipal);
        tabs.addTab("Inventario", new JPanel());

        add(tabs, BorderLayout.WEST);

        tabla.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID",
                        "Nombre",
                        "Marca",
                        "Precio",
                        "Código",
                        "Cantidad",
                        "Proveedor",
                        "Categoría",
                        "Género",
                        "Disponible",
                        "Aroma"
                }
        ));

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}