/**
 * @author Nannf
 * @date 2020/11/10 14:12
 * @description 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 输入：n = 2
 * 输出：2
 * <p>
 * 输入：n = 7
 * 输出：21
 * <p>
 * 输入：n = 0
 * 输出：1
 * <p>
 * 0 <= n <= 100
 */
public class Offer_10_2 {
    public static void main(String[] args) {
        System.out.println(new Offer_10_2().numWays(7));
    }

    public int numWays(int n) {
        int[] memo = new int[n];
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (memo[n - 1] != 0) {
            return memo[n - 1];
        }
        int val = (dfs(n - 1, memo) + dfs(n - 2, memo)) % 1000000007;
        memo[n - 1] = val;
        return val;
    }

}
