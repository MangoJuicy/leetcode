package topics.graph;

import java.util.LinkedList;
import java.util.Queue;

public class LC_934_Shortest_Bridge {

  public int shortestBridge(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    Queue<Integer> continent = new LinkedList<>();

    int index = 0;
    for (int i = 0; i < rows * cols; i++) {
      int row = i / cols;
      int col = i % cols;
      if (matrix[row][col] == 1) {
        index = i;
        break;
      }
    }
    findContinent(matrix, continent, index / cols, index % cols);

    int layer = 0;
    while (!continent.isEmpty()) {
      int size = continent.size();
      boolean found = false;
      for (int i = 0; i < size; i++) {
        index = continent.poll();
        int row = index / cols;
        int col = index % cols;

        if (fill(matrix, row - 1, col, continent)
            || fill(matrix, row + 1, col, continent)
            || fill(matrix, row, col - 1, continent)
            || fill(matrix, row, col + 1, continent)) {
          found = true;
          break;
        }
      }
      if (found) {
        break;
      }
      layer++;
    }
    return layer;
  }

  private boolean fill(int[][] matrix, int row, int col, Queue<Integer> continent) {
    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] < 0) {
      return false;
    }
    if (matrix[row][col] == 1) {
      return true;
    }
    matrix[row][col] = -1;
    continent.add(row * matrix[0].length + col);
    return false;
  }

  // DFS to find all items belonging to the continent
  private void findContinent(int[][] matrix, Queue<Integer> continent, int row, int col) {
    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] <= 0) {
      return;
    }

    continent.add(row * matrix[0].length + col);
    matrix[row][col] = -1;
    findContinent(matrix, continent, row - 1, col);
    findContinent(matrix, continent, row + 1, col);
    findContinent(matrix, continent, row, col - 1);
    findContinent(matrix, continent, row, col + 1);
  }

//  [[0,1,0],[0,0,0],[0,0,1]]
  public static void main(String[] args) {
    int[][] matrix = new int[3][3];
    matrix[0] = new int[]{0,1,0};
    matrix[1] = new int[]{0,0,0};
    matrix[2] = new int[]{0,0,1};

    LC_934_Shortest_Bridge solution = new LC_934_Shortest_Bridge();
    solution.shortestBridge(matrix);
  }
}
