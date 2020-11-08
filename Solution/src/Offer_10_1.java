/**
 * @auth Nannf
 * @date 2020/11/8 12:33
 * @description: 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * <p>
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 输入：n = 2
 * 输出：1
 */
public class Offer_10_1 {


    public int fib(int n) {
        int[] memo = new int[n];
        return dfs(n,memo);

    }

    private int dfs(int n, int[] memo) {
        if (n<=1) {
            return n;
        }
        if (memo[n-1] != 0) {
            return memo[n-1];
        }
        int ans =  dfs(n-1,memo)+dfs(n-2,memo);
        memo[n-1] = ans % 1000000007 ;
        return memo[n-1];
    }
}
