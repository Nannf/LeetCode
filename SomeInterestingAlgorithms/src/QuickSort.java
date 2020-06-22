/**
 * @auth Nannf
 * @date 2020/6/21 18:24
 * @description: 快排
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a= new int[] { 2,3,4,5,8,5};
        sort(a,6);
        for(int i : a) {
            System.out.println(i);
        }
    }


    public static void sort(int[] a, int n) {
        sort_c(a, 0, n - 1);
    }

    public static void sort_c(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partion(a, p, r);
        sort_c(a, p, q - 1);
        sort_c(a, q + 1, r);
    }

    private static int partion(int[] a, int p, int r) {
        int i = p;
        int x = a[r];
        for (int j = p; j < r; j++) {
            // 如果当前遍历的值不小于分区值，不做移动
            if (a[j] > x) {
                continue;
            }
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }
}
