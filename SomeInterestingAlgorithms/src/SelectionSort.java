/**
 * @auth Nannf
 * @date 2020/6/10 16:56
 * @description: 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] a = new int[]{7, 6, 5, 4, 3, 2, 1, 42};
        sort(a, 7);
        for (int i : a) {
            System.out.println(i);
            ;
        }
    }

    public static void sort(int[] a, int n) {
        // 如果排序数组的长度小于等于1 不用排序，直接返回
        if (n <= 1) {
            return;
        }
        //

        // 选择排序就是从未排序的字段中选择一个最小的放置在最前面
        // 最外层的循环就是确定排序后的位置
        for (int i = 0; i < n - 1; i++) {
            // 先假定最小的值的索引就是当前的i
            int minIndex = i;
            // 索引从 i + 1 开始循环，一直找到最小的那个索引
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    minIndex = j;
                }
            }
            // 把两个字段进行交换，这是原地排序， 时间复杂度是O(1)
            // 并不是稳定的排序算法
            // 5 3 5 2 1
            // 排序后两个5的位置就交换了
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }
}
