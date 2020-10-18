/**
 * @auth Nannf
 * @date 2020/10/18 15:00
 * @description: 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class Review_1617 {
    public static void main(String[] args) {
        System.out.println(new Review_1617().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {

        // dp[i] 表示以i索引为结尾的子序列的最大值
        int[] dp = new int[nums.length];

        dp[0] = Math.max(0, nums[0]);
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans,dp[i]);
        }
        return ans;

    }
}
