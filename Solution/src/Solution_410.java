import java.util.Arrays;

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

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        System.out.println(splitArray(nums, 3));
    }

    // 最值 想到动态规划
    // 满足多阶段决策最优解模型吗？
    public static int splitArray(int[] nums, int m) {
        int n = nums.length;
        if (m > n) {
            throw new IllegalArgumentException("m 不能超过给定的数组大小！");
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        if (m <= 0) {
            return 0;
        }
        if (m == 1) {
            return sub[n];
        }
        if (m == n) {
            Arrays.sort(nums);
            return nums[n - 1];
        }
        // 为什么用动态规划呢
        // 我们拿数组 {1,2,3,4}来举例子  在我们计算dp[4][3]的时候我们发现 我们先要依赖于 dp[2,1] 接着依赖 dp[3][2]存在重复的子问题
        // dp[i][j] 表示 i个数被拆成j段的解
        // 数组是从0开始计算的，这边的长度要加一
        // 最后 dp[n][m] 就是我们要求的解
        int[][] dp = new int[n + 1][m + 1];
        // 这个是由后面的递推公式得出的
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        // 表示数组的长度
        for (int i = 1; i <= n; i++) {
            // 表示能被拆成多少段 有效的取值是 [1,i] 因为题目只要求m段，所以取m和i的最小值
            for (int j = 1; j <= Math.min(i, m); j++) {
                // 把 i个数 拆成 j 段 如何把问题缩小呢？
                // 如果要 计算 i 划分成 j 段的结果 我们从 [0,i) 中间 任取一值 记为k 假设 [0,k] 之间的数据已经划分成了 j-1 段 且 结果为 dp[k][j-1]
                // 那么 对这一个确定的k 而言 它的划分结果 的数组的最大值是 ans(k) = Math.max(dp[k][j-1],sum(k+1,i));
                // k的取值范围是多少呢 [0,i-1] 其中当k = 0 时，出了dp[0][0] 其他的都是无效答案，那么问题的解，就是遍历k 取所有解中的最小值
                // 这边有一个问题，k为什么不从1开始取，而是要从0开始取，因为我们知道 k 的值 是要小于i的，如果我们要计算的值只有一个数，如果k=1的话，这时计算不出结果，除非我们对dp[1][1] 进行初始化
                // 确定了 k的取值，我们发现有很多无效的答案，比如dp[1][3]，这些无效的值 我们是置为0 还是很大的值 还是很小的值？
                // 这个空想不是很好想 我们拿个实际的情况来举例子，假设我们要计算的数组是 {1,2,3,4} 我们现在在计算 dp[4][3] ，我们讨论的前提是 所有的值加起来不会越过Integer的最大值的界
                // 会依次计算 dp[0][3],dp[1][3],dp[2][3],dp[3,3] 假设我们的取值是最小值，因为我们的结果取每个k位置对应的最大值 的最小值
                for (int k = 0; k < i; k++) {
                    // 因为我们在k移动的时候 会拿当前k计算的结果和之前计算的结果进行比较，取两者的最小值，所以，所有的值在一开始都应该被设为最大值
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return dp[n][m];
    }
}
