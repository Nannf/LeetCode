import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/5 11:32
 * @description: 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_46 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        for (List<Integer> list : new Solution_46().permute(nums)) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        dfs(nums, trace, ans);
        return ans;
    }

    private void dfs(int[] nums, List<Integer> trace, List<List<Integer>> ans) {
        if (trace.size() == nums.length) {
            List<Integer> tmp = new ArrayList<>();
            tmp.addAll(trace);
            ans.add(tmp);
            return;
        }
        for (int i : nums) {
            if (!trace.contains(i)) {
                trace.add(i);
                dfs(nums, trace, ans);
                trace.remove(trace.size() - 1);
            }
        }
    }
}
