package undigraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UndigraphComponents {

	private boolean[] marked;
	private int[] components;
	private int count;
	private int numV;
	private List<Set<Integer>> listOfComponents;
	private Set<Integer> component;
	
	public UndigraphComponents(Graph graph) {
		
		numV = graph.getNumV();
		marked = new boolean[numV];
		components = new int[numV];
		listOfComponents = new ArrayList<Set<Integer>>();
		
		for(int i = 0; i < numV; i++) {
			if(!marked[i]) {
				dfsUtil(graph, i);
				count++;
			}
		}
	}
	
	private void dfsUtil(Graph graph, int v) {
		
		marked[v] = true;
		components[v] = count;
		for(int w : graph.adj(v)) {
			if(!marked[w]) {
				dfsUtil(graph, w);
			}
		}
	}

	public int getComponentOf(int v) {
		return components[v];
	}

	public int getNumOfComponents() {
		return count;
	}
	
	public List<Set<Integer>> getComponents() {
		int numOfComponents = count - 1;
		while(numOfComponents >= 0) {
			component = new HashSet<Integer>();
			for(int i = 0; i < numV; i++) {
				if(components[i] == numOfComponents) {
					component.add(i);
				}
			}
			listOfComponents.add(component);
			numOfComponents--;			
		}
		return listOfComponents;
	} 
}
