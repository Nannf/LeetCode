import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        int[] nums = {1,1,2};
        new Solution_47().permuteUnique(nums);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        // 这边保存的都是下标索引
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrace(ans, trace, nums);

        return ans;

    }

    private List<List<Integer>> convert(List<List<Integer>> ans, int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : ans) {
            List<Integer> tmp = new ArrayList<>();
            for (int i : list) {
                tmp.add(nums[i]);
            }
            result.add(tmp);
        }
        return result;
    }

    private void backtrace(List<List<Integer>> ans, List<Integer> trace, int[] nums) {
        if (trace.size() == nums.length) {
            if (unique(trace, ans,nums)) {
               List<Integer> list = new ArrayList<>();
               for (int i : trace) {
                   list.add(nums[i]);
               }
               ans.add(list);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(i)) {
                continue;
            }
            trace.add(i);
            backtrace(ans,trace,nums);
            trace.remove(trace.size() -1);
        }
    }

    private boolean unique(List<Integer> trace, List<List<Integer>> ans, int[] nums) {
        Loop1:
        for (List<Integer> list : ans) {
            for (int i = 0; i < list.size(); i++) {
                if (nums[trace.get(i).intValue()] != list.get(i)) {
                    continue Loop1;
                }
            }
            return false;
        }
        return true;
    }

}
