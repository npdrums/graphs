package edgeweighted;

public class VerticesWithHeaviestAndLightestEdges {

	private int heaviestVertex;
	private int lightestVertex;
	private double minAccumWeight;
	private double maxAccumWeight;
	private double currWeight;
	
	public VerticesWithHeaviestAndLightestEdges(EdgeWeightedGraph ewgraph) {
		
		vertexWithHeaviestEdges(ewgraph);
		vertexWithLightestEdges(ewgraph);
	}
	
	private void vertexWithHeaviestEdges(EdgeWeightedGraph ewgraph) {
		
		maxAccumWeight = -1;
		for(int i = 0; i < ewgraph.getNumV(); i++) {
			currWeight = 0.0;
			for(Edge e : ewgraph.adj(i)) {
				currWeight += e.getWeight();
			}
			if(currWeight > maxAccumWeight) {
				maxAccumWeight = currWeight;
				heaviestVertex = i;
			}
		}
	}
	
	private void vertexWithLightestEdges(EdgeWeightedGraph ewgraph) {
		
		for(Edge e : ewgraph.adj(0)) {
			minAccumWeight += e.getWeight();
		}
		
		for(int i = 1; i < ewgraph.getNumV(); i++) {
			currWeight = 0.0;
			for(Edge e : ewgraph.adj(i)) {
				currWeight += e.getWeight();
			}
			if(currWeight < minAccumWeight) {
				minAccumWeight = currWeight;
				lightestVertex = i;
			}
		}
	}

	public int getHeaviestVertex() {
		return heaviestVertex;
	}

	public int getLightestVertex() {
		return lightestVertex;
	}

	public double getMinAccumWeight() {
		return minAccumWeight;
	}

	public double getMaxAccumWeight() {
		return maxAccumWeight;
	}
}
