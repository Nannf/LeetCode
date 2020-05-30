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

    /**
     * reverseList: head=1
     *     reverseList: head=2
     * 	    reverseList: head=3
     * 		    reverseList:head=4
     * 			    reverseList:head=5
     * 					终止返回
     * 				cur = 5
     * 				4.next.next->4，即5->4
     * 			cur=5
     * 			3.next.next->3，即4->3
     * 		cur = 5
     * 		2.next.next->2，即3->2
     * 	cur = 5
     * 	1.next.next->1，即2->1
     * @param head
     * @return
     */
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
