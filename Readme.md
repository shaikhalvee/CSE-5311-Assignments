
## Prove the Correctness of the Selection Sort Algorithm
To prove the correctness of the **Selection Sort** algorithm, we use **loop invariants** and **mathematical induction**. The steps to proving correctness typically include:

1. **Initialization**: Showing that the loop invariant holds before the loop runs.
2. **Maintenance**: Showing that if the loop invariant is true before an iteration, it remains true after the iteration.
3. **Termination**: Showing that when the loop terminates, the algorithm has correctly sorted the array.

### Selection Sort Algorithm
Selection Sort works by repeatedly selecting the smallest element from the unsorted part of the array and swapping it with the first element in the unsorted part. Here's the algorithm in pseudocode:

```
for i = 0 to n-2:
    minIndex = i
    for j = i+1 to n-1:
        if array[j] < array[minIndex]:
            minIndex = j
    swap(array[i], array[minIndex])
```

### Loop Invariant
The **loop invariant** for Selection Sort is:
- At the start of each iteration of the outer loop (indexed by `i`), the subarray from `0` to `i-1` is sorted, and all elements in this subarray are smaller than or equal to the elements in the rest of the array.

This loop invariant helps to prove the correctness of the algorithm. Let's go through the steps of the proof.

### 1. **Initialization (Base Case)**

Before the first iteration of the outer loop (i.e., when `i = 0`), the subarray from `0` to `i-1` is empty. An empty subarray is trivially sorted, so the loop invariant holds.

### 2. **Maintenance**

We now assume that the loop invariant holds at the start of an iteration of the outer loop (i.e., the subarray from `0` to `i-1` is sorted, and all elements in that subarray are less than or equal to the elements in the remaining part of the array).

During the `i`th iteration:
- The algorithm finds the smallest element from the unsorted part of the array (from index `i` to `n-1`) and swaps it with `array[i]`.
- After the swap, `array[i]` holds the smallest element from the unsorted part, and since the previous subarray (from `0` to `i-1`) is already sorted, the subarray from `0` to `i` is now sorted.

Thus, the loop invariant continues to hold at the end of the iteration.

### 3. **Termination**

The outer loop terminates when `i = n-1`. At this point, the loop invariant tells us that the subarray from `0` to `n-2` is sorted and that all elements in this subarray are less than or equal to the remaining elements in the array (in this case, just `array[n-1]`).

Since the final element is the largest (or at least not smaller than any other element), the entire array from `0` to `n-1` is sorted when the loop terminates.

### Correctness Criteria:

- **Loop Invariant**: At the start of each iteration of the outer loop, the subarray from `0` to `i-1` is sorted, and all elements in this subarray are smaller than or equal to the remaining elements in the array.
- **Base Case**: The subarray is empty before the first iteration, which is trivially sorted.
- **Inductive Step**: If the subarray is sorted at the start of an iteration, after the iteration, the subarray up to the `i`th element will be sorted.
- **Termination**: When the outer loop terminates, the entire array is sorted.

### Time Complexity:
While Selection Sort is correct, its time complexity is \(O(n^2)\), making it inefficient for large inputs. However, its correctness holds regardless of input size.
