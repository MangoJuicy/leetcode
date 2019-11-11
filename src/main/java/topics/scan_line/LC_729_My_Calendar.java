package topics.scan_line;

import java.util.TreeMap;

public class LC_729_My_Calendar {

  // start as key, end as value
  private TreeMap<Integer, Integer> treeMap;

  public LC_729_My_Calendar() {
    treeMap = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    Integer preStart = treeMap.floorKey(start);
    Integer nextStart = treeMap.ceilingKey(start);
    if ((start > end)
        || (preStart != null && treeMap.get(preStart) > start)
        || (nextStart != null && nextStart < end)) {
      return false;
    }
    treeMap.put(start, end);
    return true;
  }
}
