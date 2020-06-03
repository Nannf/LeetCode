/**
 * @author Nannf
 * @date 2020/6/3 9:20
 * @description 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class Solution_876 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        // 找中间节点,先算出链表的长度;
        int nodeSize = 0;
        ListNode tmp = head;
        while (tmp != null) {
            nodeSize++;
            tmp = tmp.next;
        }
        // 再算出中间节点
        int midSize = 0;
        if (nodeSize % 2 == 0) {
            midSize = nodeSize / 2 + 1;
        } else {
            midSize = nodeSize / 2 +1;
        }
        // 从头开始遍历,直到找到那个中间节点
        int n = 0;
        while (head != null) {
            n++;
            if (n == midSize) {
                break;
            }
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(n/2);
    }
}
