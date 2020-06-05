/**
 * @author Nannf
 * @date 2020/6/5 16:07
 * @description 冒泡排序
 */
public class BubbleSort {
    private static final int[] ARRAY_TO_SORT = new int[]{
            1, 5, 4, 2, 6, 7
    };

    public static void main(String[] args) {
        sort2();
        for (int i : ARRAY_TO_SORT) {
            System.out.println(i);
        }
    }

    // 冒泡排序
    // 每次找到尚未排序的最大的那個
    public static void sort() {
        for (int i = 0; i < ARRAY_TO_SORT.length; i++) {
            // 是否有数据交换，当一次比较没有数据交换时，代表数组已经有序，无须继续循环
            boolean flag = false;
            // 每次都从头比较，如果i=1 表示是第二次比较，只要比较倒数第二个和倒数第三个即可，依次类推
            for (int j = 0; j < ARRAY_TO_SORT.length - i - 1; j++) {
                // 如果是>就是稳定排序，如果是>=就是不稳定排序
                if (ARRAY_TO_SORT[j] > ARRAY_TO_SORT[j + 1]) {
                    int tmp = ARRAY_TO_SORT[j];
                    ARRAY_TO_SORT[j] = ARRAY_TO_SORT[j + 1];
                    ARRAY_TO_SORT[j + 1] = tmp;
                    // 有数据交换
                    flag = true;
                }

            }
            // 没有数据交换，数组已经有序，退出循环
            if (!flag) {
                break;
            }
        }
    }

    // 这个和上面的区别是每次都是找到最小的那个
    public static void sort2() {
        for (int i = 0; i < ARRAY_TO_SORT.length; i++) {
            for (int j = i + 1; j < ARRAY_TO_SORT.length; j++) {
                if (ARRAY_TO_SORT[i] > ARRAY_TO_SORT[j]) {
                    int tmp = ARRAY_TO_SORT[j];
                    ARRAY_TO_SORT[j] = ARRAY_TO_SORT[i];
                    ARRAY_TO_SORT[i] = tmp;
                }

            }
        }
    }
}
