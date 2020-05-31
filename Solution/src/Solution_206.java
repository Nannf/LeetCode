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
     * reverseList: head=2
     * reverseList: head=3
     * reverseList:head=4
     * reverseList:head=5
     * 终止返回
     * cur = 5
     * 4.next.next->4，即5->4
     * cur=5
     * 3.next.next->3，即4->3
     * cur = 5
     * 2.next.next->2，即3->2
     * cur = 5
     * 1.next.next->1，即2->1
     *
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

    /**
     * 迭代解法
     * 这个思路受链表节点交换的影响
     *
     * @param head
     * @return
     */
    public ListNode reverseList_Solution2(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 如何记录反转后的头节点
        // 把每个节点的后继改为前驱即可
        ListNode preNode = null;
        // 找到终止条件,反转需要两个节点
        while (head != null) {
            if (head.next == null) {
                head.next = preNode;
                break;
            }
            // 反转的最小操作集 是这两个节点
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // 记录下第二个节点的后继节点,当成下一个head
            ListNode tmpNode = secondNode.next;

            // 还需要记录 firstNode 的前驱节点
            firstNode.next = preNode;
            secondNode.next = firstNode;
            // 如果后继节点为空
            if (tmpNode == null) {
                head = secondNode;
                break;
            }
            head = tmpNode;
            preNode = secondNode;
        }
        return head;
    }

    public ListNode reverseList_Solution3(ListNode head) {
        // 反转需要记录节点的前一个节点
        ListNode preNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            // 防止链表中断,还需要记录下当前节点的后继节点
            // 这就要求cruNode 不能为null
            ListNode tmpNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tmpNode;
        }
        return preNode;
    }


}
