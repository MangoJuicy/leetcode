package topics.scanline;


import java.util.Arrays;

/* https://leetcode.com/problems/meeting-rooms */
public class LC_252_meeting_rooms {

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (int[] p1, int[] p2) -> {
      if (p1[0] == p2[0]) {
        return p1[1] - p2[1];
      }
      return p1[0] - p2[0];
    });

    for (int i = 1; i < intervals.length; i++) {
      int[] curr = intervals[i];
      int[] last = intervals[i - 1];
      if (curr[0] < last[1]) {
        return false;
      }
    }

    return true;
  }
}
