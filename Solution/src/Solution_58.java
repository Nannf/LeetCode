/**
 * @author Nannf
 * @version v1.0
 * @date 2021/10/25 22:19
 * @Description
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 输入：s = "Hello World"
 * 输出：5
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class Solution_58 {

    public static void main(String[] args) {
        System.out.println(new Solution_58().lengthOfLastWord("luffy is still joyboy "));
    }

    public int lengthOfLastWord(String s) {
        String[] str = s.split(" ", -1);
        for (int i = str.length - 1; i>=0;i--) {
            if (str[i].length()!=0) {
                return str[i].length();
            }
        }
        return 0;
    }
}
