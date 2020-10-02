import java.util.ArrayList;
import java.util.Collections;
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
 * <p>
 * ["10","0001","111001","1","0"]
 * 3
 * 4
 * <p>
 * ["10","0001","111001","1","0"]
 * 4
 * 3
 * <p>
 * ["11111","100","1101","1101","11000"]
 * 5
 * 7
 * <p>
 * ["0","1101","01","00111","1","10010","0","0","00","1","11","0011"]
 * 63
 * 36
 * <p>
 * ["0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11",
 * "01","00","01111","0011","1","1000","0","11101","1","0","10","0111"]
 * 9
 * 80
 * <p>
 * ["011","1","11","0","010","1","10","1","1","0","0","0","01111","011","11","00",
 * "11","10","1","0","0","0","0","101","001110","1","0","1","0","0","10","00100",
 * "0","10","1","1","1","011","11","11","10","10","0000","01","1","10","0"]
 * 44
 * 39
 */
public class Solution_474 {

    public static void main(String[] args) {
        String[] strs = {"011", "1", "11", "0", "010", "1", "10", "1", "1", "0", "0", "0", "01111", "011", "11", "00", "11", "10", "1", "0", "0", "0", "0", "101", "001110", "1", "0", "1", "0", "0", "10", "00100", "0", "10", "1", "1", "1", "011", "11", "11", "10", "10", "0000", "01", "1", "10", "0"};
        System.out.println(new Solution_474().findMaxForm(strs, 44, 39));
    }

    int ans = 0;

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length + 1][m + 1][n + 1];
        List<Integer> trace = new ArrayList<>();
        backtrace(trace, strs, m, n, 0, memo);
        return ans;
    }

    private void backtrace(List<Integer> trace, String[] strs, int m, int n, int s, int[][][] memo) {
        if (trace.size() >= 43) {
            System.out.println("回溯的轨迹是：" + trace);
        }
        if (memo[s][m][n] != 0) {
            return;
        }
        memo[s][m][n] = 1;
        // 如果0和1的数量全部用完了，或者没用完，但是已经
        if ((m <= 0 && n <= 0) || s == strs.length) {
            ans = Math.max(ans, trace.size());
            return;
        }
        for (; s < strs.length; s++) {
            int oneNum = countOneNumber(strs[s].toCharArray(), 1);
            int zeroNum = countOneNumber(strs[s].toCharArray(), 0);
            // 只有m和n同时满足需求，才可以加到最终的结果集中
            if (m >= zeroNum && n >= oneNum) {
                trace.add(s);
                backtrace(trace, strs, m - zeroNum, n - oneNum, s + 1, memo);
                if (trace.size() == 13) {
                    System.out.println("bingo!");
                }
                trace.remove(trace.size() - 1);
            } else {
                // 如果有一个不满足，就不能继续回溯
                ans = Math.max(ans, trace.size());
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
