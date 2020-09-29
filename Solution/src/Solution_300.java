import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/9/29 20:12
 * @description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 */
public class Solution_300 {


    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new Solution_300().lengthOfLIS(nums));
    }

    // 我们要找的其实是每个数，后面比他大的数有多少
    // 看到最值，我的脑海中又浮现了动态规划
    // 这个要怎么归呢？
    // 动态规划都是以小推大
    // 如果数组只有一个值，那答案就是1
    // 如果数组中有两个值，那如果第二个值比第一个大，就是2，如果小就还是1
    // 题目并没有要求 子序列必须是连续的
    // 我们回到最开始，假设i个数的最大值是k 问当i+1时，最大值是多少
    // 这个的问题就在，你不能把一个数之后的所有比这个数大的数都作为连续上升序列，
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int n = nums.length;
        // 我们用 dp[i][j] 表示下标从i-j的最长的子序列
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0);
            dp[i][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
            }
        }
        return ans;
    }
}
