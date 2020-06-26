import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/6/26 12:21
 * @description: 面试题 02.01
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 */
public class Review_0201 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode pre = head;
        set.add(pre.val);
        ListNode cur = head.next;
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
                cur = pre.next;
            } else {
                set.add(cur.val);
                pre = cur;
                cur = cur.next;
            }

        }
        return head;
    }
}
