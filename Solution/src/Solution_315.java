import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/11 14:54
 * @description: 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class Solution_315 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        System.out.println(countSmaller(nums).toString());
    }


    public  static List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        if (nums.length == 1) {
            list.add(0);
            return list;
        }
        int n = nums.length;
        int[] nums2 = new int[n];
        System.arraycopy(nums, 0, nums2, 0, n);
        Arrays.sort(nums);
        for (int i = 0; i < nums2.length -1; i++) {
            int index = findIndex(nums2[i], nums);
            int tmp = index - i;
            if (tmp > 0) {
                list.add(tmp);
            } else if (tmp == 0) {
                if (index > 0) {
                    list.add(1);
                } else {
                    list.add(0);
                }
            } else {
                list.add(0);
            }
        }
        list.add(0);
        return list;
    }

    private static int findIndex(int i, int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        return find(nums, i, l, h);
    }

    private static int find(int[] nums, int i, int l, int h) {
        int mid = (l + h) / 2;
        if (nums[mid] == i) {
            return mid;
        }
        if (nums[mid] > i) {
            h = mid -1;
            return find(nums,i,l,h);
        } else {
            l = mid + 1;
            return find(nums,i,l,h);
        }
    }


    /**
     * 暴力解法 O(n^2) 超时
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller_Ans1(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            list.add(i, count);
        }
        return list;
    }
}
