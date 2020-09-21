import java.util.*;

/**
 * @auth Nannf
 * @date 2020/9/18 21:54
 * @description: 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Solution_47 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        new Solution_47().permuteUnique(nums);
    }

    // 重复的本质是因为给定的列表中有重复的元素，导致在做选择时，选择下标A的结果，和选择下标B的结果一模一样
    // 造成了子树的重复问题，因为全排列可以理解为，不同的根到叶子节点的路径集合
    // 我们在判断下一个数需不需要取的时候，我们需要
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrace(ans, trace, nums);

        return ans;

    }

    private void backtrace(List<List<Integer>> ans, List<Integer> trace, int[] nums) {
        if (trace.size() == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : trace) {
                list.add(nums[i]);
            }
            ans.add(list);
        }
        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(i)) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !trace.contains(i - 1)) {
                continue;
            }
            trace.add(i);
            backtrace(ans, trace, nums);
            trace.remove(trace.size() - 1);
        }
    }

}
