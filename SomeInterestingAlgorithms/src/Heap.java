/**
 * @auth Nannf
 * @date 2020/7/3 14:22
 * @description: 堆
 */
public class Heap {
    private int[] a;
    private int n;
    private int elementCount;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(10);
        heap.insert(9);
        heap.insert(8);
        heap.insert(7);
        heap.insert(11);
        heap.remove();
        for (int i : heap.a) {
            System.out.println(i);
        }
    }

    public void insert(int data) {
        if (n == elementCount) {
            return;
        }
        elementCount++;
        a[elementCount] = data;
        int head = elementCount;
        int parent = elementCount / 2;
        while (a[head] > a[parent] && parent > 0) {
            int tmp = a[head];
            a[head] = a[parent];
            a[parent] = tmp;
            head = parent;
            parent = parent / 2;
        }
    }

    public void remove() {
        if (elementCount == 0) {
            return;
        }
        // 拿最后一个节点的值替换根节点的值
        a[1] = a[elementCount];
        a[elementCount] = 0;
        elementCount--;
        int i = 1;
        int left = 2;
        int right = 3;
        // 当子节点下标没有过界，并且当前的节点小于子节点的值
        while (right <= elementCount && (a[i] < a[left] || a[i] < a[right])) {
            if (a[left] > a[right]) {
                int tmp = a[left];
                a[left] = a[i];
                a[i] = tmp;
                i = left;
                left = i * 2;
                right = i * 2 + 1;
            } else {
                int tmp = a[right];
                a[right] = a[i];
                a[i] = tmp;
                i = right;
                left = i * 2;
                right = i * 2 + 1;
            }
        }

    }

    /**
     * 0,7,5,19,8,4,1,20,13,16
     * 0,20,16,19,13,4,1,7,5,8
     * @param a
     */
    public void buildHead(int[] a) {

    }

}
