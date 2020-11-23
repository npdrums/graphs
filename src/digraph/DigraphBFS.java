package digraph;

import java.util.LinkedList;
import java.util.Queue;

public class DigraphBFS {

	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo; // level
	private Queue<Integer> queue;
	
	public DigraphBFS(Digraph digraph, int v) {
		
		marked = new boolean[digraph.getNumV()];
		edgeTo = new int[digraph.getNumE()];
		distTo = new int[digraph.getNumV()];
		queue = new LinkedList<Integer>();
		bfs(digraph, v);
	}
	
	private void bfs(Digraph digraph, int v) {
		
		marked[v] = true;
		queue.add(v);
		distTo[v] = 0;
		
		while(!queue.isEmpty()) {
			int i = queue.poll();
			for(int w : digraph.adj(i)) {
				if(!marked[w]) {
					marked[w] = true;
					queue.add(w);
					edgeTo[w] = i;
					distTo[w] = distTo[i] + 1;
				}
			}
		}
	}
}
