class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // n = first list size, m = second list size
        // k = how many times the loop runs
        ListNode dummy = new ListNode(0); // 1 unit of time
        ListNode tail = dummy; // 1 unit of time

        while (list1 != null && list2 != null) { // k + 1 units of time
            if (list1.val <= list2.val) { // k units of time
                tail.next = list1; // 1 unit when used
                list1 = list1.next; // 1 unit when used
            } else {
                tail.next = list2; // 1 unit when used
                list2 = list2.next; // 1 unit when used
            }
            // if OR else runs, so together these lines take 2k units
            tail = tail.next; // k units of time
        }
        if (list1 != null) { // 1 unit of time
            tail.next = list1; // 1 unit when used
        } else {
            tail.next = list2; // 1 unit when used
        }
        return dummy.next; // 1 unit of time
    }
}

// Frequency count method
// f(k) = 1 + 1 + k + 1 + k + 2k + k + 1 + 1 + 1
// f(k) = 5k + 6
// Worst case: k = n + m - 1
// f(n,m) = 5n + 5m + 1 -> O(n + m)
// We take one node each time. When one list ends, we attach the rest.
// Best case: one list is empty -> O(1)
//
// Space complexity (simple count, not bytes)
// input nodes = n + m
// new dummy node = 1
// list1, list2, dummy, tail references = 4
// S(n,m) = n + m + 5 -> O(n + m)
