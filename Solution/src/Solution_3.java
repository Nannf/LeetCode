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

    public static int lengthOfLongestSubstring_Mine(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        Set<Character> characterSet = new HashSet<>();

        // 暴力解法的问题在于做了重复计算
        // 假设我们的字符串起始位置是 k 结束位置是 Tk
        // 那么k之后的字符 到 Tk之间也肯定是不重复的，但是我们对那部分数据也进行计算了
        for (int i = 0; i < chars.length; i++) {
            characterSet.clear();
            characterSet.add(chars[i]);
            int length = 1;
            for (int j = i + 1; j < chars.length; j++) {
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


    public static int lengthOfLongestSubstring_mine2(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        // 防止重复
        Set<Character> characterSet = new HashSet<>();
        int j = 1;
        characterSet.add(chars[0]);
        for (int i = 0; i < chars.length; i++) {
            if (j == chars.length) {
                break;
            }
            // 从第二个开始滑动
            for (; j < chars.length; j++) {
                // 如果第二个包含了第一个的
                if (characterSet.contains(chars[j])) {
                    // 这时候这个set中保留的就是最长的
                    if (characterSet.size() > maxLength) {
                        maxLength = characterSet.size();
                    }
                    // 把第一个字符去除
                    characterSet.remove(chars[i]);
                    // 开始指针往后移一位
                    break;
                }
                // 如果不包含，则把当前这个字段加入到set中
                characterSet.add(chars[j]);
            }
        }

        return Math.max(characterSet.size(),maxLength);
    }
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
