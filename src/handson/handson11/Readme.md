# Hands on Activity 11

## Questions
1. Implement a dynamic array (that is a C++ vector). You are only allowed to use C style arrays. Assume the datatype is an int.
2. Given a dynamic table that doubles in size when it needs more space, find the amortized runtime for inserting 𝑛 elements.

## Answers

### **Dynamic Array (Vector) Implementation in C++ Using C-Style Arrays**

```cpp
#include <iostream>
#include <stdexcept> // For std::out_of_range

class Vector {
private:
    int* data;          // Pointer to the dynamic array
    size_t sz;          // Current number of elements
    size_t cap;         // Current capacity

    // Method to resize the internal array when capacity is reached
    void resize(size_t new_cap) {
        // Allocate new memory
        int* new_data = new int[new_cap];
        
        // Copy existing elements to the new array
        for (size_t i = 0; i < sz; ++i) {
            new_data[i] = data[i];
        }
        
        // Delete the old array
        delete[] data;
        
        // Update pointers and capacity
        data = new_data;
        cap = new_cap;
    }

public:
    // Default Constructor
    Vector() : data(nullptr), sz(0), cap(0) {}

    // Copy Constructor
    Vector(const Vector& other) : data(nullptr), sz(other.sz), cap(other.cap) {
        if (cap > 0) {
            data = new int[cap];
            for (size_t i = 0; i < sz; ++i) {
                data[i] = other.data[i];
            }
        }
    }

    // Copy Assignment Operator
    Vector& operator=(const Vector& other) {
        if (this == &other)
            return *this; // Handle self-assignment

        // Delete existing data
        delete[] data;

        // Copy size and capacity
        sz = other.sz;
        cap = other.cap;

        // Allocate new memory and copy elements
        if (cap > 0) {
            data = new int[cap];
            for (size_t i = 0; i < sz; ++i) {
                data[i] = other.data[i];
            }
        } else {
            data = nullptr;
        }

        return *this;
    }

    // Destructor
    ~Vector() {
        delete[] data;
    }

    // Adds an element to the end of the vector
    void push_back(int value) {
        if (sz == cap) {
            // If capacity is zero, initialize to 1, else double the capacity
            size_t new_cap = (cap == 0) ? 1 : cap * 2;
            resize(new_cap);
        }
        data[sz++] = value;
    }

    // Removes the last element of the vector
    void pop_back() {
        if (sz == 0) {
            throw std::out_of_range("Vector is empty. Cannot pop_back.");
        }
        --sz;
        // Optional: Reduce capacity if necessary
    }

    // Returns the current number of elements
    size_t size() const {
        return sz;
    }

    // Returns the current capacity
    size_t capacity() const {
        return cap;
    }

    // Access element at index with bounds checking (modifiable)
    int& operator[](size_t index) {
        if (index >= sz) {
            throw std::out_of_range("Index out of range.");
        }
        return data[index];
    }

    // Access element at index with bounds checking (read-only)
    const int& operator[](size_t index) const {
        if (index >= sz) {
            throw std::out_of_range("Index out of range.");
        }
        return data[index];
    }

    // Utility function to print all elements
    void print() const {
        std::cout << "Vector elements: ";
        for (size_t i = 0; i < sz; ++i) {
            std::cout << data[i] << " ";
        }
        std::cout << "\nSize: " << sz << ", Capacity: " << cap << "\n";
    }
};
```

Let's demonstrate that this code works. 
The simulation code is below:

```cpp
int main() {
    Vector vec;

    // Adding elements using push_back
    vec.push_back(10);
    vec.push_back(20);
    vec.push_back(30);
    vec.push_back(40);

    vec.print(); // Expected: 10 20 30 40

    // Accessing elements using operator[]
    std::cout << "Element at index 2: " << vec[2] << "\n"; // Expected: 30

    // Modifying an element
    vec[2] = 35;
    std::cout << "After modification:\n";
    vec.print(); // Expected: 10 20 35 40

    // Removing the last element
    vec.pop_back();
    std::cout << "After pop_back:\n";
    vec.print(); // Expected: 10 20 35

    // Demonstrating copy constructor
    Vector vec_copy = vec;
    std::cout << "Copy of vec:\n";
    vec_copy.print(); // Expected: 10 20 35

    // Demonstrating assignment operator
    Vector vec_assign;
    vec_assign.push_back(100);
    vec_assign.push_back(200);
    std::cout << "Before assignment, vec_assign:\n";
    vec_assign.print(); // Expected: 100 200

    vec_assign = vec;
    std::cout << "After assignment, vec_assign:\n";
    vec_assign.print(); // Expected: 10 20 35

    return 0;
}
```

**Time Complexity:**
- `push_back`: Amortized O(1)
- `pop_back`: O(1)
- `operator[]`: O(1) 
- Copy operations: O(n)

### **Aggregated and Accounting Method**

Let's analyze the amortized runtime for inserting n elements into a dynamic table that doubles in size when it reaches its capacity, using two methods: the aggregate method and the accounting method.

### 1. Aggregate Method

In the aggregate method, we calculate the total cost for $ n $ insertions and then divide it by $ n $ to find the amortized cost per insertion.

#### Analysis

1. **Cost of each insertion**:
    - Each insertion has a cost of $ 1 $ to place the element in the table.
    - When the table reaches capacity, it doubles in size, and we need to copy all existing elements to the new table.

2. **Doubling cost**:
    - When the table size doubles, we copy all elements from the old table to the new table.
    - Suppose the table size is $ k $. Doubling it requires copying $ k $ elements, which takes $ O(k) $ time.

3. **Total cost of $ n $ insertions**:
    - The cost of inserting elements without resizing is $ O(n) $, as each insertion takes $ 1 $ unit of time.
    - The cost of resizing is calculated based on powers of 2. If the table doubles in size at capacities $ 1, 2, 4, 8, 16, \ldots, n $, the resizing costs are:
      $$
      1 + 2 + 4 + 8 + \dots + n/2 = 2n - 1
      $$
    - So, the total cost of $ n $ insertions, including resizing, is:
      $$
      O(n) + (2n - 1) = O(3n) = O(n)
      $$

4. **Amortized cost per insertion**:
   $$
   \text{Amortized cost} = \frac{\text{Total cost}}{n} = \frac{O(n)}{n} = O(1)
   $$

Thus, the amortized cost per insertion using the aggregate method is $ O(1) $.

### 2. Accounting Method

In the accounting method, we assign an amortized cost (or "credit") to each insertion. This cost covers both the insertion itself and the potential cost of future resizing operations.

#### Analysis

1. **Set the amortized cost**:
    - Assign an amortized cost of $ 3 $ for each insertion.

2. **Actual cost breakdown**:
    - For most insertions, the actual cost is $ 1 $ (inserting the element).
    - For doubling operations, some of the amortized cost is used to pay for copying elements.

3. **Cost management**:
    - Each time we insert an element without resizing, we use $ 1 $ unit for the actual insertion and "save" $ 2 $ units.
    - The saved $ 2 $ units accumulate to cover the cost of copying when the table doubles.

4. **Amortized cost for doubling**:
    - When the table doubles from size $ k $ to $ 2k $, copying $ k $ elements costs $ k $.
    - The $ 2 $ saved units from each of the previous $ k $ insertions provide exactly $ 2 \times k = k $ units, which covers the cost of copying.

Since each insertion only "pays" $3$ units, and this amount is sufficient to cover both insertion and future resizing, the amortized cost per insertion is:

$$
O(1)
$$

So,
The amortized runtime for inserting $ n $ elements into a dynamic table that doubles in size is $ O(1) $ per insertion, whether analyzed by the aggregate method or the accounting method.

