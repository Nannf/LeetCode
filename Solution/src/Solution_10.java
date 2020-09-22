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
        if (new Solution_10().isMatch("mississippi", "mis*is*p*.")) {
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
        if (pIndex == pLen) {
            if (sLen == sIndex) {
                find = true;
            }
            return;
        }

        // 如果是点直接通过
        if (p.charAt(pIndex) == '.') {
            // 直接往下比
            backtrace(s, sIndex + 1, sLen, p, pIndex + 1, pLen);
        } else if (p.charAt(pIndex) == '*') {
            char preChar = p.charAt(pIndex - 1);

            for (int k = 0; k <= sLen - sIndex; k++) {
                if (find) {
                    return;
                }
                // 这边根据这个k 来获取新的字符串的值
                if (k == 0) {
                    backtrace(s, sIndex , sLen, p, pIndex + 1, pLen);
                } else {
                    String newString = buildNewString(p, pIndex, k, preChar);
                    backtrace(s, sIndex + k - 1, sLen, newString, pIndex, newString.length());
                }

            }

        } else {
            if (p.charAt(pIndex) == s.charAt(sIndex)) {
                // 直接往下比
                backtrace(s, sIndex + 1, sLen, p, pIndex + 1, pLen);
            } else {
                // 这个如果不相同，不能直接返回，因为后面如果有*的话，这个不相等的是可以被处理的
                if (pIndex != pLen - 1 && p.charAt(pIndex + 1) == '*') {
                    backtrace(s, sIndex, sLen, p, pIndex + 1, pLen);
                }
            }
        }
    }

    private String buildNewString(String p, int pIndex, int k, char preChar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (i == pIndex) {
                for (int j = 0; j < k; j++) {
                    sb.append(preChar);
                }
            } else {
                sb.append(p.charAt(i));
            }
        }
        return sb.toString();
    }
}
