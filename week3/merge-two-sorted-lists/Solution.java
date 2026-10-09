class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Counting model: each statement or condition below costs 1 unit.
        // A whole condition is counted as one constant-time operation.
        // n = length of list1, m = length of list2.
        // k = number of while-loop iterations.
        ListNode dummy = new ListNode(0); // 1 unit of time
        ListNode tail = dummy; // 1 unit of time

        while (list1 != null && list2 != null) { // k + 1 units: includes final check
            if (list1.val <= list2.val) { // k units total
                tail.next = list1; // 1 unit when this branch runs
                list1 = list1.next; // 1 unit when this branch runs
            } else {
                tail.next = list2; // 1 unit when this branch runs
                list2 = list2.next; // 1 unit when this branch runs
            }
            // Only ONE branch runs: the two branches together cost 2k units.
            tail = tail.next; // k units total
        }
        if (list1 != null) { // 1 unit of time
            tail.next = list1; // 1 unit if this branch runs
        } else {
            tail.next = list2; // 1 unit if this branch runs
        }
        // Attaching the remaining chain costs 1 unit, not its length.
        return dummy.next; // 1 unit of time
    }
}

// TIME COMPLEXITY
// T = 1 + 1 + (k + 1) + k + 2k + k + 1 + 1 + 1
// T = 5k + 6 units in the counting model above.
// Each iteration takes one node from an input list.
// If both inputs are nonempty, k <= n + m - 1.
// Worst-case time complexity: O(n + m).
// We drop constant factors and fixed extra work.
// If one input starts empty, k = 0 and time is O(1).
//
// SPACE COMPLEXITY
// One extra dummy node and a fixed number of references are used.
// The original nodes are reused; no growing array or recursion is needed.
// Auxiliary space complexity: O(1).
