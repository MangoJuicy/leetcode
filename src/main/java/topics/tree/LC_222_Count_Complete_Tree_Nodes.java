package topics.tree;

public class LC_222_Count_Complete_Tree_Nodes {

  public int countNodes(TreeNode root) {
    if(root == null) {
      return 0;
    }

    int leftHeight = getHeight(root.left);
    int rightHeight = getHeight(root.right);

    if(leftHeight == rightHeight) {
      return countNodes(root.right) + (1<<leftHeight);
    } else {
      return countNodes(root.left) + (1<<rightHeight);
    }

  }

  private int getHeight(TreeNode root) {
    int h = 0;
    while(root != null) {
      root = root.left;
      h++;
    }
    return h;
  }

}
