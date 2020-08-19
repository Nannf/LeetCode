import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println(new Solution_410().splitArray(nums, 2));
    }

    // 使最大值 最小
    public  int splitArray(int[] nums, int m) {
        long ans = 0;
        // 我们知道所有和的最大值 不会小于 数组中的最大值
        // 所有和的最大值 不会大于 数组中所有数的和
        // 我们以这两个数字 为边界，
        long low = 0, high = 0;
        for (int i = 0; i < nums.length; i++) {
            high += nums[i];
            low = Math.max(low, nums[i]);
        }
        // 二分查找这边的条件是使用 low < high作为终止条件，还是 low <= high 作为终止条件？
        //
        while (low <= high) {
            long mid = low + ((high - low) >> 1);
            if (check(nums, m, mid)) {
                ans = mid;
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        return (int)ans;
    }

    /**
     * mid 是我们假设的最大值
     * 我们使用贪心算法
     *
     * @param nums
     * @param m
     * @param mid
     * @return
     */
    private  boolean check(int[] nums, int m, long mid) {
        int cnt = 1;
        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 等于mid 的话，表示还是可以作为一个数组的
            if (nums[i] + first > mid) {
                cnt++;
                first = nums[i];
            } else {
                first = first + nums[i];
            }
        }
        // 如果最后划分出的数组数量比实际的要多说明了什么，说明给定的mid值比实际的值要小，多就意味着 假设 多且只多一个，那么这个值理应被分到其中的
        // 一个数组里面，为什么没有被分进去，因为那个数组的值再加上这个数字就超过了规定的值，说明我们设置的数组的上限小了

        // 如果最后划分出的数组数量比实际的要少说明了什么，按照上面的思路，说明有些值应该被划出来，但是没有被划出来，说明我们设置的mid 太大了

        // 如果最后划分出的数组数量和实际的一样，我们 要返回啥，这里以[1,7,2,8,5]，m=3举例，第一次的mid 就是  12,按照这个mid就划分出了满足条件的3个
        // 但是12 并不是我们的解，因为我们知道，最后的解是10， 那如果划分的数量 恰好相等，我们假设最后的解是 x,那么x的取值，和这个mid的关系是啥样的呢？
        // 我们假设 x > mid, 这个不成立，因为扩大取值会导致数组数量的变小，本例是没办法扩大的，但是如果我把数组变成 1,7,2,8,6,如果mid 变大 似乎也是对的
        // 如此推理 x < mid 也是可能成立的，
        // 但是我们需要找到最小的那个x的值，在我们不能判断mid 是否就是唯一解的时候，我们需要把mid往小了移动
        if (cnt > m) {
            return false;
        }
        return true;

    }


    // 最值 想到动态规划
    // 满足多阶段决策最优解模型吗？
    public static int splitArray_Dp(int[] nums, int m) {
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
