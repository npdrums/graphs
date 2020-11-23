package edgeweighted;

public class Edge implements Comparable<Edge>{

	private int v, w;
	private double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int vertex) {
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new IllegalArgumentException("Unknown vertex");
	}  
	
	public int compareTo(Edge that) {
		if(this.weight < that.weight) {
			return -1;
		} else if(this.weight > that.weight) {
			return 1;
		} else {
			return 0;
		} 
	}

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
