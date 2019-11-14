package topics.datas_tructure.algorithm;

import java.util.PriorityQueue;
import topics.graph.GraphNode;

public class DijkstrasAlgorithm {

  public int shortestPath(GraphNode source, GraphNode target) {
    PriorityQueue<GraphNode> minHeap = new PriorityQueue<>((one, other) -> one.value - other.value);

    GraphNode runner = source;
    while (runner != target) {
      // TODO: implement here
    }

    return 0;
  }
}
