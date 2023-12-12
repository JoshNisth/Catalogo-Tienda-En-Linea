import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HistorialCompras {
    private static HashMap<String, List<String>> historial = new HashMap<>();
    private static final String FILE_PATH = "D:\\SEM-4\\ESTRUCTURA DE DATOS\\PRODUCTOS PROYECTO\\CATALOGO\\FiniticadoCatalogoTiendaEnLinea\\FiniticadoCatalogoTiendaEnLinea\\historial.txt";
    private static final ManejoRegistros mr = new ManejoRegistros();

    public static void agregarCompra(String usuario, String producto) {
        String fecha = ObtenerFechaDeHoy();

        for (String u : mr.obtenerNombresDeUsuario()) {
            if (u.equals(usuario)) {
                if (historial.containsKey(usuario)) {
                    historial.get(usuario).add(producto + "," + fecha);
                } else {
                    List<String> compras = new ArrayList<>();
                    compras.add(producto + "," + fecha);
                    historial.put(usuario, compras);
                }
            }
        }

        guardarHistorialEnArchivo();
    }

    public static List<String> obtenerHistorialCompra(String usuario) {
        cargarHistorialDesdeArchivo();
        return historial.getOrDefault(usuario, new ArrayList<>());
    }

    public static void main(String[] args) {
        agregarCompra("Luwu", "ProductoA");
        agregarCompra("Luwu", "ProductoB");
        agregarCompra("usuario2", "ProductoC");

        List<String> historialUsuario1 = obtenerHistorialCompra("Luwu");
        List<String> historialUsuario2 = obtenerHistorialCompra("usuario2");

        System.out.println("Historial de compras para usuario1: " + historialUsuario1);
        System.out.println("Historial de compras para usuario2: " + historialUsuario2);
    }

    public static String ObtenerFechaDeHoy() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return fechaActual.format(formatoFecha);
    }

    private static void guardarHistorialEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (HashMap.Entry<String, List<String>> entry : historial.entrySet()) {
                String usuario = entry.getKey();
                for (String compra : entry.getValue()) {
                    writer.write(usuario + ":" + compra + ";");
                    writer.newLine();
                }
            }
            System.out.println("Historial guardado en archivo correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarHistorialDesdeArchivo() {
        historial.clear(); // Limpiar historial antes de cargar desde el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String usuario = parts[0];
                    String[] compras = parts[1].split(";");
                    for (String compra : compras) {
                        historial.computeIfAbsent(usuario, k -> new ArrayList<>()).add(compra);
                    }
                }
            }
            System.out.println("Historial cargado desde archivo correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}