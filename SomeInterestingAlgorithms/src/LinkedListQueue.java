/**
 * @author Nannf
 * @date 2020/6/2 10:10
 * @description 链表实现的队列
 */
public class LinkedListQueue {
    // 带头 链表
    private ListNode data = new ListNode(-1);
    private ListNode head = data;
    private ListNode tail = data;


    // 出队
    public Object dequeue() {
        // 队列是空
        if (head.next == null) {
            return null;
        }
        // 第二个节点才是数据节点
        Object value = head.next.val;
        head.next = head.next.next;
        // 如果此时变成空节点
        if (head.next == null) {
            // 尾节点要重新指向哨兵节点
            tail = head;
        }
        return value;
    }

    public boolean enqueue(Object obj) {
        // 新节点
        ListNode listNode = new ListNode(obj);
        tail.next = listNode;
        tail = tail.next;
        return true;
    }

    // 链表的内部类
    static class ListNode {
        Object val;
        ListNode next;

        public ListNode(Object val) {
            this.val = val;
        }
    }
}
