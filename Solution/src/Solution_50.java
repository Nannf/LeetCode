/**
 * @author Nannf
 * @date 2020/11/18 8:57
 * @description 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class Solution_50 {
    public static void main(String[] args) {
        System.out.println(new Solution_50().myPow(3, 5));
    }

    public double myPow(double x, int n) {
        if (n > 0) {
            return dfs(x, n);
        } else {
            long b = -n;
            return 1 / dfs(x, b);
        }
    }

    // 栈溢出
    private double dfs(double x, long n) {
        if (n == 0) {
            return 1;
        }
        long b = n / 2;
        if (n % 2 != 0) {
            return x * dfs(x * x, b);
        } else {
            return  dfs(x * x, b);
        }
    }
}
