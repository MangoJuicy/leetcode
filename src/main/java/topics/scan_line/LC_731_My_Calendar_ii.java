package topics.scan_line;

import java.util.TreeMap;

public class LC_731_My_Calendar_ii {

  // TreeMap 方便每次增量的更新，添加新的值 O(logN)时间复杂度
  // List 存每个节点，再sort，O(NlogN)时间复杂度

  // 这个题目，继续引进segmentTree，ongoing计算方法会降到 O(logN)时间复杂度？

  // start or end as key, 1 as start's value and -1 as end's value
  // easy to calculate ongoing
  private TreeMap<Integer, Integer> treeMap;

  public LC_731_My_Calendar_ii() {
    treeMap = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    // start 和 end 相同直接合并，相当于先 end 后 start，同Meeting room 相同
    // merge inteval 中需要 先start后end，需要定义Point区分start 和 end
    treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);
    treeMap.put(end, treeMap.getOrDefault(end, 0) - 1);
    int ongoing = 0;
    for (int count : treeMap.values()) {
      ongoing += count;
      if (ongoing >= 3) {
        treeMap.put(start, treeMap.get(start) - 1);
        treeMap.put(end, treeMap.get(end) + 1);
        return false;
      }
    }
    return true;
  }
}
