使用 array 实现 minHeap / maxHeap
```
currIndex:

leftChildIndex = 2 * currIndex + 1;
rightChildIndex = 2 * currIndex + 2;

parentIndex = (currIndex - 1) / 2;
```

### sift up: add new item
```
将 newItem 放到 array 末尾，比较其与 parent item 的大小，判断是否需要swap以及继续迭代
```

### sift down: remove top item
```
将 top item 和 array 中最后一个 item 置换，size--
比较 新的 top item 与其 左右子节点，取最小值与当前值互换，继续迭代。 
注意： 左右子节点可能不存在。
```