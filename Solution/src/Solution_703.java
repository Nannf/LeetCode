/**
 * @auth Nannf
 * @date 2020/6/22 21:48
 * @description: 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 */
public class Solution_703 {
    int k;
    int[] data;
    int size;

    public Solution_703(int k, int[] nums) {
        this.k = k;
        // 只维护k个数据的小顶堆
        this.data = new int[k + 1];
        init(nums);
    }

    private void init(int[] nums) {
        if (nums.length < k) {
            for (int i = 0; i < nums.length; i++) {
                insert(nums[i]);
            }
        } else {
            for (int i = 0; i < k; i++) {
                insert(nums[i]);
            }
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > data[1]) {
                    remove();
                    insert(nums[i]);
                }
            }
        }


    }

    public void remove() {
        if (size == 0) {
            System.out.println("堆已为空，无法删除");
            return;
        }
        // 拿最后一个节点的值替换根节点的值
        // 这一步的目的是为了保证树在删除后仍然是完全二叉树
        data[1] = data[size];
        data[size--] = 0;
        // 然后首节点要下沉
        heapify(data, size, 1);
    }

    public void insert(int num) {
        // 先把数据插到数组的最后
        data[++size] = num;
        //小顶堆 插入进来之后 可能需要上浮，上浮的终止条件的浮到根节点
        int head = size;
        while (data[head] < data[head / 2] && (head / 2) > 0) {
            // 交换节点
            swap(data, head, head / 2);
            head = head / 2;
        }
    }

    private void heapify(int[] a, int size, int i) {
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

    public static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }


    public int add(int val) {
        if (size < k) {
            insert(val);
            return data[1];
        }
        if (val <= data[1]) {
            return data[1];
        }
        remove();
        insert(val);
        return data[1];
    }

    public static void main(String[] args) {
        Solution_703 heap = new Solution_703(1,new int[]{});
        System.out.println(heap.add(-3));
        System.out.println(heap.add(-2));
        System.out.println(heap.add(-4));
        System.out.println(heap.add(0));
        System.out.println(heap.add(4));
    }

}
