
Basic Logic:
```
for each item 'i'
  create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and
     recursively process the remaining capacity and items
  create a new set WITHOUT item 'i', and recursively process the remaining items
return the set from the above two sets with higher profit
```

以 Divide & Conquer 的角度来看，就容易发现其中的冗余计算。前 i 个元素，分配 capacity c1；后一部分分配 capacity c2。Recursive 是前一部分的所有可能组合，与后一部分所有可能组合，继续组合。

DP 两种方式，消除冗余计算:
1. **Top Down**: Recursive with Memorization
2. **Bottom Up**: Iteration