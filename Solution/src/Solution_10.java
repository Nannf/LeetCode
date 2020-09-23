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

    boolean find = false;


    public boolean isMatch(String s, String p) {
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

        backtrace(s, 0, sLen, p, 0, pLen);

        return find;
    }

    private void backtrace(String s, int sIndex, int sLen, String p, int pIndex, int pLen) {
        if (find) {
            return;
        }

        // 如果模式串和待匹配字符串都到了结尾
        if (pIndex >= pLen) {
            if (sLen <= sIndex) {
                find = true;
            }
            return;
        }
        //如果当前匹配的元素的下一个是* 分成几种情况
        if (pIndex + 1 < pLen && p.charAt(pIndex + 1) == '*') {
            if (sIndex >= sLen) {
                backtrace(s, sIndex, sLen, p, pIndex + 2, pLen);
            } else {
                // 当当前的匹配的时候
                if (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '.') {
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
        }

        if (sIndex >= sLen) {
            return;
        }
        if (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '.') {
            backtrace(s, sIndex + 1, sLen, p, pIndex + 1, pLen);
        }
    }

}