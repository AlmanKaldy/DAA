# Linked List Cycle

## 1. Problem

Starting at the first node, follow next links and decide whether traversal returns to an already visited node. Return true for a cycle and false if traversal reaches null.

Source: https://leetcode.com/problems/linked-list-cycle/

## 2. Approach

Store references to visited nodes in a set. Before adding the current node, check whether the set already contains that exact node. If it does, return true. Otherwise, add it and follow next. Reaching null means there is no cycle.

The set uses IdentityHashMap so it compares node identity, rather than node values or overridden equality. Two different nodes can both contain 2 without forming a cycle. The list is not modified. The example's pos describes a connection; it is not an argument to hasCycle.

### Written trace

Label the nodes A(3), B(2), C(0), D(-4). Connections are A to B, B to C, C to D, and D back to B.

| Step | Current node | Visited before check | Action |
| --- | --- | --- | --- |
| 1 | A | {} | Add A; move to B |
| 2 | B | {A} | Add B; move to C |
| 3 | C | {A, B} | Add C; move to D |
| 4 | D | {A, B, C} | Add D; move to B |
| 5 | B | {A, B, C, D} | B is already present: return true |

Without D's link back to B, step 5 reaches null and returns false. An empty list returns false immediately. A node linking to itself returns true on its second visit.

The set contains every node previously reached. Repeating a node means following the same next links again, so a cycle exists. If a reachable cycle exists, a node must eventually repeat, and the algorithm detects it.

## 3. Time Complexity

**Expected time complexity: O(n)**, where n is the number of distinct reachable nodes.

Each distinct node is checked and inserted once, followed by at most one repeated-node check. IdentityHashMap operations take expected O(1) time under normal hash distribution, giving expected O(n) total time. This is an expected bound, not a guaranteed worst-case hashing bound; pathological collisions can make total work O(n²).

## 4. Space Complexity

**Auxiliary space: O(n).** The set stores up to n distinct node references. The current pointer and other fixed bookkeeping use O(1).

## 5. Reflection / Improvement

Floyd's two-pointer algorithm improves auxiliary space to O(1) and gives worst-case O(n) time. Replace the set with a slow pointer advancing one link and a fast pointer advancing two links. If fast reaches null, there is no cycle. If they meet after advancing, there is a cycle. Inside a cycle of length k, fast gains one position per iteration relative to slow and catches it within k iterations. Compare references, not values, and check fast and fast.next before advancing twice.

The set approach is easier to trace, but stores every visited node. Floyd's algorithm avoids that storage without improving the linear time order.

## Validation

Test.java checks an empty list, a single terminal node, a self-loop, repeated values without a cycle, a cycle to the head, and a cycle beginning after the head.
