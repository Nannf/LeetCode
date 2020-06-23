/**
 * @auth Nannf
 * @date 2020/6/23 15:41
 * @description: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Solution_33 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,3};
        System.out.println(new Solution_33().search(nums,3));
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 ){
           if ( nums[0] == target) {
               return 0;
           }
           return -1;
        }
        // 先找出数组中的分界点的下标索引，使用二分查找查询出数组中第一个小于数组第一个数值的下标索引
        int partIndex = findPartIndex(nums);
        System.out.println("数组在第" + partIndex + "个位置开始逆转");
        if (partIndex == -1) {
            partIndex = nums.length;
        }
        if (target >= nums[0]) {
            return binarySearch(nums, 0, partIndex - 1,target);
        } else {
            return binarySearch(nums, partIndex, nums.length - 1,target);
        }
    }

    private int binarySearch(int[] nums, int l, int h, int target) {
        while (l <= h) {
            int mid = l + ((h - l) >>1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                h = mid -1;
            } else {
                l = mid+1;
            }
        }
        return -1;
    }

    private int findPartIndex(int[] nums) {
        return findMinIndex(nums, nums[0], 0, nums.length - 1);
    }

    // nums = [4,5,6,7,0,1,2] 4
    private int findMinIndex(int[] nums, int target, int p, int r) {
        while (p <= r) {
            int mid = p + ((r - p) >> 1);
            if (nums[mid] < target) {
                if (mid == 0 || nums[mid - 1] >= target) {
                    return mid;
                }
                r = mid - 1;
            } else {
                p = mid + 1;
            }
        }
        return -1;
    }
}
