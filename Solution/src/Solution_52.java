import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/24 19:39
 * @description: n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 */
public class Solution_52 {
    int ans = 0;

    public int totalNQueens(int n) {
        int[][] trace = new int[n][n];
        for (int i = 0; i < trace.length; i++) {
            Arrays.fill(trace[i], 0);
        }
        backtrace(n, trace, 0);
        return ans;
    }

    private void backtrace(int n, int[][] trace, int line) {
        if (line == n) {
            ans++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (check(i, trace, line)) {
                trace[line][i] = 1;
                backtrace(n, trace, line + 1);
                trace[line][i] = 0;
            }
        }

    }

    private boolean check(int i, int[][] trace, int line) {
        // 先看竖着的有没有
        for (int k = 0; k < line; k++) {
            if (trace[k][i] != 0) {
                return false;
            }
        }
        // 再看左对角线
        int t1 = line;
        int t2 = i;
        while (t1 >= 1 && t2 >= 1) {
            t1--;
            t2--;
            if (trace[t1][t2] != 0) {
                return false;
            }
        }

        t1 = line;
        t2 = i;
        while (t1 >= 1 && t2 < trace.length - 1) {
             t1--;
             t2++;
             if (trace[t1][t2] != 0) {
                 return false;
             }
        }
        return true;
    }

}
