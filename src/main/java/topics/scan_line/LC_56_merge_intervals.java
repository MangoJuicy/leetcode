package topics.scan_line;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LC_56_merge_intervals {

  public List<Interval> mergeIntervals(List<Interval> intervals) {
    List<Interval> resList = new LinkedList<>();

    List<Point> pointList = new LinkedList<>();
    for (Interval interval : intervals) {
      pointList.add(new Point(interval.start, 1));
      pointList.add(new Point(interval.end, -1));
    }

    // Attention here:
    // val相同，startPoint应该排在endPoint前面，因为这样的两个interval是应该merge的。ongoing 先加后减，保证了merge的发生
    // 这里与 meeting room ii 是刚好相反的。meeting room ii 中，在val相同时，endPoint 排在 startPoint 前，以保证可以释放房间
    Comparator<Point> comparator =
        (p1, p2) -> (p1.val == p2.val) ? (p2.flag - p1.flag) : (p1.val - p2.val);
    Collections.sort(pointList, comparator);

    int start = 0;
    int ongoing = 0;
    for (Point p : pointList) {
      if (p.flag == 1) { // start point
        if (ongoing == 0) {
          start = p.val;
        }
        ongoing++;
      } else { // end point
        ongoing--;
        if (ongoing == 0) {
          resList.add(new Interval(start, p.val));
        }
      }
    }

    return resList;
  }

  private static class Point {
    int val;
    int flag; // start as 1, end as -1, so we can count the ongoing intervals

    public Point(int val, int flag) {
      this.val = val;
      this.flag = flag;
    }
  }
}
