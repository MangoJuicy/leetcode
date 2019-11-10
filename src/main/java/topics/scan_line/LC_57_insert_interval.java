package topics.scan_line;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import topics.scan_line.model.Interval;

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
}
