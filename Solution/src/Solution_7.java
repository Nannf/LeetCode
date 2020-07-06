/**
 * @auth Nannf
 * @date 2020/7/5 19:52
 * @description: 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 输入: 123
 * 输出: 321
 */
public class Solution_7 {
    public static void main(String[] args) {
        System.out.println(new Solution_7().reverse(-1563847412));
    }

    // -2147483648 <= x <= 2147483647
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
        }
        return ans;

    }

    public int reverse2(int x) {
        if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return 0;
        }
        int y = Math.abs(x);
        // 思路是 转成char数组
        String str = String.valueOf(Math.abs(x));
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        if (sb.length() == 0) {
            return 0;
        }
        if (Long.parseLong(sb.toString()) > Integer.MAX_VALUE) {
            return 0;
        }
        if (x < 0) {
            return Integer.parseInt(sb.toString()) * -1;
        }
        return Integer.parseInt(sb.toString());
    }
}
