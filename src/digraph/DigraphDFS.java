package digraph;

import java.util.HashSet;
import java.util.Set;

public class DigraphDFS {

//	private boolean[] marked;
	private Set<Integer> marked;
	
	public DigraphDFS(Digraph graph, int v) {
		
//		marked = new boolean[graph.getNumV()];
		marked = new HashSet<Integer>();
		dfs(graph, v);
	}
	
	private void dfs(Digraph digraph, int v) {
		
		marked.add(v);
		for(int w : digraph.adj(v)) {
//			if(!marked[w]) {
			if(!marked.contains(w)) {
				dfs(digraph, w);
			}
		}
	}
	
	public Set<Integer> getMarked() {
		return marked;
	}
	
	public boolean isVisited(int w) {
		return marked.contains(w);
	}
}
