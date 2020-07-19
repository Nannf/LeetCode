/**
 * @auth Nannf
 * @date 2020/7/19 13:22
 * @description: 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * <p>
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * <p>
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 */
public class Solution_441 {

    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }

    public static int arrangeCoins(int n) {
        // ans 一定是小于n的，但是小于多少，不确定，用二分法查找

        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            double key = mid + Math.pow(mid, 2);
            // 当正好相等的时候 表示刚好用完
            if (key == 2d * n) {
                return mid;
            }
            // 如果比结果大了 表示要减了
            if (key > 2d * n) {
                high = mid - 1;
            }
            // 比结果小了 不一定要加，
            if (key < 2d * n) {
                if (mid + 1 + Math.pow((mid + 1), 2) > 2d * n) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return 0;
    }
}
