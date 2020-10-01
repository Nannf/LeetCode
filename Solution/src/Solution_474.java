import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/1 15:58
 * @description: 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * <p>
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * <p>
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * <p>
 * <p>
 * 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * <p>
 * <p>
 * 输入: strs = ["10", "0", "1"], m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 * <p>
 *
 *     ["10","0001","111001","1","0"]
 * 3
 * 4
 */
public class Solution_474 {

    public static void main(String[] args) {
        String[] strs = {"10","0001","111001","1","0"};
        System.out.println(new Solution_474().findMaxForm(strs,3,4));
    }
    int ans = 0;

    // 动态规划，由小推大
    // 第一步就是建议每一步的状态
    // 本题回溯可以解
    // 先尝试使用回溯进行解法
    public int findMaxForm(String[] strs, int m, int n) {
        // 这是回溯过程中的轨迹
        List<Integer> trace = new ArrayList<>();
        backtrace(trace, strs, m, n);
        return ans;
    }

    private void backtrace(List<Integer> trace, String[] strs, int m, int n) {
        // 如果 m =0 并且 n= 0表示数字用完，回溯结束
        if (m <= 0 && n <= 0) {
            ans = Math.max(ans, trace.size());
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            int oneNum = countOneNumber(strs[i].toCharArray(), 1);
            int zeroNum = countOneNumber(strs[i].toCharArray(), 0);
            if (m >= zeroNum && n >= oneNum && !trace.contains(i)) {
                trace.add(i);
                backtrace(trace, strs, m - zeroNum, n - oneNum);
                trace.remove(trace.size() - 1);
            }
        }
    }

    private int countOneNumber(char[] chars, int num) {
        int ans = 0;
        for (char c : chars) {
            if (Integer.parseInt(String.valueOf(c)) == num) {
                ans++;
            }
        }
        return ans;
    }
}
