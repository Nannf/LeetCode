import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/8/26 13:32
 * @description: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Solution_17 {

    static final Map<Integer, String[]> map = new HashMap<>();

    static {
        map.put(2, new String[]{"a", "b", "c"});
        map.put(3, new String[]{"d", "e", "f"});
        map.put(4, new String[]{"g", "h", "i"});
        map.put(5, new String[]{"j", "k", "l"});
        map.put(6, new String[]{"m", "n", "o"});
        map.put(7, new String[]{"p", "q", "r", "s"});
        map.put(8, new String[]{"t", "u", "v"});
        map.put(9, new String[]{"w", "x", "y", "z"});
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() ==0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        List<String> trace = new ArrayList<>();
        backtrace(result, trace, digits, 0, digits.length());
        return result;
    }

    private void backtrace(List<String> result, List<String> trace, String digits, int startNum, int totalNum) {
        if (trace.size() == totalNum) {
            result.add(buildAns(trace));
            return;
        }

        int num = Integer.parseInt(String.valueOf(digits.charAt(startNum)));
        String[] strArray = map.get(num);
        for (int j = 0; j < strArray.length; j++) {
            trace.add(strArray[j]);
            backtrace(result, trace, digits, startNum + 1, totalNum);
            trace.remove(trace.size() - 1);
        }
    }

    private String buildAns(List<String> trace) {
        StringBuilder sb = new StringBuilder();
        for (String str : trace) {
            sb.append(str);
        }
        return sb.toString();
    }
}
