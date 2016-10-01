package bottles;

public class Graph {

	private int[][] edges;
	private char[] labels;

	public Graph(int n) {
		edges = new int[n][n];
		labels = new char[n];
	}

	public void setLabel(int vertex, char label) {
		labels[vertex] = label;
	}

	public char getLabel(int vertex) {
		return labels[vertex];
	}

	public void addEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
	}

	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}
}
