import java.util.ArrayList;
import java.util.List;

class Vertice {
    int valor;

    public Vertice(int valor) {
        this.valor = valor;
    }
}

public class GestionCategorias {
    private int numVertices;
    private List<Vertice> vertices;
    private List<List<Integer>> listaAdyacencia;

    public GestionCategorias(int numVertices) {
        this.numVertices = numVertices;
        vertices = new ArrayList<>();
        listaAdyacencia = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertice(i));
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int v1, int v2) {
        listaAdyacencia.get(v1).add(v2);
        listaAdyacencia.get(v2).add(v1);
    }

    public List<Integer> obtenerVecinos(int vertice) {
        return listaAdyacencia.get(vertice);
    }

    public int obtenerNumeroVertices() {
        return numVertices;
    }

    public Vertice obtenerVertice(int indice) {
        return vertices.get(indice);
    }

    public List<Integer> obtenerCategoriasAdyacentes(int categoria) {
        return listaAdyacencia.get(categoria);
    }

    public List<Integer> obtenerSubcategorias(int categoria) {
        List<Integer> subcategorias = new ArrayList<>();
        for (int subcategoria : listaAdyacencia.get(categoria)) {
            if (subcategoria > categoria) {
                subcategorias.add(subcategoria);
            }
        }
        return subcategorias;
    }
}
