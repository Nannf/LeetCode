/**
 * @auth Nannf
 * @date 2020/6/15 13:35
 * @description: 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[]{6, 5, 4, 16, 23, 3, 2, 1, 13};
        sort(a, 0, 8);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void sort(int[] a, int l, int r) {
        if (r <= l) {
            return;
        }
        int q = (l + r) / 2;

        sort(a, l, q);
        sort(a, q + 1, r);
        merge(a, l, q, r);
    }

    private static void merge(int[] a, int l, int q, int r) {
        int[] tmp = new int[r - l + 1];
        int k = 0;
        int i = l;
        int j = q + 1;
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        for (; i <= q; i++) {
            tmp[k++] = a[i];
        }
        for (; j <= r; j++) {
            tmp[k++] = a[j];
        }
        k = 0;
        for (int m = l; m <= r; m++) {
            a[m] = tmp[k++];
        }
    }
}
