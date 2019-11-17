import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TempTest {
  private static final int BOARD_SIZE = 6;
  private static final String TARGET = "123450";

  // BFS + memorization
  public int slidingPuzzle(int[][] board) {

    Map<String, String> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    int index = convertBoard(board, sb);
    map.put(sb.toString(), sb.toString());

    Queue<String> queue = new LinkedList<>();
    Queue<Integer> zeroIndexes = new LinkedList<>();
    queue.add(sb.toString());
    zeroIndexes.add(index);

    int count = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int currIndex = zeroIndexes.poll();
        String state = queue.poll();
        if (state.equals(TARGET)) {
          printTrace(map);
          return count;
        }
        int leftIndex = currIndex - 1;
        int rightIndex = currIndex + 1;
        int verticalIndex = (currIndex + 3) % BOARD_SIZE;
        queueNextState(currIndex, leftIndex, state, map, queue, zeroIndexes);
        queueNextState(currIndex, rightIndex, state, map, queue, zeroIndexes);
        queueNextState(currIndex, verticalIndex, state, map, queue, zeroIndexes);
      }
      count++;
    }
    return -1;
  }

  private void printTrace(Map<String, String> map) {
    String state = TARGET;
    while (map.containsKey(state)  && !state.equals(map.get(state))) {
      System.out.println(state.substring(0, 3));
      System.out.println(state.substring(3, 6));
      System.out.println();
      state = map.get(state);
    }
  }

  private void queueNextState(int zeroIndex, int targetIndex, String state, Map<String, String> map, Queue<String> states, Queue<Integer> indexes) {
    if (targetIndex < 0 || targetIndex >= BOARD_SIZE) {
      return;
    }
    StringBuilder nextState = new StringBuilder(state);
    nextState.setCharAt(zeroIndex, nextState.charAt(targetIndex));
    nextState.setCharAt(targetIndex, '0');
    String nextStateStr = nextState.toString();
    if (map.containsKey(nextStateStr)) {
      return;
    }
    states.add(nextStateStr);
    indexes.add(targetIndex);
    map.put(nextStateStr, state);
  }

  private int convertBoard(int[][] board, StringBuilder sb) {
    int zeroIndex = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        sb.append(board[i][j]);
        if (board[i][j] == 0) {
          zeroIndex = i * board[i].length + j;
        }
      }
    }
    return zeroIndex;
  }

  private static class ShortestDistanceNode {
    int oneDist;
    int twoDist;
    int threeDist;

    public ShortestDistanceNode(int one, int two, int three) {
      this.oneDist = one;
      this.twoDist = two;
      this.threeDist = three;
    }

    public static ShortestDistanceNode createNode(int value) {
      int oneDist = (value == 1) ? 0 : -1;
      int twoDist = (value == 2) ? 0 : -1;
      int threeDist = (value == 3) ? 0 : -1;
      return new ShortestDistanceNode(oneDist, twoDist, threeDist);
    }
  }

  // [1,1,2,1,3,2,2,3,3]
  // [[1,3],[2,2],[6,1]]
  public static void main(String[] args) {
    int[][] board = new int[2][3];
    board[0] = new int[]{3, 2, 4};
    board[1] = new int[]{1, 5, 0};

    TempTest solution = new TempTest();
    solution.slidingPuzzle(board);
  }
}
