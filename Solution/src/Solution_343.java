import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/30 9:45
 * @description: 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * <p>
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class Solution_343 {


    // 动态规划
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 0;
        for (int j = 2; j <= n; j++) {
            for (int k = 1; k < j; k++) {
                dp[j] = Math.max(dp[j],Math.max(k * (j - k), k * dp[j - k]));
            }
        }
        return dp[n];

    }


    static int ans = Integer.MIN_VALUE;

    // 回溯有解，但是超时
    public int integerBreak_backtrace(int n) {
        ans = Integer.MIN_VALUE;
        List<Integer> traceList = new ArrayList<>();
        // 记录所有的回溯路径
        backtrace(traceList, n);
        return ans;
    }

    private void backtrace(List<Integer> traceList, int n) {
        if (n == 0) {
            if (traceList.size() == 1) {
                return;
            }
            ans = Math.max(ans, multi(traceList));
            return;
        }
        for (int i = 1; i <= n; i++) {
            traceList.add(i);
            backtrace(traceList, n - i);
            traceList.remove(traceList.size() - 1);
        }
    }

    private int multi(List<Integer> traceList) {
        int sum = 1;
        for (int i : traceList) {
            sum *= i;
        }
        return sum;
    }
}
