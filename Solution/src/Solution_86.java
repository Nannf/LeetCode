/**
 * @auth Nannf
 * @date 2020/12/12 23:26
 * @description: 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class Solution_86 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = first.next;
        while (second != null) {
            if (first.val >= x) {
                if (second.val < x) {
                    convertNodes(first, second);
                    first = first.next;
                    second = second.next;
                } else {
                    second = second.next;
                }
            } else {
                first = first.next;
                second = first.next;
            }
        }
        return head;
    }

    private void convertNodes(ListNode first, ListNode second) {
        ListNode n1 = first;
        ListNode n2 = second;
        while (n1 != n2) {
            int t = n1.val;
            n1.val = n2.val;
            n2.val = t;
            n1 = n1.next;
        }
    }
}
