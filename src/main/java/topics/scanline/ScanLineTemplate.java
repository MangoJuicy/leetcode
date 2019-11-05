package topics.scanline;

import java.util.Comparator;

public class ScanLineTemplate {

  Comparator<Point> comparator = (point1, point2) -> {
    if (point1.val != point2.val) {
      return point1.val - point2.val;
    }

    return point1.isStart ? 1 : -1; // end Point is less than start Point when vals are equal
  };

  static class Point {
    int val;
    boolean isStart;  // flag

    public Point(int val, boolean isStart) {
      this.val = val;
      this.isStart = isStart;
    }
  }
}
