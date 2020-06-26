/**
 * @auth Nannf
 * @date 2020/6/26 9:17
 * @description: 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * <p>
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * 返回 true.
 * <p>
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * 返回 false.
 */
public class Solution_392 {
    public static void main(String[] args) {
        String s = "leeeeetcode";
        String t = "yyyyyyyyylyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyeyyyyyyyyeyeyeyeytyyycyyoyyydyyye";
        if (isSubsequence(s,t)) {
            System.out.println("bingo!");
        }
    }

    // 动态规划做
    // 本题的思路就是逐个循环s每个位置的字符，判断其在t的位置，如果后出现的字符的下标索引<=它前一个的下标索引，表示不存在

    public static boolean isSubsequence(String s, String t) {
        if(t.length() == 0) {
            if(s.length() == 0) {
                return true;
            }
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        if (s.length() == 1) {
            return t.contains(s);
        }
        // 因为java的字符串是不可变的，但是我想把我判断过的字符给置为#，这样后面在出现这个字符时，计算的就是后面出现的字符位置
        StringBuilder sb = new StringBuilder(t);
        int[] dp = new int[s.length()];
        dp[0] = t.indexOf(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            dp[i] = sb.toString().indexOf(s.charAt(i));
            // s = leeeeetcode
            // t = yyyyyyyyylyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyeyyyyyyyyeyeyeyeytyyycyyoyyydyyye
            // 当s循环到e的时候，因为t中的e的个数是大于s中的，这就意味着，在d之前有些e还是存在的，因为我们获取的是第一个e出现的位置，
            // 这就要把之前的那些多余的e全部替换掉
            while (dp[i] != -1 && dp[i] <=dp[i-1]) {
                int start = sb.indexOf(String.valueOf(s.charAt(i)));
                int end = start+1;
                sb.replace(start,end,"#");
                dp[i] = sb.toString().indexOf(s.charAt(i));
            }
            if (dp[i] <= dp[i-1]) {
                return false;
            }
            int start = sb.indexOf(String.valueOf(s.charAt(i)));
            int end = start+1;
            sb.replace(start,end,"#");
        }
        return true;
    }
}
