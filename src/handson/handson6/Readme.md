## Hands-on Activity: 6

1. Implement both versions of quicksort (random and non-random choice for the pivot).

2.  For the non-random pivot version of quicksort show the following benchmarks on the same dijkstraGraph:

    a) best case (generate a set of inputs that will always be the best case, repeat for multiple array input sizes "n"). 
   
    b) worst case (generate a set of inputs that will always be the worst case, repeat for multiple array input sizes "n"). 
    
    c) average case (generate a set of inputs from a uniform distribution, repeat for multiple array input sizes "n").

3. Mathematically derive the average runtime complexity of the non-random pivot version of quicksort.

## Answers

1. Both versions of quicksort are implemented in the class: [QuickSort.java](QuickSort.java). In `quickSortNonRandom` method, last index is chosen as the pivot. And in the `quickSortRandom`, a random index is chosen as the pivot.
2. In the [QuickSortBenchmark.java](QuickSortBenchmark.java) class, benchmarking for best, worst and average version are implemented. Let's say the input sizes `= {1000, 2000, 4000, 8000, 16000}` 
   1. Best case output for given input sizes
      1. Best case time for n=1000: 86875 ns
      2. Best case time for n=2000: 82459 ns
      3. Best case time for n=4000: 165250 ns
      4. Best case time for n=8000: 346917 ns
      5. Best case time for n=16000: 722667 ns
   2. Worst case for the given input sizes.
      1. Worst case time for n=1000: 2563542 ns
      2. Worst case time for n=2000: 2517334 ns
      3. Worst case time for n=4000: 3255334 ns
      4. Worst case time for n=8000: 13681834 ns
      5. Worst case time for n=16000: 51383459 ns
   3. Average case for the given input sizes
      1. Average case time for n=1000: 492958 ns
      2. Average case time for n=2000: 136417 ns
      3. Average case time for n=4000: 272542 ns
      4. Average case time for n=8000: 483708 ns
      5. Average case time for n=16000: 1022500 ns
3. Deriving the Average Runtime Complexity of the Non-Random Pivot Version of Quicksort

    For the non-random pivot version, the average case time complexity is derived as follows:

   1. **Dividing the array**: The pivot ideally splits the array into two equal halves on average, requiring   $O(n)$ comparisons to partition.

   2. **Recursive Splitting**: The recurrence relation for Quicksort's average case is given by:
      $$
      T(n) = 2T\left(\frac{n}{2}\right) + O(n)
      $$
      This can be expanded as:
      $$
      T(n) = 2 \left(2T\left(\frac{n}{4}\right) + O\left(\frac{n}{2}\right)\right) + O(n)
      $$
      $$
      = 4T\left(\frac{n}{4}\right) + O(n) + O(n)
      $$
      $$
      = 8T\left(\frac{n}{8}\right) + O(n) + O(n) + O(n)
      $$
      Summing these results yields $O(n \log n)$.

Hence, the **average runtime complexity** for the non-random pivot version is $ O(n \log n) $.



