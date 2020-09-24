import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/9/24 16:55
 * @description: 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * <p>
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class Solution_44 {

    public static void main(String[] args) {
        if (new Solution_44().isMatch("aa", "*")) {
            System.out.println("bingo");
        }
    }

    public boolean isMatch(String s, String p) {
        // S串当前下标位置，刚开始为0
        int index = 0;
        // 下一个状态集合
        // 这里状态用p串的下标表示，代表下一次可以从p的这些下标开始匹配
        Set<Integer> nextState = new HashSet<>();
        // 由于是刚开始匹配，这时候p下一个下标是0
        nextMatch(p, 0, nextState);
        // nextState不为空时，表示还有合法的下一个状态，匹配继续
        while (!nextState.isEmpty()) {
            // 当前状态就是上一次的nextState
            Set<Integer> nowState = nextState;
            // 创建新的nextState
            nextState = new HashSet<>();
            // 测试s[index]和集合里的状态是否有匹配
            for (int state : nowState) {
                // 如果同时到达s和p串末尾，匹配成功
                if (state >= p.length() && index >= s.length()) {
                    return true;
                }
                // 仅仅p到达末尾还不行
                else if (state >= p.length()) {
                    continue;
                }
                // s和p都未到达末尾
                else if (index < s.length()) {
                    // 这里是匹配上的情况
                    if (p.charAt(state) == '.' || s.charAt(index) == p.charAt(state)) {
                        // 如果p串的下一个字符是'*'，当前状态可以匹配任意多次，所以下一个状态还是当前
                        if (state + 1 < p.length() && p.charAt(state + 1) == '*') {
                            nextMatch(p, state, nextState);
                        }
                        // 否则，下一个状态就是state+1
                        else {
                            nextMatch(p, state + 1, nextState);
                        }
                    }
                }
            }
            index++;
        }
        // 此时，nextState为空，代表没有合法的下一个状态了，匹配失败
        return false;
    }

    // p:正则表达式
    // state:下一个状态
    // nextState:下一个状态集合，无重复
    private void nextMatch(String p, int state, Set<Integer> nextState) {
        // 首先加上下一个状态到状态集中
        nextState.add(state);
        // 如果当前的元素是*的话，那么合法的状态
        if (p.charAt(state) == '*') {

        }
        // 这里是判断下一个字符是'*'的情况，由于此时匹配次数可以是0，所以state+2也是合法的下一个状态
        if (state + 1 < p.length() && p.charAt(state + 1) == '*') {
            nextMatch(p, state + 2, nextState);
        }
    }

















    boolean find = false;
    public boolean isMatch_backtrace_withMemo(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        int[][] memo = new int[s.length() + 1][p.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(memo[i], 0);
        }
        backtrace(s.toCharArray(), 0, p.toCharArray(), 0, memo);

        return find;
    }

    private void backtrace(char[] s, int sIndex, char[] p, int pIndex, int[][] memo) {
        if (find) {
            return;
        }
        if (memo[sIndex][pIndex] != 0) {
            return;
        }
        memo[sIndex][pIndex] = 1;
        int sLen = s.length;
        int pLen = p.length;
        if (pIndex >= pLen) {
            if (sIndex >= sLen) {
                find = true;
            }
            return;
        }

        if (p[pIndex] == '*') {
            if (sIndex >= sLen) {
                for (;pIndex<pLen;pIndex++) {
                    if (p[pIndex] != '*') {
                        return;
                    }
                }
                find = true;
                return;
//                backtrace(s, sIndex, p, pIndex + 1, memo);
            } else {
                for (int k = 1; k + sIndex <= sLen; k++) {
                    if (find) {
                        return;
                    }
                    backtrace(s, sIndex + k, p, pIndex, memo);
                }

                // *代表空
                backtrace(s, sIndex, p, pIndex + 1, memo);


            }
        } else {
            if (sIndex >= sLen) {
                return;
            }
            if (p[pIndex] == s[sIndex] || p[pIndex] == '?') {
                backtrace(s, sIndex + 1, p, pIndex + 1, memo);
            }
        }
    }
}
