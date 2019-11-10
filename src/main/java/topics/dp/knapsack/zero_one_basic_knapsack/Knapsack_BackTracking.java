package topics.dp.knapsack.zero_one_basic_knapsack;

import java.util.LinkedList;
import java.util.List;

// 求取所有可能组合使用 BackTracking
public class Knapsack_BackTracking {

  public static int[] weights = {2, 3, 1, 4};
  public static int[] profits = {4, 5, 3, 7};

  /**
   * get max profits with given capacity. capacity >= 0;
   *
   * @param capacity
   * @return
   */
  public static int getMaxProfitWithCapacity(int capacity) {
    List<Integer> res = new LinkedList<Integer>();
    res.add(0);
    backtracking(capacity, 0, 0, res, new LinkedList<Integer>());

    return res.get(0);
  }

  private static void backtracking(
      int capacity, int index, int profitSoFar, List<Integer> res, LinkedList<Integer> indices) {
    if (capacity == 0 || index == weights.length) {
      res.set(0, Math.max(res.get(0), profitSoFar));
      return;
    }

    res.set(0, Math.max(res.get(0), profitSoFar));

    for (int i = index; i < weights.length; i++) {
      if (weights[i] <= capacity) {
        indices.add(i);
        backtracking(capacity - weights[i], i + 1, profitSoFar + profits[i], res, indices);
        indices.removeLast();
      }
    }
  }

  public static void main(String[] args) {
    int maxProfit = getMaxProfitWithCapacity(5);
    System.out.println(maxProfit);
  }
}
