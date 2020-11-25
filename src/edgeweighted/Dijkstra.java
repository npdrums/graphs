package edgeweighted;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.princeton.cs.algs4.IndexMinPQ;

public class Dijkstra {

	private double[] distTo;
	private Edge[] edgeTo;
	private IndexMinPQ<Double> pq;
	private EdgeWeightedGraph ewgraph;
	private int start;
	
	public Dijkstra(EdgeWeightedGraph ewgraph, int s) {
		
		this.ewgraph = ewgraph;
		this.start = s;
		distTo = new double[ewgraph.getNumV()];
		edgeTo = new Edge[ewgraph.getNumV()];
		pq = new IndexMinPQ<Double>(ewgraph.getNumV());
		
		for(int i = 0; i < ewgraph.getNumV(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		pq.insert(s, distTo[s]);
		
		while(!pq.isEmpty()) {
			relax(ewgraph, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedGraph ewgraph, int v) {
		
		for(Edge edge : ewgraph.adj(v)) {
			
			int w = edge.other(v);
			if(distTo[w] > distTo[v] + edge.getWeight()) {
				
				distTo[w] = distTo[v] + edge.getWeight();
				edgeTo[w] = edge;
				
				if(pq.contains(w)) {
					pq.changeKey(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
 			}
		}
	}
	
	public double distTo(int w) {
		return distTo[w];
	}
	
	public boolean hasPathTo(int w) {
		return edgeTo[w] != null;
	}
	
	public Iterable<Edge> pathTo(int w) {
		
		if(!hasPathTo(w)) {
			return null;
		}
		
		Stack<Edge> path = new Stack<Edge>();
		Edge edge = edgeTo[w];
		while(edge != null) {
			path.push(edge);
			w = edge.other(w);
			edge = edgeTo[w];
		}
		
		return path;
	}
	
	public List<Iterable<Edge>> pathToAll() {
		
		List<Iterable<Edge>> list = new ArrayList<Iterable<Edge>>();
		
		for(int i = 0; i < ewgraph.getNumV(); i++) {
			if(i != start && hasPathTo(i)) {
				list.add(pathTo(i));
			}
		}
		
		return list;
	}
}
