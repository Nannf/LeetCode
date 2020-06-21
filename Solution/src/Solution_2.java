import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

/**
 * @auth Nannf
 * @date 2020/6/20 18:24
 * @description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Solution_2 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode result = addTwoNumbers(l1, l2);
        System.out.println(1);

    }

    public static ListNode addTwoNumbers_NotPass(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        Stack<Integer> num1 = new Stack<>();
        Stack<Integer> num2 = new Stack<>();
        while (l1 != null) {
            num1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            num2.push(l2.val);
            l2 = l2.next;
        }
        StringBuilder numSb = new StringBuilder();
        while (!num1.isEmpty()) {
            numSb.append(num1.pop());
        }
        StringBuilder numSb2 = new StringBuilder();
        while (!num2.isEmpty()) {
            numSb2.append(num2.pop());
        }
        BigInteger bigDecimal1 = new BigInteger(numSb.toString());
        BigInteger bigDecimal2 = new BigInteger(numSb2.toString());
        BigInteger result = bigDecimal1.add(bigDecimal2);
        ListNode dummyNode = new ListNode(-1);
        char[] chars = (result + "").toCharArray();
        for (char c : chars) {
            ListNode listNode = new ListNode(Integer.valueOf(String.valueOf(c)));
            listNode.next = dummyNode.next;
            dummyNode.next = listNode;
        }
        return dummyNode.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        // 哨兵节点
        ListNode dummyNode = new ListNode(-1);
        ListNode head = dummyNode;
        int sympol = 0;
        while (l1 != null && l2 != null) {
            int value = (l1.val + l2.val + sympol) % 10;
            sympol = (l1.val + l2.val + sympol) / 10;
            ListNode listNode = new ListNode(value);
            head.next = listNode;
            head = listNode;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int value = (l1.val + sympol) % 10;
            sympol = (l1.val + sympol) / 10;
            ListNode listNode = new ListNode(value);
            head.next = listNode;
            head = listNode;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = (l2.val + sympol) % 10;
            sympol = (l2.val + sympol) / 10;
            ListNode listNode = new ListNode(value);
            head.next = listNode;
            head = listNode;
            l2 = l2.next;
        }
        if ( sympol ==1) {
            ListNode listNode = new ListNode(1);
            head.next = listNode;
            head = listNode;
        }
        return dummyNode.next;
    }
}
