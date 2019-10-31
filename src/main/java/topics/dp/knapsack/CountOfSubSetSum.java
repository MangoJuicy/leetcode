package topics.dp.knapsack;

public class CountOfSubSetSum {

  public int getCountOfSubSetSum(int[] num, int sum) {
    int[][] dp = new int[2][sum + 1];
    // dp[row][col]: 前 row 个 item 得到 sum 为 col 的题解个数

    dp[0][0] = 1;
    dp[1][0] = 1; // bug was caught here, needs to initialize the first column as 1
                  // 下面的计算中，不会对 first column 进行写操作，需要初始化

    for (int row = 1; row <= num.length; row++) {
      for (int col = 1; col <= sum; col++) {
        int currRow = row % 2;
        int lastRow = (row - 1) % 2;

        dp[currRow][col] = dp[lastRow][col];
        if (num[row - 1] <= col) {
          dp[currRow][col] += dp[lastRow][col - num[row - 1]];
        }
      }
    }

    return dp[num.length % 2][sum];
  }

  public static void main(String[] args) {
    CountOfSubSetSum ss = new CountOfSubSetSum();
    int[] num = {1, 1, 2, 3};
    System.out.println(ss.getCountOfSubSetSum(num, 4));
    num = new int[]{1, 2, 7, 1, 5};
    System.out.println(ss.getCountOfSubSetSum(num, 9));
  }
}
