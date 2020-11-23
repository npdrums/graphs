package undigraph;

import java.util.LinkedList;
import java.util.Queue;

public class UndigrahBFS {

	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo; // level
	private Queue<Integer> queue;
	
	public UndigrahBFS(Graph graph) {
		
		marked = new boolean[graph.getNumV()];
		edgeTo = new int[graph.getNumE()];
		distTo = new int[graph.getNumV()];
		queue = new LinkedList<Integer>();
		for(int i = 0; i < graph.getNumV(); i++) {
			if(!marked[i]) {
				bfs(graph, i);
			}
		}
	}
	
	private void bfs(Graph graph, int v) {
		
		queue.add(v);
		marked[v] = true;
		distTo[v] = 0;
		
		while(!queue.isEmpty()) {
			int i = queue.poll();
			for(int w : graph.adj(i)) {
				if(!marked[w]) {
					queue.add(w);
					marked[w] = true;
					edgeTo[w] = i;
					distTo[w] = distTo[i] + 1;
				}
			}
		}
	}
}
