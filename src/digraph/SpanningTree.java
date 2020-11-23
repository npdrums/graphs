package digraph;

import java.util.HashSet;
import java.util.Set;

public class SpanningTree {

	private Set<Integer> marked;
	private Digraph digraph;
	private Digraph spanningTree;
	
	public SpanningTree(Digraph digraph) {
		
		this.digraph = digraph;
		spanningTree = new Digraph(digraph.getNumV());
		marked = new HashSet<Integer>();
	}
	
	private void dfsTreeUtil(int v) {
		
		marked.add(v);
		for(int w : digraph.adj(v)) {
			if(!marked.contains(w)) {
				spanningTree.addEdge(v, w);
				dfsTreeUtil(w);
			}
		}
	}
	
	public Digraph getSpanningTree() {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			dfsTreeUtil(i);
			if(marked.size() == digraph.getNumV()) {
				return spanningTree;
			}
		}
		
		return null;
	}
}
