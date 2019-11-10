import java.util.List;
import oracle.lc425.BackTrackingSolution;

public class Main {
  public static void main(String[] args) {
    String[] words = {"abat", "baba", "atan", "atal"};
    BackTrackingSolution lc425BackTracking = new BackTrackingSolution();
    List<List<String>> res = lc425BackTracking.wordSquares(words);

    for (List<String> list : res) {
      for (String str : list) {
        System.out.print(str);
      }
      System.out.println();
    }
  }

  private static boolean isSquareWords(List<String> list) {
    boolean squared = true;
    for (int layer = 0; layer < list.size(); layer++) {
      for (int k = layer + 1; k < list.size(); k++) {
        char rowChar = list.get(layer).charAt(k);
        char colChar = list.get(k).charAt(layer);
        if (rowChar != colChar) {
          return false;
        }
      }
    }
    return true;
  }
}
