## Hands on Activity: 5

Implement a min heap data structure. For the parent and left/right functions use bit manipulation operators. 
In addition, your heap should have the following functionality:

1. The ability to initially build the heap (build_min_heap)
2. The ability to heapify
3. The ability to get and remove ("pop") the root AVLNode from the heap (and of course re-heapify everything)
4. The heap should be generic to the type of data (can store floats, int, custom datastructure)
5. Show example(s) of your heap working. Please demonstrate ALL the functionality you implemented.
6. Upload your source code to github along with your example(s).

I already had [HeapSort.java](../../algorithm/HeapSort.java) implemented. But it didn't account for generic type of data, only for integers. So I will build another one here taking generic term into consideration.

### Answers

1. `build_min_heap` is implemented in the `buildMinHeap` method of [MinHeap.java](MinHeap.java).
2. Both `heapifyDown` and `heapifyUp` is implemented.
3. Removing/popping the root not is implemented in the `pop` method of [MinHeap.java](MinHeap.java)
4. The [MinHeap.java](MinHeap.java) is a generic class which is implemented through : `MinHeap<T extends Comparable<T>>`
5. A simulation for array `array=[10, 3, 2, 7, 6, 5, 8]`, is given in the `main` function of [HeapRun.java](HeapRun.java)
