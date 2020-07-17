/**
 * @auth Nannf
 * @date 2020/7/17 9:18
 * @description: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class Solution_35 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        System.out.println(new Solution_35().searchInsert(nums, 5));
    }

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // 如果中间的值大于等于给定的值，
            if (nums[mid] >= target) {
                // 如果已经是第一个值，或者前一个数小于给定的数值
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 没找到
        return n;
    }


    public int searchInsert_ans1(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int l, int r) {
        if (l >= r) {
            if (nums[l] < target) {
                return l + 1;
            }
            if (nums[l] > target) {
                return l;
            }
        }
        int mid = (l + r) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return search(nums, target, l, mid - 1);
        } else {
            return search(nums, target, mid + 1, r);
        }
    }
}
