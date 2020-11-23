package digraph;

public class TransitiveClosure {

	private DigraphDFS[] dfsFromAll;
	private Digraph digraph;
	
	public TransitiveClosure(Digraph digraph) {
		
		dfsFromAll = new DigraphDFS[digraph.getNumV()];
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			dfsFromAll[i] = new DigraphDFS(digraph, i);
		}
	}
	
	private boolean isReachable(int v, int w) {
		return dfsFromAll[v].isVisited(w);
	}
	
	public Digraph getTransitiveClosure() {
		
		digraph = new Digraph(dfsFromAll.length);
		
		for(int v = 0; v < dfsFromAll.length; v++) {
			for(int w : dfsFromAll[v].getMarked()) {
				if(isReachable(v, w)) { // Obsolete?
					digraph.addEdge(v, w);
				}
			}
		}
		
		return digraph;
	}
}
