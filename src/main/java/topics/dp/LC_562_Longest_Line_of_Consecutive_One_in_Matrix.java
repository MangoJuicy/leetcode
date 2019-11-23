package topics.dp;

public class LC_562_Longest_Line_of_Consecutive_One_in_Matrix {

  public int longestLine(int[][] matrix) {

    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int rows = matrix.length;
    int cols = matrix[0].length;
    Point[][] dp = new Point[2][cols + 2];    // 当前值可以由右上值进行计算

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < cols + 2; j++) {
        dp[i][j] = new Point();
      }
    }

    int maxLen = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        int dpRow = (i + 1) % 2;
        int dpCol = j + 1;
        int lastDpRow = i % 2;
        Point currPoint = dp[dpRow][dpCol];
        if (matrix[i][j] == 0) {
          currPoint.setToZeros();
        } else {
          currPoint.horizontalLen = dp[dpRow][dpCol - 1].horizontalLen + 1;
          currPoint.verticalLen = dp[lastDpRow][dpCol].verticalLen + 1;
          currPoint.diagonalLen = dp[lastDpRow][dpCol - 1].diagonalLen + 1;
          currPoint.antiDiagonalLen = dp[lastDpRow][dpCol + 1].antiDiagonalLen + 1;
          maxLen = currPoint.calculateMaxLen(maxLen);
        }
      }
    }

    return maxLen;
  }

  private static class Point {
    int horizontalLen;
    int verticalLen;
    int diagonalLen;
    int antiDiagonalLen;

    private void setToZeros() {
      this.horizontalLen = 0;
      this.verticalLen = 0;
      this.diagonalLen = 0;
      this.antiDiagonalLen = 0;
    }

    private int calculateMaxLen(int maxLenSoFar) {
      int maxLen = Math.max(maxLenSoFar, this.horizontalLen);
      maxLen = Math.max(maxLen, this.verticalLen);
      maxLen = Math.max(maxLen, this.diagonalLen);
      maxLen = Math.max(maxLen, this.antiDiagonalLen);

      return maxLen;
    }
  }
}