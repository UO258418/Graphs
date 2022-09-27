package p2graphs;

import java.text.DecimalFormat;

/**
 * @author Nestor
 * @version 2022-23
 */
public abstract class GraphAbstract<T> {
	protected T[] nodes; // Node array

	protected boolean[][] edges; // Adjacency matrix

	protected double[][] weights; // Weight matrix

	protected int numNodes; // The current size of the graph

	protected double aFloyd[][]; // A Floyd Matrix

	protected int pFloyd[][]; // P Floyd Matrix

	/**
	 * 
	 * @param maxSize Maximum number of nodes that can be stored in the graph
	 */
	@SuppressWarnings("unchecked")
	public GraphAbstract(int maxSize) {
		nodes = (T[]) new Object[maxSize];
		numNodes = 0;
	}

	protected double[][] getAFloyd() {
		return aFloyd;
	}

	protected int[][] getPFloyd() {
		return pFloyd;
	}

	/**
	 * @return Returns a String with all the information of the current graph
	 *         (including Floyd matrices)
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";

		cadena += "NODES\n";
		for (int i = 0; i < numNodes; i++) {
			cadena += nodes[i].toString() + "\t";
		}
		cadena += "\n\nEDGES\n";
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}
		cadena += "\nWEIGHTS\n";
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {

				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}

		double[][] aFloyd = getAFloyd();
		if (aFloyd != null) {
			cadena += "\nAFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += df.format(aFloyd[i][j]) + "\t";
				}
				cadena += "\n";
			}
		}

		int[][] pFloyd = getPFloyd();
		if (pFloyd != null) {
			cadena += "\nPFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += (pFloyd[i][j] >= 0 ? df.format(pFloyd[i][j]) : "-") + "\t";
				}
				cadena += "\n";
			}
		}

		return cadena;
	}

}
