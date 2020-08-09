import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/9 14:14
 * @description: 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Solution_93 {

    public static void main(String[] args) {
        System.out.println(new Solution_93().restoreIpAddresses("010010"));
    }

    /**
     * 第一反应，枚举所有的可能，然后用剪枝缩减范围
     * 回溯
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        backtrace(tmp, s, result);
        return result;
    }

    public void backtrace(List<String> tmp, String chioce, List<String> result) {
        // 回溯的终止条件
        if (tmp.size() == 4) {
            if (chioce.length() == 0) {
                result.add(buildIp(tmp));
                return;
            } else {
                return;
            }
        }

        for (int i = 1; i <= Math.min(3, chioce.length()); i++) {
            String t = chioce.substring(0, i);
            // 这边要剪枝，t有几个条件，一是要小于等于255，还有就是除了是0以外，不能以0大头
            if (Integer.parseInt(t) <= 255) {
                if (t.startsWith("0") && t.length()>1) {
                    continue;
                }
                tmp.add(t);
                backtrace(tmp, chioce.substring(i), result);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private String buildIp(List<String> tmp) {
        StringBuilder sb = new StringBuilder();
        for (String str : tmp) {
            sb.append(str);
            sb.append(".");
        }
        return sb.substring(0, sb.lastIndexOf("."));
    }



}
