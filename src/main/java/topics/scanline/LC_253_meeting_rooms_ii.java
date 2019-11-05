package topics.scanline;

/* https://leetcode.com/problems/meeting-rooms-ii/ */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LC_253_meeting_rooms_ii {

  public int minMeetingRooms(int[][] intervals) {
    Comparator<Point> comparator = (point1, point2) -> {
      if (point1.time != point2.time) {
        return point1.time - point2.time;
      }
      return point1.isStart ? 1 : -1;
    };

    ArrayList<Point> list = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      int[] pair = intervals[i];
      list.add(new Point(pair[0], true));
      list.add(new Point(pair[1], false));
    }
    Collections.sort(list, comparator);

    int currRoomsCount = 0;
    int maxRoomsCount = 0;
    for (Point point : list) {
      if (point.isStart) {
        currRoomsCount++;
        maxRoomsCount = Math.max(maxRoomsCount, currRoomsCount);
      } else {
        currRoomsCount--;
      }
    }

    return maxRoomsCount;
  }

  static class Point {
    int time;
    boolean isStart;

    public Point(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }
}
