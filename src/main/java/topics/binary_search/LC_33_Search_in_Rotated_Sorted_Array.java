package topics.binary_search;

public class LC_33_Search_in_Rotated_Sorted_Array {

  public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while(left <= right) {
      int mid = left + (right - left)/2;
      if(nums[mid] == target) {
        return mid;
      }

      if(nums[left] <= nums[right]) {
        if(nums[mid] < target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      } else if(nums[mid] >= nums[left]){
        if(nums[left] <= target && nums[mid] > target) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if(nums[mid] < target && nums[right] >= target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }
}
