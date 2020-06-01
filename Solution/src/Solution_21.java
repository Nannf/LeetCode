/**
 * @author Nannf
 * @date 2020/6/1 8:58
 * @description 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution_21 {
    class ListNode {
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

    /**
     * 两个链表不一定一样长
     * 先计算两个链表的长度,因为链表本身是有序的,所以逐个比较即可
     * 用第三个链表来作为结果链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 结果链表,带头
        ListNode resultNode = new ListNode(-1);
        ListNode dummtNode = resultNode;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode tmp = new ListNode(l2.val);
                resultNode.next= tmp;
                resultNode = resultNode.next;
                l2 = l2.next;
            } else {
                ListNode tmp = new ListNode(l1.val);
                resultNode.next= tmp;
                resultNode = resultNode.next;
                l1 = l1.next;
            }
        }
        while ( l1 != null) {
            ListNode tmp = new ListNode(l1.val);
            resultNode.next= tmp;
            resultNode = resultNode.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode tmp2 = new ListNode(l2.val);
            resultNode.next= tmp2;
            resultNode = resultNode.next;
            l2 = l2.next;
        }
        return dummtNode.next;
    }

    /**
     * 有很多可以优化的地方
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_Solution2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 结果链表,带头
        ListNode resultNode = new ListNode(-1);
        ListNode dummtNode = resultNode;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                resultNode.next = l2;
                resultNode = resultNode.next;
                l2 = l2.next;
            } else {
                resultNode.next = l1;
                resultNode = resultNode.next;
                l1 = l1.next;
            }
        }
        if (l1 != null) {
            resultNode.next = l1;
        }
        if (l2 != null) {
            resultNode.next = l2;
        }
        return dummtNode.next;
    }
}
