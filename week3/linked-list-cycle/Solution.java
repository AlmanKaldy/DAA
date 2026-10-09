import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

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
