/**
 * @auth Nannf
 * @date 2020/11/24 21:44
 * @description: 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 说明：
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * 输入：
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 提示：
 * <p>
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1.length == m + n
 * nums2.length == n
 * <p>
 * [4,0,0,0,0,0]
 * 1
 * [1,2,3,5,6]
 * 5
 */
public class Solution_88 {

    public static void main(String[] args) {
        new Solution_88().merge(new int[]{4, 0, 0, 0, 0, 0}, 1, new int[]{1, 2, 3, 5, 6}, 5);
        System.out.println(1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            if (i == m + j) {
                System.arraycopy(nums2, j, nums1, m + j, n - j);
                return;
            }
            if (nums2[j] <= nums1[i]) {
                System.arraycopy(nums1, i, nums1, i + 1, m + j - i);
                nums1[i] = nums2[j];
                j++;
                i++;
            } else {
                i++;
            }
        }
    }
}
