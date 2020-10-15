import java.util.*;

/**
 * @auth Nannf
 * @date 2020/6/18 22:47
 * @description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Solution_5 {
    public static void main(String[] args) {
        System.out.println(new Solution_5().longestPalindrome("aba"));
    }

    static class CheckResult {
        boolean findFlag;
        String matchString;

        public boolean isFindFlag() {
            return findFlag;
        }

        public void setFindFlag(boolean findFlag) {
            this.findFlag = findFlag;
        }

        public String getMatchString() {
            return matchString;
        }

        public void setMatchString(String matchString) {
            this.matchString = matchString;
        }
    }

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        StringBuilder resultString = new StringBuilder(s.substring(0, 1));
        int ans = 1;
        // 感觉二分查找可以做
        // 二分好像不能做，因为如果不满足 其实不代表 长度比它长的就不满足回文序列
        // 还得从高到低逐个遍历才行
        int low = 1;
        int high = s.length();

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            CheckResult checkResult = check(mid, s);
            if (checkResult.findFlag) {
                low = mid + 1;
                if (mid > ans) {
                    ans = mid;
                    resultString.setLength(0);
                    resultString.append(checkResult.matchString);
                }
            } else {
                high = mid - 1;
            }
        }
        return resultString.toString();
    }

    private CheckResult check(int mid, String s) {
        CheckResult checkResult = new CheckResult();
        int i = 0;
        int j = mid;
        while (j <= s.length()) {
            if (isPalindrome(s.substring(i, j))) {
                checkResult.findFlag = true;
                checkResult.matchString = s.substring(i, j);
                return checkResult;
            } else {
                i++;
                j++;
            }
        }
        return checkResult;
    }


    private static boolean isPalindrome(String str) {
        char[] c = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (!(c[i++] == c[j--])) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome_before(String s) {
        if (s.length() <= 1) {
            return s;
        }
        // 暴力解法有出路吗？
        // 用指针指向每个字符，然后找到和当前字符相同的下标的位置，然后判断两个相同的字符之间是不是回文串
        // 如果是，记录下来，最后选择一个最长的进行返回
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxLength = 0;
        List<String> resultList = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = chars.length - 1; j >= 0; j--) {
                if (chars[j] == chars[i] && isPalindrome(s.substring(i, j + 1))) {
                    if (j - i > maxLength) {
                        maxLength = j - i;
                        resultList.clear();
                        resultList.add(s.substring(i, j + 1));
                    }
                    break;
                }
            }
        }
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return s.substring(0, 1);
        }
    }


    public String longestPalindrome_NotPass(String s) {
        if (s.length() <= 1) {
            return s;
        }
        // 暴力解法有出路吗？
        // 用指针指向每个字符，然后找到和当前字符相同的下标的位置，然后判断两个相同的字符之间是不是回文串
        // 如果是，记录下来，最后选择一个最长的进行返回
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxLength = 0;
        List<String> resultList = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] == chars[i]) {
                    if (map.containsKey(i)) {
                        map.get(i).add(j);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(j);
                        map.put(i, list);
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue() != null && entry.getValue().size() > 0) {
                for (int i : entry.getValue()) {
                    String s1 = s.substring(entry.getKey(), i + 1);
                    if (isPalindrome(s1) && s1.length() > maxLength) {
                        maxLength = s1.length();
                        resultList.clear();
                        resultList.add(s1);
                    }
                }
            }
        }
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return s.substring(0, 1);
        }
    }

}
