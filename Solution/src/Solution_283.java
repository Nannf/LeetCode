import java.util.Arrays;

/**
 * @author Nannf
 * @date 2020/11/19 16:51
 * @description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Solution_283 {
    public static void main(String[] args) {
        int[] nums = {-1, -11, 0, 0, 12};
        new Solution_283().moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    // 先对数组进行排序，使非零元素相对有序
    // 题干中没有声明说都是自然数，我们需要考虑负数的情况
    // 理解错了，不是把不是0的元素从小到大排列
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = i + 1;
        while (j < nums.length) {
            if (nums[i] == 0) {
                if (nums[j] != 0) {
                    nums[i] = nums[j];
                    nums[j] = 0;
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

    }


}
