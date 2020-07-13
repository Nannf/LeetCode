import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/13 9:33
 * @description: 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class Solution_350 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        int[] ans = intersect(nums1,nums2);
        for (int i : ans) {
            System.out.println(i);
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0 || l2 == 0) {
            return new int[0];
        }
        int l = Math.min(l1,l2);
        int[] tmp = new int[l];
        int i = 0, j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int k = 0;
        while (i < l1 && j < l2) {
            if (nums1[i] == nums2[j]) {
                tmp[k++] = (nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] ans = new int[k];
        System.arraycopy(tmp,0,ans,0,k);
        return ans;
    }
}
