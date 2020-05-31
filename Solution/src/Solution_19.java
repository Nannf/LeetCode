/**
 * @author Nannf
 * @date 2020/5/31 11:01
 * @description 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class Solution_19 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 给定链表,删除,
     * 要删除给定的倒数第几个,先第一轮循环,得出链表的总节点数,下一轮遍历时,记录
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 哨兵节点
        ListNode dummyNode  = new ListNode(-1);
        dummyNode.next = head;

        // 一遍遍历得出链表的节点数
        ListNode tmpNode = head;
        int nodeNum =0;
        while (tmpNode != null) {
            nodeNum ++;
            tmpNode = tmpNode.next;
        }
        // 再计算正数遍历到第几个的时候需要删除
        // 假设一共有两个节点,倒数第一个,正数第二个
        // 一共五个,倒数第一个,正数第五个,倒数第二个,正数第四个,倒数第三个,正数第三个
        // 由上可得,假设总节点数是m,倒数第n个,那正数就是 m+1-n 个
        int whichNum = nodeNum - n + 1;
        if (whichNum < 1) {
            throw new IllegalArgumentException("不合法的参数!");
        }
        // 第二遍遍历删除
        int count = 0;
        ListNode preNode = dummyNode;
        ListNode node = head;
        while (node!= null) {
            count++;
            if (count == whichNum) {
                preNode.next = node.next;
                break;
            }
            preNode = node;
            node = node.next;
        }
        return dummyNode.next;
    }
}
