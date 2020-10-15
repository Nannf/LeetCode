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

    public  List<List<String>> findAllSub(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> trace = new ArrayList<>();
        backtrace(ans, trace, s, 0);
        return ans;
    }

    private  void backtrace(List<List<String>> ans, List<String> trace, String srcString, int startIndex) {
        if (startIndex >= srcString.length()) {
            ans.add(new ArrayList<>(trace));
            return;
        }
        for (int i = 1; i <= srcString.length() - startIndex; i++) {
            String tmp = srcString.substring(startIndex, startIndex + i);
            if (isDupulicate(tmp)) {
                trace.add(tmp);
                backtrace(ans, trace, srcString, startIndex + i);
                trace.remove(trace.size() - 1);
            }
        }
    }

    private  boolean isDupulicate(String tmp) {
        if (tmp.length() == 1) {
            return true;
        }
        int i = 0;
        int j = tmp.length() -1;
        while (i<=j) {
            if (tmp.charAt(i++) != tmp.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
