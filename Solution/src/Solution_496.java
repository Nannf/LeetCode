/**
 * @author Nannf
 * @date 2020/6/4 23:09
 * @description 给定两个 没有重复元素 的数组 nums1 和 nums2 ，
 * 其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2
 *  中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 */
public class Solution_496 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] result = nextGreaterElement(nums1, nums2);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resultArray = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                // 因为是子集，所以一定能找到相等的那个
                if (nums2[j] == nums1[i]) {
                    boolean flag = false;
                    for (int k = j; k < nums2.length; k++) {
                        if (nums2[k] > nums1[i]) {
                            flag = true;
                            resultArray[i] = nums2[k];
                            break;
                        }
                    }
                    if (!flag) {
                        resultArray[i] = -1;
                    }
                }

            }
        }
        return resultArray;
    }

}
