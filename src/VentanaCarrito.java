import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaCarrito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableCarrito;
    private int xOffset, yOffset;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        CarritoDeCompras carrito = new CarritoDeCompras();

        // Agregar productos al carrito
        Producto producto1 = new Producto(1, "Producto1", "Descripción del Producto1", 29.99, 2, 10);
        Producto producto2 = new Producto(2, "Producto2", "Descripción del Producto2", 19.99, 14, 5);

        carrito.agregarAlCarrito("Leche", "2", "14");
        carrito.agregarAlCarrito("Carne", "1", "20");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaCarrito frame = new VentanaCarrito(carrito);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the frame.
     */
    public VentanaCarrito(CarritoDeCompras carrito) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 605, 423);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        setUndecorated(true);
        setLocationByPlatform(true);
        setBounds(100, 100, 575, 423); // Adjust as needed

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel titleBarPanel = new JPanel();
        titleBarPanel.setBackground(new Color(18, 55, 107));
        titleBarPanel.setBounds(0, 0, 595, 20);
        titleBarPanel.setLayout(null);

        titleBarPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });
        titleBarPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - xOffset, e.getYOnScreen() - yOffset);
            }
        });

        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setBackground(new Color(22, 45, 78));
        closeButtonPanel.setBounds(555, 0, 20, 20);
        closeButtonPanel.setLayout(null);

        JLabel closeLabel = new JLabel("X");
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setForeground(Color.WHITE);
        closeLabel.setBounds(0, 0, 20, 20);
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeButtonPanel.setBackground(new Color(191, 64, 53));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButtonPanel.setBackground(new Color(22, 45, 78));
            }
        });

        closeButtonPanel.add(closeLabel);
        titleBarPanel.add(closeButtonPanel);
        contentPane.add(titleBarPanel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 595, 388);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 124, 593, 182);
        panel.add(scrollPane);

        tableCarrito = new JTable();
        tableCarrito.setRowHeight(30);
        tableCarrito.setFont(new Font("Roboto", Font.PLAIN, 15));
        tableCarrito.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Producto", "Cantidad", "Subtotal"
                }
        ));
        tableCarrito.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableCarrito.getColumnModel().getColumn(1).setPreferredWidth(15);
        tableCarrito.getColumnModel().getColumn(2).setPreferredWidth(15);
        scrollPane.setViewportView(tableCarrito);

        for (String c : carrito.mostrarCarrito()){
            DefaultTableModel modeloFactura = (DefaultTableModel) tableCarrito.getModel();
            Vector<Object> rowData = new Vector<>();
            String[] pro = c.split(",");
            rowData.add(pro[0]);
            rowData.add(pro[1]);
            rowData.add(pro[2]);
            modeloFactura.addRow(rowData);
        }

        RoundButton btnVaciarCarrito = new RoundButton("Vaciar Carrito");
        btnVaciarCarrito.setForeground(Color.WHITE);
        btnVaciarCarrito.setFont(new Font("Roboto Light", Font.BOLD, 15));
        btnVaciarCarrito.setBounds(44, 335, 160, 38);
        panel.add(btnVaciarCarrito);

        JLabel lblTotal = new JLabel("TOTAL:");
        lblTotal.setFont(new Font("Roboto", Font.BOLD, 15));
        lblTotal.setBounds(440, 341, 67, 27);
        panel.add(lblTotal);

        JLabel lblDatoTotal = new JLabel("");
        lblDatoTotal.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblDatoTotal.setBounds(508, 343, 52, 22);
        panel.add(lblDatoTotal);
        lblDatoTotal.setText(Double.toString(carrito.mostrarTotal()));

        JLabel lblNewLabel = new JLabel("CARRITO DE COMPRAS");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 30));
        lblNewLabel.setBounds(125, 39, 350, 38);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(0, 0, 593, 118);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblNewLabel_1, "src/CatalogoFotos/imagenCarrito.png");
        panel.add(lblNewLabel_1);

        btnVaciarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrito.vaciarCarrito();
                tableCarrito.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "Producto", "Cantidad", "Subtotal"
                        }
                ));
                tableCarrito.getColumnModel().getColumn(0).setPreferredWidth(100);
                tableCarrito.getColumnModel().getColumn(1).setPreferredWidth(15);
                tableCarrito.getColumnModel().getColumn(2).setPreferredWidth(15);

                lblDatoTotal.setText("0.0");
            }
        });
    }
}