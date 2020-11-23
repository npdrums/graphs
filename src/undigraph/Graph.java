package undigraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

	private int numV;
	private int numE;
	private List<Set<Integer>> adj;
	
	public Graph(int numV) {
		
		this.numV = numV;
		adj = new ArrayList<Set<Integer>>();
		for (int i = 0; i < numV; i++) {
			adj.add(i, new HashSet<Integer>());
		}
		
	}
	
	public Graph(String fileName) {
		
		FileReader file = null;
		BufferedReader buffRead = null;
		
		try {
			file = new FileReader(fileName);
			buffRead = new BufferedReader(file);
			
			numV = Integer.parseInt(buffRead.readLine());
			numE = Integer.parseInt(buffRead.readLine());
			adj = new ArrayList<Set<Integer>>(numV);
			
			for(int i = 0; i < numV; i++) {
				adj.add(i, new HashSet<Integer>());
			}
			
			String[] helper = new String[2];
			int v, w;
			String line;
			
			while((line = buffRead.readLine()) != null) {
				helper = line.split(" ");
				v = Integer.parseInt(helper[0]);
				w = Integer.parseInt(helper[1]);
				addEdge(v, w);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				buffRead.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean addEdge(int v, int w) {
		
		try {
			adj.get(v).add(w);
			adj.get(w).add(v);
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	/* Alternativna povratna vrednost:  Iterable<Integer> */
	public Set<Integer> adj(int vertex) {
		/*
		 * Ako je bitno da niko ne dira internu strukturu,
		 * moze se koristiti Collections.unmodifiableSet(),
		 * koji ce napraviti wrapper i nece dati da se menja
		 * originalni objekat!
		 * */
		return Collections.unmodifiableSet(adj.get(vertex));
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
	
	public void print() {
		
		System.out.println("Number of vertices: " + numV);
		for(int i = 0; i < numV; i++) {
			Set<Integer> neighbours = adj.get(i);
			for(int j : neighbours) {
				System.out.println(i + " - " + j);
			}
		}	
	}
}
