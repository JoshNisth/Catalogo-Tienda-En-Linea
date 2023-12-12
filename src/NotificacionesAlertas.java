import javax.swing.JOptionPane;
import java.util.PriorityQueue;

public class NotificacionesAlertas {
    private static PriorityQueue<Notificacion> colaNotificaciones;

    static {
        colaNotificaciones = new PriorityQueue<>();
    }

    public static void agregarNotificacion(Notificacion notificacion) {
        colaNotificaciones.offer(notificacion);
    }

    public static void procesarNotificaciones() {
        while (!colaNotificaciones.isEmpty()) {
            Notificacion notificacion = colaNotificaciones.poll();
            mostrarPopup(notificacion.getMensaje());
        }
    }

    public static void mostrarPopup(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Producto producto1 = new Producto(1, "ProductoA", "Descripción A", 29.99, 1, 0);
        Producto producto2 = new Producto(2, "ProductoB", "Descripción B", 19.99, 2, 4);

        // Agregar notificación de producto agotado
        if (producto1.getDisponibilidad() == 0) {
            Notificacion notificacionAgotado = new Notificacion("Producto agotado: " + producto1.getNombre(), 0);
            agregarNotificacion(notificacionAgotado);
        }

        // Agregar notificación de oferta especial
        // Supongamos que solo notificamos si hay menos de 10 unidades disponibles
        if (producto2.getDisponibilidad() < 5) {
            Notificacion notificacionOferta = new Notificacion("Oferta especial: " + producto2.getNombre(), 1);
            agregarNotificacion(notificacionOferta);
        }

        // Procesar notificaciones
        procesarNotificaciones();
    }
}

class Notificacion implements Comparable<Notificacion> {
    private String mensaje;
    private int prioridad;  // 0 para agotado, 1 para oferta

    public Notificacion(String mensaje, int prioridad) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public int compareTo(Notificacion otra) {
        // Comparar primero por prioridad (agotado tiene prioridad sobre oferta)
        int resultado = Integer.compare(this.prioridad, otra.prioridad);

        // Si las prioridades son iguales, comparar por el tamaño del mensaje
        if (resultado == 0) {
            resultado = Integer.compare(this.mensaje.length(), otra.mensaje.length());
        }

        return resultado;
    }
}
