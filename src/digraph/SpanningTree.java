package digraph;

import java.util.HashSet;
import java.util.Set;

public class SpanningTree {

	private Set<Integer> marked;
	private Digraph digraph;
	private Digraph spanningTree;
	private ReachableVertex reachableVertex;
	
	public SpanningTree(Digraph digraph) {
		
		this.digraph = digraph;
		spanningTree = new Digraph(digraph.getNumV());
		marked = new HashSet<Integer>();
		reachableVertex = new ReachableVertex(digraph);
	}
	
	private void dfsUtil(int v) {
		
		marked.add(v);
		for(int w : digraph.adj(v)) {
			if(!marked.contains(w)) {
				spanningTree.addEdge(v, w);
				dfsUtil(w);
			}
		}
	}
	
	public Digraph getSpanningTree() {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			dfsUtil(i);
			if(marked.size() == digraph.getNumV()) {
				return spanningTree;
			}
		}
		
		return null;
	}
	
	public Digraph getSpanningTreeOpt() {
		
		// Running only from vertices that can reach to every other vertex!
		for(int v : reachableVertex.getWithReachToEveryone()) {
			dfsUtil(v);
			if(marked.size() == digraph.getNumV() - 1) {
				return spanningTree; // Returns the first one!
			}
		}
		
		return null;
	}
}
