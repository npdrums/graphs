package edgeweighted;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EdgeWeightedGraph {

	private int numV;
	private int numE;
	private List<Set<Edge>> adj;
	
	public EdgeWeightedGraph(int numV, int numE) {
		this.numV = numV;
		this.numE = numE;
		adj = new ArrayList<Set<Edge>>(numV);
		for(int i = 0; i < numV; i++) {
			adj.add(i, new HashSet<Edge>());
		}
	}
	
	public EdgeWeightedGraph(String fileName) {
		
		FileReader file = null;
		BufferedReader buffRead = null;
		String[] helper;
		int v, w, weight;
		String line;
		
		try {
			file = new FileReader(fileName);
			buffRead = new BufferedReader(file);
			
			numV = Integer.parseInt(buffRead.readLine());
			numE = Integer.parseInt(buffRead.readLine());
			adj = new ArrayList<Set<Edge>>(numV);
			
			for(int i = 0; i < numV; i++) {
				adj.add(i, new HashSet<Edge>());
			}
			
			helper = new String[3];
			
			while((line = buffRead.readLine()) != null) {
				helper = line.split(" ");
				v = Integer.parseInt(helper[0]);
				w = Integer.parseInt(helper[1]);
				weight = Integer.parseInt(helper[2]);
				addEdge(new Edge(v, w, weight));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addEdge(Edge e) {
		
		int v = e.either();
		int w = e.other(v);
		
		try {
			adj.get(v).add(e);
			adj.get(w).add(e);
			
			return true;
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return false;
	}
	
	public int getNumV() {
		return numV;
	}

	public int getNumE() {
		return numE;
	}

	public List<Set<Edge>> getAdj() {
		return adj;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj.get(v);
	}
	
	public int degree(int v) {
		return adj.get(v).size();
	}
	
	public void print() {
		
		System.out.println("Number of vertices: " + numV);
		for(int i = 0; i < numV; i++) {
			for(Edge j : adj.get(i)) {
				System.out.println(i + "-" + j.other(i) + " : " + j.getWeight());
			}
		}
	}
}
