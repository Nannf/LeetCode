
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

    public Heap(int[] a, int capacity) {
        this.a = a;
        n = capacity;

    }

    public static void main(String[] args) {
//        Heap heap = new Heap(10);
//        heap.insert(10);
//        heap.insert(9);
//        heap.insert(8);
//        heap.insert(7);
//        heap.insert(11);
//        heap.remove();
//        for (int i : heap.a) {
//            System.out.println(i);
//        }
        int[] a = new int[]{0, 7, 5, 19, 8, 4, 1, 20, 13, 16};
        Heap heap = new Heap(a, 100);
        heap.buildHeap2();

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
     */
    public void buildHeap() {
        for (int i = 1; i < a.length; i++) {
            insert(a[i]);
        }
        for (int i : a) {
            System.out.println(i);
        }
    }

    /**
     * 0,7,5,19,8,4,1,20,13,16
     * 0,20,16,19,13,4,1,7,5,8
     */
    public void buildHeap2() {
        // 最后一个非叶子节点出现的索引
        int lastPlace = (a.length - 1) / 2;
        for (int i = lastPlace; i > 0; i--) {
            swapNode(i);
        }
        for (int i : a) {
            System.out.println(i);
        }
    }

    private void swapNode(int i) {
        // 当当前节点至少有一个孩子节点的时候
        while (i * 2 <= a.length || i * 2 + 1 <= a.length) {
            int childMax = 0;
            // 如果右孩子大于规定的大小
            if (i * 2 + 1 > n) {
                childMax = a[i * 2];
            } else {
                childMax = Math.max(a[i * 2], a[i * 2 + 1]);
            }
            if (a[i] < childMax) {
                if (a[i * 2] == childMax) {
                    int tmp = a[i * 2];
                    a[i * 2] = a[i];
                    a[i] = tmp;
                    i = i * 2;
                } else {
                    int tmp = a[i * 2 + 1];
                    a[i * 2 + 1] = a[i];
                    a[i] = tmp;
                    i = i * 2 + 1;
                }
            }

        }

    }

}
