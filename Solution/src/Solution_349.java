import java.util.*;

/**
 * @auth Nannf
 * @date 2020/11/2 21:35
 * @description: 给定两个数组，编写一个函数来计算它们的交集。
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 */
public class Solution_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> ans = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                ans.add(nums1[i]);
                i++;
                j++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                i++;
                continue;
            }
            j++;
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(ans);
        int[] ss = new int[list.size()];
        for (int s =0; s<list.size();s++) {
            ss[s] = list.get(s);
        }
        return ss;
    }
}
