import java.util.*;


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
 *
 * [26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41]
 *
 * [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
 */
public class Solution_315 {
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] nums = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        System.out.println(countSmaller_FailedAns(nums).toString());
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        if (nums.length == 1) {
            list.add(0);
            return list;
        }
        int[] numsCopy = new int[nums.length];
        System.arraycopy(nums, 0, numsCopy, 0, nums.length);
        Arrays.sort(numsCopy);
        for (int i : nums) {

        }
    }




    public static List<Integer> countSmaller_FailedAns(int[] nums) {
        map.clear();
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        if (nums.length == 1) {
            list.add(0);
            return list;
        }
        int[] numsCopy = new int[nums.length];
        System.arraycopy(nums, 0, numsCopy, 0, nums.length);
        int n = nums.length;
        sort(nums, 0, n - 1);
        for (int i : numsCopy) {
            list.add(map.get(i) == null ? 0 : map.get(i));
        }
        return list;
    }

    private static void sort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int mid = (l + h) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    private static void merge(int[] nums, int l, int mid, int h) {
        int[] tmp = new int[h - l + 1];

        int i = l;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= h) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], map.get(nums[i]) + 1);
                } else {
                    int m = i;
                    while (m <= mid) {
                        if (map.containsKey(nums[m])) {
                            map.put(nums[m], map.get(nums[m]) + 1);
                        } else {
                            map.put(nums[m], 1);
                        }
                        m++;
                    }

                }
                tmp[k++] = nums[j++];
            }
        }

        for (; i <= mid; i++) {
            tmp[k++] = nums[i];
        }
        for (; j <= h; j++) {
            tmp[k++] = nums[j];
        }
        System.arraycopy(tmp, 0, nums, l, h - l + 1);
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
