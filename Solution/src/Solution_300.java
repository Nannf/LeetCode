import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(new Solution_300().lengthOfLIS(nums));
    }


    // 这题关于状态的定义其实听关键的，我们可以定义dp[i]为下标在[0,i]之间的最长子序列的长度
    // 这样的话，dp[n-1]就是我们要求的解，但是 这地方有个问题，就是dp[i]和i这个值关系其实不是很大的。
    // 我们在做状态转移的时候，从i-1 --> i 的时候，不知道上一个状态的具体位置，这个只是我个人的一点理解，也是参照题解给出的答案理解出来的
    // 这是不是就意味者，我们定义的状态是要跟这个下标有联系的呢？ 这个我们后期多做题，多总结
    // 还有一种定义方式，就是，把dp[i]定义为以nums[i]结尾的最长上升子序列的长度的解。
    // 这样在 i -1 --> i 的时候，从0遍历到i-1 如果在遍历的过程中，nums[i] 大于遍历的值，那么dp[i]的一个解就是大于的那个下标，我们记为j dp[j]+1
    // 最后我们从遍历得到的解集合中找到一个最大值，即为dp[i]的最终解
    // 这里面需要先明确一个事情，就是
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    tmp = Math.max(tmp, dp[j]);
                }
            }
            dp[i] = tmp + 1;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

}
