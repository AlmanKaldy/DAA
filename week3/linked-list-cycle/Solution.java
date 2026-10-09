import java.util.ArrayList;

class Solution {
    public boolean hasCycle(ListNode head) {
        // Counting model: each statement or condition below costs 1 unit.
        // ArrayList.add is charged 1 amortized unit (resizing spreads over adds).
        // n = number of distinct reachable nodes.
        // The exact sum below uses a list WITHOUT a cycle.
        ArrayList<ListNode> visited = new ArrayList<>(); // 1 unit of time
        ListNode current = head; // 1 unit of time

        while (current != null) { // n + 1 units, including the final check
            // On visit k + 1, visited contains k nodes (k = 0 to n - 1).
            // i = 0: 1 unit per outer iteration, n units total.
            // i < visited.size(): k + 1 units for this visit.
            // i++: k units for this visit.
            for (int i = 0; i < visited.size(); i++) {
                if (visited.get(i) == current) { // k units for this visit
                    return true; // 1 unit if a repeated node is found
                }
            }
            visited.add(current); // 1 amortized unit per visit, n total
            current = current.next; // 1 unit per visit, n total
        }
        return false; // 1 unit of time
    }
}

// TIME COMPLEXITY
// S = 0 + 1 + 2 + ... + (n - 1) = n(n - 1) / 2.
// Inner-loop initializations: n units.
// Inner-loop condition checks: S + n units.
// Inner-loop increments: S units.
// Node comparisons: S units.
// T = 1 + 1 + (n + 1) + n + (S + n) + S + S + n + n + 1
// T = 3S + 5n + 4 = 3n(n - 1)/2 + 5n + 4.
// This is an amortized teaching-model count, not exact CPU instructions.
// ArrayList resizing adds O(n) total work and does not change the result.
// The n squared term dominates: worst-case time complexity is O(n^2).
// With a cycle, at most n distinct visits and one repeated visit occur.
// That last search costs at most O(n), so the bound remains O(n^2).
//
// SPACE COMPLEXITY
// visited stores up to n node references, with O(n) backing-array capacity.
// current and i need constant extra space.
// Auxiliary space complexity: O(n).
//
// IMPROVEMENT
// Two pointers (one moving 1 step, another moving 2) can detect a cycle
// in O(n) time and O(1) auxiliary space.
