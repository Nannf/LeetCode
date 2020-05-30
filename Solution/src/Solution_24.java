/**
 * @author Nannf
 * @date 2020/5/30 9:11
 * @description 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution_24 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode swapPairs(ListNode head) {
        // 先定边界条件
        if (head == null || head.next == null) {
            return head;
        }
        // 先找到最小的操作集，再循环操作，再定循环终止条件
        // 最小操作集：
        //  pre     head        next        next2
        //   ↓       ↓           ↓            ↓
        //   a       b           c            d
        //  b.next = d; c.next = b; a.next=c; 至此 bc 交换完成，pre 指向 head，head 指向 d
        // 循环终止条件 两两交换，当当前节点为空时或当前节点的next为空时，停止交换
        // 这个是交换之后的头节点
        ListNode newHeadNode = head.next;
        // 每次交换之后的前一个节点需要存储起来
        ListNode preNode = null;
        while (head != null) {
            // 当前节点的下一个节点
            ListNode nextNode = head.next;
            if (nextNode == null) {
                return newHeadNode;
            }
            // 当前节点的下两个节点
            ListNode nextNextNode = nextNode.next;
            // 交换后继节点
            head.next = nextNextNode;
            nextNode.next = head;
            // 把链表连接起来
            // 第一次执行
            if (preNode == null) {
                preNode = head;
            } else {
                preNode.next = nextNode;
                preNode = head;
            }
            head = nextNextNode;
        }
        return newHeadNode;
    }

    // 递归解法
    public ListNode swapPairs_WithRecursion(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 傻乎乎的想直接照抄链表反转的例子
//        ListNode newNode = swapPairs_WithRecursion(head.next);
        // 递归每次的处理应该是越来越小的
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = swapPairs_WithRecursion(secondNode);
        secondNode.next = firstNode;

        return secondNode;
    }

}
