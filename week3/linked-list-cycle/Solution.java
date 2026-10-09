import java.util.ArrayList;

class Solution {
    public boolean hasCycle(ListNode head) {
        ArrayList<ListNode> visited = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            for (int i = 0; i < visited.size(); i++) {
                if (visited.get(i) == current) {
                    return true;
                }
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }
}
