import java.util.Arrays;

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

    boolean find = false;

    public boolean isMatch(String s, String p) {
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
                backtrace(s, sIndex, p, pIndex + 1, memo);
            } else {
                // *代表空
                backtrace(s, sIndex, p, pIndex + 1, memo);
                if (find) {
                    return;
                }
                for (int k = 1; k + sIndex <= sLen; k++) {
                    if (find) {
                        return;
                    }
                    backtrace(s, sIndex + k, p, pIndex, memo);
                }
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
