/**
 * @auth Nannf
 * @date 2020/8/26 20:18
 * @description:
 */
public class ReverseArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        reverse(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void reverse(int[] array) {
        int size = array.length;

        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            int temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }


    }
}
