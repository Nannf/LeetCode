import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/11 14:54
 * @description:
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 */
public class Solution_315 {


    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        for (int i = 0; i <nums.length;i++) {
            int count = 0;
            for (int j = i+1; j< nums.length;j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            list.add(i,count);
        }
        return list;
    }

    /**
     * 暴力解法 O(n^2) 超时
     * @param nums
     * @return
     */
    public List<Integer> countSmaller_Ans1(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        for (int i = 0; i <nums.length;i++) {
            int count = 0;
            for (int j = i+1; j< nums.length;j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            list.add(i,count);
        }
       return list;
    }
}
