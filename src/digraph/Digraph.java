package digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Digraph {
	
	private int numV;
	private int numE;
	private List<Set<Integer>> adj;
	private int[] indegree;
	
	public Digraph(int numV) {
		
		this.numV = numV;
		adj = new ArrayList<Set<Integer>>();
		indegree = new int[numV];
		
		for(int i = 0; i < numV; i++) {
			adj.add(i, new HashSet<Integer>());
		}
	}
	
	public Digraph(String fileName) {
		
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
			adj = new ArrayList<Set<Integer>>(numV);
			indegree = new int[numV];
			
			for(int i = 0; i < numV; i++) {
				adj.add(i, new HashSet<Integer>());
			}
			
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
	
	public boolean addEdge(int v, int w) {
		
		try {
			adj.get(v).add(w);
			indegree[w]++;
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public int getNumV() {
		return numV;
	}

	public int getNumE() {
		return numE;
	}

	public List<Set<Integer>> getAdj() {
		return adj;
	}
	
	public Set<Integer> adj(int v) {
		return adj.get(v);
	}

	public int indegree(int v) {
		return indegree[v];
	}
	
	public int outdegree(int v) {
		return adj.get(v).size();
	}
	
	public void print() {
		
		System.out.println("Number of vertices: " + numV);
		for(int i = 0; i < numV; i++) {
			for(int j : adj.get(i)) {
				System.out.println(i + "->" + j);
			}
		}
	}
}
