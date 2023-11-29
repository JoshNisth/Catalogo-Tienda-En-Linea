import java.io.*;
import java.util.HashMap;

public class ManejoRegistros {
    //ubicacion a preferencia del usuario
	private static final String FILE_PATH = "C:\\Users\\JOSH13\\Downloads\\FiniticadoCatalogoTiendaEnLinea\\usuarios.txt";
    private static HashMap<String, String> registros = new HashMap<>();

    static {
        // Load existing user registrations from file when the class is loaded
        cargarRegistrosDesdeArchivo();
    }

    public static void agregarRegistro(String usuario, String contraseña) {
        registros.put(usuario, contraseña);
        guardarRegistrosEnArchivo();
    }

    public static boolean validarUsuarioContraseña(String usuario, String contraseña) {
        return registros.containsKey(usuario) && registros.get(usuario).equals(contraseña);
    }

    private static void cargarRegistrosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    registros.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarRegistrosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (HashMap.Entry<String, String> entry : registros.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}