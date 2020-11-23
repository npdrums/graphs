package undigraph;

import java.util.Stack;

public class UndigraphPaths {
	
	private boolean[] marked;
	private int[] edgeTo; // parent-link
	
	public UndigraphPaths(Graph graph, int v) {
		
		marked = new boolean[graph.getNumV()];
		edgeTo = new int[graph.getNumE()];
		dfsUtil(graph, v);
	}
	
	private void dfsUtil(Graph graph, int v) {
		
		marked[v] = true;
		for(int w : graph.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfsUtil(graph, w);
			}
		}
	}

	public boolean hasPath(int v, int w) {
		if(v == w) { return false; } // loop prevention
		return marked[v] && marked[w];
	}
	
	public Iterable<Integer> getPath(int v, int w) {
		
		if(!hasPath(v, w)) { return null; }
		Stack<Integer> path = new Stack<Integer>();
		for(int i = w; i != v; i = edgeTo[i]) {
			path.push(i);
		}
		path.push(v);
		return path;
	}
}
