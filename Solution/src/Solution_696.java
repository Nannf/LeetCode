import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/10 22:00
 * @description: 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * <p>
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_696 {

    public static void main(String[] args) {
        System.out.println(new Solution_696().countBinarySubstrings("00110011"));
    }

    // 这个解法是看到官方的题解得出的
    public int countBinarySubstrings(String s) {
        int ans =0;

        char tmp = s.charAt(0);
        int last = 0;
        int current = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == tmp) {
                current++;
            } else {
                if (last != 0) {
                    ans += Math.min(last, current);
                }
                last = current;
                current = 1;
                tmp = s.charAt(i);
            }
        }
        ans += Math.min(last, current);
        return ans;
    }

    // 暴力解法 超时
    public int countBinarySubstrings_ans1(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (judge(s.substring(i, j))) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean judge(String substring) {
        if (substring.length() % 2 != 0) {
            return false;
        }
        int j = substring.length() - 1;
        char left = substring.charAt(0);
        char right = substring.charAt(j);
        if (left == right) {
            return false;
        }
        int mid = (j - 0) / 2;
        for (int i = 1; i <= mid; i++) {
            if (substring.charAt(i) != left) {
                return false;
            }
        }
        for (int k = mid + 1; k < j; k++) {
            if (substring.charAt(k) != right) {
                return false;
            }
        }
        return true;
    }

}
