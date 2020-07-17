/**
 * @auth Nannf
 * @date 2020/7/17 10:32
 * @description: 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 6, 7, 8, 8, 9, 10};
        System.out.println(binarySearch_FindFirstLarge(nums, 8));
    }

    /**
     * 基础版本，有返回索引，没有返回-1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_Base(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找到最后一个小于等于给定值的元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_FindLastSmall(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 当mid 的值小于给定的值的时候，我们要判断是否是最后一个
            if (nums[mid] <= target) {
                // 如果当前的mid 已经是最后一个元素，或者mid的下一个元素比给定的大了，说明这个就是我们要找的
                if (mid == n - 1 || nums[mid + 1] > target) {
                    return mid;
                } else {
                    // 否则，这个结果肯定出现在mid+1 - high 中
                    low = mid + 1;
                }
            }
            if (nums[mid] > target) {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个相等的下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_FindLastMatcher(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 当mid 的值小于给定的值的时候，我们要判断是否是最后一个
            if (nums[mid] == target) {
                // 如果当前的mid 已经是最后一个元素，或者mid的下一个元素比给定的大了，说明这个就是我们要找的
                if (mid == n - 1 || nums[mid + 1] > target) {
                    return mid;
                } else {
                    // 否则，这个结果肯定出现在mid+1 - high 中
                    low = mid + 1;
                }
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定元素的
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_FindFirstLarge(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 当mid 的值大于等于给定的值的时候，我们要判断是否是第一个
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
