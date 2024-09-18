# Problem 0: Fibonacci Sequence

Implement the Fibonacci sequence

```python
x = fib(n)
    if n == 0
        return 0
    if n == 1
         return 1
    return fib(n-1) + fib(n-2)
```

Debug the code and "step into" the function for fib(5). I want you to step into all recursive calls and list out the the function call stack ex. fib(5) -> fib(4) -> fib(3) ?....  that you observe.
Note, don't turn on optimization if your programming language allows it.
Another note make sure to implement the return exactly as

`return fib(n-1) + fib(n-2)` instead of say

```
a = fib(n-1)
b = fib(n-2)
return a + b
```
OR as

`return fib(n-2) + fib(n-1)`

## Answer

#### Code:
```python
def fib(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fib(n-1) + fib(n-2)
```

#### "Step into" the function for `fib(5)`:
When calling `fib(5)`, the function performs the following recursive calls, showing how the call stack builds up:

1. **fib(5)** calls:
    - **fib(4)** calls:
        - **fib(3)** calls:
            - **fib(2)** calls:
                - **fib(1)** (returns 1)
                - **fib(0)** (returns 0)
            - **fib(2)** returns 1
            - **fib(1)** (returns 1)
        - **fib(3)** returns 2
        - **fib(2)** calls:
            - **fib(1)** (returns 1)
            - **fib(0)** (returns 0)
        - **fib(2)** returns 1
    - **fib(4)** returns 3
    - **fib(3)** calls:
        - **fib(2)** calls:
            - **fib(1)** (returns 1)
            - **fib(0)** (returns 0)
        - **fib(2)** returns 1
        - **fib(1)** (returns 1)
    - **fib(3)** returns 2

Finally, **fib(5)** returns **5**.

#### Time Complexity:
The time complexity for the naive recursive Fibonacci function is **O(2^n)** because it generates an exponential number of calls due to the repeated recalculation of Fibonacci numbers.

#### Ways to Improve:
- **Memoization**: Store the results of previously calculated Fibonacci numbers to avoid redundant calculations, improving the time complexity to **O(n)**.
- **Bottom-up Dynamic Programming**: Use an iterative approach, calculating Fibonacci numbers in sequence and storing the previous two results, reducing both time and space complexity.

---

# Problem 1: Merge K Sorted Arrays

### Approach:
We need to merge \( K \) sorted arrays into one large sorted array. A simple approach involves merging arrays pair by pair. A more optimized approach uses a **min-heap** to maintain the smallest element across arrays.

#### Code:
The code is here: [MergeKSortedArrays.java](MergeKSortedArrays.java)
```Java
package handson.hanson4;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	// Custom class to store elements along with the array index and element index
	static class ArrayEntry implements Comparable<ArrayEntry> {
		int value;
		int arrayIndex;
		int elementIndex;

		public ArrayEntry(int value, int arrayIndex, int elementIndex) {
			this.value = value;
			this.arrayIndex = arrayIndex;
			this.elementIndex = elementIndex;
		}

		// Override compareTo to make the priority queue a min-heap
		@Override
		public int compareTo(ArrayEntry other) {
			return Integer.compare(this.value, other.value);
		}
	}

	public static int[] mergeKSortedArrays(int[][] arrays) {
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();
		int totalSize = 0;

		// Push the first element of each array along with array index and element index
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].length > 0) {
				minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
				totalSize += arrays[i].length;  // Calculate total size of the merged array
			}
		}

		int[] mergedArray = new int[totalSize];
		int index = 0;

		// Continue extracting minimum elements and pushing the next elements of that array
		while (!minHeap.isEmpty()) {
			ArrayEntry entry = minHeap.poll();
			mergedArray[index++] = entry.value;

			// Push the next element of the same array into the heap
			if (entry.elementIndex + 1 < arrays[entry.arrayIndex].length) {
				int nextValue = arrays[entry.arrayIndex][entry.elementIndex + 1];
				minHeap.offer(new ArrayEntry(nextValue, entry.arrayIndex, entry.elementIndex + 1));
			}
		}

		return mergedArray;
	}

	public static void main(String[] args) {
		int[][] arrays = {
				{1, 3, 5, 7},
				{2, 4, 6, 8},
				{0, 9, 10, 11}
		};

		int[] result = mergeKSortedArrays(arrays);
		for (int val : result) {
			System.out.print(val + " ");
		}
	}
}
```

#### Example:
The example here

`arrays = [[1, 3, 5, 7], [2, 4, 6, 8], [0, 9, 10, 11]]`

**Output**: `[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]`

#### Time Complexity:
- **Inserting** and **extracting** from a min-heap takes \(O(log K)\), and we perform this operation for each element in the arrays.
- The total number of elements is \( K * N \).
- Therefore, the time complexity is **O(N*K*\log K)**.

#### Ways to Improve:
- This method is already quite optimized using a min-heap. Further improvements can only come from parallel processing if the arrays are independent, or if the input size is extremely large, we could consider using external memory techniques.

---

# Problem 2: Remove Duplicates from a Sorted Array

### Approach:
Since the array is sorted, we can remove duplicates in-place by maintaining a pointer for the "last unique element."

#### Code:
The code is here: ([RemoveDuplicates.java](RemoveDuplicates.java))
```java
import java.util.Arrays;

public class RemoveDuplicates {

	public static int[] removeDuplicates(int[] array) {
		if (array == null || array.length == 0) {
			return new int[0];  // Return empty array if input is null or empty
		}

		// Pointer for the last unique element
		int lastUnique = 0;

		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[lastUnique]) {
				lastUnique++;
				array[lastUnique] = array[i];
			}
		}

		// Return a copy of the array containing only unique elements
		return Arrays.copyOf(array, lastUnique + 1);
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 2, 3, 4, 4, 4, 5, 5};

		int[] result = removeDuplicates(array);
		System.out.println(Arrays.toString(result));  // Output: [1, 2, 3, 4, 5]
	}
}
```

#### Example:
The example here
```
array = [1, 2, 2, 3, 4, 4, 4, 5, 5]
```
**Output**: `[1, 2, 3, 4, 5]`

#### Time Complexity:
- Since we are only traversing the array once, the time complexity is **O(N)**, where \( N \) is the length of the array.
- Space complexity is **O(1)** because we are modifying the array in place and not using additional space proportional to the input size.

#### Ways to Improve:
- The current implementation is already efficient for removing duplicates in-place with **O(N)** time and **O(1)** space complexity. No significant improvements are necessary unless the array is extremely large and requires external memory handling.

---

These implementations can be uploaded to GitHub as requested. For both problems, we have discussed time complexity and potential optimizations. Let me know if you need further explanations or the GitHub link!
