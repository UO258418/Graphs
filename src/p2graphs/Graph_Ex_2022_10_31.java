package p2graphs;

public class Graph_Ex_2022_10_31<T> extends GraphAbstract<T> {

	public final static int INDEX_NOT_FOUND = -1;
	public final static int EMPTY = -1;
	public final static double INFINITE = Double.POSITIVE_INFINITY;

	public Graph_Ex_2022_10_31(int maxSize) {
		super(maxSize);
		edges = new boolean[maxSize][maxSize];
		weights = new double[maxSize][maxSize];
		pFloyd = new int[maxSize][maxSize];
		aFloyd = new double[maxSize][maxSize];
	}

	public double[][] getA() {
		return aFloyd;
	}

	public int[][] getP() {
		return pFloyd;
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

					// remove edges
					for(int j = 0; j < getSize(); j++) {
						edges[i][j] = false;
						edges[j][i] = false;
					}

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
			weights[sourceIndex][targetIndex] = 0.0;
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

	public double eccentricity(T node) throws ElementNotPresentException {
		if(!existsNode(node))
			throw new ElementNotPresentException("Node" + node + "is not present in the current graph");

		DijkstraDataClass result = dijkstra(node);
		double maxDistance = 0;
		double[] distances = result.getdDijkstra();
		for(int i = 0; i < getSize(); i++) {
			if(distances[i] > maxDistance)
				maxDistance = distances[i];
		}

		return maxDistance;
	}


	public void floyd(int iterations) {
		initsFloyd();
		for(int k = 0; k < iterations; k++)
			for(int i = 0; i < getSize(); i++)
				for(int j = 0; j < getSize(); j++) {
					double costThroughPivot = aFloyd[i][k] + aFloyd[k][j];
					if(costThroughPivot < aFloyd[i][j]) {
						aFloyd[i][j] = costThroughPivot;
						pFloyd[i][j] = k;
					}
				}
	}

	public String printFloydPath(T source, T target) throws ElementNotPresentException {
		if(!existsNode(source))
			throw new ElementNotPresentException(source + " is not present in the current graph.");

		if(!existsNode(target))
			throw new ElementNotPresentException(target + " is not present in the current graph.");

		int sourceIndex = getNode(source);
		int targetIndex = getNode(target);
		int pivot = pFloyd[sourceIndex][targetIndex];

		return aFloyd[sourceIndex][targetIndex] == INFINITE ? "_NO_PATH_FOUND_TO_"
				: removeCharsFromEnd(
					recursivePrintFloyd(sourceIndex, pivot) + recursivePrintFloyd(pivot, targetIndex),
					target.toString().length()
				);
	}

	private String recursivePrintFloyd(int sourceIndex, int pivot) {
		if(sourceIndex == EMPTY || pivot == EMPTY)
			return "";

		return recursivePrintFloyd(sourceIndex, pFloyd[sourceIndex][pivot]) + nodes[pivot].toString();
	}

	private String removeCharsFromEnd(String s, int numOfCharsToRemove) {
		return (s == null || s.length() == 0) ? "" : (s.substring(0, s.length() - numOfCharsToRemove));
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
		if(!existsNode(nodoOrigen))
			return null;

		double D[] = new double[getSize()];
		int PD[] = new int[getSize()];
		int nodeIndex = getNode(nodoOrigen);
		initsDijkstra(D, PD, nodeIndex);

		boolean sSet[] = new boolean[getSize()];
		sSet[nodeIndex] = true;

		int pivot = getPivot(D, sSet);

		while(pivot != EMPTY) {
			sSet[pivot] = true; // set the pivot as visited
			for(int i = 0; i < getSize(); i++) {
				if(edges[pivot][i]) {
					double costThroughPivot = D[pivot] + weights[pivot][i];
					if (costThroughPivot < D[i]) {
						D[i] = costThroughPivot;
						PD[i] = pivot;
					}
				}
			}

			pivot = getPivot(D, sSet);
		}

		return new DijkstraDataClass(getSize(), nodeIndex, D, PD);
	}

	private int getPivot(double[] D, boolean[] sSet) {
		int pivot = EMPTY;
		double minWeight = INFINITE;
		for(int i = 0; i < getSize(); i++) {
			if(!sSet[i]) {
				if (D[i] < minWeight) {
					minWeight = D[i];
					pivot = i;
				}
			}
		}

		return pivot;
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


	public String traverseGraphDF(T element) {
		boolean visited[] = new boolean[getSize()];
		int nodeIndex = getNode(element);
		return nodeIndex == INDEX_NOT_FOUND ? null : DFPrint(nodeIndex, visited);
	}

	private String DFPrint(int currentIndex, boolean[] visited) {
		String result = nodes[currentIndex].toString() + "-";
		visited[currentIndex] = true;
		for(int i = 0; i < getSize(); i++) {
			if(existsEdge(nodes[currentIndex], nodes[i]) && !visited[i])
				result += DFPrint(i, visited);
		}

		return result;
	}

	private void initsFloyd() {
		for(int i = 0; i < getSize(); i++)
			for(int j = 0; j < getSize(); j++) {
				// Fill aFloyd
				if(i == j) aFloyd[i][j] = 0;
				else if(edges[i][j]) aFloyd[i][j] = weights[i][j];
				else aFloyd[i][j] = INFINITE;

				// Fill pFloyd
				pFloyd[i][j] = EMPTY;
			}
	}

	private void initsDijkstra(double[] D, int[] PD, int elementIndex) {
		for(int i = 0; i < getSize(); i++) {
			if(elementIndex == i) {
				D[i] = 0;
				PD[i] = elementIndex;
			}

			else {
				D[i] = edges[elementIndex][i] ? weights[elementIndex][i] : INFINITE;
				PD[i] = edges[elementIndex][i] ? elementIndex : EMPTY;
			}
		}
	}

}
