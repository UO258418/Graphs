package p2graphs;

public class Graph<T> extends GraphAbstract<T> {


	public Graph(int maxSize) {
		super(maxSize);
	}

	protected int getNode(T node) {
		// Completad el metodo...
		return -2;
	}


	public boolean addNode(T node) {
		// Completad el metodo...
//		throw new NullPointerException("Element to insert is null.");
// 		...		
//		throw new FullStructureException(node);
		return false;
	}


	public boolean removeNode(T node) {
//		throw new NullPointerException("Element to remove is null.");
		// Completad el método...
		return false;
	}


	public boolean existsNode(T node) {
		// Completad el método...
		return false;
	}


	public boolean existsEdge(T source, T target) {
		// Completad el metodo...
		return false;
	}


	public boolean addEdge(T source, T target, double edgeWeight) {
		// Completad el metodo...
//		throw new ElementNotPresentException(
//				"Edge could not be loaded: source element " + source + " is not part of the graph.");

//		throw new ElementNotPresentException(
//				"Edge could not be loaded: target element " + target + " is not part of the graph.");

//		throw new IllegalArgumentException(
//				"Weight edge could not be less or equals to 0");

		return false;
	}

	public boolean removeEdge(T source, T target) {
		// Completad el metodo...
//		throw new ElementNotPresentException(
//				"Edge could not be removed: " + source + " is not present in the current graph.");

//		throw new ElementNotPresentException(
//				"Edge could not be removed: " + target + " is not present in the current graph.");

		return false;
	}


	public double getEdge(T source, T target) {
		// Completad el metodo...
//		throw new ElementNotPresentException(
//		"Edge weight could not be obtained: " + source + " is not present in the current graph.");

//		throw new ElementNotPresentException(
//		"Edge weight could not be obtained: " + target + " is not present in the current graph.");
		return -1;
	}


	public boolean floyd() {
		// Completad el metodo...
		return false;
	}


	public String recorridoProfundidad(T nodo) {
		// Completad el metodo...
		return "";
	}


	public String path(T origen, T destino) {
		// Completad el metodo
		return "";
	}


	public DijkstraDataClass dijkstra(T nodoOrigen) {
		// Completad el metodo...
		return null;
	}


	public double minCostPath(T origen, T destino) {
		// Implementad el metodo...
		return -1;
	}


}
