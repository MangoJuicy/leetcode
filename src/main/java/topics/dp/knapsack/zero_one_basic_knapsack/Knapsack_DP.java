package topics.dp.knapsack.zero_one_basic_knapsack;

import java.util.LinkedList;
import java.util.List;

public class Knapsack_DP {

  /*
   dp 数据结构： new int[items.size + 1][capacity + 1]
   dp 递推公式：dp[i][c] = Math.max(dp[i - 1][c],
                                              (w[i - 1] <= c) ? (p[i - 1] + dp[i - 1][c - w[i - 1]]) : 0)
  */

  public static void main(String[] args) {
    Knapsack_DP ks = new Knapsack_DP();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.getMaxProfit(weights, profits, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.getMaxProfit(weights, profits, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }

  public int getMaxProfit(int[] weights, int[] profits, int capacity) {
    int[][] dp = new int[weights.length + 1][capacity + 1];

    for (int i = 1; i < dp.length; i++) {
      for (int c = 1; c <= capacity; c++) {
        int profit0 = dp[i - 1][c];
        int profit1 = (weights[i - 1] <= c) ? (profits[i - 1] + dp[i - 1][c - weights[i - 1]]) : 0;
        dp[i][c] = Math.max(profit0, profit1);
      }
    }

    // getPathIndices(weights, profits, dp);

    return dp[weights.length][capacity];
  }

  // 求取 max profit 路径
  private List<Integer> getPathIndices(int[] weights, int[] profits, int[][] dp) {
    LinkedList<Integer> indices = new LinkedList<>();
    int rows = dp.length;
    int cols = dp[0].length;

    for (int row = rows - 1, col = cols - 1; row > 0; row--) {
      if (dp[row][col] == dp[row - 1][col]) {
        continue;
      } else {
        indices.add(0, row - 1);
        col = col - weights[row - 1];
      }
    }

    System.out.println(indices);

    return indices;
  }
}
