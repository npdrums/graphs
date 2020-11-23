package digraph;

import java.util.Stack;

public class TopologicalSort {

	private boolean[] marked;
	private Stack<Integer> reversePostorder;
	
	public TopologicalSort(Digraph digraph, DigraphCycle cycle) {
		
		marked = new boolean[digraph.getNumV()];
		reversePostorder = new Stack<Integer>();
		if(!cycle.hasCycle()) {
			for(int i = 0; i < digraph.getNumV(); i++) {
				if(!marked[i]) {
					dfsUtil(digraph, i);
				}
			}
		}
	}
	
	private void dfsUtil(Digraph digraph, int v) {
		
		marked[v] = true;
		for(int w : digraph.adj(v)) {
			if(!marked[v]) {
				dfsUtil(digraph, w);
			}
		}
		reversePostorder.push(v);
	}
	
	public Iterable<Integer> reversePostorder() {
		return reversePostorder;
	}
}
