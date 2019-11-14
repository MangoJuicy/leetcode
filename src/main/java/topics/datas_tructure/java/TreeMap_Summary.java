package topics.datas_tructure.java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import topics.scan_line.Interval;

// ordered, self-balanced tree
public class TreeMap_Summary {

  public void commonUse() {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    insertIntervals(treeMap);

    int target = 7;
    Integer preStart = treeMap.floorKey(target);     // could be null
    Integer nextStart = treeMap.ceilingKey(target);  // could be null

    Map.Entry<Integer, Integer> preEntry = treeMap.floorEntry(target);
    Map.Entry<Integer, Integer> nextEntry = treeMap.ceilingEntry(target);
  }

  private void insertIntervals(TreeMap<Integer, Integer> treeMap) {
    List<Interval> intervals = new LinkedList<>(Arrays.asList(
        new Interval(2, 5),
        new Interval(3, 8),
        new Interval(9, 13)
    ));
    intervals.forEach((interval) -> {
      treeMap.put(interval.start, interval.end);
    });
  }
}
