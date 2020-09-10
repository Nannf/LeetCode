import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 * @auth Nannf
 * @date 2020/9/10 19:25
 * @description: 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Solution_40 {


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrace(ans, trace, candidates, target, 0);
        return ans;

    }

    /**
     * @param ans
     * @param trace      存放数组的下标索引
     * @param candidates
     * @param target
     */
    private void backtrace(List<List<Integer>> ans, List<Integer> trace, int[] candidates, int target, int sum) {
        if (sum == target) {
            addList(ans, trace, candidates);
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (trace.contains(i)) {
                continue;
            }
            trace.add(i);
            sum += candidates[i];
            backtrace(ans, trace, candidates, target, sum);
            sum -= candidates[i];
            trace.remove(trace.size() - 1);
        }
    }

    private void addList(List<List<Integer>> ans, List<Integer> trace, int[] candidates) {
        List<Integer> traceAfterConvert = convertTrace(trace, candidates);
        Collections.sort(traceAfterConvert);
        for (List<Integer> list : ans) {
            if (judgeDunplicate(list,traceAfterConvert)) {
                return;
            }
        }
        ans.add(traceAfterConvert);
    }

    private boolean judgeDunplicate(List<Integer> list, List<Integer> traceAfterConvert) {
        if (list.size() != traceAfterConvert.size()) {
            return false;
        }
        for (int i = 0; i< list.size();i++) {
            if (list.get(i) != traceAfterConvert.get(i)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> convertTrace(List<Integer> trace, int[] candidates) {
        List<Integer> list = new ArrayList<>();
        for (int i : trace) {
            list.add(candidates[i]);
        }
        return list;
    }
}
