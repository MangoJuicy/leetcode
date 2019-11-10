package topics.dp.knapsack;

import utils.Utils;

public class TargetSum {

  public static void main(String[] args) {
    TargetSum ts = new TargetSum();
    int[] num = {1, 1, 2, 3};
    System.out.println(ts.getNumOfWaysForTargetSum(num, 1));
    num = new int[] {1, 2, 7, 1};
    System.out.println(ts.getNumOfWaysForTargetSum(num, 9));
  }

  public int getNumOfWaysForTargetSum(int[] num, int target) {
    int sum = Utils.calculateSum(num);
    if ((sum + target) % 2 != 0 || sum < target) {
      return 0;
    }
    int subsetSum = (sum + target) / 2;
    subsetSum = Math.min(subsetSum, sum - subsetSum);

    int[][] dp = new int[2][subsetSum + 1];

    // initialize first column as 1
    dp[0][0] = 1;
    dp[1][0] = 1;

    for (int row = 1; row <= num.length; row++) {
      for (int col = 1; col <= subsetSum; col++) {
        int currRow = row % 2;
        int lastRow = (row - 1) % 2;

        dp[currRow][col] = dp[lastRow][col];
        if (num[row - 1] <= col) {
          dp[currRow][col] += dp[lastRow][col - num[row - 1]];
        }
      }
    }

    return dp[num.length % 2][subsetSum];
  }
}
