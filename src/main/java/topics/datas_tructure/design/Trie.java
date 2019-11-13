package topics.datas_tructure.design;

public class Trie {

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode runner = root;
    root.count += 1;
    for (int i = 0; i < word.length(); i++) {
      int index = word.charAt(i) - 'a';
      if (runner.children[index] == null) {
        runner.children[index] = new TrieNode();
      }
      runner.count += 1;
      runner = runner.children[index];
    }
    runner.isEnd = true;
  }

  // 判断 Dictionary 中是否存在目标word
  public boolean search(String word) {
    TrieNode runner = root;
    for (int i = 0; i < word.length(); i++) {
      int index = word.charAt(i) - 'a';
      if (runner.children[index] == null) {
        return false;
      }
      runner = runner.children[index];
    }
    return runner.isEnd;
  }

  // prefix 对应的单词数量
  public int countWordByPrefix(String prefix) {
    TrieNode runner = root;
    for (int i = 0; i < prefix.length(); i++) {
      int index = prefix.charAt(i) - 'a';
      if (runner.children[index] == null) {
        return 0;
      }
      runner = runner.children[index];
    }
    return runner.count;
  }

  private static class TrieNode {
    private static final Integer CHAR_COUNT = 26;

    TrieNode[] children;
    boolean isEnd;
    int count;  // 用来计数，prefix所对应的单词数

    public TrieNode() {
      children = new TrieNode[CHAR_COUNT];
      count = 1;
    }
  }
}
