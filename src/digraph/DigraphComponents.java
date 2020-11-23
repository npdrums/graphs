package digraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Beleske sa vezbi:

Komponenta je slicna kao kod neusmerenog grafa,
ali ovde je bitna razlika da smo tamo mogli da krenemo od proizvoljnog cvora
i nadjemo celu komponentu, a ovde nije svejedno zbog usmerenosti. 
Treba obratiti paznju da prilikom nalazenja komponente,
mozemo naici da smo povezani sa nekom koja je vec deklarisana
i adekvatno prilagoditi podatke (tj spojiti tu komponentu sa tekucom,
ili nasu ubaciti u tu vec postojecu).	 
*/

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
				DFSCompUtil(digraph, i);
				addComponentToList(component);
			}
		}
	}
	
	public void DFSCompUtil(Digraph digraph, int v) {
		marked[v] = true;
		component.add(v);
		for(int w: digraph.adj(v)) {
			if(!component.contains(w)) {
				DFSCompUtil(digraph, w);
			}
		}
	}
	
	private void addComponentToList(Set<Integer> comp) {
		for(Set<Integer> set: list) {
			for(int v : set) {
				if(comp.contains(v)) {
					set.addAll(comp);
					return;
				}
			}
		}
		list.add(comp);
	 }

	public List<Set<Integer>> getDigraphComponents() {
		return list;
	}	
}
