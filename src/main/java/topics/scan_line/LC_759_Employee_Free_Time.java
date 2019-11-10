package topics.scan_line;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import topics.scan_line.model.Interval;

public class LC_759_Employee_Free_Time {

  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> freetime = new LinkedList<>();
    List<Interval> timeLine = new LinkedList<>();
    schedule.forEach((intervalList) -> timeLine.addAll(intervalList));
    Collections.sort(timeLine, (one, other) -> one.start - other.start);

    Interval last = timeLine.get(0);
    for (Interval curr: timeLine) {
      if (curr.start > last.end) {
        freetime.add(new Interval(last.end, curr.start));
      }
      if (curr.end > last.end) {
        last = curr;
      }
    }

    return freetime;
  }
}
