/**
 * @author X5442(时子成)
 * @date 2020/5/28 17:51
 * @description <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class Solution_1 {

    public static void main(String[] args) {
        // 审题 给一个int数组，给一个目标值，数组中有两个值加起来会等于目标值，返回这两个值在数组中的下标索引
        // 假设有且仅有一个解，且一个索引只能被使用一次
    }

    // 解法一  时间复杂度 o(n^2)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
