import java.util.ArrayList;
import java.util.List;

/**
 * @author Nannf
 * @version v1.0
 * @date 2021/10/5 20:36
 * @Description
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 *输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * 1 <= n <= 8
 */
public class Solution_22 {

    // 这个用回溯很好解决
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        backtrace(ans,0,n);
        return ans;
    }

    private void backtrace(List<String> ans, int i, int n) {
    }
}
