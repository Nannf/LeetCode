/**
 * @auth Nannf
 * @date 2020/6/10 14:54
 * @description: 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
       int[] a = new int[]{7,6,5,4,3,2,1};
       sort(a,7);
       for (int i : a) {
           System.out.println(i);;
       }
    }

    /**
     * @param a 待排序的数组
     * @param n 待排序数组的长度
     */
    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        // 从 数组的第二个数字开始，每次都从上一个索引开始找，一直找到数组的开头，找到合适的位置插入进去
        for (int i = 1; i < n; i++) {
            // 要先保存起来，这样数组中i这个位置就空出来了，给后面的移动创造了条件
            int value = a[i];
            int j = i -1;
            // 从i的上一个开始找起，一直找到数组开始
            for (; j >= 0; j--) {
                    // 如果前一个数字比当前的数字大
                    if (a[j] > value) {
                        // 把那个数字后移一位
                        a[j+1] = a[j];
                    } else {
                        break;
                    }
            }
            // 插入
            a[j+1] = value;
        }
    }
}
