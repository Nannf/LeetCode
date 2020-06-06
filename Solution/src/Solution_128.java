import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/6/6 18:00
 * @description: 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Solution_128 {
    private static final int[] TEST = new int[]{
            2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645
    };

    public static void main(String[] args) {
        System.out.println(new Solution_128().longestConsecutive(TEST));
    }

    // 先用暴力解法，再分析暴力解法无用的地方，对其进行优化
    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("不合法的参数");
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        // 先放到set中去重，然后后面查找时时间复杂度是O(1)
        Set<Integer> numSet = new HashSet<>();
        for (int i : nums) {
            numSet.add(i);
        }
        int maxResult = 0;

        // 循环每一个值，设为A，到数组中去寻找A+1，A+2...存不存在
        for (int i : numSet) {
            // 长度包含自己
            int length = 1;

            int board = i + numSet.size() - 1;
            // 越界了
            if (board < 0) {
                board = Integer.MAX_VALUE;
            }
            for (int j = i + 1; j <= board; j++) {
                if (numSet.contains(j)) {
                    length++;
                } else {
                    break;
                }
            }
            if (length > maxResult) {
                maxResult = length;
            }
        }
        return maxResult;
    }
}
