/**
 * @auth Nannf
 * @date 2020/6/24 21:04
 * @description: 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class Solution_16 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest(nums,1));
    }
    // 如何定义最近呢？
    // 列举出所有的可能性吗 然后算出最小的值，最后返回
    // 时间复杂度 O(n^3)
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int distance = Integer.MAX_VALUE;
        int value = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int tmp =Math.abs( nums[i] + nums[j] +nums[k] - target);
                    if (tmp < distance) {
                        distance = tmp;
                        value = nums[i] + nums[j] +nums[k];
                    }
                }
            }
        }
        return value;
    }
}
