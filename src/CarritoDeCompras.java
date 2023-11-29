import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CarritoDeCompras {
    private Queue<Producto> productosEnCarrito;

    public CarritoDeCompras() {
        productosEnCarrito = new LinkedList<>();
    }

    public void agregarAlCarrito(Producto producto) {
        productosEnCarrito.add(producto);
    }

    public List<Producto> mostrarCarrito() {
        List<Producto> listaCarrito = new LinkedList<>();
        for (Producto producto : productosEnCarrito) {
            listaCarrito.add(producto);
        }
        return listaCarrito;
    }

    public void vaciarCarrito() {
        productosEnCarrito.clear();
        System.out.println("Carrito vaciado.");
    }

    public static void main(String[] args) {
        // Ejemplo de uso del carrito de compras
        CarritoDeCompras carrito = new CarritoDeCompras();

        // Agregar productos al carrito
        Producto producto1 = new Producto(1, "Producto1", "Descripción del Producto1", 29.99, 2, 10);
        Producto producto2 = new Producto(2, "Producto2", "Descripción del Producto2", 19.99, 14, 5);

        carrito.agregarAlCarrito(producto1);
        carrito.agregarAlCarrito(producto2);

        // Mostrar productos en el carrito
        List<Producto> productosEnCarrito = carrito.mostrarCarrito();
        for (Producto producto : productosEnCarrito) {
            System.out.println("Producto en el carrito: " + producto.getNombre());
        }

        // Vaciar el carrito
        carrito.vaciarCarrito();
    }
}