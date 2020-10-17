/**
 * @auth Nannf
 * @date 2020/10/17 17:07
 * @description: 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
 * 实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 * <p>
 * 输入：n = 3
 * 输出：4
 * 说明: 有四种走法
 * <p>
 * 输入：n = 5
 * 输出：13
 */
public class Review_0801 {
    public static void main(String[] args) {
        System.out.println(new Review_0801().waysToStep(5));
    }

    public int waysToStep(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 4;
        }
        // 我们要如何定义呢
        // 如何从小问题 一步一步的推到大问题呢?
        // 当我们加一个台阶的时候 意味着什么？
        // 我们用dp[i]表示到达i这个位置的所有的可能
        // 如果此时加了一个台阶i+1，意味着我们的dp[i]可以一步登上来
        // 也意味者 dp[i-1] 可以
        long[] dp = new long[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for (int i = 3; i < n; i++) {
        }
        return (int)(dp[n - 1] % 1000000007);

    }
}
