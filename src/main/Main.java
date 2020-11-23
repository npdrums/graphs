package main;

import undigraph.*;

import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Set;

import digraph.*;
import edgeweighted.DeepDive;
import edgeweighted.Dijkstra;
import edgeweighted.EdgeWeightedGraph;
import edgeweighted.PrimMSTLazy;
import edgeweighted.VerticesWithHeaviestAndLightestEdges;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		
		// UNDIRECTED GRAPHS
		Graph graph = new Graph("resources/tinyG.txt");
		
		// DFS
		UndigraphDFS dfs = new UndigraphDFS(graph);
		
		// BFS
		UndigrahBFS bfs = new UndigrahBFS(graph);
		
		// CYCLE DETECTION
		UndigraphCycle cycle = new UndigraphCycle(graph);

		if(cycle.hasCycle()) {
			System.out.println("The Graph has a cycle! ");
			for(int v : cycle.getCycle()) {
				System.out.println(v);
			}
		} else {
			System.out.println("No cycles were detected in this Graph!");
		}
		
		// GRAPH COMPONENTS
		UndigraphComponents components = new UndigraphComponents(graph);
		
		System.out.println("Number of Graph components: " + components.getNumOfComponents());
				

		int i =  components.getNumOfComponents();
		for(Set<Integer> component : components.getComponents()) {
			System.out.println("Component " + i + " has following vertices: ");
			for(int v : component) {
				System.out.println(v);
			}
			i--;
		}
	
		// BIPARTITE
		UndigraphBipartite bipartite = new UndigraphBipartite(graph);
		if(bipartite.isBipartite()) {
			System.out.println("The Graph is bipartite!");
		} else {
			System.out.println("The Graph is NOT bipartite!");
		}
		
		// PATHS
		UndigraphPaths paths = new UndigraphPaths(graph, 0);
		System.out.println("Path between 0 and 4: " + paths.hasPath(0, 4));
		System.out.println(paths.getPath(0, 4));
		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
		// DIRECTED GRAPHS - DIGRAPHS
		Digraph digraph = new Digraph("resources/tinyDG.txt");
		
		// DFS from vertex 0
		DigraphDFS dDfs = new DigraphDFS(digraph, 0);
		
		// BFS from vertex 0
		DigraphBFS dBfs = new DigraphBFS(digraph, 0);
		
		// CYCLES
		DigraphCycle dCycle = new DigraphCycle(digraph);
		
		if(dCycle.hasCycle()) {
			System.out.println("The Diraph has a cycle! ");
			for(int v : dCycle.getCycle()) {
				System.out.println(v);
			}
		} else {
			System.out.println("No cycles were detected in this Diraph!");
		}
		
		// DIGRAPH COMPONENTS
		DigraphComponents dComponents = new DigraphComponents(digraph);
						
		System.out.println("Number of Digraph components: " + dComponents.getDigraphComponents().size());
		
		int j = 1;
		for(Set<Integer> component : dComponents.getDigraphComponents()) {
			Iterator<Integer> iterator = component.iterator();
			System.out.println("Component " + j + " has following vertices: ");
			while(iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			j++;
		}
		
		// TOPOLOGICAL SORT
		TopologicalSort topSort = new TopologicalSort(digraph, dCycle);
		
		if(!dCycle.hasCycle()) {
			System.out.println("Topological Sort: ");
			for(int v : topSort.reversePostorder()) {
				System.out.println(v);
			}
		} else {
			System.out.println("Digraph has a cycle! Topological Sort not possible!");
		}
		
		// SOURCES AND SINKS
		SourcesAndSinks sourcesAndSinks = new SourcesAndSinks(digraph);
		
		System.out.println("Sources:");
		for(Integer v : sourcesAndSinks.getSources()) {
			System.out.println(v);
		}		
		
		System.out.println("Sinks:");
		for(Integer v : sourcesAndSinks.getSinks()) {
			System.out.println(v);
		}
		
		// TRANSITIVE CLOSURE
		System.out.println("Transitive Closure: ");
		TransitiveClosure transitiveClosure = new TransitiveClosure(digraph);
		transitiveClosure.getTransitiveClosure().print();
		
		// VERTEX REACH
		System.out.println("Vertices that can reach to every other vertex: ");
		ReachableVertex reachableVertex = new ReachableVertex(transitiveClosure);
		for(Integer vertex : reachableVertex.getWithReachToEveryone()) {
			System.out.println(vertex);
		}
		
		System.out.println("Vertices that can be reached from every other vertex: ");
		for(Integer vertex : reachableVertex.getWithReachFromEveryone()) {
			System.out.println(vertex);
		}
		
		// SPANNING TREE - NOT MST
		System.out.println("Spanning tree: ");
		SpanningTree spanningTree = new SpanningTree(digraph);
		spanningTree.getSpanningTree().print();

		System.out.println("Spanning tree (optimized): ");
		SpanningTreeOpt spanningTreeOpt = new SpanningTreeOpt(reachableVertex, digraph);
		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
		// EDGE WEIGHTED GRAPH
		EdgeWeightedGraph ewgraph = new EdgeWeightedGraph("resources/neusm-t1.txt");
		ewgraph.print();
		
		// VERTICES WITH THE HEAVIEST AND THE LIGHTEST EDGES
		VerticesWithHeaviestAndLightestEdges heaviestAndLightestEdges = new VerticesWithHeaviestAndLightestEdges(ewgraph);
		System.out.println("The Heaviest: " + heaviestAndLightestEdges.getHeaviestVertex());
		System.out.println("Weight: " + heaviestAndLightestEdges.getMaxAccumWeight());
		System.out.println("The Lightest: " + heaviestAndLightestEdges.getLightestVertex());
		System.out.println("Weight: " + heaviestAndLightestEdges.getMinAccumWeight());
		
		// SHORTEST PATH
		Dijkstra dijkstra = new Dijkstra(ewgraph, 0);
		System.out.println(dijkstra.hasPathTo(7));
		
		// TODO: Iterate through list
		dijkstra.pathToAll();
		
		// DEEP DIVE
		DeepDive deepDive = new DeepDive(ewgraph, 7, 9, 4);
		System.out.println(deepDive.canDeepDive());		
		System.out.println(deepDive.canDeepDiveWithBreak());
		
		// PRIMS'S ALGORITHM FOR MST
		PrimMSTLazy prim = new PrimMSTLazy(ewgraph, 0);
		// TODO: Iterate
		prim.getMST();
		prim.getMSTweight();
	}
}
