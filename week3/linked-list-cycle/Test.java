import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
// Standalone test runner; embedded solution mirrors Solution.java.
class Test {
    static void check(boolean expected, int pos, int... values) {
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 1; i < nodes.length; i++) nodes[i-1].next = nodes[i];
        if (pos >= 0) nodes[nodes.length-1].next = nodes[pos];
        ListNode head = nodes.length == 0 ? null : nodes[0];
        if (new Solution().hasCycle(head) != expected) throw new AssertionError("Cycle mismatch");
    }
    public static void main(String[] args) {
        check(false, -1);
        check(false, -1, 1);
        check(true, 0, 1);
        check(false, -1, 2,2,2);
        check(true, 0, 1,2);
        check(true, 1, 3,2,0,-4);
        System.out.println("Cycle: 6 checks passed");
    }
}

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }
}
