import java.util.ArrayList;

class Solution {
    public boolean hasCycle(ListNode head) {
        // Frequency Count Method, using the lecture convention:
        // a complete for header is counted k + 1 times for k iterations.
        // Do not count its initialization and increment separately.
        // n = number of distinct reachable nodes.
        ArrayList<ListNode> visited = new ArrayList<>(); // 1 unit of time
        ListNode current = head; // 1 unit of time

        while (current != null) { // n + 1 units for a list without a cycle
            // k = visited.size(): 0, 1, 2, ..., n - 1.
            for (int i = 0; i < visited.size(); i++) { // k + 1 units per visit
                if (visited.get(i) == current) { // k units per visit
                    return true; // 1 unit if a repeated node is found
                }
            }
            visited.add(current); // 1 amortized unit per visit: n total
            current = current.next; // n units of time
        }
        return false; // 1 unit of time
    }
}

// Time complexity (trace a list WITHOUT a cycle):
// Visit 1: k = 0, for header = 1, comparisons = 0.
// Visit 2: k = 1, for header = 2, comparisons = 1.
// Visit 3: k = 2, for header = 3, comparisons = 2.
// ...
// Visit n: k = n - 1, for header = n, comparisons = n - 1.
//
// All for headers: 1 + 2 + ... + n = n(n + 1)/2.
// All comparisons: 0 + 1 + ... + (n - 1) = n(n - 1)/2.
// f(n) = 1 + 1 + (n + 1) + n(n + 1)/2 + n(n - 1)/2 + n + n + 1
// f(n) = n^2 + 3n + 4
// Worst-case time complexity = O(n^2).
// With a cycle, the final repeated visit adds at most O(n) work,
// so the worst-case bound is still O(n^2).
// Best case: empty input, f(0) = 4, O(1).
// These units follow the lecture model, not exact Java instruction counts.
// add has amortized O(1) cost: resizing costs O(n) over all n additions,
// which does not change the quadratic complexity.
//
// Space complexity (logical storage units, not bytes):
// Input nodes: n.
// Stored references in visited: up to n.
// References head, visited, current and loop variable i: 4.
// S(n) = n + n + 4 = 2n + 4 in this simplified storage model.
// Total space including inputs = O(n).
// Extra space only = n + 4 = O(n).
// ArrayList can have spare capacity; its actual capacity is still O(n).
// visited stores references, not copies of the input nodes.
//
// Improvement:
// Two pointers can detect a cycle in O(n) time and O(1) extra space.
