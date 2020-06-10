/**
 * @auth Nannf
 * @date 2020/6/10 16:56
 * @description: 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] a = new int[]{7,6,5,4,3,2,1};
        sort(a,7);
        for (int i : a) {
            System.out.println(i);;
        }
    }

    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        int minNum = Integer.MAX_VALUE;
        int minIndex = -1;

        // 选择排序就是从未排序的字段中选择一个最小的放置在最前面
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (a[j] < minNum) {
                    minNum = a[j];
                    minIndex = j;
                }
            }
            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex ] = tmp;
            minNum = Integer.MAX_VALUE;
            minIndex = -1;
        }
    }
}
