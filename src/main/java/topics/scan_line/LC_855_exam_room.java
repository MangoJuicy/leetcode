package topics.scan_line;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC_855_exam_room {

  private int seatsNum;
  private PriorityQueue<Interval> maxHeap = null;

  public LC_855_exam_room(int N) {
    seatsNum = N;
    Comparator<Interval> comparator = (one, other) -> {
      int oneMaxDistance = getMaxDistanceOfInterval(one);
      int otherMaxDistance = getMaxDistanceOfInterval(other);
      if (oneMaxDistance != otherMaxDistance) {
        return otherMaxDistance - oneMaxDistance;
      }
      return one.start - other.start;
    };
    maxHeap = new PriorityQueue<>(comparator);
    maxHeap.add(new Interval(-1, N));
  }

  public int seat() {

    Interval interval = maxHeap.poll();
    int targetSeat;
    if (interval.start == -1) {
      targetSeat = 0;
    } else if (interval.end == seatsNum) {
      targetSeat = seatsNum - 1;
    } else {
      targetSeat = interval.start + (interval.end - interval.start) / 2;
    }

    maxHeap.add(new Interval(interval.start, targetSeat));
    maxHeap.add(new Interval(targetSeat, interval.end));

    return targetSeat;
  }

  public void leave(int p) {
    Interval first = null;
    Interval second = null;
    for (Interval interval: maxHeap) {
      if (interval.end == p) {
        first = interval;
      } else if (interval.start == p) {
        second = interval;
      }
    }
    maxHeap.remove(first);
    maxHeap.remove(second);
    maxHeap.add(new Interval(first.start, second.end));
  }

  private int getMaxDistanceOfInterval(Interval interval) {
    if (interval.start == -1 || interval.end == seatsNum) {
      return interval.end - interval.start - 1;
    }
    return (interval.end - interval.start) / 2;
  }

  private static class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static void main(String[] args) {
    LC_855_exam_room examRoom = new LC_855_exam_room(10);
    System.out.println(examRoom.seat());
    System.out.println(examRoom.seat());
    System.out.println(examRoom.seat());
    System.out.println(examRoom.seat());
    examRoom.leave(4);
    System.out.println(examRoom.seat());
  }
}