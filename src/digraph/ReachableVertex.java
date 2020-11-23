package digraph;

import java.util.HashSet;
import java.util.Set;

public class ReachableVertex {

	private Set<Integer> reachFrom;
	private Set<Integer> reachTo;
	private Digraph digraph;
	
	public ReachableVertex(TransitiveClosure transitiveClosure) {
		
		reachFrom = new HashSet<Integer>();
		reachTo = new HashSet<Integer>();
		digraph = transitiveClosure.getTransitiveClosure();
	}
	
	public Set<Integer> getWithReachFromEveryone() {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			reachFrom.add(i);
		}
		
		for(Set<Integer> neighbour : digraph.getAdj()) {
			reachFrom.retainAll(neighbour);
		}
		
		return reachFrom;
	}
	
	public Set<Integer> getWithReachToEveryone() {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			if(digraph.adj(i).size() == digraph.getNumV() - 1) {
				reachTo.add(i);
			}
		}
		
		return reachTo;
	}
}
