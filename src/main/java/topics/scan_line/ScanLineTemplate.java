package topics.scan_line;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ScanLineTemplate {

  public void generalMethod() {
    Comparator<Point> comparator = (point1, point2) -> {
      if (point1.val != point2.val) {
        return point1.val - point2.val;
      }

      // case to case ordering
      // meeting rooms, endPoint 在前
      // merge intervals, startPoint 在前
      return point1.isStart ? 1 : -1;
    };

    List<Point> points = new LinkedList<>();
    Collections.sort(points, comparator);
  }

  static class Point {
    int val;
    boolean isStart;  // flag

    public Point(int val, boolean isStart) {
      this.val = val;
      this.isStart = isStart;
    }
  }
}
