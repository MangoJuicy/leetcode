package topics.scan_line;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LC_986_Interval_List_Intersections {

  public List<Interval> intervalIntersection(List<Interval> A, List<Interval> B) {
    Comparator<Point> comparator =
        (one, other) -> {
          if (one.val != other.val) {
            return one.val - other.val;
          }
          return other.flag - one.flag; // start 在前
        };

    List<Point> pointList = new LinkedList<>();
    for (Interval pair : A) {
      pointList.add(new Point(pair.start, 1, Point.Group.A));
      pointList.add(new Point(pair.end, -1, Point.Group.A));
    }
    for (Interval pair : B) {
      pointList.add(new Point(pair.start, 1, Point.Group.B));
      pointList.add(new Point(pair.end, -1, Point.Group.B));
    }
    Collections.sort(pointList, comparator);

    boolean onGoingA = false;
    boolean onGoingB = false;
    int start = 0;
    List<Interval> res = new LinkedList<>();
    for (Point p : pointList) {
      if (p.group == Point.Group.A) {
        onGoingA = (p.flag == 1);
      } else {
        onGoingB = (p.flag == 1);
      }
      if (onGoingA && onGoingB) {
        start = p.val;
      } else if ((p.flag == -1) && (onGoingA || onGoingB)) {
        // bug was caught here: needs to confirm p is an end point
        res.add(new Interval(start, p.val));
      }
    }

    return res;
  }

  private static class Point {
    int val;
    int flag; // 1 for start and -1 for end
    Group group;

    public Point(int val, int flag, Group group) {
      this.val = val;
      this.flag = flag;
      this.group = group;
    }

    public enum Group {
      A,
      B
    }
  }

  private static class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
