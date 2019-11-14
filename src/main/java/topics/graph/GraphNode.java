package topics.graph;

import java.util.LinkedList;
import java.util.List;

public class GraphNode {
  public int value;
  public List<Edge> neighbors;

  public GraphNode(int value) {
    this.value = value;
    this.neighbors = new LinkedList<>();
  }
}
