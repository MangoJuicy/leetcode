package topics.dp.knapsack;

public class SubsetSum {

  // dp[row][col] = dp[row - 1][col]
  //                || ((num[row - 1] <= col) && dp[row - 1][col - num[row - 1]])

  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = {1, 2, 3, 7};
    System.out.println(ss.canGenerateSum(num, 6));
    num = new int[] {1, 2, 7, 1, 5};
    System.out.println(ss.canGenerateSum(num, 10));
    num = new int[] {1, 3, 4, 8};
    System.out.println(ss.canGenerateSum(num, 6));
  }

  public boolean canGenerateSum(int[] num, int sum) {
    boolean[][] dp = new boolean[2][sum + 1];

    // initialize first column as true, first row as false.
    dp[0][0] = true;
    dp[1][0] = true;

    for (int row = 1; row <= num.length; row++) {
      for (int col = 1; col <= sum; col++) {
        int currRow = row % 2;
        int lastRow = (row - 1) % 2;

        boolean canGenerateSumWithoutCurr = dp[lastRow][col];
        boolean canGenerateSumWithCurr = (num[row - 1] <= col) && dp[lastRow][col - num[row - 1]];
        dp[currRow][col] = canGenerateSumWithCurr || canGenerateSumWithoutCurr;
      }
    }

    return dp[num.length % 2][sum];
  }
}
