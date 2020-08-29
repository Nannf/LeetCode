/**
 * @author Nannf
 * @date 2020/8/30 7:21
 * @description 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 */
public class Solution_557 {
    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println(new Solution_557().reverseWords(str));
    }
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char data = s.charAt(i);
            if (!Character.isSpaceChar(data)) {
                sb.append(data);
            } else {
                ans.append(reverseSb(sb));
                sb.setLength(0);
                ans.append(' ');
            }
        }
        if (sb.length() > 0) {
            ans.append(reverseSb(sb));
        }

        return ans.toString();
    }

    private String reverseSb(StringBuilder sb) {
        StringBuilder ans = new StringBuilder();
        for (int i = sb.length() - 1; i>=0 ;i--) {
            ans.append(sb.charAt(i));
        }
        return ans.toString();
    }
}
