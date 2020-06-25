/**
 * @auth Nannf
 * @date 2020/6/25 15:36
 * @description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class Solution_53 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        System.out.println(maxSubArray(nums));
    }

    // 假设我们要计算子数组k的最大和
    // 如果我k-1的最大和大于0，那就是k-1的最大和，否则就是自己本身
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("");
        }
        int length = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0] + nums[1], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i< dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
