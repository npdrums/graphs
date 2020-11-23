package undigraph;

public class UndigraphBipartite {

	private boolean[] marked;
	private boolean isBipartite;
	private boolean[] color;
	
	public UndigraphBipartite(Graph graph) {
		
		marked = new boolean[graph.getNumV()];
		color = new boolean[graph.getNumV()];
		for(int i = 0; i < graph.getNumV(); i++) {
			if(!marked[i]) {
				dfsUtil(graph, i);
			}
		}
	}
	
	private void dfsUtil(Graph graph, int v) {
		
		marked[v] = true;
		for(int w : graph.adj(v)) {
			if(!marked[w]) {
				color[w] = !color[v];
				dfsUtil(graph, w);
			} else if(color[v] == color[w]) {
				isBipartite = false;
			}
		}
		isBipartite = true;
	}

	public boolean isBipartite() {
		return isBipartite;
	}
}
