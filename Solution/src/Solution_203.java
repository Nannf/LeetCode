/**
 * @author Nannf
 * @version v1.0
 * @date 2021/10/13 17:52
 * @Description 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * <p>
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class Solution_203 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(7);
        ListNode second = new ListNode(7);
        ListNode third = new ListNode(7);
        ListNode fourth = new ListNode(7);
        first.next = second;
        second.next = third;
        third.next = fourth;

        ListNode ans = new Solution_203().removeElements(first,7);

        System.out.println(1);

    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null ) {
            return head;
        }
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        ListNode cur = dummyNode;
        while (cur != null) {
            // 找到下一个节点
            ListNode next = cur.next;
            // 如果下一个节点是空，直接返回
            if (next == null) {
                return dummyNode.next;
            }
            // 如果 下一个节点的值是给定的值，直接删除
            if (next.val == val) {

                cur.next = next.next;
                cur = cur.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyNode.next;

    }


}
