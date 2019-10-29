package topics.dp.knapsack.zero_one_basic_knapsack;

public class Knapsack_Recursive {

  public int getMaxProfit(int[] weights, int[] profits, int capacity) {
    return recursive(weights, profits, capacity, 0);
  }

  private int recursive(int[] weights, int[] profits, int capacity, int index) {
    if (capacity == 0 || index == weights.length) {
      return 0;
    }

    // 0 case
    int profit0 = recursive(weights, profits, capacity, index + 1);

    // 1 case
    int profit1 = 0;
    if (weights[index] <= capacity) {
      // 可以在此前后使用backtracking得到最大profit的具体题解， list.add(index), list.removeLast()
      profit1 = profits[index] + recursive(weights, profits, capacity - weights[index], index + 1);
    }

    return Math.max(profit0, profit1);
  }

  public static void main(String[] args) {
    Knapsack_Recursive ks = new Knapsack_Recursive();
    int[] weights = {1, 2, 3, 5};
    int[] profits = {1, 6, 10, 16};
    int maxProfit = ks.getMaxProfit(weights, profits, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.getMaxProfit(weights, profits, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}