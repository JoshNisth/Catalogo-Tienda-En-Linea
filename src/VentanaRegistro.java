import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textCarnet;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textUsuario;
	private JTextField textContrasena;
	private int xOffset, yOffset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(450, 150, 666, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setUndecorated(true);
		setLocationByPlatform(true);
		setBounds(100, 100, 620, 397);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel titleBarPanel = new JPanel();
		titleBarPanel.setBackground(new Color(18, 55, 107));
		titleBarPanel.setBounds(0, 0, 620, 20);
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
		closeButtonPanel.setBounds(600, 0, 20, 20);
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
		panel.setBounds(0, 0, 650, 397);
		contentPane.add(panel);
		panel.setLayout(null);

		textNombre = new JTextField(10);
		textNombre.setBounds(130, 176, 140, 18);
		textNombre.setFont(new Font("Roboto", Font.PLAIN, 15));
		textNombre.setBorder(null);
		panel.add(textNombre);

		textCarnet = new JTextField(10);
		textCarnet.setFont(new Font("Roboto", Font.PLAIN, 15));
		textCarnet.setBorder(null);
		textCarnet.setBounds(130, 256, 140, 18);
		panel.add(textCarnet);

		textDireccion = new JTextField(10);
		textDireccion.setFont(new Font("Roboto", Font.PLAIN, 15));
		textDireccion.setBorder(null);
		textDireccion.setBounds(455, 176, 140, 18);
		panel.add(textDireccion);

		textTelefono = new JTextField(10);
		textTelefono.setFont(new Font("Roboto", Font.PLAIN, 15));
		textTelefono.setBorder(null);
		textTelefono.setBounds(455, 256, 140, 18);
		panel.add(textTelefono);

		JSeparator separatorNombre = new JSeparator();
		separatorNombre.setBackground(new Color(192, 192, 192));
		separatorNombre.setForeground(Color.BLACK);
		separatorNombre.setBounds(130, 141, 140, 2);
		panel.add(separatorNombre);

		JSeparator separatorCarnet = new JSeparator();
		separatorCarnet.setForeground(Color.BLACK);
		separatorCarnet.setBounds(130, 205, 140, 2);
		panel.add(separatorCarnet);

		JSeparator separatorTelefono = new JSeparator();
		separatorTelefono.setForeground(Color.BLACK);
		separatorTelefono.setBounds(130, 284, 140, 2);
		panel.add(separatorTelefono);

		JSeparator separatorUbicacion = new JSeparator();
		separatorUbicacion.setForeground(Color.BLACK);
		separatorUbicacion.setBounds(455, 284, 140, 2);
		panel.add(separatorUbicacion);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblNombre.setBounds(32, 164, 81, 56);
		panel.add(lblNombre);

		JLabel lblCiCliente = new JLabel("Carnet:");
		lblCiCliente.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblCiCliente.setBounds(42, 235, 81, 56);
		panel.add(lblCiCliente);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblTelefono.setBounds(328, 231, 102, 64);
		panel.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblDireccion.setBounds(328, 156, 114, 72);
		panel.add(lblDireccion);

		RoundButton btnRegistrar = new RoundButton("Registrar");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setFont(new Font("Roboto Light", Font.BOLD, 15));
		btnRegistrar.setBounds(459, 325, 136, 56);
		panel.add(btnRegistrar);

		//Aqui se agrega la info al HashMap
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textUsuario.getText();
				String contraseña = textContrasena.getText();

				// Agregar el registro al HashMap
				ManejoRegistros.agregarRegistro(usuario, contraseña);

				// Cerrar la ventana de registro
				dispose();
			}
		});


		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblUsuario.setBounds(32, 91, 81, 56);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblContrasea.setBounds(328, 87, 136, 56);
		panel.add(lblContrasea);

		textUsuario = new JTextField(10);
		textUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		textUsuario.setBorder(null);
		textUsuario.setBounds(130, 112, 140, 18);
		panel.add(textUsuario);

		textContrasena = new JTextField(10);
		textContrasena.setFont(new Font("Roboto", Font.PLAIN, 15));
		textContrasena.setBorder(null);
		textContrasena.setBounds(455, 108, 140, 18);
		panel.add(textContrasena);

		JSeparator separatorNombre_1 = new JSeparator();
		separatorNombre_1.setForeground(Color.BLACK);
		separatorNombre_1.setBackground(Color.LIGHT_GRAY);
		separatorNombre_1.setBounds(455, 136, 140, 2);
		panel.add(separatorNombre_1);

		JSeparator separatorNombre_2 = new JSeparator();
		separatorNombre_2.setForeground(Color.BLACK);
		separatorNombre_2.setBackground(Color.LIGHT_GRAY);
		separatorNombre_2.setBounds(455, 205, 140, 2);
		panel.add(separatorNombre_2);

		JLabel lblRegistrarDatos = new JLabel("REGISTRAR DATOS");
		lblRegistrarDatos.setFont(new Font("Roboto", Font.BOLD, 25));
		lblRegistrarDatos.setBounds(195, 26, 240, 43);
		panel.add(lblRegistrarDatos);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/CatalogoFotos/carroPrueba.png")));
		lblNewLabel.setBounds(348, 306, 92, 77);
		panel.add(lblNewLabel);
	}
}