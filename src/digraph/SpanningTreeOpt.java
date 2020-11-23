package digraph;

import java.util.HashSet;
import java.util.Set;

public class SpanningTreeOpt {

	private Set<Integer> marked;
	private Digraph digrah;
	private Digraph spanningTree;
	private Set<Integer> reachToAll;
	
	public SpanningTreeOpt(ReachableVertex reachableVertex, Digraph digraph) {
		
		this.digrah = digraph;
		spanningTree = new Digraph(digraph.getNumV());
		reachToAll = reachableVertex.getWithReachToEveryone();
		marked = new HashSet<Integer>();
	}
	
	private void dfsTreeUtil(int v) {
		
		marked.add(v);
		for(int w : digrah.adj(v)) {
			if(!marked.contains(w)) {
				spanningTree.addEdge(v, w);
				dfsTreeUtil(w);
			}
		}
	}
	
	public Digraph getDigraph() {
		
		// Running only from vertices that can reach to every other vertex!
		for(int v : reachToAll) {
			dfsTreeUtil(v);
			if(marked.size() == digrah.getNumV() - 1) {
				return spanningTree; // Returns the first one!
			}
		}
		
		return null;
	}
}
