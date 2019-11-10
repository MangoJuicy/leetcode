package topics.scan_line;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class LC_57_insert_interval {

  public List<Interval> insertInterval(ArrayList<Interval> sortedIntervals, Interval interval) {
    List<Interval> res = new LinkedList<>();
    int index = 0;
    for (; index < sortedIntervals.size(); index++) {
      Interval curr = sortedIntervals.get(index);
      if (interval.start <= curr.end) {
        break;
      } else {
        res.add(curr);
      }
    }
    if (index < sortedIntervals.size()) {
      interval.start = Math.min(interval.start, sortedIntervals.get(index).start);
    }

    res.add(interval);

    for (; index < sortedIntervals.size(); index++) {
      if (sortedIntervals.get(index).start > interval.end) {
        break;
      }
    }
    if (index > 0) {
      interval.end = Math.max(interval.end, sortedIntervals.get(index - 1).end);
    }
    for (; index < sortedIntervals.size(); index++) {
      res.add(sortedIntervals.get(index));
    }

    return res;
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
