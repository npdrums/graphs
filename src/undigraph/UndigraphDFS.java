package undigraph;

public class UndigraphDFS {

	private boolean[] marked;
	
	public UndigraphDFS(Graph graph) {
		
		marked = new boolean[graph.getNumV()];
		for(int i = 0; i < graph.getNumV(); i++) {
			if(!marked[i]) {
				dfs(graph, i);
			}
		}
	}
	
	private void dfs(Graph graph, int v) {
		
		marked[v] = true;
		for(int w : graph.adj(v)) {
			if(!marked[v]) {
				dfs(graph, w);
			}
		}
	}
	
	// TODO: UndigraphDFS Iter

}
