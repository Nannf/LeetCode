/**
 * @auth Nannf
 * @date 2020/6/28 20:24
 * @description:
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class Solution_209 {

    public int minSubArrayLen(int s, int[] nums) {
        int min = nums.length+1;
        for (int i = 0; i<nums.length;i++) {
            int sum = nums[i];
            int count = 1;
            if (sum >= s) {
                return 1;
            }
            for (int j = i+1; j<nums.length;j++) {
                sum += nums[j];
                count++;
                if (sum>= s) {
                    min = Math.min(min,count);
                    break;
                }
            }
        }
        if (min> nums.length) {
            return 0;
        }
        return min;


    }
}
