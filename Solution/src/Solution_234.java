import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/23 20:19
 * @description: 请判断一个链表是否为回文链表。
 * <p>
 * 输入: 1->2
 * 输出: false
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * <p>
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Solution_234 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        // 先转成数组，然后通过数组的随机访问的特性完成回文的判断
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = list.size() -1;
        while (i<=j) {
            if (list.get(i).intValue() == list.get(j).intValue()) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
