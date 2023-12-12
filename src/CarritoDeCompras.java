import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CarritoDeCompras {
    private Queue<String> productosEnCarrito;

    public CarritoDeCompras() {
        productosEnCarrito = new LinkedList<>();
    }

    public void agregarAlCarrito(String producto, String cantidad, String totalPro) {
        productosEnCarrito.add(producto + "," + cantidad + "," + totalPro);
    }

    public List<String> mostrarCarrito() {
        List<String> listaCarrito = new LinkedList<>();
        for (String producto : productosEnCarrito) {
            listaCarrito.add(producto);
        }
        return listaCarrito;
    }

    public double mostrarTotal(){
        double suma = 0;
        for (String producto : productosEnCarrito){
            String[] des = producto.split(",");
            Double monto = Double.valueOf(des[2]);
            suma += monto;
        }

        return suma;
    }

    public void vaciarCarrito() {
        productosEnCarrito.clear();
        System.out.println("Carrito vaciado.");
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Carrito de Compras:\n");

        for (String producto : productosEnCarrito) {
            String[] partes = producto.split(",");
            stringBuilder.append("Producto: ").append(partes[0])
                    .append(", Cantidad: ").append(partes[1])
                    .append(", Precio Total: ").append(partes[2])
                    .append("\n");
        }

        return stringBuilder.toString();
    }
    
    

    public static void main(String[] args) {
        // Ejemplo de uso del carrito de compras
        CarritoDeCompras carrito = new CarritoDeCompras();

        // Agregar productos al carrito
        Producto producto1 = new Producto(1, "Producto1", "Descripción del Producto1", 29.99, 2, 10);
        Producto producto2 = new Producto(2, "Producto2", "Descripción del Producto2", 19.99, 14, 5);

        carrito.agregarAlCarrito("Leche", "2", "14");
        carrito.agregarAlCarrito("Carne", "1", "20");

        // Mostrar productos en el carrito
        List<String> productosEnCarrito = carrito.mostrarCarrito();
        for (String producto : productosEnCarrito) {
            System.out.println("Producto en el carrito: " + producto.split(",")[0]);
        }

        // Vaciar el carrito
        carrito.vaciarCarrito();
    }
}