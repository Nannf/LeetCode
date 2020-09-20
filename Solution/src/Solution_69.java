/**
 * @auth Nannf
 * @date 2020/9/18 22:45
 * @description: 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 输入: 4
 * 输出: 2
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class Solution_69 {

    public static void main(String[] args) {
        System.out.println(new Solution_69().mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int ans = 0;
        int low = 1;
        int high = x - 1;

        while (low <= high) {

            long mid = low + ((high - low) >> 1);
            long mm = mid * mid;

            if (mm == x) {
                return (int) mid;
            }
            if (mm > x) {
                if ((mid - 1) * (mid - 1) > x) {
                    high = (int) (mid - 1);
                } else {
                    return (int) (mid - 1);
                }
            } else {
                if ((mid + 1) * (mid + 1) > x) {
                    return (int) mid;
                } else {
                    low = (int) (mid + 1);
                }
            }

        }

        return ans;
    }
}
