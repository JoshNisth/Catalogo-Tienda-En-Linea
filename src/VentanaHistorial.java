import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class VentanaHistorial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHistorial;
	private int xOffset, yOffset;

	public static void main(String[] args) {
		HistorialCompras historialCompras = new HistorialCompras();
		historialCompras.agregarCompra("Luwu", "ProductoA,5,6");
		historialCompras.agregarCompra("Luwu", "ProductoB,8,6");
		historialCompras.agregarCompra("usuario2", "ProductoC");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHistorial frame = new VentanaHistorial(historialCompras, "Luwu");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaHistorial(HistorialCompras historialCompras, String usuario) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setUndecorated(true);
		setLocationByPlatform(true);
		setBounds(100, 100, 591, 423);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel titleBarPanel = new JPanel();
		titleBarPanel.setBackground(new Color(18, 55, 107));
		titleBarPanel.setBounds(0, 0, 591, 20);
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
		closeButtonPanel.setBounds(571, 0, 20, 20); // Adjust the position on the right side of the title bar
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
		panel.setBounds(0, 0, 591, 386);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHistorial = new JLabel("HISTORIAL");
		lblHistorial.setForeground(Color.WHITE);
		lblHistorial.setFont(new Font("Roboto", Font.BOLD, 30));
		lblHistorial.setBackground(Color.WHITE);
		lblHistorial.setBounds(209, 38, 165, 38);
		panel.add(lblHistorial);

		JLabel lblImagenHistorial = new JLabel("");
		lblImagenHistorial.setIcon(new ImageIcon(VentanaHistorial.class.getResource("/CatalogoFotos/ImagenHistorial1.png")));
		lblImagenHistorial.setBounds(0, 0, 593, 118);
		panel.add(lblImagenHistorial);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 121, 593, 265);
		panel.add(scrollPane);

		tableHistorial = new JTable();
		tableHistorial.setFont(new Font("Roboto", Font.PLAIN, 15));
		tableHistorial.setRowHeight(30);
		tableHistorial.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Producto", "Cantidad", "Subtotal", "Fecha" }
		));
		scrollPane.setViewportView(tableHistorial);

		List<String> hist = HistorialCompras.obtenerHistorialCompra(usuario);

		DefaultTableModel modeloHist = (DefaultTableModel) tableHistorial.getModel();
		if (!hist.isEmpty()) {
			for (String c : hist) {
				Vector<Object> rowData = new Vector<>();
				String[] hi = c.split(",");
				rowData.add(hi[0]); // Producto
				rowData.add(hi[1]); // Cantidad (ajusta según tu lógica)
				rowData.add(hi[2]); // Subtotal (ajusta según tu lógica)
				rowData.add(hi[3]); // Fecha
				modeloHist.addRow(rowData);
			}
		} else {
			JOptionPane.showMessageDialog(this, "El usuario no tiene historial de compras.", "Sin Historial", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
