import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaUsuario extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIngreseSuUsuario;
	private JPasswordField passwordField;
	private JTextField textIncorrecto;
	private int xOffset, yOffset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RegistroProductos registro = new RegistroProductos();
		// Carnes
		registro.agregarProducto(1,"Carne de Res Molida", "500 g", 17.0, 2, 10);
		registro.agregarProducto(2,"Pollo Entero", "1.7 kg", 39.0, 3, 6);
		registro.agregarProducto(3,"Hamburguesa de Res", "12 unidades", 31.9, 2, 21);
		registro.agregarProducto(4,"Chorizo de Res", "350 g", 16.5, 2, 9);
		registro.agregarProducto(5,"Nuggets de Pollo", "20 unidades", 28.0, 3, 10);
		registro.agregarProducto(6,"Chuleta de cerdo", "1 unidad", 16.5, 6, 15);
		registro.agregarProducto(7,"Pechuga de Pavo Ahumada", "2.1 kg", 254.44, 3, 7);


		// Bebidas
		registro.agregarProducto(8,"Gaseosa Coca-Cola", "3 Litros", 13.0, 21, 13);
		registro.agregarProducto(9,"Gaseosa Sprite", "2 Litros", 8.0, 21, 10);
		registro.agregarProducto(10,"Gaseosa Fanta", "2.5 Litros", 11.0, 21, 25);
		registro.agregarProducto(11,"Jugo Del Valle Manzana", "2 Litros", 13.5, 21, 74);
		registro.agregarProducto(12,"Agua Vital sin Gas", "2 Litros", 7.6, 21, 77);
		registro.agregarProducto(13,"Bebida Powerade Mora", "1.1 Litros", 11.0, 21, 25);
		registro.agregarProducto(14,"Ron Havana Club", "1 Litro", 80.0, 20, 20);

		// Lácteos
		registro.agregarProducto(15,"Mantequilla Pil Con Sal", "200 g", 13.8, 13, 5);
		registro.agregarProducto(16,"Leche Pil Deslactosada", "800 ml", 8.1, 8, 20);
		registro.agregarProducto(17,"Leche Pil Natural", "946 ml", 6.7, 8, 8);
		registro.agregarProducto(18,"Yogurt Bebible Frutilla", "1 litro", 11.0, 11, 6);
		registro.agregarProducto(19,"Queso Crema Bonle", "200 g", 17.3, 12, 20);
		registro.agregarProducto(20,"Leche de Soya Natural Pil", "946 ml", 5.8, 8, 15);
		registro.agregarProducto(21,"Yogurt Griego Natural", "170 ml", 5.89, 11, 8);

		// Golosinas
		registro.agregarProducto(22,"Bombones Surtidos Nestle", "153 g", 17.9, 15, 20);
		registro.agregarProducto(23,"Papas Lay’s", "105 g", 12.0, 18, 15);
		registro.agregarProducto(24,"Chocolate KitKat", "41.5 g", 8.7, 15, 20);
		registro.agregarProducto(25,"Chocolate Vizzio Estuche", "182.4 g", 32.5, 15, 28);
		registro.agregarProducto(26,"Mogul Dientes", "150 g", 6.0, 15, 20);
		registro.agregarProducto(27,"Tableta Blanca Breick", "100.4 g", 18.7, 15, 30);
		registro.agregarProducto(28,"Papitas Pringles", "149 g", 21.0, 18, 20);

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
					VentanaUsuario frame = new VentanaUsuario(registro, na);
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
	public VentanaUsuario(RegistroProductos registro, NotificacionesAlertas na) {
		setTitle("Inicio de Sesión");
		setFont(new Font("Roboto Light", Font.BOLD, 28));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 620, 486);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setUndecorated(true);
		setLocationByPlatform(true);
		setBounds(100, 100, 620, 448); // Adjust as needed

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
		panel.setBounds(0, 0, 624, 447);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/CatalogoFotos/logoCatalogo.png")));
		//lblBanner.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/imagenes/libreriaBanner2.png")));
		//rsscalelabel.RSScaleLabel.setScaleLabel(lblBanner, "D:\\SEM-4\\ESTRUCTURA DE DATOS\\PRODUCTOS PROYECTO\\PROYECTO\\CatalogoTiendaEnLinea\\src\\CatalogoFotos/logoCatalogo.png");
		lblBanner.setBounds(294, 0, 330, 447);
		panel.add(lblBanner);

		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/imagenes/loglib2.png")));
		lblNewLabel.setBounds(45, 30, 66, 51);
		panel.add(lblNewLabel);

		JLabel lblIniciarSesion = new JLabel("INICIAR SESIÓN");
		lblIniciarSesion.setFont(new Font("Roboto", Font.BOLD, 25));
		lblIniciarSesion.setBounds(45, 38, 196, 43);
		panel.add(lblIniciarSesion);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblUsuario.setBounds(45, 113, 129, 29);
		panel.add(lblUsuario);

		txtIngreseSuUsuario = new JTextField();
		txtIngreseSuUsuario.setBorder(null);
		txtIngreseSuUsuario.setToolTipText("");
		txtIngreseSuUsuario.setForeground(new Color(192, 192, 192));
		txtIngreseSuUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		txtIngreseSuUsuario.setBackground(new Color(255, 255, 255));
		txtIngreseSuUsuario.setText("Ingrese su usuario");
		txtIngreseSuUsuario.setBounds(45, 152, 205, 19);
		panel.add(txtIngreseSuUsuario);
		txtIngreseSuUsuario.setColumns(10);

		txtIngreseSuUsuario.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				txtIngreseSuUsuario.setText("");
			}

			public void mouseEntered(MouseEvent e) {
				txtIngreseSuUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				txtIngreseSuUsuario.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}
		});

		panel.add(txtIngreseSuUsuario);
		getContentPane().add(panel);

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblContraseña.setBounds(44, 224, 151, 19);
		panel.add(lblContraseña);

		passwordField = new JPasswordField();
		passwordField.setBounds(45, 263, 129, 19);
		passwordField.setBorder(null);
		passwordField.setText("contraseña");
		passwordField.setForeground(new Color(192, 192, 192));
		panel.add(passwordField);

		RoundButton btnIngresar = new RoundButton("Ingresar");
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setFont(new Font("Roboto Light", Font.BOLD, 15));
		btnIngresar.setBounds(88, 336, 114, 43);
		panel.add(btnIngresar);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtIngreseSuUsuario.getText();
				String contraseña = new String(passwordField.getPassword());

				if (ManejoRegistros.validarUsuarioContraseña(usuario, contraseña)) {
					// Usuario y contraseña válidos, abrir VentanaCatalogo
					VentanaCatalogo ventanaCatalogo = new VentanaCatalogo(registro, registro.obtenerTodosLosIDs(), na,usuario);
					ventanaCatalogo.setVisible(true);

					// Cerrar la ventana actual de inicio de sesión
					dispose();
				} else {
					// Usuario y contraseña incorrectos
					textIncorrecto.setText("Usuario o contraseña incorrectos");
				}
			}
		});




		/*
		//Esto es lo de la pizzeria
		final JButton finalBtnIngresar = btnIngresar;
		btnIngresar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String usuario = txtIngreseSuUsuario.getText();
		        String contraseña = new String(passwordField.getPassword());

		        // Verificar el usuario y la contraseña
		        if (usuario.equals("Uwu") && contraseña.equals("170209")) {
		            // Usuario y contraseña son correctos, abrir la ventana VentanaMenuPedidos
		            VentanaCatalogo ventanaCategorias = new VentanaCatalogo();
		            ventanaCategorias.setVisible(true);

		            // Cerrar la ventana actual de inicio de sesión
		            dispose();
		        } else {
		            // Usuario o contraseña son incorrectos, mostrar mensaje de error
		            System.out.println("Usuario o contraseña incorrectos");
		        }
		    }
		});*/

		JSeparator separatorUsuario = new JSeparator();
		separatorUsuario.setForeground(new Color(0, 0, 0));
		separatorUsuario.setBounds(44, 181, 205, 2);
		panel.add(separatorUsuario);

		JSeparator separatorContrasena = new JSeparator();
		separatorContrasena.setForeground(Color.BLACK);
		separatorContrasena.setBounds(44, 292, 205, 2);
		panel.add(separatorContrasena);

		JButton btnRegistrar = new JButton("¿No tiene cuenta? Regístrese");
		btnRegistrar.setForeground(new Color(11, 50, 79));
		btnRegistrar.setFont(new Font("Roboto Light", Font.BOLD, 13));
		btnRegistrar.setBackground(new Color(255, 255, 255));
		btnRegistrar.setBounds(38, 390, 215, 43);
		panel.add(btnRegistrar);
		// Establecer el borde del botón como null
		btnRegistrar.setBorder(null);

		JButton btnRegistrar_1 = new JButton("¿No tiene cuenta? Registrese");
		btnRegistrar_1.setForeground(new Color(11, 50, 79));
		btnRegistrar_1.setFont(new Font("Roboto Light", Font.BOLD, 15));
		btnRegistrar_1.setBorder(null);
		btnRegistrar_1.setBackground(Color.WHITE);
		btnRegistrar_1.setBounds(45, 390, 209, 43);
		panel.add(btnRegistrar_1);

		textIncorrecto = new JTextField();
		textIncorrecto.setForeground(new Color(255, 0, 0));
		textIncorrecto.setFont(new Font("Roboto Light", Font.BOLD, 13));
		textIncorrecto.setBounds(45, 305, 214, 20);
		textIncorrecto.setBorder(null);
		panel.add(textIncorrecto);
		textIncorrecto.setColumns(10);

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventanaRegistro = new VentanaRegistro();
				ventanaRegistro.setVisible(true);
			}
		});




		passwordField.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				passwordField.setText("");
			}

			public void mouseEntered(MouseEvent e) {
				passwordField.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				passwordField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}
		});
	}
}