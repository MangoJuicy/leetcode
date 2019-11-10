package topics.dp.knapsack;

import utils.Utils;

public class MinimumSubsetSumDifference {

  public static void main(String[] args) {
    MinimumSubsetSumDifference ps = new MinimumSubsetSumDifference();
    int[] num = {1, 2, 3, 9};
    System.out.println(ps.getMinimumSubsetSumDifference(num));
    num = new int[] {1, 2, 7, 1, 5};
    System.out.println(ps.getMinimumSubsetSumDifference(num));
    num = new int[] {1, 3, 100, 4};
    System.out.println(ps.getMinimumSubsetSumDifference(num));
  }

  public int getMinimumSubsetSumDifference(int[] num) {
    int minimumDiff = Integer.MAX_VALUE;
    int sum = Utils.calculateSum(num);
    int targetSum = sum / 2;

    boolean[][] dp = new boolean[2][targetSum + 1];
    // dp[row][col]: 前 row 个 item 是否可以计算出 sum == col

    // initialize first column as true
    dp[0][0] = true;
    dp[1][0] = true;

    for (int row = 1; row <= num.length; row++) {
      for (int col = 1; col <= targetSum; col++) {
        int currRow = row % 2;
        int lastRow = (row - 1) % 2;

        if (dp[lastRow][col]) {
          dp[currRow][col] = true;
        } else if (num[row - 1] <= col) {
          dp[currRow][col] = dp[lastRow][col - num[row - 1]];
        }

        if (dp[currRow][col]) {
          minimumDiff = Math.min(minimumDiff, Math.abs(sum - col - col));
          if (minimumDiff == 0) {
            return minimumDiff;
          }
        }
      }
    }

    return minimumDiff;
  }
}
