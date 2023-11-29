import java.util.*;

class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int categoriaId;
    private int disponibilidad;

    public Producto(int id, String nombre, String descripcion, double precio, int categoriaId, int disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.disponibilidad = disponibilidad;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}

public class RegistroProductos {
    private static HashMap<Integer, Producto> productosMap;  // Mapa de productos por ID
    private static HashMap<String, List<Integer>> productosPorNombre;  // Mapa para búsqueda por nombre
    private static HashMap<Integer, List<Integer>> productosPorCategoria;  // Mapa para filtrado por categoría

    static {
        productosMap = new HashMap<>();
        productosPorNombre = new HashMap<>();
        productosPorCategoria = new HashMap<>();
    }

    public void agregarProducto(int id, String nombre, String descripcion,
                                       double precio, int categoriaId, int disponibilidad) {
        Producto producto = new Producto(id, nombre, descripcion, precio, categoriaId, disponibilidad);
        productosMap.put(id, producto);

        // Actualizar el mapa para búsqueda por nombre
        productosPorNombre.computeIfAbsent(nombre, k -> new ArrayList<>()).add(id);

        // Actualizar el mapa para filtrado por categoría
        productosPorCategoria.computeIfAbsent(categoriaId, k -> new ArrayList<>()).add(id);
    }

    public Producto obtenerProductoPorId(int id) {
        return productosMap.get(id);
    }

    public void mostrarProductos() {
        for (Producto producto : productosMap.values()) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Categoría ID: " + producto.getCategoriaId());
            System.out.println("Disponibilidad en stock: " + producto.getDisponibilidad() + " unidades\n");
        }
    }

    public ArrayList<Integer> obtenerTodosLosIDs() {
        Set<Integer> ids = productosMap.keySet();
        return new ArrayList<>(ids);
    }
    public List<Producto> obtenerProductosPorCategoria(int categoriaId) {
        List<Integer> ids = productosPorCategoria.getOrDefault(categoriaId, new ArrayList<>());
        return obtenerProductos(ids);
    }

    public String obtenerNombreCategoriaDeProducto(Producto producto) {
        int categoriaId = producto.getCategoriaId();
        GestionCategorias grafo = GrafoHelper.obtenerGrafo();
        Map<Integer, String> categoriasMap = MapHelper.obtenerCategoriasMap();

        // Buscar el nombre de la categoría utilizando el grafo y el mapa de categorías
        List<Integer> categoriasVisitadas = new ArrayList<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.add(0); // Comenzar desde la categoría principal

        while (!cola.isEmpty()) {
            int categoriaActual = cola.poll();
            categoriasVisitadas.add(categoriaActual);

            if (categoriaActual == categoriaId) {
                return categoriasMap.get(categoriaActual);
            }

            List<Integer> subcategorias = grafo.obtenerSubcategorias(categoriaActual);
            for (int subcategoria : subcategorias) {
                if (!categoriasVisitadas.contains(subcategoria)) {
                    cola.add(subcategoria);
                }
            }
        }

        // Si no se encuentra la categoría, devolver un mensaje indicando que no se encontró
        return "Categoría no encontrada";
    }

    public List<Producto> filtrarPorCategoria(int categoriaId) {
        List<Integer> ids = productosPorCategoria.getOrDefault(categoriaId, new ArrayList<>());
        return obtenerProductos(ids);
    }
    public List<Producto> buscarPorNombre(String nombre) {
        List<Integer> ids = productosPorNombre.getOrDefault(nombre, new ArrayList<>());
        return obtenerProductos(ids);
    }

    public List<Producto> obtenerProductos(List<Integer> ids) {
        List<Producto> productos = new ArrayList<>();
        for (int id : ids) {
            Producto producto = productosMap.get(id);
            if (producto != null) {
                productos.add(producto);
            }
        }
        return productos;
    }
    /*
    public static void main(String[] args) {
        // Ejemplo de uso
        RegistroProductos registro = new RegistroProductos();
        registro.agregarProducto(1, "ProductoA", "Descripción A", 29.99, 1, 50);
        registro.agregarProducto(2, "ProductoB", "Descripción B", 19.99, 2, 30);
        registro.agregarProducto(3, "ProductoC", "Descripción C", 39.99, 1, 20);

        // Búsqueda por nombre
        List<Producto> resultadoBusquedaNombre = registro.buscarPorNombre("ProductoB");
        System.out.println("Búsqueda por nombre (ProductoB):");
        for (Producto producto : resultadoBusquedaNombre) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Categoría ID: " + producto.getCategoriaId());
            System.out.println("Disponibilidad en stock: " + producto.getDisponibilidad() + " unidades\n");
        }

        // Filtrado por categoría
        List<Producto> resultadoFiltradoCategoria = registro.filtrarPorCategoria(1);
        System.out.println("Filtrado por categoría (ID 1):");
        for (Producto producto : resultadoFiltradoCategoria) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Categoría ID: " + producto.getCategoriaId());
            System.out.println("Disponibilidad en stock: " + producto.getDisponibilidad() + " unidades\n");
        }
    }
    */
}
class MapHelper {
    private static Map<Integer, String> categoriasMap;

    static {
        categoriasMap = new HashMap<>();
        categoriasMap.put(0, "Alimentos");
        categoriasMap.put(1, "Carnes");
        categoriasMap.put(2, "Res");
        categoriasMap.put(3, "Aves");
        categoriasMap.put(4, "Pollo");
        categoriasMap.put(5, "Pavo");
        categoriasMap.put(6, "Cerdo");
        categoriasMap.put(7, "Lacteos");
        categoriasMap.put(8, "Leche");
        categoriasMap.put(9, "Animal");
        categoriasMap.put(10, "Vegetal");
        categoriasMap.put(11, "Yogurt");
        categoriasMap.put(12, "Queso");
        categoriasMap.put(13, "Otros");
        categoriasMap.put(14, "Golosinas");
        categoriasMap.put(15, "Dulces");
        categoriasMap.put(16, "Chocolates");
        categoriasMap.put(17, "Gomitas");
        categoriasMap.put(18, "Salados");
        categoriasMap.put(19, "Bebidas");
        categoriasMap.put(20, "Con Alcohol");
        categoriasMap.put(21, "Sin Alcohol");
        categoriasMap.put(22, "Gaseosas");
        categoriasMap.put(23, "Jugos");
        categoriasMap.put(24, "Agua");
    }

    public static Map<Integer, String> obtenerCategoriasMap() {
        return categoriasMap;
    }
    public static List<String> obtenerSubs(List<Integer> numCat){
        List<String> nCate = new ArrayList<>();
        for (int n : numCat){
            nCate.add(categoriasMap.get(n));
        }
        return nCate;
    }
}
class GrafoHelper {
    private static GestionCategorias grafo;

    static {
        grafo = new GestionCategorias(25);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 7);
        grafo.agregarArista(0, 14);
        grafo.agregarArista(0, 19);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(3, 4);
        grafo.agregarArista(3, 5);
        grafo.agregarArista(1, 6);
        grafo.agregarArista(7, 8);
        grafo.agregarArista(8, 9);
        grafo.agregarArista(8, 10);
        grafo.agregarArista(7, 11);
        grafo.agregarArista(7, 12);
        grafo.agregarArista(7, 13);
        grafo.agregarArista(14, 15);
        grafo.agregarArista(15, 17);
        grafo.agregarArista(15, 16);
        grafo.agregarArista(14, 18);
        grafo.agregarArista(19, 20);
        grafo.agregarArista(19, 21);
        grafo.agregarArista(21, 22);
        grafo.agregarArista(21, 23);
        grafo.agregarArista(21, 24);
    }

    public static GestionCategorias obtenerGrafo() {
        return grafo;
    }
    public static List<Integer> obtenerSub(int categoria){return grafo.obtenerSubcategorias(categoria);}
}