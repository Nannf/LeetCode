/**
 * @author Nannf
 * @date 2020/11/11 19:43
 * @description 泰波那契序列 Tn 定义如下： 
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 *  输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * 输入：n = 25
 * 输出：1389537
 */
public class Solution_1137 {
    public static void main(String[] args) {
        System.out.println(new Solution_1137().tribonacci(25));
    }

    public int tribonacci(int n) {
        int[] memo = new int[n];
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        if (memo[n - 1] != 0) {
            return memo[n - 1];
        }
        int val = dfs(n - 1, memo) + dfs(n - 2, memo) + dfs(n - 3, memo);
        memo[n - 1] = val;
        return val;
    }
}
