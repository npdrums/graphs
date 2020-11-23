package edgeweighted;

import java.util.Stack;

public class DeepDive {
	
	private boolean[] marked;
	private Edge[] edgeTo;// parent-link
	private EdgeWeightedGraph prunedEWGraph;
	private boolean canDeepDive;
	private boolean canDeepDiveWithBreak;
	private double currBreath;
	
	public DeepDive(EdgeWeightedGraph ewgraph, double breath, int v, int w) {
		
		marked = new boolean[ewgraph.getNumV()];
		edgeTo = new Edge[ewgraph.getNumE()];
		this.currBreath = breath;
		
		prunedEWGraph = new EdgeWeightedGraph(ewgraph.getNumV(), ewgraph.getNumE());

		for(int i = 0; i < ewgraph.getNumV(); i++) {
			for(Edge e : ewgraph.adj(i)) {
				if(e.getWeight() <= breath) {
					prunedEWGraph.addEdge(e);
				}
			}
		}
		
		dfsUtil(prunedEWGraph, v);
				
		if(hasPath(v, w)) {
			canDeepDive = true;
		} else {
			canDeepDive = false;
		}
		
		canDeepDiveEvenBreak(prunedEWGraph, breath, v, w);
	}
	
	private void dfsUtil(EdgeWeightedGraph ewgraph, int v) {
		
		marked[v] = true;
		for(Edge e : ewgraph.adj(v)) {
			if(!marked[e.other(v)]) {
				edgeTo[e.other(v)] = e;
				dfsUtil(ewgraph, e.other(v));
			}
		}
	}

	public boolean hasPath(int v, int w) {
		if(v == w) { return false; } // loop prevention
		return marked[v] && marked[w];
	}
	
	public Iterable<Edge> getPath(int v, int w) {
        if (!hasPath(v, w)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = w;
        for (Edge e = edgeTo[w]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

	
	public boolean canDeepDive() {
		return canDeepDive;
	}
	
	public void canDeepDiveEvenBreak(EdgeWeightedGraph ewgraph, double breath, int v, int w) {
		if(hasPath(v, w)) {
			for(Edge e : getPath(v, w)) {
				if(currBreath < 0) {
					canDeepDiveWithBreak = false;
					break;
				} else if(ewgraph.degree(e.either()) % 2 == 0) {
					currBreath = breath;
				} else {
					currBreath -= e.getWeight();
				}
			}
			canDeepDiveWithBreak = true;
		}
		canDeepDiveWithBreak = false;
	}
	
	public boolean canDeepDiveWithBreak() {
		return canDeepDiveWithBreak;
	}
}

