/**
 * @auth Nannf
 * @date 2020/8/24 18:43
 * @description: 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 输入: "abab"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * <p>
 * 输入: "aba"
 * <p>
 * 输出: False
 * <p>
 * 输入: "abcabcabcabc"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_459 {
    public static void main(String[] args) {
        String s = "abcabcabcabc";
        if (new Solution_459().repeatedSubstringPattern(s)) {
            System.out.println("bingo!");
        }
    }

    // 满足条件的字符串的特征是什么样子的?
    // 假设字符串s 的长度是n 那么最终的结果是 [1,n-1] 字符串组成，对这n种子字符串而言，依次遍历原字符串，看是否满足条件，时间复杂度O(n^2)
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n == 0) {
            return false;
        }
        for (int i = 1; i < n; i++) {
            String tmp = s.substring(0, i);
            if (check(tmp, s)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(String tmp, String s) {
        int length = tmp.length();
        if (s.length() % length != 0) {
            return false;
        }
        int n = s.length() / length;
        for (int i = 1; i < n; i++) {
            String t = s.substring(i*length,(i+1)*length );
            if (!t.equals(tmp)) {
                return false;
            }
        }
        return true;
    }
}
