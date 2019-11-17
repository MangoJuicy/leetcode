package topics.two_pointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class LC_1182_Shortest_Distance_to_Target_Color {

  /******* Solution 2: Binary Search ***** */
  public List<Integer> shortestDistanceColor2(int[] colors, int[][] queries) {
    List<Integer> res = new LinkedList<>();

    // color mapping to ordered indices of color
    // 因为 index 是递增的，使用 ArrayList<> 就可以，无需使用 TreeSet<>
    Map<Integer, TreeSet<Integer>> map = new HashMap<>();

    int i = 0;
    for (int color: colors) {
      if (!map.containsKey(color)) {
        map.put(color, new TreeSet<>());
      }
      map.get(color).add(i);
      i++;
    }

    for (int[] query: queries) {
      int index = query[0];
      int color = query[1];

      if (!map.containsKey(color)) {
        res.add(-1);
        continue;
      }

      Integer floorIndex = map.get(color).floor(index);
      Integer ceilingIndex = map.get(color).ceiling(index);

      if (floorIndex == null && ceilingIndex == null) {
        res.add(-1);
      } else if (floorIndex == null || ceilingIndex == null) {
        int closestIndex = (floorIndex != null) ? floorIndex : ceilingIndex;
        res.add(Math.abs(closestIndex - index));
      } else {
        int shortestPath = Math.min(index - floorIndex, ceilingIndex - index);
        res.add(shortestPath);
      }
    }

    return res;
  }

    /******* Solution 1: two pointers or Scan from two ends  ******/

  private static final int COLOR_COUNT = 3;

  public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
    List<Integer> res = new LinkedList<>();

    int len = colors.length;
    Distance[] leftToRight = new Distance[len];
    Distance[] rightToLeft = new Distance[len];

    leftToRight[0] = new Distance(colors[0]);
    rightToLeft[len - 1] = new Distance(colors[len -1]);

    for (int i = 1; i < len; i++) {
      Distance last = leftToRight[i - 1];
      leftToRight[i] = new Distance(colors[i]);
      updateDistanceFromLast(leftToRight[i], last);
    }

    for (int i = len - 2; i >= 0; i--) {
      Distance last = rightToLeft[i + 1];
      rightToLeft[i] = new Distance(colors[i]);
      updateDistanceFromLast(rightToLeft[i], last);
    }

    for (int[] query: queries) {
      int index = query[0];
      int color = query[1];
      int dist = findShortestPath(leftToRight[index], rightToLeft[index], color);
      res.add(dist);
    }

    return res;
  }

  private void updateDistanceFromLast(Distance curr, Distance last) {
    for (int i = 0; i < COLOR_COUNT; i++) {
      if (curr.distances[i] == -1 && last.distances[i] >= 0) {
        curr.distances[i] = last.distances[i] + 1;
      }
    }
  }

  private int findShortestPath(Distance leftDist, Distance rightDist, int color) {

    int shortestDistLeft = leftDist.distances[color - 1];
    int shortestDistRight = rightDist.distances[color - 1];

    if (shortestDistLeft == -1 && shortestDistRight == -1) {
      return -1;
    } else if (shortestDistLeft == -1 || shortestDistRight == -1) {
      return Math.max(shortestDistLeft, shortestDistRight);
    } else {
      return Math.min(shortestDistLeft, shortestDistRight);
    }
  }

  private static class Distance {
//    int oneDist;
//    int twoDist;
//    int threeDist;
    int[] distances;

    public Distance(int color, int colorCount) {
      distances = new int[colorCount];
      Arrays.fill(distances, -1);
      distances[color - 1] = 0;
    }

    public Distance(int color) {
      this(color, COLOR_COUNT);
    }
  }
}
