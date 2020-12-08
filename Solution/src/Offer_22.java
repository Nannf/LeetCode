import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/12/8 22:23
 * @description: 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 * 本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 * <p>
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * <p>
 * 返回链表 4->5.
 */
public class Offer_22 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 暴力解法，先暴力第一遍，把所有的节点和索引信息放在hash表中，然后直接获取，
    public ListNode getKthFromEnd(ListNode head, int k) {
        Map<Integer, ListNode> indexInfo = new HashMap<>();
        // count 的最终值就是链表的长度
        // 假设 长度为6 count = 6, 倒数第二个就是 6-2 下标索引是4的
        int count = 1;
        while (head != null) {
            indexInfo.put(count++, head);
            head = head.next;
        }

        return indexInfo.get(count - k);
    }
}
