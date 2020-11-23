package digraph;

import java.util.ArrayList;
import java.util.List;

public class SourcesAndSinks {

	private List<Integer> sources;
	private List<Integer> sinks;
	
	public SourcesAndSinks(Digraph digraph) {
		
		sources = new ArrayList<Integer>();
		sinks = new ArrayList<Integer>();
		graphSources(digraph);
		graphSinks(digraph);
	}
	
	private void graphSources(Digraph digraph) {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			if(digraph.indegree(i) == 0) {
				sources.add(i);
			}
		}
	}
	
	private void graphSinks(Digraph digraph) {
		
		for(int i = 0; i < digraph.getNumV(); i++) {
			if(digraph.outdegree(i) == 0) {
				sinks.add(i);
			}
		}
	}
	
	public List<Integer> getSources() {
		return sources;
	}
	
	public List<Integer> getSinks() {
		return sinks;
	}
}
