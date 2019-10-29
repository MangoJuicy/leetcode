package topics.dp.knapsack.zero_one_basic_knapsack;

public class Knapsack_Memorization {
  public int getMaxProfit(int[] weights, int[] profits, int capacity) {
    Integer[][] dp = new Integer[weights.length][capacity + 1];
    return recursiveWithMemorization(capacity, 0, weights, profits, dp);
  }

  private int recursiveWithMemorization(int capacity, int index, int[] weights, int[] profits, Integer[][] dp) {
    if (capacity == 0 || index == weights.length) {
      return 0;
    }

    if (dp[index][capacity] != null) {
      return dp[index][capacity];
    }

    // 0 case
    int profit0 = recursiveWithMemorization(capacity, index + 1, weights, profits, dp);

    // 1 case
    int profit1 = 0;
    if (weights[index] <= capacity) {
      profit1 = profits[index] + recursiveWithMemorization(capacity - weights[index], index + 1, weights, profits, dp);
    }

    dp[index][capacity] = Math.max(profit0, profit1);
    return dp[index][capacity];
  }
}
