import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/12/18 21:15
 * @description: 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * <p>
 * 输入：s = "", t = "y"
 * 输出："y"'
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * <p>
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class Solution_389 {

    // 遍历s和t，用hash表存储个数
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (sMap.containsKey(s.charAt(i))) {
                sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            } else {
                sMap.put(s.charAt(i), 1);
            }
            if (tMap.containsKey(t.charAt(i))) {
                tMap.put(t.charAt(i), tMap.get(t.charAt(i)) + 1);
            } else {
                tMap.put(t.charAt(i), 1);
            }
        }

        if (tMap.containsKey(t.charAt(t.length() - 1))) {
            tMap.put(t.charAt(t.length() - 1), tMap.get(t.charAt(t.length() - 1)) + 1);
        } else {
            tMap.put(t.charAt(t.length() - 1), 1);
        }

        for (Map.Entry<Character,Integer> entry : tMap.entrySet()) {
            if (!sMap.containsKey(entry.getKey())) {
                return entry.getKey();
            }
            if (sMap.get(entry.getKey()).intValue() != entry.getValue().intValue()) {
                return entry.getKey();
            }
        }

        return 'a';
    }
}
