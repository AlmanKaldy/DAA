import java.util.ArrayList;

class Solution {
    public boolean hasCycle(ListNode head) {
        // n = number of different nodes
        // Count the whole for line like in class, not i++ separately.
        ArrayList<ListNode> visited = new ArrayList<>(); // 1 unit of time
        ListNode current = head; // 1 unit of time

        while (current != null) { // n + 1 units if there is no cycle
            // k = visited size: 0, 1, 2 ... n - 1
            for (int i = 0; i < visited.size(); i++) { // k + 1 units each visit
                if (visited.get(i) == current) { // k units each visit
                    return true; // 1 unit if the same node is found
                }
            }
            visited.add(current); // n units total, using amortized cost
            current = current.next; // n units of time
        }
        return false; // 1 unit of time
    }
}

// Frequency count method (list without a cycle)
// for checks: 1 + 2 + ... + n = n(n + 1)/2
// if checks: 0 + 1 + ... + n - 1 = n(n - 1)/2
// f(n) = 1 + 1 + (n + 1) + n(n + 1)/2 + n(n - 1)/2 + n + n + 1
// f(n) = n^2 + 3n + 4 -> O(n^2)
// For each node we check all the previous nodes.
// A cycle adds one last search, at most n checks. Still O(n^2).
// Best case: empty list -> O(1)
// These are class counting units, not exact Java running time.
// add sometimes resizes the array. All n adds together cost O(n).
//
// Space complexity (simple count, not bytes)
// input nodes = n
// visited holds up to n references
// head, visited, current, i = 4
// S(n) = n + n + 4 = 2n + 4 -> O(n)
// ArrayList can have unused slots, but space is still O(n).
//
// Improvement: use two pointers, one moves 1 step, the other 2.
// This gives O(n) time. Total space is still O(n) including the input.
