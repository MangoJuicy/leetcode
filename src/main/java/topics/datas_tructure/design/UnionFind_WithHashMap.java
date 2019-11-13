package topics.datas_tructure.design;

import java.util.HashMap;
import java.util.Map;

public class UnionFind_WithHashMap {

  // node mapping to parent node
  Map<Integer, Integer> parentMap = new HashMap<>();

  // find root parent
  public int find(int target) {
    while (target != parentMap.get(target)) {
      target = parentMap.get(target);
    }
    return target;
  }

  // union two groups, no compression
  public void union(int one, int other) {
    int oneGroup = find(one);
    int otherGroup = find(other);
    if (oneGroup != otherGroup) {
      parentMap.put(oneGroup, otherGroup);
    }
  }
}
