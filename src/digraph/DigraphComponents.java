package digraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DigraphComponents {

	private boolean[] marked;
	private List<Set<Integer>> list;
	private Set<Integer> component;
	
	public DigraphComponents(Digraph digraph) {
		
		marked = new boolean[digraph.getNumV()];
		list = new ArrayList<Set<Integer>>();
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			if(!marked[i]) {
				component = new HashSet<Integer>();
				dfsUtil(digraph, i);
				addComponentToList(component);
			}
		}
	}
	
	public void dfsUtil(Digraph digraph, int v) {

		marked[v] = true;
		component.add(v);
		for(int w: digraph.adj(v)) {
			if(!component.contains(w)) {
				dfsUtil(digraph, w);
			}
		}
	}
	
	private void addComponentToList(Set<Integer> component) {

		// If any vertex from the component is already in a component within the list
		// We just merge the components
		for(Set<Integer> set: list) {
			for(int v : set) {
				if(component.contains(v)) {
					set.addAll(component);
					return;
				}
			}
		}

		list.add(component);
	 }

	public List<Set<Integer>> getDigraphComponents() {
		return list;
	}	
}
