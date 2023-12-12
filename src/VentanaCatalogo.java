import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaCatalogo extends JFrame {

    public static void main(String[] args) {
        RegistroProductos registro = new RegistroProductos();
        // Carnes
        registro.agregarProducto(1, "Carne de Res Molida", "500 g", 17.0, 2, 10);
        registro.agregarProducto(2, "Pollo Entero", "1.7 kg", 39.0, 3, 6);
        registro.agregarProducto(3, "Hamburguesa de Res", "12 unidades", 31.9, 2, 21);
        registro.agregarProducto(4, "Chorizo de Res", "350 g", 16.5, 2, 9);
        registro.agregarProducto(5, "Nuggets de Pollo", "20 unidades", 28.0, 3, 10);
        registro.agregarProducto(6, "Chuleta de cerdo", "1 unidad", 16.5, 6, 15);
        registro.agregarProducto(7, "Pechuga de Pavo Ahumada", "2.1 kg", 254.44, 3, 7);

// Bebidas
        registro.agregarProducto(8, "Gaseosa Coca-Cola", "3 Litros", 13.0, 21, 13);
        registro.agregarProducto(9, "Gaseosa Sprite", "2 Litros", 8.0, 21, 10);
        registro.agregarProducto(10, "Gaseosa Fanta", "2.5 Litros", 11.0, 21, 25);
        registro.agregarProducto(11, "Jugo Del Valle Manzana", "2 Litros", 13.5, 21, 74);
        registro.agregarProducto(12, "Agua Vital sin Gas", "2 Litros", 7.6, 21, 77);
        registro.agregarProducto(13, "Bebida Powerade Mora", "1.1 Litros", 11.0, 21, 25);
        registro.agregarProducto(14, "Ron Havana Club", "1 Litro", 80.0, 20, 20);

// Lácteos
        registro.agregarProducto(15, "Mantequilla Pil Con Sal", "200 g", 13.8, 13, 5);
        registro.agregarProducto(16, "Leche Pil Deslactosada", "800 ml", 8.1, 8, 20);
        registro.agregarProducto(17, "Leche Pil Natural", "946 ml", 6.7, 8, 8);
        registro.agregarProducto(18, "Yogurt Bebible Frutilla", "1 litro", 11.0, 11, 6);
        registro.agregarProducto(19, "Queso Crema Bonle", "200 g", 17.3, 12, 20);
        registro.agregarProducto(20, "Leche de Soya Natural Pil", "946 ml", 5.8, 8, 15);
        registro.agregarProducto(21, "Yogurt Griego Natural", "170 ml", 5.89, 11, 8);

// Golosinas
        registro.agregarProducto(22, "Bombones Surtidos Nestle", "153 g", 17.9, 15, 20);
        registro.agregarProducto(23, "Papas Lay’s", "105 g", 12.0, 18, 15);
        registro.agregarProducto(24, "Chocolate KitKat", "41.5 g", 8.7, 15, 20);
        registro.agregarProducto(25, "Chocolate Vizzio Estuche", "182.4 g", 32.5, 15, 28);
        registro.agregarProducto(26, "Mogul Dientes", "150 g", 6.0, 15, 20);
        registro.agregarProducto(27, "Tableta Blanca Breick", "100.4 g", 18.7, 15, 30);
        registro.agregarProducto(28, "Papitas Pringles", "149 g", 21.0, 18, 20);

        List<Integer> l = registro.obtenerTodosLosIDs();
        NotificacionesAlertas na = new NotificacionesAlertas();
        for (Producto p:registro.obtenerProductos(l)){
            if (p.getDisponibilidad() == 0) {
                Notificacion notificacionAgotado = new Notificacion("Producto agotado: " + p.getNombre(), 0);
                na.agregarNotificacion(notificacionAgotado);
            }
            // Agregar notificación de oferta especial
            // Supongamos que solo notificamos si hay menos de 10 unidades disponibles
            if (p.getDisponibilidad() < 5) {
                Notificacion notificacionOferta = new Notificacion("Oferta especial: " + p.getNombre(), 1);
                na.agregarNotificacion(notificacionOferta);
            }

        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaCatalogo frame = new VentanaCatalogo(registro, registro.obtenerTodosLosIDs(), na, "Luwu");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private List<JLabel> im;
    private List<JLabel> no;
    private List<JLabel> ca;
    private List<JLabel> pr;
    private JTextField txtTotal;

    private JComboBox<Integer> cantidadComboBox1;
    private JComboBox<Integer> cantidadComboBox2;
    private JComboBox<Integer> cantidadComboBox3;
    private JComboBox<Integer> cantidadComboBox4;
    private JComboBox<Integer> cantidadComboBox5;
    private JComboBox<Integer> cantidadComboBox6;
    private JComboBox<Integer> cantidadComboBox7;
    // Declara una lista que contendrá todas tus JComboBox de cantidad
    private List<Double> preciosTotales = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    private List<JComboBox<Integer>> cantidadComboBoxes = new ArrayList<>();
    private JComboBox<String> CategoriaComboBox;
    public CarritoDeCompras carrito = new CarritoDeCompras();
    private int xOffset, yOffset;

    public VentanaCatalogo(RegistroProductos registro, ArrayList<Integer> ids, NotificacionesAlertas na, String usuario) {
        HistorialCompras historialCompras = new HistorialCompras();
        Map<Integer, String> map = MapHelper.obtenerCategoriasMap();
        GestionCategorias grafo = GrafoHelper.obtenerGrafo();
        na.procesarNotificaciones();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 730);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setUndecorated(true);
        setLocationByPlatform(true);
        setBounds(100, 100, 1000, 700);

        JPanel panelSup = new JPanel();
        panelSup.setBackground(new Color(18, 55, 107));
        panelSup.setBounds(0, 0, 1000, 20);
        panelSup.setLayout(null);

        panelSup.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });
        panelSup.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - xOffset, e.getYOnScreen() - yOffset);
            }
        });

        JPanel panelX = new JPanel();
        panelX.setBackground(new Color(22, 45, 78));
        panelX.setBounds(980, 0, 20, 20); // Ajusta la posición al lado derecho del panel superior
        panelX.setLayout(null);

        JLabel lblExit = new JLabel("X");
        lblExit.setHorizontalAlignment(SwingConstants.CENTER);
        lblExit.setForeground(Color.WHITE);
        lblExit.setBounds(0, 0, 20, 20);
        lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                panelX.setBackground(new Color(191, 64, 53));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panelX.setBackground(new Color(22, 45, 78));
            }
        });

        panelX.add(lblExit);
        panelSup.add(panelX);
        contentPane.add(panelSup);

        //Lista de Labels de productos
        im = new ArrayList<>();
        no = new ArrayList<>();
        ca = new ArrayList<>();
        pr = new ArrayList<>();

        //Producto 1
        JLabel i1 = new JLabel();
        i1.setBounds(20, 60, 185, 159);
        contentPane.add(i1);
        String imagePathInicial = obtenerRutaImagen("1");
        rsscalelabel.RSScaleLabel.setScaleLabel(i1, imagePathInicial);
        JLabel n1 = new JLabel();
        n1.setBounds(20, 223, 200, 20);
        contentPane.add(n1);
        JLabel c1 = new JLabel();
        c1.setBounds(20, 249, 200, 20);
        contentPane.add(c1);
        JLabel p1 = new JLabel();
        p1.setBounds(20, 272, 200, 20);
        contentPane.add(p1);

        //Producto 2
        JLabel i2 = new JLabel();
        i2.setBounds(215, 60, 181, 159);
        contentPane.add(i2);
        JLabel n2 = new JLabel();
        n2.setBounds(215, 223, 200, 20);
        contentPane.add(n2);
        JLabel c2 = new JLabel();
        c2.setBounds(215, 249, 200, 20);
        contentPane.add(c2);
        JLabel p2 = new JLabel();
        p2.setBounds(215, 272, 200, 20);
        contentPane.add(p2);

        //Producto 3
        JLabel i3 = new JLabel();
        i3.setBounds(432, 60, 181, 155);
        contentPane.add(i3);
        JLabel n3 = new JLabel();
        n3.setBounds(431, 223, 200, 20);
        contentPane.add(n3);
        JLabel c3 = new JLabel();
        c3.setBounds(432, 249, 200, 20);
        contentPane.add(c3);
        JLabel p3 = new JLabel();
        p3.setBounds(431, 272, 200, 20);
        contentPane.add(p3);


        //Producto 4
        JLabel i4 = new JLabel();
        i4.setBounds(708, 60, 181, 159);
        contentPane.add(i4);
        JLabel n4 = new JLabel();
        n4.setBounds(708, 223, 200, 20);
        contentPane.add(n4);
        JLabel c4 = new JLabel();
        c4.setBounds(708, 249, 200, 20);
        contentPane.add(c4);
        JLabel p4 = new JLabel();
        p4.setBounds(708, 272, 200, 20);
        contentPane.add(p4);


        //Producto 5
        JLabel i5 = new JLabel();
        i5.setBounds(24, 353, 181, 164);
        contentPane.add(i5);
        JLabel n5 = new JLabel();
        n5.setBounds(20, 553, 200, 20);
        contentPane.add(n5);
        JLabel c5 = new JLabel();
        c5.setBounds(20, 583, 200, 20);
        contentPane.add(c5);
        JLabel p5 = new JLabel();
        p5.setBounds(20, 613, 200, 20);
        contentPane.add(p5);

        //Producto 6
        JLabel i6 = new JLabel();
        i6.setBounds(246, 353, 174, 164);
        contentPane.add(i6);
        JLabel n6 = new JLabel();
        n6.setBounds(246, 553, 200, 20);
        contentPane.add(n6);
        JLabel c6 = new JLabel();
        c6.setBounds(246, 583, 200, 20);
        contentPane.add(c6);
        JLabel p6 = new JLabel();
        p6.setBounds(250, 613, 200, 20);
        contentPane.add(p6);

        //Producto 7
        JLabel i7 = new JLabel();
        i7.setBounds(486, 353, 174, 164);
        contentPane.add(i7);
        JLabel n7 = new JLabel();
        n7.setBounds(486, 553, 200, 20);
        contentPane.add(n7);
        JLabel c7 = new JLabel();
        c7.setBounds(486, 583, 200, 20);
        contentPane.add(c7);
        JLabel p7 = new JLabel();
        p7.setBounds(486, 613, 200, 20);
        contentPane.add(p7);

        //ArrayList componentes productos
        im = new ArrayList<>(Arrays.asList(i1, i2, i3, i4, i5, i6, i7));
        no = new ArrayList<>(Arrays.asList(n1, n2, n3, n4, n5, n6, n7));
        ca = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
        pr = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        //inicializarProductos();

        //ComboBoxCategorias
        JComboBox CategoriaComboBox = new JComboBox<String>();
        CategoriaComboBox.addItem(map.get(1));
        CategoriaComboBox.addItem(map.get(7));
        CategoriaComboBox.addItem(map.get(14));
        CategoriaComboBox.addItem(map.get(19));
        CategoriaComboBox.setBounds(10, 31, 150, 25);
        contentPane.add(CategoriaComboBox);
        CategoriaComboBox.setSelectedItem("Carnes");

        //ComboBoxSubCategorias
        JComboBox<String> comboBoxSubCategoria = new JComboBox<>();
        comboBoxSubCategoria.setBounds(304, 31, 131, 24);
        contentPane.add(comboBoxSubCategoria);
        comboBoxSubCategoria.setVisible(false);
        comboBoxSubCategoria.setEnabled(false);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (CategoriaComboBox.getSelectedItem().equals(entry.getValue())) {
                Integer idCategoria = entry.getKey();
                List<String> subcategorías = MapHelper.obtenerSubs(GrafoHelper.obtenerSub(idCategoria));
                for (String c : subcategorías){
                    comboBoxSubCategoria.addItem(c);
                }
            }
        }
        //COMBOBOX CANTIDADES
        JComboBox<Integer> cantidadComboBox1 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox1.addItem(i);
        }
        cantidadComboBox1.setSelectedItem(0);
        cantidadComboBox1.setBounds(135, 267, 50, 25);
        contentPane.add(cantidadComboBox1);
        //combobox2
        JComboBox<Integer> cantidadComboBox2 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox2.addItem(i);
        }
        cantidadComboBox2.setSelectedItem(0);
        cantidadComboBox2.setBounds(319, 267, 50, 25);
        contentPane.add(cantidadComboBox2);

        //combobox3
        JComboBox<Integer> cantidadComboBox3 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox3.addItem(i);
        }
        cantidadComboBox3.setSelectedItem(0);
        cantidadComboBox3.setBounds(538, 267, 50, 25);
        contentPane.add(cantidadComboBox3);
        //combobox4
        JComboBox<Integer> cantidadComboBox4 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox4.addItem(i);
        }
        cantidadComboBox4.setSelectedItem(0);
        cantidadComboBox4.setBounds(810, 267, 50, 25);
        contentPane.add(cantidadComboBox4);

        //combobox5
        JComboBox<Integer> cantidadComboBox5 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox5.addItem(i);
        }
        cantidadComboBox5.setSelectedItem(0);
        cantidadComboBox5.setBounds(135, 610, 50, 25);
        contentPane.add(cantidadComboBox5);

        //combobox6
        JComboBox<Integer> cantidadComboBox6 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox6.addItem(i);
        }
        cantidadComboBox6.setSelectedItem(0);
        cantidadComboBox6.setBounds(350, 610, 50, 25);
        contentPane.add(cantidadComboBox6);

        //combobox7
        JComboBox<Integer> cantidadComboBox7 = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cantidadComboBox7.addItem(i);
        }
        cantidadComboBox7.setSelectedItem(0);
        cantidadComboBox7.setBounds(580, 610, 50, 25);
        contentPane.add(cantidadComboBox7);
        cantidadComboBoxes.add(cantidadComboBox1);
        cantidadComboBoxes.add(cantidadComboBox2);
        cantidadComboBoxes.add(cantidadComboBox3);
        cantidadComboBoxes.add(cantidadComboBox4);
        cantidadComboBoxes.add(cantidadComboBox5);
        cantidadComboBoxes.add(cantidadComboBox6);
        cantidadComboBoxes.add(cantidadComboBox7);

        cantidadComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 1);
                int cantidadSeleccionada = (int) cantidadComboBox1.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;

                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;

                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });

        cantidadComboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 2);
                int cantidadSeleccionada = (int) cantidadComboBox2.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;

                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;

                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });


        cantidadComboBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 3);
                int cantidadSeleccionada = (int) cantidadComboBox3.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;
                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;
                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });

        cantidadComboBox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 4);
                int cantidadSeleccionada = (int) cantidadComboBox4.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;
                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;
                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });
        cantidadComboBox5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 5);
                int cantidadSeleccionada = (int) cantidadComboBox5.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;
                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;
                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });
        cantidadComboBox6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 6);
                int cantidadSeleccionada = (int) cantidadComboBox6.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;
                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;
                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });
        cantidadComboBox7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = obtenerPrecioProducto((String) CategoriaComboBox.getSelectedItem(), 7);
                int cantidadSeleccionada = (int) cantidadComboBox7.getSelectedItem();
                double subtotal = precioProducto * cantidadSeleccionada;
                double totalActual = carrito.mostrarTotal();
                double nuevoTotal = totalActual + subtotal;
                txtTotal.setText(String.format("%.2f Bs", nuevoTotal));
            }
        });

        //FIN PRUEBA
        //Funcion categoriacombobox para imagenes
        CategoriaComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoria = (String) CategoriaComboBox.getSelectedItem();
                int inicioId;
                int finId;

                switch (categoria) {
                    case "Carnes":
                        inicioId = 1;
                        finId = 7;
                        manejarVisibilidadCantidad(7);
                        break;
                    case "Bebidas":
                        inicioId = 8;
                        finId = 14;
                        manejarVisibilidadCantidad(7);
                        break;
                    case "Lacteos":
                        inicioId = 15;
                        finId = 21;
                        manejarVisibilidadCantidad(7);
                        break;
                    case "Golosinas":
                        inicioId = 22;
                        finId = 28;
                        manejarVisibilidadCantidad(7);
                        break;
                    // Agregar más casos según sea necesario para otras categorías
                    default:
                        inicioId = 0;
                        finId = 0;
                        break;
                }

                actualizarImagenes(inicioId, finId);
                actualizarNombre(n1, 1, CategoriaComboBox);
                actualizarNombre(n2, 2, CategoriaComboBox);
                actualizarNombre(n3, 3, CategoriaComboBox);
                actualizarNombre(n4, 4, CategoriaComboBox);
                actualizarNombre(n5, 5, CategoriaComboBox);
                actualizarNombre(n6, 6, CategoriaComboBox);
                actualizarNombre(n7, 7, CategoriaComboBox);
                actualizarDescripcion(c1, 1, CategoriaComboBox);
                actualizarDescripcion(c2, 2, CategoriaComboBox);
                actualizarDescripcion(c3, 3, CategoriaComboBox);
                actualizarDescripcion(c4, 4, CategoriaComboBox);
                actualizarDescripcion(c5, 5, CategoriaComboBox);
                actualizarDescripcion(c6, 6, CategoriaComboBox);
                actualizarDescripcion(c7, 7, CategoriaComboBox);
                actualizarPrecio(p1, 1, CategoriaComboBox);
                actualizarPrecio(p2, 2, CategoriaComboBox);
                actualizarPrecio(p3, 3, CategoriaComboBox);
                actualizarPrecio(p4, 4, CategoriaComboBox);
                actualizarPrecio(p5, 5, CategoriaComboBox);
                actualizarPrecio(p6, 6, CategoriaComboBox);
                actualizarPrecio(p7, 7, CategoriaComboBox);
                reiniciarCantidades();
            }

            private JLabel[] labels = {i1, i2, i3, i4, i5, i6, i7};

            private void actualizarNombre(JLabel n, int num,JComboBox categoria) {
                String categoriaa = (String) CategoriaComboBox.getSelectedItem();
                String nombreProducto= obtenerNombreProducto(categoriaa, num);
                n.setText(nombreProducto);
            }

            private void actualizarDescripcion(JLabel n, int num,JComboBox categoria) {
                String categoriaa = (String) CategoriaComboBox.getSelectedItem();
                String descripcionProducto= obtenerDescripcionProducto(categoriaa, num);
                n.setText(descripcionProducto);
            }
            private void actualizarPrecio(JLabel n, int num,JComboBox categoria) {
                String categoriaa = (String) CategoriaComboBox.getSelectedItem();
                Double precioProducto= obtenerPrecioProducto(categoriaa, num);
                n.setText(Double.toString(precioProducto)+" Bs.");
            }


            private void actualizarImagenes(int inicioId, int finId) {
                int lugarLabel = 0;  // Comienzas desde la posición 0 del array
                for (int j = inicioId; j <= finId; j++) {
                    JLabel label = labels[lugarLabel];
                    String imagePath = obtenerRutaImagen(String.valueOf(j));
                    rsscalelabel.RSScaleLabel.setScaleLabel(label, imagePath);
                    lugarLabel = (lugarLabel + 1) % labels.length;  // Rotas al siguiente label
                }
            }

        });

        //CheckBox Activar SubCategorias
        JCheckBox chckbxSubCategoria = new JCheckBox("Sub categoría");
        chckbxSubCategoria.setBounds(182, 32, 111, 23);
        contentPane.add(chckbxSubCategoria);
        mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
        chckbxSubCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chckbxSubCategoria.isSelected()) {
                    comboBoxSubCategoria.setVisible(false);
                    comboBoxSubCategoria.setEnabled(false);
                    mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
                }else{
                    comboBoxSubCategoria.setVisible(true);
                    comboBoxSubCategoria.setEnabled(true);
                    comboBoxSubCategoria.removeAllItems();
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        if (CategoriaComboBox.getSelectedItem().equals(entry.getValue())) {
                            Integer idCategoria = entry.getKey();
                            List<String> subcategorías = MapHelper.obtenerSubs(GrafoHelper.obtenerSub(idCategoria));
                            for (String c : subcategorías){
                                comboBoxSubCategoria.addItem(c);
                            }
                        }
                    }
                    mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
                }
            }
        });

        //Busqueda
        RoundButton btnNewButton = new RoundButton("Buscar");
        btnNewButton.setBounds(885, 30, 89, 25);
        contentPane.add(btnNewButton);
        JTextField textFieldBusqueda = new JTextField();
        textFieldBusqueda.setBounds(758, 30, 117, 23);
        contentPane.add(textFieldBusqueda);
        textFieldBusqueda.setColumns(10);
        // Agrega estas líneas al constructor de VentanaCatalogo
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = textFieldBusqueda.getText().trim();
                if (!nombreBusqueda.isEmpty()) {
                    buscarPorNombre(registro ,nombreBusqueda);
                } else {
                    // Si el campo de búsqueda está vacío, muestra todos los productos nuevamente
                    mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
                    manejarVisibilidadCantidad(1);
                }
            }
        });

        //Carrito
        RoundButton btnButtonAgregarALcarrito = new RoundButton("Agregar al Carrito");
        btnButtonAgregarALcarrito.setBounds(804, 460, 157, 23);
        contentPane.add(btnButtonAgregarALcarrito);

        //Historial
        RoundButton btnButtonVerHistorial = new RoundButton("Ver Historial");
        btnButtonVerHistorial.setBounds(804, 490, 157, 23);
        contentPane.add(btnButtonVerHistorial);

        //Finalización
        RoundButton btnButtonFinalizarCompras = new RoundButton("Finalizar Compra");
        btnButtonFinalizarCompras.setBounds(804, 520, 157, 23);
        contentPane.add(btnButtonFinalizarCompras);

        txtTotal = new JTextField();
        txtTotal.setBounds(865, 619, 96, 19);
        contentPane.add(txtTotal);
        txtTotal.setColumns(10);
        txtTotal.setText(Double.toString(carrito.mostrarTotal()));

        RoundButton btnVerCarrito = new RoundButton("Ver carrito");
        btnVerCarrito.setBounds(804, 430, 157, 23);
        contentPane.add(btnVerCarrito);

        //ActionListener ver Historial
        btnButtonVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaHistorial historial = new VentanaHistorial(historialCompras, usuario);
                historial.setVisible(true);
            }
        });

        //ActionListener Ver Carrito
        btnVerCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCarrito ventanaCarrito = new VentanaCarrito(carrito);
                ventanaCarrito.setVisible(true);
                reiniciarCantidades();
                //txtTotal.setText("0.00BS");
            }
        });


        //ActionListener ComboBoxes
        CategoriaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxSubCategoria.removeAllItems();
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    if (CategoriaComboBox.getSelectedItem().equals(entry.getValue())) {
                        Integer idCategoria = entry.getKey();
                        List<String> subcategorías = MapHelper.obtenerSubs(GrafoHelper.obtenerSub(idCategoria));
                        for (String c : subcategorías){
                            comboBoxSubCategoria.addItem(c);
                        }
                    }
                }
                mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
            }
        });

        comboBoxSubCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductos(registro, comboBoxSubCategoria, comboBoxSubCategoria, chckbxSubCategoria);
                reiniciarCantidades();
            }
        });

        btnButtonAgregarALcarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < cantidadComboBoxes.size(); i++) {
                    if (((Number) cantidadComboBoxes.get(i).getSelectedItem()).doubleValue() != 0.0 && cantidadComboBoxes.get(i).isVisible()) {
                        String pro = no.get(i).getText();
                        String pre = pr.get(i).getText();

                        // Elimina el símbolo "Bs" y los espacios en blanco
                        pre = pre.split(" ")[0];

                        try {
                            double prec = Double.parseDouble(pre);
                            String can = Integer.toString((int) cantidadComboBoxes.get(i).getSelectedItem());
                            Double preci = prec * ((int) cantidadComboBoxes.get(i).getSelectedItem());
                            String precio = Double.toString(preci);
                            carrito.agregarAlCarrito(pro, can, precio);

                            // Imprime el contenido del carrito utilizando la función mostrarCarrito()
                            List<String> productosEnCarrito = carrito.mostrarCarrito();
                            for (String producto : productosEnCarrito) {
                                System.out.println("Producto en el carrito: " + producto);
                            }
                        } catch (NumberFormatException e1) {
                            // Manejar la excepción, ya que la cadena no es un número válido
                            e1.printStackTrace(); // o mostrar un mensaje de error, etc.
                        }
                    }
                }
                // Actualizar el total
                double total = carrito.mostrarTotal();
                //txtTotal.setText(String.format("%.2f Bs", total));
                reiniciarCantidades();
            }
        });

        //ActionListener Finalizar
        btnButtonFinalizarCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String c :carrito.mostrarCarrito()){
                    historialCompras.agregarCompra(usuario, c);
                    carrito.vaciarCarrito();
                    na.procesarNotificaciones();
                    txtTotal.setText("0.0");
                }
            }
        });

    }

    private void mostrarProductos(RegistroProductos registro, JComboBox comboBoxSubCategoria,
                                  JComboBox comboBoxCategoria, JCheckBox chckbx) {
        Map<Integer, String> map = MapHelper.obtenerCategoriasMap();
        Set<Integer> productosMostradosIds = new HashSet<>();

        // Obtener subcategoría seleccionada si está activada la opción
        if (chckbx.isSelected() && comboBoxSubCategoria.getSelectedItem() != null) {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (comboBoxSubCategoria.getSelectedItem().equals(entry.getValue())) {
                    Integer idSubCategoria = entry.getKey();
                    List<Producto> pro = registro.obtenerProductosPorCategoria(idSubCategoria);
                    productosMostradosIds.addAll(pro.stream().map(Producto::getId).collect(Collectors.toList()));
                }
            }
        } else if (comboBoxCategoria.getSelectedItem() != null) {
            // Obtener categoría seleccionada
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (comboBoxCategoria.getSelectedItem().equals(entry.getValue())) {
                    Integer idCategoria = entry.getKey();
                    List<Integer> subcategorias = GrafoHelper.obtenerSub(idCategoria);
                    for (Integer idSubCategoria : subcategorias) {
                        List<Producto> pro = registro.obtenerProductosPorCategoria(idSubCategoria);
                        productosMostradosIds.addAll(pro.stream().map(Producto::getId).collect(Collectors.toList()));
                    }
                }
            }
        }

        // Obtener los productos completos según los IDs recopilados
        List<Producto> productosMostrados = new ArrayList<>();
        for (Integer p : productosMostradosIds){
            productosMostrados.add(registro.obtenerProductoPorId(p));
        }

        // Actualizar los valores de los JLabels con los productos obtenidos
        for (int i = 0; i < im.size(); i++) {
            if (i < productosMostrados.size()) {
                Producto producto = productosMostrados.get(i);
                //"D:\\SEM-4\\ESTRUCTURA DE DATOS\\PRODUCTOS PROYECTO\\INTERFAZ\\CatalogoTiendaEnLinea\\src\\CatalogoFotos"
                String path = "src/CatalogoFotos/" + producto.getId() + ".png";
                rsscalelabel.RSScaleLabel.setScaleLabel(im.get(i), path);
                no.get(i).setText(producto.getNombre());
                ca.get(i).setText((producto.getDescripcion()));
                pr.get(i).setText(producto.getPrecio() + " Bs");
            } else {
                // If there are fewer products than the number of JLabels, clear the remaining labels
                im.get(i).setIcon(null);
                no.get(i).setText("");
                ca.get(i).setText("");
                pr.get(i).setText("");
            }
        }
        manejarVisibilidadCantidad(productosMostrados.size());
    }

    // Agrega la función manejarVisibilidadCantidad con el siguiente contenido
    private void manejarVisibilidadCantidad(int cantidadProductos) {
        // Itera sobre tu lista de JComboBox y maneja la visibilidad
        for (int i = 0; i < cantidadComboBoxes.size(); i++) {
            if (i < cantidadProductos) {
                cantidadComboBoxes.get(i).setVisible(true);
            } else {
                cantidadComboBoxes.get(i).setVisible(false);
            }
        }
    }


    private String obtenerRutaImagen(String nombre) {
        // Puedes ajustar esto según tus necesidades
        return "src/CatalogoFotos/" + nombre + ".png";
    }

    private double obtenerPrecioProducto(String categoria, int numeroProducto) {
        int idProducto = -1;  // Inicializar con un valor que no se espera
        RegistroProductos registroProductos = new RegistroProductos();
        switch (categoria) {
            case "Carnes":
                idProducto = (numeroProducto == 1) ? 1 :
                        (numeroProducto == 2) ? 2 :
                                (numeroProducto == 3) ? 3 :
                                        (numeroProducto == 4) ? 4 :
                                                (numeroProducto == 5) ? 5 :
                                                        (numeroProducto == 6) ? 6 :
                                                                (numeroProducto == 7) ? 7 : -1;

                break;
            case "Bebidas":
                idProducto = (numeroProducto == 1) ? 8 :
                        (numeroProducto == 2) ? 9 :
                                (numeroProducto == 3) ? 10 :
                                        (numeroProducto == 4) ? 11 :
                                                (numeroProducto == 5) ? 12 :
                                                        (numeroProducto == 6) ? 13 :
                                                                (numeroProducto == 7) ? 14 : -1;
                break;
            case "Lacteos":
                idProducto = (numeroProducto == 1) ? 15 :
                        (numeroProducto == 2) ? 16 :
                                (numeroProducto == 3) ? 17 :
                                        (numeroProducto == 4) ? 18 :
                                                (numeroProducto == 5) ? 19 :
                                                        (numeroProducto == 6) ? 20 :
                                                                (numeroProducto == 7) ? 21 : -1;
                break;
            case "Golosinas":
                idProducto = (numeroProducto == 1) ? 22 :
                        (numeroProducto == 2) ? 23 :
                                (numeroProducto == 3) ? 24 :
                                        (numeroProducto == 4) ? 25 :
                                                (numeroProducto == 5) ? 26 :
                                                        (numeroProducto == 6) ? 27 :
                                                                (numeroProducto == 7) ? 28 : -1;
                break;
            // Añadir más casos según sea necesario para otras categorías
        }

        // Obtener el precio del producto según el id
        if (idProducto != -1) {
            Producto producto = registroProductos.obtenerProductoPorId(idProducto);
            if (producto != null) {
                return producto.getPrecio();
            }
        }

        // Devolver un valor predeterminado o manejar el caso de error
        return 0.0;
    }
    //no sirve aun esta funcion
    private void calcularComboBox(JComboBox<Integer> cantidadComboBox, int idCat) {
        double precioTot = 0;
        String selectedCategoria = (String) CategoriaComboBox.getSelectedItem();

        // Obtén el índice seleccionado correctamente
        int cantidadSeleccionada = (int) cantidadComboBox.getSelectedItem();

        double precioProducto = obtenerPrecioProducto(selectedCategoria, idCat);

        if (cantidadSeleccionada > 0) {
            precioTot = precioProducto * cantidadSeleccionada;
        } else {
            precioTot = 0;
        }

        preciosTotales.set(idCat - 1, precioTot);
        System.out.println(precioTot);
    }



    private void calcularTotal(List<Double> preciosTotales) {
        double total=0;
        for (int i = 0; i < 7; i++) {
            total=total+preciosTotales.get(i);
        }
        total=Math.round(total*100.0)/100.0;

        if(total>0) {
            txtTotal.setText(String.format("%.2f", total)+" Bs");
        }

    }
    private double calcularTotalBoton(List<Double> preciosTotales) {
        double total=0;
        for (int i = 0; i < 7; i++) {
            total=total+preciosTotales.get(i);
        }
        total=Math.round(total*100.0)/100.0;
        return total;
    }

    private void mostrarProductosEnLista(List<Producto> productos) {
        for (int i = 0; i < im.size(); i++) {
            if (i < productos.size()) {
                Producto producto = productos.get(i);
                String imagePath = obtenerRutaImagen(String.valueOf(producto.getId()));
                rsscalelabel.RSScaleLabel.setScaleLabel(im.get(i), imagePath);
                no.get(i).setText(producto.getNombre());
                ca.get(i).setText(Integer.toString(producto.getDisponibilidad()));
                pr.get(i).setText(producto.getPrecio() + " Bs");
            } else {
                im.get(i).setIcon(null);
                no.get(i).setText("");
                ca.get(i).setText("");
                pr.get(i).setText("");
            }
        }
    }

    private void buscarPorNombre(RegistroProductos registro    ,String nombre) {
        List<Producto> productosEncontrados = registro.buscarPorNombre(nombre);
        mostrarProductosEnLista(productosEncontrados);
    }

    private String obtenerNombreProducto(String categoria, int numeroProducto) {
        int idProducto = -1;  // Inicializar con un valor que no se espera
        RegistroProductos registroProductos = new RegistroProductos();
        switch (categoria) {
            case "Carnes":
                idProducto = (numeroProducto == 1) ? 1 :
                        (numeroProducto == 2) ? 2 :
                                (numeroProducto == 3) ? 3 :
                                        (numeroProducto == 4) ? 4 :
                                                (numeroProducto == 5) ? 5 :
                                                        (numeroProducto == 6) ? 6 :
                                                                (numeroProducto == 7) ? 7 : -1;

                break;
            case "Bebidas":
                idProducto = (numeroProducto == 1) ? 8 :
                        (numeroProducto == 2) ? 9 :
                                (numeroProducto == 3) ? 10 :
                                        (numeroProducto == 4) ? 11 :
                                                (numeroProducto == 5) ? 12 :
                                                        (numeroProducto == 6) ? 13 :
                                                                (numeroProducto == 7) ? 14 : -1;
                break;
            case "Lacteos":
                idProducto = (numeroProducto == 1) ? 15 :
                        (numeroProducto == 2) ? 16 :
                                (numeroProducto == 3) ? 17 :
                                        (numeroProducto == 4) ? 18 :
                                                (numeroProducto == 5) ? 19 :
                                                        (numeroProducto == 6) ? 20 :
                                                                (numeroProducto == 7) ? 21 : -1;
                break;
            case "Golosinas":
                idProducto = (numeroProducto == 1) ? 22 :
                        (numeroProducto == 2) ? 23 :
                                (numeroProducto == 3) ? 24 :
                                        (numeroProducto == 4) ? 25 :
                                                (numeroProducto == 5) ? 26 :
                                                        (numeroProducto == 6) ? 27 :
                                                                (numeroProducto == 7) ? 28 : -1;
                break;
            // Añadir más casos según sea necesario para otras categorías
        }

        // Obtener el nombre del producto según el id
        if (idProducto != -1) {
            Producto producto = registroProductos.obtenerProductoPorId(idProducto);
            if (producto != null) {
                return producto.getNombre();
            }
        }
        // Devolver un valor predeterminado o manejar el caso de error
        return "Producto Desconocido";
    }

    private void reiniciarCantidades() {
        for (JComboBox<Integer> comboBox : cantidadComboBoxes) {
            comboBox.setSelectedItem(0);
        }
    }

    //para descripcion
    private String obtenerDescripcionProducto(String categoria, int numeroProducto) {
        int idProducto = -1;  // Inicializar con un valor que no se espera
        RegistroProductos registroProductos = new RegistroProductos();
        switch (categoria) {
            case "Carnes":
                idProducto = (numeroProducto == 1) ? 1 :
                        (numeroProducto == 2) ? 2 :
                                (numeroProducto == 3) ? 3 :
                                        (numeroProducto == 4) ? 4 :
                                                (numeroProducto == 5) ? 5 :
                                                        (numeroProducto == 6) ? 6 :
                                                                (numeroProducto == 7) ? 7 : -1;
                break;
            case "Bebidas":
                idProducto = (numeroProducto == 1) ? 8 :
                        (numeroProducto == 2) ? 9 :
                                (numeroProducto == 3) ? 10 :
                                        (numeroProducto == 4) ? 11 :
                                                (numeroProducto == 5) ? 12 :
                                                        (numeroProducto == 6) ? 13 :
                                                                (numeroProducto == 7) ? 14 : -1;
                break;
            case "Lacteos":
                idProducto = (numeroProducto == 1) ? 15 :
                        (numeroProducto == 2) ? 16 :
                                (numeroProducto == 3) ? 17 :
                                        (numeroProducto == 4) ? 18 :
                                                (numeroProducto == 5) ? 19 :
                                                        (numeroProducto == 6) ? 20 :
                                                                (numeroProducto == 7) ? 21 : -1;
                break;
            case "Golosinas":
                idProducto = (numeroProducto == 1) ? 22 :
                        (numeroProducto == 2) ? 23 :
                                (numeroProducto == 3) ? 24 :
                                        (numeroProducto == 4) ? 25 :
                                                (numeroProducto == 5) ? 26 :
                                                        (numeroProducto == 6) ? 27 :
                                                                (numeroProducto == 7) ? 28 : -1;
                break;
            // Añadir más casos según sea necesario para otras categorías
        }

        // Obtener el nombre del producto según el id
        if (idProducto != -1) {
            Producto producto = registroProductos.obtenerProductoPorId(idProducto);
            if (producto != null) {
                return producto.getDescripcion();
            }
        }
        // Devolver un valor predeterminado o manejar el caso de error
        return "Producto Desconocido";
    }
}