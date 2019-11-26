package topics.datas_tructure.design;

public class MinHeap {

  private int[] array;
  private int size;
  private int maxSize;

  // current index as i, left child index is (2 * i + 1), right child index is (2 * i + 2)
  // current index as i, parent index is (i - 1)/2

  public MinHeap(int maxSize) {
    array = new int[maxSize];
    this.size = 0;
    this.maxSize = maxSize;
  }

  public int peek() throws Exception {
    if (size == 0) {
      throw new Exception("There is no item available in the min heap.");
    }
    return array[0];
  }

  public void offer(int val) {
    if (size == maxSize) {
      scaleMinHeap();
    }

    size++;
    int currIndex = size - 1;
    array[currIndex] = val;
    int parentIndex = (currIndex - 1) / 2;
    while (array[currIndex] < array[parentIndex]) {
      swap(currIndex, parentIndex);
      currIndex = parentIndex;
      parentIndex = (currIndex - 1) / 2;
    }
  }

  // remove the min value from heap
  public int poll() throws Exception {
    if (size == 0) {
      throw new Exception("There is no item available in the min heap.");
    }

    int lastIndex = size - 1;
    int popedValue = array[lastIndex];
    swap(0, lastIndex);
    size--;

    int currIndex = 0;
    while (currIndex < size) {
      int nextIndex = getIndexToSwap(currIndex);
      if (currIndex == nextIndex) {
        break;
      } else {
        swap(currIndex, nextIndex);
        currIndex = nextIndex;
      }
    }

    return popedValue;
  }

  // When sift down, find the child index to swap.
  // Return current index if no child available or current value is less than any child value
  private int getIndexToSwap(int currIndex) {
    int nextIndex = currIndex;

    int leftIndex = currIndex * 2 + 1;
    if (leftIndex < size && array[leftIndex] < array[nextIndex]) {
      nextIndex = leftIndex;
    }

    int rightIndex = currIndex * 2 + 2;
    if (rightIndex < size && array[rightIndex] < array[nextIndex]) {
      nextIndex = rightIndex;
    }

    return nextIndex;
  }

  // double size the array when needed
  private void scaleMinHeap() {
    int newMaxSize = 2 * maxSize;
    int[] scaledArray = new int[newMaxSize];
    for (int i = 0; i < maxSize; i++) {
      scaledArray[i] = array[i];
    }

    maxSize = newMaxSize;
    array = scaledArray;
  }

  private void swap(int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  // 0                            log2(size): 0                       (2^h - 1): 0
  // 1, 2                                     1, 1                               1, 1
  // 3, 4, 5, 6                               2, 2, 2, 2                         3, 3, 3, 3
  // 7, 8, 9, 10, 11, 12, 13, 14              3, 3, 3, 3, 3, 3, 3, 3             7, 7, 7, 7, 7, 7, 7, 7
  public boolean isLeaf(int index) {
    int height = (int)(Math.log(size) / Math.log(2));
    int indexOfFirstLeaf = (int)Math.pow(2, height) - 1;
    return (index < size) && (index >= indexOfFirstLeaf);
  }
}
