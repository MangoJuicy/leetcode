package topics.scan_line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 不是很懂，有时间看一下
// solution copied from: https://leetcode.com/problems/the-skyline-problem/discuss/61230/Java-Segment-Tree-Solution-47-ms

public class LC_218_Solution_With_SegmentTree {

  private static class Node{
    int start, end;
    Node left, right;
    int height;

    public Node(int start, int end){
      this.start = start;
      this.end = end;
    }
  }

  public List<int[]> getSkyline(int[][] buildings) {
    Set<Integer> endpointSet = new HashSet<Integer>();
    for(int[] building : buildings){
      endpointSet.add(building[0]);
      endpointSet.add(building[1]);
    }

    List<Integer> endpointList = new ArrayList<Integer>(endpointSet);
    Collections.sort(endpointList);

    HashMap<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();
    for(int i = 0; i < endpointList.size(); i++){
      endpointMap.put(endpointList.get(i), i);
    }

    Node root = buildNode(0, endpointList.size() - 1);
    for(int[] building : buildings){
      add(root, endpointMap.get(building[0]), endpointMap.get(building[1]), building[2]);
    }

    List<int[]> result = new ArrayList<int[]>();
    explore(result, endpointList, root);

    if(endpointList.size() > 0){
      result.add(new int[]{endpointList.get(endpointList.size() - 1), 0});
    }

    return result;
  }

  private Node buildNode(int start, int end){
    if(start > end){
      return null;
    }else{
      Node result = new Node(start, end);
      if(start + 1 < end){
        int center = start + (end - start) / 2;
        result.left = buildNode(start, center);
        result.right = buildNode(center, end);
      }

      return result;
    }
  }

  private void add(Node node, int start, int end, int height){
    if(node == null || start >= node.end || end <= node.start || height < node.height){
      return;
    }else{
      if(node.left == null && node.right == null){
        node.height = Math.max(node.height, height);
      }else{
        add(node.left, start, end, height);
        add(node.right, start, end, height);
        node.height = Math.min(node.left.height, node.right.height);
      }
    }
  }

  private void explore(List<int[]> result, List<Integer> endpointList, Node node){
    if(node == null){
      return;
    }else{
      if(node.left == null && node.right == null && (result.size() == 0 || result.get(result.size() - 1)[1] != node.height)){
        result.add(new int[]{endpointList.get(node.start), node.height});
      }else{
        explore(result, endpointList, node.left);
        explore(result, endpointList, node.right);
      }
    }
  }
}
