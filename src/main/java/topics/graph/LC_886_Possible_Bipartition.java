package topics.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LC_886_Possible_Bipartition {

  public boolean possibleBipartition(int N, int[][] dislikes) {

    int[] group = new int[N + 1];   // 1, -1 as different groups, 0 as not assigned group yet

    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] pair: dislikes) {
      if (!map.containsKey(pair[0])) {
        map.put(pair[0], new LinkedList<Integer>());
      }
      if (!map.containsKey(pair[1])) {
        map.put(pair[1], new LinkedList<Integer>());
      }
      map.get(pair[0]).add(pair[1]);
      map.get(pair[1]).add(pair[0]);
    }

    for (int i = 1; i <= N; i++) {
      if (!map.containsKey(i) || group[i] != 0) {
        continue;
      }
      if (!dfs(i, 1, group, map)) {
        return false;
      }
    }
    return true;
  }

  private boolean dfs(int index, int expectedValue, int[] group, Map<Integer, List<Integer>> map) {
    if (group[index] != 0) {
      return group[index] == expectedValue;
    }

    group[index] = expectedValue;
    for (int nextIndex: map.get(index)) {
      if (!dfs(nextIndex, -expectedValue, group, map)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    LC_886_Possible_Bipartition solution = new LC_886_Possible_Bipartition();

    int N = 3;
    List<List<Integer>> list = Arrays.asList(
        Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3)
    );
    int[][] dislikes = new int[list.size()][2];
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < 2; j++) {
        dislikes[i][j] = list.get(i).get(j);
      }
    }

    solution.possibleBipartition(N, dislikes);
  }
}
