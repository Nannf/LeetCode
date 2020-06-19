/**
 * @auth Nannf
 * @date 2020/6/19 8:33
 * @description: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class Solution_125 {
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        if (new Solution_125().isPalindrome(str)) {
            System.out.println("bingo!");
        }
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        String str = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        System.out.println(str);
        char[] chars = str.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (!(chars[i++] == chars[j--])) {
                return false;
            }
        }
        return true;
    }
}
