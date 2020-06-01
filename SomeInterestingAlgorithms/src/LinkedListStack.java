/**
 * @author Nannf
 * @date 2020/6/1 12:00
 * @description 链表实现的栈
 */
public class LinkedListStack {
    private ListNode data = new ListNode(-1);
    private int size;

   static class ListNode {
       Object val;
        ListNode next;
        public ListNode (Object val) {
            this.val = val;
        }
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }
        return removeLastNodeAndReturn();
    }

    public boolean push(Object obj) {
       return insertNewNodeToEnd(obj);
    }

    private boolean insertNewNodeToEnd(Object obj) {
       ListNode cur = data;
       ListNode pre = data;
       while (cur != null) {
           pre = cur;
           cur = cur.next;
       }
       ListNode tmp = new ListNode(obj);
       pre.next = tmp;
       size ++;
       return  true;
    }


    private Object removeLastNodeAndReturn() {
        ListNode head = data;
        // 第一个节点是头节点
        if (head.next == null) {
            return null;
        }
        ListNode pre = data;
        while (head.next != null) {
            pre = head;
            head = head.next;
        }
        Object obj = pre.next.val;
        pre.next = null;
        size --;
        return obj;
    }

}
