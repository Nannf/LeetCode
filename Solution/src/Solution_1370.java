import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/11/25 19:52
 * @description: 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * <p>
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * <p>
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * <p>
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * <p>
 * 输入：s = "spo"
 * 输出："ops"
 */
public class Solution_1370 {

    public static void main(String[] args) {
        System.out.println(new Solution_1370().sortString("leetcode"));
    }

    static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 0);
        map.put(0, 1);
    }

    boolean endFlag = false;

    // 感觉是个递归的过程
    public String sortString(String s) {
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[s.length()];
        dfs(ans, visited, s, 0, false);
        StringBuilder result = new StringBuilder();
        for (int i : ans) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    private void dfs(List<Integer> ans, boolean[] visited, String s, int i, boolean isChange) {
        if (endFlag) {
            return;
        }

        if (ans.size() == s.length()) {
            endFlag = true;
            return;
        }

        int index = findFirstNotVisitedIndex(visited);
        if (i == 0) {
            // 找到s中所有的未被访问的最小的那个索引
            for (int j = 0; j < s.length(); j++) {
                if (!visited[j] && s.charAt(j) <= s.charAt(index)) {
                    if (isChange) {
                        index = j;
                    } else {
                        if (ans.size() == 0 || s.charAt(j) != s.charAt(ans.get(ans.size() - 1))) {
                            index = j;
                        }
                    }

                }
            }
            if (ans.size() == 0) {
                ans.add(index);
                visited[index] = true;
                dfs(ans, visited, s, i, false);
            } else {
                if (!isChange) {
                    if (s.charAt(index) > s.charAt(ans.get(ans.size() - 1))) {
                        ans.add(index);
                        visited[index] = true;
                        dfs(ans, visited, s, i, false);
                    } else {
                        dfs(ans, visited, s, map.get(i), true);
                    }
                } else {
                    ans.add(index);
                    visited[index] = true;
                    dfs(ans, visited, s, i, false);
                }
            }
        } else {
            // 找最大的索引
            for (int j = 0; j < s.length(); j++) {
                if (!visited[j] && s.charAt(j) >= s.charAt(index)) {
                    if (isChange) {
                        index = j;
                    } else {
                        if (s.charAt(j) != s.charAt(ans.get(ans.size() - 1))) {
                            index = j;
                        }
                    }
                }
            }

            if (isChange) {
                ans.add(index);
                visited[index] = true;
                dfs(ans, visited, s, i, false);
            } else {
                if (s.charAt(index) < s.charAt(ans.get(ans.size() - 1))) {
                    ans.add(index);
                    visited[index] = true;
                    dfs(ans, visited, s, i, false);
                } else {
                    dfs(ans, visited, s, map.get(i), true);
                }
            }
        }
    }

    private int findFirstNotVisitedIndex(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return 0;
    }
}