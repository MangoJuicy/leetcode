package topics.dp.knapsack;

public class EqualSubsetSumPartition {

  /* ================  DP Bottom Up With Space Optimized ================== */
  public static class DPBottomUpWithSpaceOptimized {

    public boolean canPartition(int[] num) {
      int sum = calculateSum(num);
      if (sum % 2 != 0) {
        return false;
      }
      int targetSum = sum / 2;
      boolean[][] dp = new boolean[2][targetSum + 1];

      // initialize first column as true and first row as false
      dp[0][0] = true;

      for (int row = 1; row <= num.length; row++) {
        for (int col = 1; col <= targetSum; col++) {
          int currRow = row % 2;
          int lastRow = (row - 1) % 2;

          boolean canPartitionWithoutCurr = dp[lastRow][col];
          boolean canPartitionWithCurr = false;
          if (num[row - 1] <= col) {
            canPartitionWithCurr = dp[lastRow][col - num[row - 1]];
          }
          dp[currRow][col] = canPartitionWithoutCurr || canPartitionWithCurr;
        }
      }

      return dp[num.length % 2][targetSum];
    }
  }

  /* ================  DP Bottom Up ================== */
  public static class DPBottomUpSolution {

    public boolean canPartition(int[] num) {
      int sum = calculateSum(num);
      if (sum % 2 != 0) {
        return false;
      }
      int targetSum = sum / 2;
      boolean[][] dp = new boolean[num.length + 1][targetSum + 1];

      // initialize first column as true and first row as false
      for (int row = 0; row <= num.length; row++) {
        dp[row][0] = true;
      }

      for (int row = 1; row <= num.length; row++) {
        for (int col = 1; col <= targetSum; col++) {
          boolean canPartitionWithoutCurr = dp[row - 1][col];
          boolean canPartitionWithCurr = false;
          if (num[row - 1] <= col) {
            canPartitionWithCurr = dp[row - 1][col - num[row - 1]];
          }
          dp[row][col] = canPartitionWithoutCurr || canPartitionWithCurr;
        }
      }

      return dp[num.length][targetSum];
    }
  }

  /* ================  DP Top Down with Memorization ================== */
  public static class RecursionWithMemorizationSolution {

    public boolean canPartition(int[] num) {

      int sum = calculateSum(num);
      if (sum % 2 != 0) {
        return false;
      }
      int targetSum = sum / 2;
      Boolean[][] dp = new Boolean[num.length][targetSum + 1];

      return recursionWithMemorization(num, dp, 0, targetSum);
    }

    private boolean recursionWithMemorization(int[] num, Boolean[][] dp, int index, int targetSum) {

      if (targetSum == 0) {
        return true;
      }
      if (index == num.length) {
//        dp[index][targetSum] = Boolean.FALSE; // bug was caught here. 当 index 为 num.length, 不应该对dp赋值
        return false;
      }

      // 这里的 memorization 是可以消除冗余计算的，
      // 例如[1, 1, 2, 7]中[1, 1] 和 [2] 在[7]处就是冗余计算，都为 dp[3][targetSum - 2]
      if (dp[index][targetSum] != null) {
        return dp[index][targetSum];
      }

      // case 1
      if (num[index] <= targetSum && recursionWithMemorization(num, dp, index + 1, targetSum - num[index])) {
        dp[index][targetSum] = Boolean.TRUE;
        return true;
      }

      // case 0
      dp[index][targetSum] = recursionWithMemorization(num, dp, index + 1, targetSum);
      return dp[index][targetSum];
    }
  }


  /* ================ Recursive Solution ================ */
  public static class RecursionSolution {

    public boolean canPartition(int[] num) {

      int sum = calculateSum(num);
      if (sum % 2 != 0) {
        return false;
      }

      return recursive(num, 0, sum / 2);
    }

    private boolean recursive(int[] num, int index, int targetSum) {
      if (targetSum == 0) {
        return true;
      }
      if (index == num.length) {
        return false;
      }

      // case 1
      if (num[index] <= targetSum && recursive(num, index + 1, targetSum - num[index])) {
        return true;
      }

      // case 0
      return recursive(num, index + 1, targetSum);
    }
  }


  private static int calculateSum(int[] num) {

    int sum = 0;
    for (int i = 0; i < num.length; i++) {
      sum += num[i];
    }

    return sum;
  }

  public static void main(String[] args) {
    testRecursionSolution();
    testRecursionWithMemorizationSolution();
    testDPBottomUpSolution();
    testDPBottomUpWithSpaceOptimized();
  }

  private static void testRecursionSolution() {

    RecursionSolution ps = new RecursionSolution();
    System.out.println("RecursionSolution: ");
    int[] num = new int[]{1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));

    System.out.println("\n\n");
  }

  private static void testRecursionWithMemorizationSolution() {

    RecursionWithMemorizationSolution ps = new RecursionWithMemorizationSolution();
    System.out.println("RecursionWithMemorizationSolution: ");
    int[] num = new int[]{1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));

    System.out.println("\n\n");
  }

  private static void testDPBottomUpSolution() {

    DPBottomUpSolution ps = new DPBottomUpSolution();
    System.out.println("DPBottomUpSolution: ");
    int[] num = new int[]{1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));

    System.out.println("\n\n");
  }

  private static void testDPBottomUpWithSpaceOptimized() {

    DPBottomUpWithSpaceOptimized ps = new DPBottomUpWithSpaceOptimized();
    System.out.println("DPBottomUpWithSpaceOptimized: ");
    int[] num = new int[]{1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));

    System.out.println("\n\n");
  }
}
