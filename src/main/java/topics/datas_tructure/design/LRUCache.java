package topics.datas_tructure.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  private int capacity;
  private Map<Integer, Node> map;
  private Node head;
  private Node tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>();
    head = new Node(0, 0);
    tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }

    Node node = map.get(key);
    removeNodeFromList(node);
    addNodeToTail(node);

    return node.val;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.val = value;
      removeNodeFromList(node);
      addNodeToTail(node);
    } else {
      Node node = new Node(key, value);
      if (map.size() == capacity) {
        Node nodeToEvict = removeNodeFromList(head.next);
        map.remove(nodeToEvict.key);
      }
      addNodeToTail(node);
      map.put(key, node);
    }
  }

  private Node removeNodeFromList(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;

    return node;
  }

  private void addNodeToTail(Node node) {
    tail.prev.next = node;
    node.prev = tail.prev;
    node.next = tail;
    tail.prev = node;
  }

  private static class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
}
