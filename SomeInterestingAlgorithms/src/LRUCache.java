/**
 * @author Nannf
 * @date 2020/5/30 20:00
 * @description
 * LRU 缓存淘汰算法，
 * LRU 最近最久未使用，链表版本
 * 每次加入时，判断当前链表的长度有没有到规定的长度，如果没有则插入，如果达到了，就选择最早插入的那个节点删除
 * 每次加入的时候需要判断当前插入的节点是否存在，如果存在，把那个节点删除，在用头插法插入节点
 */
public class LRUCache {
    // 缓存的长度
    private static final int CACHE_SIZE = 3;

    // 准备插入的数据
    private static final int[] READY_INSERT_ARRAY = new int[] {
            1,2,3,1,4,5
    };

    // 当前缓存中的数量
    private static int CURRENT_CACHE_SIZE = 0;
    static class ListNode {
        int x;
        ListNode next;
        public ListNode(int x) {
            this.x = x;
        }
    }

    public static void main(String[] args) {
        // 哨兵节点
        ListNode dummyNode = new ListNode(-1);

        // 遍历要插入的数据
        for (int i : READY_INSERT_ARRAY){
            // 要先判断这个待插入的节点存不存在
            if (contains(i,dummyNode)) {
                // 如果在这个里面,要把这个给删了,然后插一个到头节点
                reinsertNode(i,dummyNode);
                continue;
            }
            // 如果当前缓存中不包含要加入的新节点,
            // 且当前的缓存已经满了
            if (CURRENT_CACHE_SIZE == CACHE_SIZE) {
                // 把链表的最后一个节点删除
                deleteLastNode(dummyNode);
                // 把这个节点插入到链表的开始
                insertNewNode(i,dummyNode);
            } else {
                // 把这个节点插入到链表的开始
                insertNewNode(i,dummyNode);
                CURRENT_CACHE_SIZE++;
            }
        }
        // 打印链表
        printListNode(dummyNode);
    }

    private static void reinsertNode(int i, ListNode dummyNode) {
        ListNode pre = dummyNode;
        ListNode cur = dummyNode.next;
        while (cur != null) {
            if (cur.x == i) {
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        ListNode listNode = new ListNode(i);
        listNode.next = dummyNode.next;
        dummyNode.next = listNode;
    }

    private static boolean contains(int i, ListNode dummyNode) {
        ListNode cruiser = dummyNode;
        while (cruiser != null) {
           if (cruiser.x == i) {
               return  true;
           }
            cruiser = cruiser.next;
        }
        return false;
    }

    private static void insertNewNode(int i, ListNode dummyNode) {
        ListNode listNode = new ListNode(i);
        listNode.next = dummyNode.next;
        dummyNode.next = listNode;
    }

    private static void deleteLastNode(ListNode dummyNode) {
            if (dummyNode.next == null) {
                return;
            }
            ListNode preNode = dummyNode;
            ListNode headNode = dummyNode.next;
            while (headNode.next != null) {
                preNode = headNode;
                headNode = headNode.next;
            }
            preNode.next = null;
    }

    private static void printListNode (ListNode node) {
        ListNode cruiser = node.next;
        while (cruiser != null) {
            System.out.println(cruiser.x);
            cruiser = cruiser.next;
        }
    }


}
