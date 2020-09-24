import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/7/16 20:09
 * @description: 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class Solution_10 {
    public static void main(String[] args) {
        if (new Solution_10().isMatch("ab", ".*c")) {
            System.out.println("bingo!");
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
        // 这里是判断下一个字符是'*'的情况，由于此时匹配次数可以是0，所以state+2也是合法的下一个状态
        if (state + 1 < p.length() && p.charAt(state + 1) == '*') {
            nextMatch(p, state + 2, nextState);
        }
    }


    boolean find = false;

    public boolean isMatch_backtrace(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        // 如果都为空，返回true
        if (sLen == 0 && sLen == pLen) {
            return true;
        }
        // 如果是.* 肯定匹配
        if (p.equals(".*")) {
            return true;
        }
        // 正则表达式不能以*打头
        if (p.startsWith("*")) {
            return false;
        }

        backtrace(s.toCharArray(), 0, sLen, p.toCharArray(), 0, pLen);

        return find;
    }

    private void backtrace(char[] s, int sIndex, int sLen, char[] p, int pIndex, int pLen) {
        if (find) {
            return;
        }

        // 如果模式串和待匹配字符串都到了结尾
        // 这边之所以不加sLen的判断，是因为会出现sIndex 已经到结尾，但是pIndex没到结尾，仍然会匹配的情况
        if (pIndex >= pLen) {
            if (sLen <= sIndex) {
                find = true;
            }
            return;
        }
        //如果当前匹配的元素的下一个是* 分成几种情况
        if (pIndex + 1 < pLen && p[pIndex + 1] == '*') {
            // 这种情况出现的原因是在于*可以表示0个
            // 假设匹配串是 aa,模式串是 aab*c*d*
            // 这样仍然是可以匹配的，因为匹配串已经到结尾，所以这种情况，直接跳过*即可
            if (sIndex >= sLen) {
                backtrace(s, sIndex, sLen, p, pIndex + 2, pLen);
            } else {
                // 当当前的匹配的时候
                if (p[pIndex] == s[sIndex] || p[pIndex] == '.') {
                    // 这里的*表示只出现了一次
                    backtrace(s, sIndex + 1, sLen, p, pIndex + 2, pLen);
                    if (find) {
                        return;
                    }
                    // 这里的pIndex 不变，表示*出现了任意次
                    backtrace(s, sIndex + 1, sLen, p, pIndex, pLen);

                    if (find) {
                        return;
                    }
                    backtrace(s, sIndex, sLen, p, pIndex + 2, pLen);
                } else {
                    // 当*代表0的时候
                    // 匹配串不动，模式串往后移动两位，跳过*
                    backtrace(s, sIndex, sLen, p, pIndex + 2, pLen);
                }
            }
        } else {

            // 这边加上判断是因为 前文没有对这个做判断，这边如果不加的话 下面的相比会报数组越界
            if (sIndex >= sLen) {
                return;
            }
            if (p[pIndex] == s[sIndex] || p[pIndex] == '.') {
                backtrace(s, sIndex + 1, sLen, p, pIndex + 1, pLen);
            }
        }
    }

}