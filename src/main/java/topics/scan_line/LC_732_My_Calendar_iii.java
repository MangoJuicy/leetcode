package topics.scan_line;

import java.util.TreeMap;

public class LC_732_My_Calendar_iii {

  // start or end as key, 1 or -1 as value accordingly
  private TreeMap<Integer, Integer> calendar;

  public LC_732_My_Calendar_iii() {
    calendar = new TreeMap<>();
  }

  public int book(int start, int end) {
    calendar.put(start, calendar.getOrDefault(start, 0) + 1);
    calendar.put(end, calendar.getOrDefault(end, 0) - 1);

    int ongoing = 0;
    int maxOngoing = 0;
    for (Integer count: calendar.values()) {
      ongoing += count;
      maxOngoing = Math.max(maxOngoing, ongoing);
    }

    return maxOngoing;
  }
}
