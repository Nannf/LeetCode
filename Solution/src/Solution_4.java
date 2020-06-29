/**
 * @auth Nannf
 * @date 2020/6/29 20:41
 * @description: 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution_4 {

    public static void main(String[] args) {
        int[]nums1 = new int[]{1,2};
        int[]nums2 = new int[]{3,4};
        System.out.println(new Solution_4().findMedianSortedArrays(nums1,nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return caluMedianNum(nums2);
        }
        if (nums2 == null || nums2.length == 0) {
            return caluMedianNum(nums1);
        }
        int l1 = nums1.length;
        int l2 = nums2.length;
        int n3[] = new int[l1 + l2];
        int i = 0, j = 0;
        int count = 0;
        while (i < l1 && j < l2) {
            if (nums1[i] <= nums2[j]) {
                n3[count++] = nums1[i++];
            } else {
                n3[count++] = nums2[j++];
            }
        }
        if (i < l1) {
            System.arraycopy(nums1, i, n3, count, l1 - i);
        } else {
            System.arraycopy(nums2, j, n3, count, l2 - j);
        }

        return caluMedianNum(n3);
    }

    private double caluMedianNum(int nums[]) {
        int n = nums.length;
        if (n % 2 == 0) {
            return ((double)nums[n / 2 - 1] + (double)nums[n / 2]) / 2;
        }
        return nums[n / 2];
    }
}
