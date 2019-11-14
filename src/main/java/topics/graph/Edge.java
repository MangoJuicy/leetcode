package topics.graph;


public class Edge {
  public int weight;
  public GraphNode fromNode;
  public GraphNode toNode;

  public Edge(int weight, GraphNode fromNode, GraphNode toNode) {
    this.weight = weight;
    this.fromNode = fromNode;
    this.toNode = toNode;
  }
}
