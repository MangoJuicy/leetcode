package topics.scan_line;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// 这套题目，point排序比较复杂，position相同，要考虑4个case，排序方式都不一样
public class LC_218_The_Skyline_Problem {

  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<Point> points = new LinkedList<>();
    for (int[] building: buildings) {
      points.add(new Point(building[0], building[2], true));
      points.add(new Point(building[1], building[2], false));
    }
    Collections.sort(points, (one, other) -> {
      if (one.position != other.position) {
        return one.position - other.position;
      }
      if (one.isStart && other.isStart) {
        return other.height - one.height;   // 两起点，高的排在前，低的不会产生output点
      } else if (!one.isStart && !other.isStart) {
        return one.height - other.height;   // 两终点，低的排在前，低的不会产生output点
      } else {
        return one.isStart ? -1 : 1;         // 一起点一终点，start 排在前，两种情况画图看一下
        // 起点高，终点低：先将起点高度add到heap中，产生新的output -> 终点poll不产生新的output
        // 起点低，终点高：先将起点高度add到heap中，不产生新的output -> 终点poll会产生新的output
      }
    });

    PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
    maxHeap.add(0);
    List<List<Integer>> res = new LinkedList<>();
    for (Point p: points) {
      if (p.isStart) {
        if (p.height > maxHeap.peek()) {
          res.add(Arrays.asList(p.position, p.height));
        }
        maxHeap.add(p.height);
      } else {
        maxHeap.remove(p.height);
        if (p.height > maxHeap.peek()) {
          res.add(Arrays.asList(p.position, maxHeap.peek()));
        }
      }
    }
    return res;
  }

  private static class Point {
    int position;
    int height;
    boolean isStart;

    public Point(int position, int height, boolean isStart) {
      this.position = position;
      this.height = height;
      this.isStart = isStart;
    }
  }
}
