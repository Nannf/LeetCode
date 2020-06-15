/**
 * @auth Nannf
 * @date 2020/6/15 9:38
 * @description: 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class Solution_14 {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"aac","ab"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String minLengthStr = strs[0];
        for (String str : strs) {
            if (str.length() < minLengthStr.length()) {
                minLengthStr = str;
            }
        }

        char[] resultArray = minLengthStr.toCharArray();
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            for (int i = 0; i < resultArray.length; i++) {
                if (tmp[i] == resultArray[i]) {
                    resultArray[i] = tmp[i];
                } else {
                    for (int j = i; j < resultArray.length; j++) {
                        resultArray[j] = ' ';
                    }
                    if (String.valueOf(resultArray).trim().isEmpty()) {
                        return "";
                    } else {
                        break;
                    }
                }
            }

        }
        return String.valueOf(resultArray).trim();
    }
}
