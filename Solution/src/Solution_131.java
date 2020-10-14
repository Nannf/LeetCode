import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/14 20:13
 * @description: 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class Solution_131 {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> trace = new ArrayList<>();
        backtrace(ans, trace, 0, s);
        return ans;
    }

    // 问题就是如何拆分成子串呢
    private void backtrace(List<List<String>> ans, List<String> trace, int i, String s) {
        if (i == s.length()) {
            List<String> tmp = new ArrayList<>();
            if (!judgeContains(ans, trace)) {
                ans.add(tmp);
            }
            return;
        }
    }

    private boolean judgeContains(List<List<String>> ans, List<String> trace) {
        Loop1:
        for (List<String> list : ans) {
            if (list.size() != trace.size()) {
                continue;
            }
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).equalsIgnoreCase(trace.get(i))) {
                    continue Loop1;
                }
            }
            return true;
        }
        return false;
    }
}
