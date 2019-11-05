package topics.dp.knapsack_unbouded;

public class UnboundedKnapsack {

  static class DPBottomUpSolution {
    public int getMaxProfit(int[] weights, int[] profits, int capacity) {
      // dp[i][j]： 前i种item，在capacity为j的条件下，最大的profit
      int[][] dp = new int[weights.length + 1][capacity + 1];

      // initialize first row and first column as 0
      // 递推方程：dp[i][j] = max(dp[i-1][j], dp[i][j - weights[i - 1]] + profits[i - 1])
      for (int row = 1; row <= weights.length; row++) {
        for (int col = 1; col <= capacity; col++) {
          int maxProfitWithoutCurr = dp[row - 1][col];
          int maxProfitWithCurr = 0;
          if (weights[row - 1] <= col) {
            maxProfitWithCurr = profits[row - 1] + dp[row][col - weights[row - 1]];
          }
          dp[row][col] = Math.max(maxProfitWithCurr, maxProfitWithoutCurr);
        }
      }

      return dp[weights.length][capacity];
    }
  }

  public static void main(String[] args) {
    testDPBottomUpSolution();
  }

  private static void testDPBottomUpSolution() {
    DPBottomUpSolution ks = new DPBottomUpSolution();
    int[] profits = {15, 50, 60, 90};
    int[] weights = {1, 3, 4, 5};
    System.out.println(ks.getMaxProfit(weights, profits, 8));
    System.out.println(ks.getMaxProfit(weights, profits, 6));
  }
}
