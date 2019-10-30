package topics.dp.knapsack.zero_one_basic_knapsack;

public class Knapsack_DP_SpaceOptimized {

  public int getMaxProfit(int[] weights, int[] profits, int capacity) {
    int[][] dp = new int[2][capacity + 1];

    for (int row = 1; row <= weights.length; row++) {
      for (int col = 1; col <= capacity; col++) {
        int currRow = row % 2;
        int lastRow = (row - 1) % 2;

        int profit0 = dp[lastRow][col];
        int profit1 = (weights[row - 1] <= col) ? (profits[row - 1] + dp[lastRow][col - weights[row - 1]]) : 0;
        dp[currRow][col] = Math.max(profit0, profit1);
      }
    }

    return dp[weights.length % 2][capacity];
  }


  // Educative Solution
  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    // basic checks
    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
      return 0;

    int n = profits.length;
    // we only need one previous row to find the optimal solution, overall we need '2' rows
    // the above solution is similar to the previous solution, the only difference is that
    // we use `i%2` instead if `i` and `(i-1)%2` instead if `i-1`
    int[][] dp = new int[2][capacity+1];

    // if we have only one weight, we will take it if it is not more than the capacity
    for(int c=0; c <= capacity; c++) {
      if(weights[0] <= c)
        dp[0][c] = dp[1][c] = profits[0];
    }

    // process all sub-arrays for all the capacities
    for(int i=1; i < n; i++) {
      for(int c=0; c <= capacity; c++) {
        int profit1= 0, profit2 = 0;
        // include the item, if it is not more than the capacity
        if(weights[i] <= c)
          profit1 = profits[i] + dp[(i-1)%2][c-weights[i]];
        // exclude the item
        profit2 = dp[(i-1)%2][c];
        // take maximum
        dp[i%2][c] = Math.max(profit1, profit2);
      }
    }

    return dp[(n-1)%2][capacity];
  }


  public static void main(String[] args) {
    Knapsack_DP_SpaceOptimized ks = new Knapsack_DP_SpaceOptimized();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.getMaxProfit(weights, profits, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.getMaxProfit(weights, profits, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}

