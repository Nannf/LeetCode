/**
 * @auth Nannf
 * @date 2020/7/8 10:02
 * @description: 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class Solution_295 {
    // 大顶堆,存放排序后数组的前半部分
    int[] maxHeap = new int[101];
    // 小顶堆，存放排序后数组的后半部分
    int[] minHeap = new int[101];
    int maxHeapSize = 0;
    int minHeapSize = 0;

    /**
     * initialize your data structure here.
     */
    public Solution_295() {

    }

    public static void main(String[] args) {
        Solution_295 heap = new Solution_295();
        heap.addNum(-1);
        System.out.println(heap.findMedian());
        heap.addNum(-2);
        System.out.println(heap.findMedian());
        heap.addNum(-3);
        System.out.println(heap.findMedian());
        heap.addNum(-4);
        System.out.println(heap.findMedian());
        heap.addNum(-5);
        System.out.println(heap.findMedian());
    }

    public void addNum(int num) {
        // 先判断大小，如果已经存满了 就把数组的长度扩大一倍
        if (minHeapSize + 1 == minHeap.length) {
            int[] tmp = new int[minHeap.length << 2];
            System.arraycopy(minHeap, 0, tmp, 0, minHeap.length);
            minHeap = tmp;
        }
        if (maxHeapSize + 1 == maxHeap.length) {
            int[] tmp = new int[maxHeap.length << 2];
            System.arraycopy(maxHeap, 0, tmp, 0, maxHeap.length);
            maxHeap = tmp;
        }
        // 如果要加入的字段比大顶堆的最大值还大
        if (num > maxHeap[1]) {
            // 插入到小顶堆后面
            mininsert(num, minHeap, minHeapSize);
            minHeapSize++;
            // 小顶堆只能比大顶堆的数量小1或者相等
            if (minHeapSize > maxHeapSize) {
                maxinsert(minHeap[1], maxHeap, maxHeapSize);
                maxHeapSize++;
                minRemove(minHeap, minHeapSize);
                minHeapSize--;
            }
        } else {
            // 插入到大顶堆
            maxinsert(num, maxHeap, maxHeapSize);
            maxHeapSize++;
            // 大顶堆的数量只能和小顶堆相等或者大于一
            if (maxHeapSize - minHeapSize > 1) {
                mininsert(maxHeap[1], minHeap, minHeapSize);
                minHeapSize++;
                maxRemove(maxHeap, maxHeapSize);
                maxHeapSize--;
            }
        }
    }

    public double findMedian() {
        if ((maxHeapSize + minHeapSize) % 2 == 0) {
            return ((double) minHeap[1] + (double) maxHeap[1]) / 2;
        }
        return (double) maxHeap[1];
    }

    private void minHeapify(int[] a, int size, int i) {
        // 记录当前节点 和 其左右孩子节点中 值最小的那个下标
        int minpos = i;
        while (true) {
            // 先比较左节点
            if (i * 2 <= size && a[i * 2] < a[i]) {
                minpos = i * 2;
            }
            // 再比较右节点
            if (i * 2 + 1 <= size && a[i * 2 + 1] < a[minpos]) {
                minpos = i * 2 + 1;
            }
            // 如果三个节点最大的是自己，说明下沉到了最低点
            if (minpos == i) {
                break;
            }
            //否则，交换，接着下沉
            swap(a, i, minpos);
            i = minpos;
        }
    }

    private void maxHeapify(int[] a, int size, int i) {
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

    public static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public void minRemove(int[] data, int size) {
        // 拿最后一个节点的值替换根节点的值
        // 这一步的目的是为了保证树在删除后仍然是完全二叉树
        data[1] = data[size];
        data[size--] = 0;
        // 然后首节点要下沉
        minHeapify(data, size, 1);
    }

    public void maxRemove(int[] data, int size) {
        // 拿最后一个节点的值替换根节点的值
        // 这一步的目的是为了保证树在删除后仍然是完全二叉树
        data[1] = data[size];
        data[size--] = 0;
        // 然后首节点要下沉
        maxHeapify(data, size, 1);
    }

    public void mininsert(int num, int[] data, int size) {
        // 先把数据插到数组的最后
        data[++size] = num;
        //大顶堆 插入进来之后 可能需要上浮，上浮的终止条件的浮到根节点
        int head = size;
        while (data[head] < data[head / 2] && (head / 2) > 0) {
            // 交换节点
            swap(data, head, head / 2);
            head = head / 2;
        }
    }

    public void maxinsert(int num, int[] data, int size) {
        // 先把数据插到数组的最后
        data[++size] = num;
        //大顶堆 插入进来之后 可能需要上浮，上浮的终止条件的浮到根节点
        int head = size;
        while (data[head] > data[head / 2] && (head / 2) > 0) {
            // 交换节点
            swap(data, head, head / 2);
            head = head / 2;
        }
    }
}
