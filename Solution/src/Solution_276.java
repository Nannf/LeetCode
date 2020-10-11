import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/9 20:22
 * @description: 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 * <p>
 * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
 * <p>
 * 注意:
 * n 和 k 均为非负的整数。
 * <p>
 * 输入: n = 3，k = 2
 * 输出: 6
 * 解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:
 * <p>
 *             柱 1    柱 2   柱 3
 * -----      -----  -----  -----
 * 1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1 
 * 5         c2     c1     c2
 *    6         c2     c2     c1
 */
public class Solution_276 {
    public static void main(String[] args) {
        System.out.println(new Solution_276().numWays(4, 3));
    }


    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n <= 2) {
            return (int) Math.pow(k, n);
        }
        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] * (k-1) + dp[i-2]* (k-1);
        }
        return dp[n];
    }


    // 回溯可解，但是时间复杂都是k^n 贼高，先试试回溯会不会超时
    // 回溯最后还需要去重
    // 先试试把
    // 超出了内存限制
    public int numWays_backtrace(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrace(ans, trace, n, k, 0);
        return ans.size();
    }

    private void backtrace(List<List<Integer>> ans, List<Integer> trace, int n, int k, int i) {
        if (i == n) {
            List<Integer> tmp = new ArrayList<>(trace);
            if (!judgeUniqe(ans, tmp)) {
                ans.add(tmp);
            }

            return;
        }
        for (int j = 1; j <= k; j++) {
            if (judgeLegal(j, trace)) {
                trace.add(j);
                backtrace(ans, trace, n, k, i + 1);
                trace.remove(trace.size() - 1);
            }
        }
    }

    private boolean judgeLegal(int j, List<Integer> trace) {
        if (trace.size() <= 1) {
            return true;
        }
        int lastValue = trace.get(trace.size() - 1);
        int secondLastValue = trace.get(trace.size() - 2);
        return !(lastValue == secondLastValue && secondLastValue == j);
    }

    private boolean judgeUniqe(List<List<Integer>> ans, List<Integer> arrayList) {
        for (List<Integer> list : ans) {
            int tmpsize = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != arrayList.get(i)) {
                    break;
                }
                tmpsize++;
            }
            if (tmpsize == arrayList.size()) {
                return true;
            }
        }
        return false;
    }
}
