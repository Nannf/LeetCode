/**
 * @author Nannf
 * @date 2020/5/28 21:56
 * @description <p>
 * Reverse a singly linked list.
 */
public class Solution_206 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    // 递归的实现
    public ListNode reverseList(ListNode head) {
        // 递归的终止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node1 = reverseList(head.next);
        ListNode t1 = head.next;
        t1.next = head;
        head.next = null;
        return node1;
    }



}
