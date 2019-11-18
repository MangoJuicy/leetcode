package topics.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class LC_773_Sliding_Puzzle {

  private static final int BOARD_SIZE = 6;
  private static final int COLUMN_SIZE = 3;
  private static final String TARGET = "123450";

  // BFS + memorization
  public int slidingPuzzle(int[][] board) {

    State initialState = convertBoardToState(board);

    // current state: last state
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(initialState.state, null);

    Queue<State> stateQueue = new LinkedList<>();
    stateQueue.add(initialState);

    int stepCount = 0;
    while (!stateQueue.isEmpty()) {
      int size = stateQueue.size();
      for (int i = 0; i < size; i++) {
        State currState = stateQueue.poll();
        if (TARGET.equals(currState.state)) {
          printPath(stateMap);   // output the path to solve the puzzle
          return stepCount;
        }
        queueNextStates(currState, stateMap, stateQueue);
      }
      stepCount++;
    }
    return -1;
  }

  private  void queueNextStates(State currState, Map<String, String> stateMap, Queue<State> stateQueue) {
    List<Integer> nextIndexes = new LinkedList<>();
    if (currState.zeroIndex % COLUMN_SIZE != 0) {
      nextIndexes.add(currState.zeroIndex - 1);
    }
    if (currState.zeroIndex % COLUMN_SIZE != COLUMN_SIZE - 1) {
      nextIndexes.add(currState.zeroIndex + 1);
    }
    nextIndexes.add((currState.zeroIndex + 3) % BOARD_SIZE);

    for (Integer nextIndex: nextIndexes) {
      StringBuilder sb = new StringBuilder(currState.state);
      sb.setCharAt(currState.zeroIndex, currState.state.charAt(nextIndex));
      sb.setCharAt(nextIndex, '0');
      String nextStateStr = sb.toString();
      if (stateMap.containsKey(nextStateStr)) {
        continue;
      }
      stateMap.put(nextStateStr, currState.state);
      stateQueue.add(new State(nextIndex, nextStateStr));
    }
  }

  // convert board to a string and return the index of 0
  private State convertBoardToState(int[][] board) {
    int zeroIndex = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        sb.append(board[i][j]);
        if (board[i][j] == 0) {
          zeroIndex = i * board[i].length + j;
        }
      }
    }
    return new State(zeroIndex, sb.toString());
  }

  private void printPath(Map<String, String> map) {
    String state = TARGET;
    while (state != null && map.containsKey(state)) {
      System.out.println(state.substring(0, 3));
      System.out.println(state.substring(3, 6));
      System.out.println();
      state = map.get(state);
    }
  }

  private static class State {
    int zeroIndex;
    String state;

    public State(int zeroIndex, String state) {
      this.zeroIndex = zeroIndex;
      this.state = state;
    }

    @Override
    public int hashCode() {
      return Objects.hash(state);
    }
  }
}
