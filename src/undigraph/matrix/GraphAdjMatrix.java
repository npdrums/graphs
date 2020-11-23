package undigraph.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GraphAdjMatrix {

	private int numV;
	private int numE;
	private boolean[][] adj;
	
	public GraphAdjMatrix(int numV) {
		
		this.numV = numV;
		adj = new boolean[numV][numV];
	}
	
	public GraphAdjMatrix(String fileName) {
		
		FileReader file = null;
		BufferedReader buffRead = null;
		String[] helper;
		int v, w;
		String line;
		
		try {
			
			file = new FileReader(fileName);
			buffRead = new BufferedReader(file);
			
			numV = Integer.parseInt(buffRead.readLine());
			numE = Integer.parseInt(buffRead.readLine());
			adj = new boolean[numV][numV];
			
			helper = new String[2];
			
			while((line = buffRead.readLine()) != null) {
				helper = line.split(" ");
				v = Integer.parseInt(helper[0]);
				w = Integer.parseInt(helper[1]);
				addEdge(v, w);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v][w] = adj[w][v] = true;
	}

	public int getNumV() {
		return numV;
	}

	public int getNumE() {
		return numE;
	}
	
	public Iterable<Integer> adj(int v) {
		
		Set<Integer> neighbours = new HashSet<Integer>();
		for(int i = 0; i < numV; i++) {
			if(adj[v][i] == true) {
				neighbours.add(i);
			}
		}
		return Collections.unmodifiableSet(neighbours);
	}
	
	public void print() {
		
		System.out.println("Number of vertices: " + numV);
		for(int i = 0; i < numV; i++) {
			for(int j : adj(i)) {
				System.out.println(i + " - " + j);
			}
		}
	}
}
