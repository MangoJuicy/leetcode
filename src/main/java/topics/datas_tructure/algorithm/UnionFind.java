package topics.datas_tructure.algorithm;


import java.util.HashMap;
import java.util.Map;

/* refered to https://github.com/MangoJuicy/mission-peace-interview/blob/master/src/com/interview/graph/DisjointSet.java */

public class UnionFind {

  // always maps as { node.data: node }
  private Map<Integer, Node> map = new HashMap<>();

  /**
   * Create a Group/Union with only one item
   * @param data
   */
  public void makeGroup(int data) {
    Node node = new Node(data, 0);
    map.put(data, node);
  }

  public int findGroup(int data) {
    Node groupNode = findGroup(map.get(data));
    return groupNode.data;
  }

  /**
   * Find the group of given node, also compress path
   * @param node
   * @return
   */
  public Node findGroup(Node node) {
    if (node == node.parent) {
      return node;
    }
    Node groupNode = findGroup(node.parent);
    node.parent = groupNode;    // 递归，将path上的node的parent全部更新为groupNode， 路径压缩
    return groupNode;
  }

  public boolean union(int one, int other) {
    return union(map.get(one), map.get(other));
  }

  /**
   * @param one
   * @param other
   * @return true when union happens, otherwise false
   */
  public boolean union(Node one, Node other) {
    Node oneGroup = findGroup(one);
    Node otherGroup = findGroup(other);

    if (oneGroup == otherGroup) {
      return false;   // no union happens
    }

    if (oneGroup.rank >= otherGroup.rank) {
      otherGroup.parent = oneGroup;
      if (oneGroup.rank == otherGroup.rank) {
        oneGroup.rank += 1;
      }
    } else {
      oneGroup.parent = otherGroup;
    }
    return true;
  }

  class Node {
    int data;
    int rank;
    Node parent;

    public Node(int data, int rank) {
      this.data = data;
      this.rank = rank;
      this.parent = this;   // node's parent is itself when initializing
    }
  }
}
