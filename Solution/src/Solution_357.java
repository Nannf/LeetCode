/**
 * @auth Nannf
 * @date 2020/9/25 19:13
 * @description: 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class Solution_357 {
    int ans = 0;

    public static void main(String[] args) {
        System.out.println(new Solution_357().countNumbersWithUniqueDigits(2));
    }

    // 暴力解法，超时
    public int countNumbersWithUniqueDigits(int n) {
        for (double i = 0d; i < Math.pow(10, n); i++) {
            if (occured(i)) {
                continue;
            }
            ans++;
        }
        return ans;
    }

    private void backtrace(double i, int n) {

        if (i >= Math.pow(10, n)) {
            System.out.println("bingo" + i);
            return;
        }
        System.out.println("before" + i);
        if (occured(i)) {
            return;
        }
        System.out.println("after" + i);
        ans++;
        backtrace(i + 1, n);
    }

    private boolean occured(double i) {
        int[] memo = new int[10];
        String str = String.valueOf(i);
        char[] array = str.toCharArray();
        for (int k = 0; k < array.length; k++) {
            if (!Character.isDigit(array[k])) {
                break;
            }
            int t = Integer.parseInt(String.valueOf(array[k]));
            if (memo[t] != 0) {
                return true;
            }
            memo[t] = 1;
        }
        return false;
    }
}
