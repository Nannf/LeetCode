
/**
 * @auth Nannf
 * @date 2020/7/3 14:22
 * @description: 堆
 * 代码中给出的示例是大顶堆，小顶堆类似
 */
public class Heap {

    private static final int DEFAULT_CAPACITY = 100;
    /**
     * 存放数据的数组
     */
    private int[] a;
    /**
     * 堆的最大容量
     */
    private int capacity;

    /**
     * 堆中存放数据的个数
     */
    private int size;

    private Heap(int capacity) {
        this.a = new int[capacity + 1];
        this.size = 0;
        this.capacity = capacity + 1;
    }

    private Heap(int[] a) {
        this.a = a;
        this.size = a.length - 1;
        this.capacity = DEFAULT_CAPACITY;
    }


    public static void main(String[] args) {
        int[] a = new int[]{0, 7, 5, 19, 8, 4, 1, 20, 13, 16};
//        testInsert();
//        testRemove();
        Heap heap = new Heap(a);
//        heap.buildHeap2();
        heap.sort();
        printHeap(heap.a);

    }


    public void insert(int data) {
        // 暂时没考虑扩容
        if (size == capacity) {
            System.out.println("堆已满，无法添加新元素！");
            return;
        }
        // 先把数据插到数组的最后
        a[++size] = data;
        //大顶堆 插入进来之后 可能需要上浮，上浮的终止条件的浮到根节点
        int head = size;
        while (a[head] > a[head / 2] && (head / 2) > 0) {
            // 交换节点
            swap(a, head, head / 2);
            head = head / 2;
        }
    }


    public void remove() {
        if (size == 0) {
            System.out.println("堆已为空，无法删除");
            return;
        }
        // 拿最后一个节点的值替换根节点的值
        // 这一步的目的是为了保证树在删除后仍然是完全二叉树
        a[1] = a[size];
        a[size--] = 0;
        // 然后首节点要下沉
        heapify(a, size, 1);
    }

    private void heapify(int[] a, int size, int i) {
        int maxpos = i;
        while (true) {
            // 先比较左节点
            if (i * 2 <= size && a[i * 2] > a[i]) {
                maxpos = i * 2;
            }
            // 再比较右节点
            if (i * 2 + 1 <= size && a[i * 2 + 1] > a[maxpos]) {
                maxpos = i * 2 + 1;
            }
            // 如果三个节点最大的是自己，说明下沉到了最低点
            if (maxpos == i) {
                break;
            }
            //否则，交换，接着下沉
            swap(a, i, maxpos);
            i = maxpos;
        }
    }

    /**
     * 0,7,5,19,8,4,1,20,13,16
     * 0,20,16,19,13,4,1,7,5,8
     */
    public void buildHeap(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            insert(a[i]);
        }
    }

    /**
     * 0,7,5,19,8,4,1,20,13,16
     * 0,20,16,19,13,4,1,7,5,8
     */
    public void buildHeap2() {
        // 最后一个非叶子节点出现的索引
        int lastPlace = size / 2;
        for (int i = lastPlace; i > 0; i--) {
            heapify(a, size + 1, i);
        }
    }


    public void sort() {
        // 先从数组中建堆
        buildHeap2();
        int k = size;
        while (k > 1) {
            swap(a, k, 1);
            k--;
            heapify(a, k, 1);
        }
    }

    public void sort2() {
        // 先从数组中建堆
        buildHeap2();
        int[] result = new int[size];
        while (size > 0) {
            result[size -1] = a[1];
            remove();
        }

    }


    public static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void printHeap(int[] a) {
        for (int i = 1; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }


    private static void testRemove() {
        Heap heap = new Heap(9);
        heap.insert(7);
        heap.insert(5);
        heap.insert(19);
        heap.insert(8);
        heap.insert(4);
        heap.insert(1);
        heap.insert(20);
        heap.insert(13);
        heap.insert(16);
        heap.remove();
        printHeap(heap.a);
    }

    private static void testInsert() {
        Heap heap = new Heap(9);
        heap.insert(7);
        heap.insert(5);
        heap.insert(19);
        heap.insert(8);
        heap.insert(4);
        heap.insert(1);
        heap.insert(20);
        heap.insert(13);
        heap.insert(16);
        printHeap(heap.a);
    }

}
