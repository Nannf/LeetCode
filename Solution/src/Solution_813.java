import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/7/29 9:44
 * @description: 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。
 * <p>
 * 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * <p>
 * 示例:
 * 输入:
 * A = [9,1,2,3,9]
 * K = 3
 * 输出: 20
 * 解释:
 * A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * 我们也可以把 A 分成[9, 1], [2], [3, 9].
 * 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 * <p>
 * <p>
 * 说明:
 * <p>
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * 答案误差在 10^-6 内被视为是正确的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-sum-of-averages
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_813 {

    public static void main(String[] args) {
        int[] A = {9,1,2,3,9};
        System.out.println(largestSumOfAverages(A,3));
    }
    // 本题和410类似
    public static double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + A[i];
        }
        double[][] dp = new double[n + 1][K + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, K); j++) {
                for (int m = 0; m < i; m++) {
                    // 假设 [0-i) 节点之间有一点k k之前所有的已经计算完成 dp[k][j-1] 那么此时的解就是 dp[k][j-1] + (sub[i] - sub[k])/(i-k)
                    // k的取值是[0,i-1]，从这些结果中选出一个最大值即可
                    dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + (double) (sub[i] - sub[m]) / (i - m));
                }
            }
        }
        return dp[n][K];
    }
}
