package topics.matrix;

public class LC_1138_Alphabet_Board_Path {

  private final int COLUMNS = 5;

  public String alphabetBoardPath(String target) {
    StringBuilder sb = new StringBuilder();
    char lastChar = 'a';
    for (int i = 0; i < target.length(); i++) {
      char currChar = target.charAt(i);
      getPathBetweenChars(lastChar, currChar, sb);
      lastChar = currChar;
    }
    return sb.toString();
  }

  private void getPathBetweenChars(char source, char target, StringBuilder sb) {
    int sourceRow = (source - 'a') / COLUMNS;
    int sourceCol = (source - 'a') % COLUMNS;
    int targetRow = (target - 'a') / COLUMNS;
    int targetCol = (target - 'a') % COLUMNS;

    int diffRow = targetRow - sourceRow;
    int diffCol = targetCol - sourceCol;

    // 四个 case:
    // S 左上，T 右下，先后无所谓
    // S 左下，T 右上，先上后右
    // S 右上，T 左下，先左后右
    // S 右下，T 左上，先后无所谓

    if (diffCol > 0) {
      // source 左，target 右
      addVerticalPath(diffRow, sb);
      addHorizontalPath(diffCol, sb);
    } else {
      // source 右，target 左
      addHorizontalPath(diffCol, sb);
      addVerticalPath(diffRow, sb);
    }

    sb.append('!');
  }

  private void addVerticalPath(int rowDiff, StringBuilder sb) {
    char action = (rowDiff > 0) ? 'D' : 'U';
    for (int i = 0; i < Math.abs(rowDiff); i++) {
      sb.append(action);
    }
  }

  private void addHorizontalPath(int colDiff, StringBuilder sb) {
    char action = (colDiff > 0) ? 'R' : 'L';
    for (int i = 0; i < Math.abs(colDiff); i++) {
      sb.append(action);
    }
  }
}
