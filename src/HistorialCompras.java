import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HistorialCompras {
    private static HashMap<String, List<String>> historial = new HashMap<>();

    public static void agregarCompra(String usuario, String producto) {
        // Verificar si el usuario ya tiene un historial de compras
        if (historial.containsKey(usuario)) {
            historial.get(usuario).add(producto);
        } else {
            // Si no existe un historial, crear uno nuevo
            List<String> compras = new ArrayList<>();
            compras.add(producto);
            historial.put(usuario, compras);
        }
    }

    public static List<String> obtenerHistorialCompra(String usuario) {
        return historial.getOrDefault(usuario, new ArrayList<>());
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        agregarCompra("usuario1", "ProductoA");
        agregarCompra("usuario1", "ProductoB");
        agregarCompra("usuario2", "ProductoC");

        List<String> historialUsuario1 = obtenerHistorialCompra("usuario1");
        List<String> historialUsuario2 = obtenerHistorialCompra("usuario2");

        System.out.println("Historial de compras para usuario1: " + historialUsuario1);
        System.out.println("Historial de compras para usuario2: " + historialUsuario2);
    }
}
