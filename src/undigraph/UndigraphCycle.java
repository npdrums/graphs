package undigraph;

import java.util.Stack;

public class UndigraphCycle {
	
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	public UndigraphCycle(Graph graph) {
		
		marked = new boolean[graph.getNumV()];
		edgeTo = new int[graph.getNumE()];
		for(int i = 0; i < graph.getNumV(); i++) {
			if(!marked[i]) {
				dfsUtil(graph, -1, i);
			}
		}
	}
	
	/* algs4.princeton.edu */
	/* 
	 * In every recursion step we follow the parent node with int u
	 * This allows us to follow edges to 
	 * */
	private void dfsUtil(Graph G, int u, int v) {
		
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if cycle already found
            if (hasCycle()) { return; }
			
            if (!marked[w]) {
                edgeTo[w] = v;
                dfsUtil(G, v, w);
            }

            // check for cycle (but disregard reverse of edge leading to v)
            else if (w != u) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}

	public Iterable<Integer> getCycle() {
		return cycle;
	}
}
