// Standalone test runner; embedded solution mirrors Solution.java.
class Test {
    static ListNode list(int... values) {
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int value : values) { tail.next = new ListNode(value); tail = tail.next; }
        return dummy.next;
    }
    static void check(int[] a, int[] b, int[] expected) {
        ListNode left = list(a), right = list(b);
        java.util.ArrayList<ListNode> nodes = new java.util.ArrayList<>();
        ListNode x = left, y = right;
        while (x != null && y != null) {
            if (x.val <= y.val) { nodes.add(x); x = x.next; }
            else { nodes.add(y); y = y.next; }
        }
        while (x != null) { nodes.add(x); x = x.next; }
        while (y != null) { nodes.add(y); y = y.next; }
        ListNode result = new Solution().mergeTwoLists(left, right);
        for (int i = 0; i < expected.length; i++) {
            if (result == null || result.val != expected[i] || result != nodes.get(i))
                throw new AssertionError("Merge mismatch at " + i);
            result = result.next;
        }
        if (result != null) throw new AssertionError("Unexpected extra nodes");
    }
    public static void main(String[] args) {
        check(new int[]{}, new int[]{}, new int[]{});
        check(new int[]{}, new int[]{0}, new int[]{0});
        check(new int[]{0}, new int[]{}, new int[]{0});
        check(new int[]{1,2,4}, new int[]{1,3,4}, new int[]{1,1,2,3,4,4});
        check(new int[]{-3,0,0}, new int[]{-2,0,5,8}, new int[]{-3,-2,0,0,0,5,8});
        check(new int[]{1}, new int[]{2,3,4}, new int[]{1,2,3,4});
        System.out.println("Merge: 6 checks passed");
    }
}

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}
