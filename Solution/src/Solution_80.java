/**
 * @auth Nannf
 * @date 2020/11/23 20:03
 * @description: 给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下：
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 你不需要考虑数组中超出新长度后面的元素。
 * 提示：
 * <p>
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 按递增顺序排列
 */
public class Solution_80 {

    public static void main(String[] args) {
        new Solution_80().removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3});
    }

    public int removeDuplicates(int[] nums) {
        // 先处理边界情况
        if (nums.length <= 2) {
            return nums.length;
        }
        int delCount = 0;
        // 感觉可以先标记，再清除
        boolean[] del = new boolean[nums.length];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    delCount++;
                    del[i] = true;
                }
            } else {
                count = 1;
            }
        }
        int i = 2;
        int j = 3;
        while (j < nums.length) {
            if (del[i]) {
                if (!del[j]) {
                    nums[i] = nums[j];
                    del[j] = true;
                    i++;
                    j++;
                } else {
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }

        return nums.length - delCount;
    }
}
