package p2graphs;

public class Graph<T> extends GraphAbstract<T> {

	private final static int INDEX_NOT_FOUND = -1;

	public Graph(int maxSize) {
		super(maxSize);
		edges = new boolean[maxSize][maxSize];
		weights = new double[maxSize][maxSize];
	}

	public int getSize() {
		return numNodes;
	}

	public int getNode(T node) throws NullPointerException {
		if(node == null)
			throw new NullPointerException("Element to find is null.");

		for(int i = 0; i < getSize(); i++) {
			if(nodes[i].equals(node))
				return i;
		}

		return INDEX_NOT_FOUND;
	}

	public boolean addNode(T node) throws NullPointerException, FullStructureException{
		if(node == null)
			throw new NullPointerException("Element to insert is null.");

		if(getSize() == nodes.length)
			throw new FullStructureException(node);

		if(!existsNode(node)) {
			for(int i = 0; i < nodes.length; i++)
				if(nodes[i] == null) {
					nodes[i] = node;
					numNodes++;
					return true;
				}
		}

		return false;
	}


	public boolean removeNode(T node) throws NullPointerException {
		if(node == null)
			throw new NullPointerException("Element to remove is null.");

		int nodeIndex = getNode(node);
		if(nodeIndex == INDEX_NOT_FOUND)
			return false;

		int lastNode = getSize() - 1;

		// remove node
		if(nodeIndex == lastNode) {
			nodes[nodeIndex] = null;
		}

		else{
			nodes[nodeIndex] = nodes[lastNode];
			nodes[lastNode] = null;

			// copy edges and weights to its new position
			edges[nodeIndex][nodeIndex] = edges[lastNode][lastNode];
			for(int i = 0; i < getSize() - 1; i++) {
				if(i != nodeIndex) {
					edges[nodeIndex][i] = edges[lastNode][i];
					edges[i][nodeIndex] = edges[i][lastNode];

					weights[nodeIndex][i] = weights[lastNode][i];
					weights[i][nodeIndex] = weights[i][lastNode];
				}
			}
		}

		// remove edges
		for(int i = 0; i < getSize(); i++) {
			edges[lastNode][i] = false;
			edges[i][lastNode] = false;
		}

		numNodes--;
		return true;
	}


	public boolean existsNode(T node) throws NullPointerException {
		if(node == null)
			throw new NullPointerException("The node to be checked is null.");

		for(int i = 0; i < getSize(); i++)
			if(nodes[i].equals(node))
				return true;

		return false;
	}


	public boolean existsEdge(T source, T target) throws NullPointerException {
		if(source == null)
			throw new NullPointerException("Source node is null");

		if(target == null)
			throw new NullPointerException("Target node is null");

		int sourceIndex = getNode(source);
		int targetIndex = getNode(target);
		if(sourceIndex == INDEX_NOT_FOUND || targetIndex == INDEX_NOT_FOUND)
			return false;

		return edges[sourceIndex][targetIndex];
	}


	public boolean addEdge(T source, T target, double edgeWeight) throws ElementNotPresentException,
			IllegalArgumentException {
		if(!existsNode(source))
			throw new ElementNotPresentException(
						"Edge could not be loaded: source element " + source + " is not part of the graph.");

		if(!existsNode(target))
			throw new ElementNotPresentException(
					"Edge could not be loaded: target element " + target + " is not part of the graph.");

		if(edgeWeight <= 0)
			throw new IllegalArgumentException(
					"Weight edge could not be less or equal to 0");

		if(!existsEdge(source, target)) {
			int sourceIndex = getNode(source);
			int targetIndex = getNode(target);
			edges[sourceIndex][targetIndex] = true;
			weights[sourceIndex][targetIndex] = edgeWeight;
			return true;
		}

		return false;
	}

	public boolean removeEdge(T source, T target) {
		if(!existsNode(source))
			throw new ElementNotPresentException(
					"Edge could not be removed: " + source + " is not present in the current graph.");

		if(!existsNode(target))
			throw new ElementNotPresentException(
					"Edge could not be removed: " + target + " is not present in the current graph.");

		if(existsEdge(source, target)) {
			int sourceIndex = getNode(source);
			int targetIndex = getNode(target);
			edges[sourceIndex][targetIndex] = false;
			weights[sourceIndex][targetIndex] = Integer.MAX_VALUE;
			return true;
		}

		return false;
	}


	public double getEdge(T source, T target) throws ElementNotPresentException {
		if(!existsNode(source))
			throw new ElementNotPresentException(
				"Edge weight could not be obtained: " + source + " is not present in the current graph.");

		if(!existsNode(target))
			throw new ElementNotPresentException(
				"Edge weight could not be obtained: " + target + " is not present in the current graph.");

		if(existsEdge(source, target)) {
			int sourceIndex = getNode(source);
			int targetIndex = getNode(target);
			return weights[sourceIndex][targetIndex];
		}

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


    public boolean[][] getEdges() {
		return edges;
    }

	public double[][] getWeight() {
		return weights;
	}


}
