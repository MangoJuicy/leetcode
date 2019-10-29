package oracle.lc425;

import java.util.ArrayList;
import java.util.List;

public class BackTrackingSolution {

  public List<List<String>> wordSquares(String[] words) {
    List<List<String>> res = new ArrayList<>();
    backtracking(0, words, new ArrayList<String>(), res);

    return res;
  }

  private void backtracking(int count, String[] words, List<String> list, List<List<String>> res) {
    if (count == words[0].length()) {
      if (isSquaredWords(list)) {
        res.add(new ArrayList<String>(list));
      }
      return;
    }

    for (int i = 0; i < words.length; i++) {
      list.add(words[i]);
      backtracking(count + 1, words, list, res);
      list.remove(list.size() - 1);
    }
  }

  private boolean isSquaredWords(List<String> list) {
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
