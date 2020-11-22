import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/11/22 7:45
 * @description: 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class Solution_242 {


    public static void main(String[] args) {
        System.out.println(new Solution_242().isAnagram("anagram", "nagaram"));
    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        Map<Character, Integer> sIndex = new HashMap<>();
        Map<Character, Integer> tIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (sIndex.containsKey(c )) {
                sIndex.put(c,sIndex.get(c)+1);
            } else{
                sIndex.put(c,1);
            }
            Character c2 = t.charAt(i);
            if (tIndex.containsKey(c2 )) {
                tIndex.put(c2,tIndex.get(c2)+1);
            } else {
                tIndex.put(c2,1);
            }
        }

        for (Map.Entry<Character,Integer> entry : sIndex.entrySet()) {
            if (!tIndex.containsKey(entry.getKey()) || entry.getValue().intValue() != tIndex.get(entry.getKey()).intValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字母异位词 就是字母数相同，但是顺序不相同
     * 嵌套循环会超时
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_n2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        boolean[] visted = new boolean[t.length()];
        // 超时
        for (int i = 0; i < s.length(); i++) {
            boolean isfind = false;
            for (int j = 0; j < t.length(); j++) {
                if (!visted[j] && t.charAt(j) == s.charAt(i)) {
                    isfind = true;
                    visted[j] = true;
                    break;
                }
            }
            if (!isfind) {
                return false;
            }
        }
        return true;
    }
}
