
// Clase para representar grafos como listas de adyacencia
class Grafo {
    private Map<Integer, List<Integer>> adjList;

    public Grafo() {
        this.adjList = new HashMap<>();
    }

    public void agregarArista(int origen, int destino) {
        adjList.putIfAbsent(origen, new ArrayList<>());
        adjList.get(origen).add(destino);
    }

    public List<Integer> obtenerVecinos(int nodo) {
        return adjList.getOrDefault(nodo, new ArrayList<>());
    }
}

// Clase abstracta Busqueda
abstract class Busqueda {
    protected Grafo grafo;

    public Busqueda(Grafo grafo) {
        this.grafo = grafo;
    }

    public int buscar(int inicio, int objetivo) {
        Set<Integer> visitados = new HashSet<>();
        Secuencia<Integer> secuencia = crearSecuencia();

        secuencia.agregar(inicio);
        int nodosExplorados = 0;

        while (!secuencia.vacio()) {
            int nodo = secuencia.remover();

            if (!visitados.contains(nodo)) {
                visitados.add(nodo);
                nodosExplorados++;

                if (nodo == objetivo) {
                    return nodosExplorados;
                }

                for (int vecino : grafo.obtenerVecinos(nodo)) {
                    if (!visitados.contains(vecino)) {
                        secuencia.agregar(vecino);
                    }
                }
            }
        }
        return -1; // Si no se encuentra el objetivo
    }

    protected abstract Secuencia<Integer> crearSecuencia();
}

// Clase concreta DFS
class DFS extends Busqueda {
    public DFS(Grafo grafo) {
        super(grafo);
    }

    @Override
    protected Secuencia<Integer> crearSecuencia() {
        return new Pila<>();
    }
}

// Clase concreta BFS
class BFS extends Busqueda {
    public BFS(Grafo grafo) {
        super(grafo);
    }

    @Override
    protected Secuencia<Integer> crearSecuencia() {
        return new Cola<>();
    }
}

// Clase principal para pruebas
public class Main {
    public static void main(String[] args) {
        // Crear el grafo
        Grafo grafo = new Grafo();
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(3, 4);
        grafo.agregarArista(4, 5);

        // Prueba de BFS y DFS
        Busqueda dfs = new DFS(grafo);
        Busqueda bfs = new BFS(grafo);

        System.out.println("DFS: " + dfs.buscar(1, 5)); // Salida: Cantidad de nodos explorados
        System.out.println("BFS: " + bfs.buscar(1, 5)); // Salida: Cantidad de nodos explorados

        System.out.println("DFS (no alcanzable): " + dfs.buscar(1, 6)); // Salida: -1
        System.out.println("BFS (no alcanzable): " + bfs.buscar(1, 6)); // Salida: -1
    }
}
