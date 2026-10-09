class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Frequency Count Method: one statement/condition = 1 unit of time.
        // n and m are input lengths; k is the number of loop iterations.
        ListNode dummy = new ListNode(0); // 1 unit of time
        ListNode tail = dummy; // 1 unit of time

        while (list1 != null && list2 != null) { // k + 1 units of time
            if (list1.val <= list2.val) { // k units of time
                tail.next = list1; // 1 unit each time this branch runs
                list1 = list1.next; // 1 unit each time this branch runs
            } else {
                tail.next = list2; // 1 unit each time this branch runs
                list2 = list2.next; // 1 unit each time this branch runs
            }
            // Only one branch runs, so branch statements total 2k units.
            tail = tail.next; // k units of time
        }
        if (list1 != null) { // 1 unit of time
            tail.next = list1; // 1 unit if selected
        } else {
            tail.next = list2; // 1 unit if selected
        }
        return dummy.next; // 1 unit of time
    }
}

// Time complexity:
// f(k) = 1 + 1 + (k + 1) + k + 2k + k + 1 + 1 + 1
// f(k) = 5k + 6
// Each iteration takes one node. Worst case: k = n + m - 1,
// when both lists are nonempty and stay interleaved until the end.
// f(n, m) = 5(n + m - 1) + 6 = 5n + 5m + 1
// Time complexity = O(n + m).
// Best case: one input is empty, k = 0, f = 6, O(1).
//
// Space complexity (logical storage units, not bytes):
// Input nodes: n + m (each node has constant size).
// Dummy node: 1.
// References list1, list2, dummy, tail: 4.
// S(n, m) = n + m + 1 + 4 = n + m + 5.
// Total space including inputs = O(n + m).
// Extra space only = 1 + 4 = 5 = O(1).
// Moving references does not copy nodes. The result reuses input nodes.
