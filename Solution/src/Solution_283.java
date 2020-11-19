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
        int[] nums = {-1, 1, 0, 3, 12};
        new Solution_283().moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public void moveZeroes(int[] nums) {
        // 先对数组进行排序，使非零元素相对有序
        // 题干中没有声明说都是自然数，我们需要考虑负数的情况
        int[] firstZeroIndexAndZeroNum = findFirstZeroIndex(nums);
        if (firstZeroIndexAndZeroNum[0] == -1) {
            return;
        }
        Arrays.sort(nums);
        int firstNotZeroIndex = firstZeroIndexAndZeroNum[0] + firstZeroIndexAndZeroNum[1];
        System.arraycopy(nums, firstNotZeroIndex,
                nums, firstZeroIndexAndZeroNum[0], nums.length - firstNotZeroIndex);
        Arrays.fill(nums, nums.length - firstZeroIndexAndZeroNum[1], nums.length, 0);
    }


    private int[] findFirstZeroIndex(int[] nums) {
        int[] ans = new int[2];
        int smallThanZeroCount = 0;
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                smallThanZeroCount++;
            }
            if (nums[i] == 0) {
                zeroCount++;
            }
        }
        if (zeroCount == 0) {
            ans[0] = -1;
            return ans;
        }
        ans[0] = smallThanZeroCount;
        ans[1] = zeroCount;
        return ans;
    }
}
