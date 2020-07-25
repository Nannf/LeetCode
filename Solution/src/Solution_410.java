/**
 * @auth Nannf
 * @date 2020/7/25 13:56
 * @description: 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * <p>
 * 注意:
 * 数组长度 n 满足以下条件:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * <p>
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * 输出:
 * 18
 * <p>
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */
public class Solution_410 {
    // 最值 想到动态规划
    // 满足多阶段决策最优解模型吗？
    public int splitArray(int[] nums, int m) {
        // 本题默认m<=n 不用额外的判断
        int n = nums.length;
        // dp[i][j] 表示前i个数被分成j段所得到的最大连续子数组的最小值
        // 题目要求的是 前n个数被分成m段所得到的最大连续子数组的最小值
        // 解题思路和数学归纳法类似，先找一个中间数k k<=i f[k][j-1] 表示前k个数被分成j-1段所得到的最大值记为A，然后 还有一个 k+1~i 之间的所有的和加起来值记为B 取 max(A,B)就是一个最大值的解
        // 然后把 k = 0~i 的所有的值汇合起来，取所有最大值中的最小值 即为f[i][j] 的解
        // j <= i
        int[][] dp = new int[n][m];
        // 外层的循环是
        for (int i = 0; i < n; i++) {
            // 里层就是k k从 1 - i+1
            for (int j = 1; j <= i + 1; j++) {
                int a = dp[j][j- 1];
                int b = countB(nums, j -1, i);
                dp[i][j] = Math.min(Math.max(a, b), dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    private int countB(int[] nums, int start, int end) {
        int sum = 0;

        for (int i = start; i <= end; ++i) {
            sum += nums[i];
        }
        return sum;
    }
}
