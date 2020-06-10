import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/6/10 20:57
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class Solution_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return  1;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        Set<Character> characterSet = new HashSet<>();

        for (int i = 0 ; i < chars.length; i++) {
            characterSet.clear();
            characterSet.add(chars[i]);
            int length = 1;
            for (int j = i+1; j< chars.length; j++) {
                if (characterSet.contains(chars[j])) {
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    break;
                } else {
                    length++;
                    characterSet.add(chars[j]);
                }
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }
}
