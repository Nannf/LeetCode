import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/8/6 17:22
 * @description: 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Solution_152 {

    public static void main(String[] args) {
        int[] nums = {-2,0,-1};
        System.out.println(new Solution_152().maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        // 不能从前往后动态规划，因为这样会依赖后面的值

        // 每个位置的数据的情况 拿 [-2,-3,-8,1]为例
        // 当i=0时 就有四种情况，什么也不连，就是-2，连一个就是6 连两个就是48,连三个就是-48 这样取所有结果的最大值就是结果
        int[][] dp = new int[n + 1][n];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n - 1 - i; j++) {
                dp[i][j] = dp[i+1][j] * nums[i];
                ans = Math.max(dp[i][j],ans);
            }
        }
        return ans;
    }
}
