package digraph;

import java.util.LinkedList;
import java.util.List;

public class DigraphCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private List<Integer> cycle;
	
	public DigraphCycle(Digraph digraph) {
		
		marked = new boolean[digraph.getNumV()];
		edgeTo =  new int[digraph.getNumE()];
		for(int i = 0; i < digraph.getNumV(); i++) {
			if(!marked[i]) {
				dfsUtil(digraph, i);
			}
		}
	}
	
	private void dfsUtil(Digraph digraph, int v) {
		
		marked[v] = true;
		for(int w : digraph.adj(v)) {
			if(hasCycle()) {
				return;
			} else if(!marked[w]) {
				edgeTo[w] = v;
				dfsUtil(digraph, w);
			} else if (w != v) {
				cycle = new LinkedList<Integer>();
				for(int i = v; i != w; i = edgeTo[i]) {
					cycle.add(0, i);
				}
				cycle.add(0, w);
				cycle.add(cycle.size(), w);
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
