package edgeweighted;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimMSTLazy {

	private Set<Integer> marked;
	private List<Edge> mst;
	private PriorityQueue<Edge> pq;
	private double weight;
	
	public PrimMSTLazy(EdgeWeightedGraph ewgraph) {
		
		marked = new HashSet<Integer>();
		mst = new LinkedList<Edge>();
		pq = new PriorityQueue<Edge>();
		
		// Run from all vertices to get a minimum spanning forest!
		for(int i = 0; i < ewgraph.getNumV(); i++) {
			if(!marked.contains(i)) {
				prim(ewgraph, i);
			}
		}
	}
	
	public PrimMSTLazy(EdgeWeightedGraph ewgraph, int v) {
		
		marked = new HashSet<Integer>();
		mst = new LinkedList<Edge>();
		pq = new PriorityQueue<Edge>();
		
		prim(ewgraph, v);
	}

	private void prim(EdgeWeightedGraph ewgraph, int s) {
		
		visit(ewgraph, s);
		while(!pq.isEmpty()) {
			Edge edge = pq.remove();
			int v = edge.either();
			int w = edge.other(v);
			
			if(marked.contains(v) && marked.contains(w)) {
				continue;
			}
			
			mst.add(mst.size(), edge);
			
			weight += edge.getWeight();
			
			if(!marked.contains(v)) {
				visit(ewgraph, v);
			}
			
			if(!marked.contains(w)) {
				visit(ewgraph, w);
			}
		}
	}
	
	private void visit(EdgeWeightedGraph ewgraph, int v) {
		
		marked.add(v);
		for(Edge edge: ewgraph.adj(v)) {
			if(!marked.contains(edge.other(v))) {
				pq.add(edge);
			}
		}
	}
	
	public List<Edge> getMST() {
		return mst;
	}
	
	public double getMSTweight() {
		return weight;
	}
}
