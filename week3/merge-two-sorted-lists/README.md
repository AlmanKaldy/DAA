# Merge Two Sorted Lists

## 1. Problem

Two singly linked lists are already sorted in ascending order, allowing equal values. Combine their existing nodes into one sorted list and return its first node.

Source: https://leetcode.com/problems/merge-two-sorted-lists/

## 2. Approach

Compare the first remaining node of each list. Attach the smaller one to the result, advance only that input pointer, and move the result's tail to the attached node. On equal values, choose list1.

A dummy node gives the result an initial tail, so the first attachment needs no special case. The dummy is not part of the returned list. When an input runs out, attach the other list's whole remaining chain: it is already sorted.

Before each comparison, the selected prefix is sorted. The smallest remaining value must be at one of the two input heads because both inputs are sorted. Therefore, selecting the smaller head preserves sorted order. Each step consumes one node, so the loop terminates.

### Written trace

Input A: [1, 2, 4], input B: [1, 3, 4]. The selected prefix below ends at tail; its temporary next link may still point into an input until a later attachment.

| Step | A head | B head | Action | Selected prefix |
| --- | --- | --- | --- | --- |
| 1 | 1 | 1 | Take A; advance A | [1] |
| 2 | 2 | 1 | Take B; advance B | [1, 1] |
| 3 | 2 | 3 | Take A; advance A | [1, 1, 2] |
| 4 | 4 | 3 | Take B; advance B | [1, 1, 2, 3] |
| 5 | 4 | 4 | Take A; A becomes null | [1, 1, 2, 3, 4] |
| Finish | null | 4 | Attach remaining B | [1, 1, 2, 3, 4, 4] |

If both inputs are empty, return null. If one is empty, return the other chain directly. This solution changes next links in the original lists.

## 3. Time Complexity

**Worst-case time complexity: O(n + m)**, where n and m are the input lengths.

Each loop iteration consumes exactly one node and performs constant work. There are at most n + m - 1 comparisons when both lists are nonempty. Attaching the remaining chain takes O(1), without traversing it. Interleaved input values require a linear number of comparisons. If one input starts empty, this implementation takes O(1).

## 4. Space Complexity

**Auxiliary space: O(1).** Only one dummy node and a fixed number of references are used. Existing input nodes form the output; no separate collection or recursion stack grows with the input.

## 5. Reflection / Improvement

The iterative merge already achieves optimal worst-case linear time for ordinary linked lists and constant auxiliary space. Collecting values and sorting them would take O((n + m) log(n + m)) time and extra storage. A recursive merge is shorter but adds up to O(n + m) stack space. To preserve the inputs, new output nodes could be allocated, increasing output storage to O(n + m).

## Validation

Test.java checks empty inputs, the standard example, duplicates, negative values, and unequal lengths. It also verifies that the result contains the original nodes in the expected order.
