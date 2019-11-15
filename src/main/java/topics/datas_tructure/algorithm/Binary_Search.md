
`nums` is sorted array, return index of `target` in `nums`. return -1 if `target` not in `nums`
#### 代码模板
```

public int findTargetIndex(int[] nums, int target) {
  int left = 0;
  int right = nums.length - 1;

  while(left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) {
      return mid;
    } else if (nums[mid] < target) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  return -1;
}
```

### Summary:
1. mid 的更新方式 mid = start + (end - start) / 2 为偏向start方向的移动，当 end == start + 1 时，mid 的值为 start
循环判断条件为 start <= end，包含了 nums.length == 1的情况
2. start 的更新方式应为 mid + 1，否则下面的例子会出现死循环的：
mid 取中后等于 start，且 nums[mid] < target
3. target 大于 nums 中所有值（实际上，该Case最终iteration会是上面的Case）
4. end 的更新方式为 mid - 1，否则下例会出现死循环：target 小于 nums 中的所有值。
5. 如果nums中不存在 target，那么while循环的最终结果为 start == end + 1，排序角度来讲，该 target 在nums 中应该放置于 end 和 start 之间。
> 论证：
>
> Case 1: start == end - 1, 那么新的 mid 值为 start:
>* if nums[mid] < target, start 更新为 mid + 1, 即为 end，归纳为Case 2
>* if nums[mid] > target, end 更新为 mid - 1，即为 start - 1，循环结束
>
> Case 2: start == end，那么新的 mid 值为 start:
>* if nums[mid] < target, start 更新为 mid + 1，即为 end + 1，循环结束
>* if nums[mid] > target, end 更新为 mid - 1，即为 start - 1，循环结束
>
> Case 1 & 2 循环最终结果为 start == end + 1

6.target 超出 nums 范围的情况:
* nums 中所有items 大于 target，结果：start 值为
nums.length, end 值为 nums.length - 1.
* nums 中所有 items 大于 target，结果：start 值为 0， end 值为 -1.


### 延伸
Binary Search 的思想延伸及优化可能性：

> search a node in Binary Search Tree
> Heap

Heap 是 Binary Search Tree 的延伸，Heap是使用array实现的 balanced BST。
