/**
 * @author Nannf
 * @date 2020/12/11 20:34
 * @description
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 *
 * 说明：
 *
 * 给定的 k 保证是有效的。
 */
public class Review_0202 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public int kthToLast(ListNode head, int k) {

          ListNode slow = head;
          ListNode fast = head;
          int a = k;
          while (a > 0) {
              fast = fast.next;
              a--;
          }
          if (fast == null) {
              return slow.val;
          }
          while (fast.next != null) {
              slow = slow.next;
              fast = fast.next;
          }
        return slow.next.val;
    }
}
