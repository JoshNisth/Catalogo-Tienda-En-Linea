import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textCarnet;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textUsuario;
	private JTextField textContrasena;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 666, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		textDireccion.setBounds(475, 176, 140, 18);
		panel.add(textDireccion);

		textTelefono = new JTextField(10);
		textTelefono.setFont(new Font("Roboto", Font.PLAIN, 15));
		textTelefono.setBorder(null);
		textTelefono.setBounds(475, 256, 140, 18);
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
		separatorUbicacion.setBounds(475, 284, 140, 2);
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
		lblTelefono.setBounds(348, 231, 102, 64);
		panel.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblDireccion.setBounds(348, 156, 114, 72);
		panel.add(lblDireccion);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setFont(new Font("Roboto Light", Font.BOLD, 15));
		btnRegistrar.setBackground(new Color(11, 50, 79));
		btnRegistrar.setBounds(479, 325, 136, 56);
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
		lblContrasea.setBounds(348, 87, 136, 56);
		panel.add(lblContrasea);

		textUsuario = new JTextField(10);
		textUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		textUsuario.setBorder(null);
		textUsuario.setBounds(130, 112, 140, 18);
		panel.add(textUsuario);

		textContrasena = new JTextField(10);
		textContrasena.setFont(new Font("Roboto", Font.PLAIN, 15));
		textContrasena.setBorder(null);
		textContrasena.setBounds(475, 108, 140, 18);
		panel.add(textContrasena);

		JSeparator separatorNombre_1 = new JSeparator();
		separatorNombre_1.setForeground(Color.BLACK);
		separatorNombre_1.setBackground(Color.LIGHT_GRAY);
		separatorNombre_1.setBounds(475, 136, 140, 2);
		panel.add(separatorNombre_1);

		JSeparator separatorNombre_2 = new JSeparator();
		separatorNombre_2.setForeground(Color.BLACK);
		separatorNombre_2.setBackground(Color.LIGHT_GRAY);
		separatorNombre_2.setBounds(475, 205, 140, 2);
		panel.add(separatorNombre_2);

		JLabel lblRegistrarDatos = new JLabel("REGISTRAR DATOS");
		lblRegistrarDatos.setFont(new Font("Roboto", Font.BOLD, 25));
		lblRegistrarDatos.setBounds(202, 26, 219, 43);
		panel.add(lblRegistrarDatos);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/CatalogoFotos/carroPrueba.png")));
		lblNewLabel.setBounds(358, 306, 92, 77);
		panel.add(lblNewLabel);
	}
}