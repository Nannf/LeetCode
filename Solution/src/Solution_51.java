import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/17 19:00
 * @description: n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_51 {

    public static void main(String[] args) {
        new Solution_51().solveNQueens(4);
    }

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();
        int[][] trace = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(trace[i], 0);
        }
        backtrace(ans, trace, n, 0);
        return ans;

    }

    private void backtrace(List<List<String>> ans, int[][] trace, int n, int i) {
        if (i == n) {
            ans.add(buildAnswer(trace, n));
            return;
        }
        for (int q = 0; q < n; q++) {
            if (check(i, q, trace, n)) {
                trace[i][q] = 1;
                backtrace(ans, trace, n, i + 1);
                trace[i][q] = 0;
            }
        }
    }

    private boolean check(int i, int q, int[][] trace, int n) {
        // 检查 竖有没有冲突
        for (int j = 0; j < i; j++) {
            if (trace[j][q] != 0) {
                return false;
            }
        }
        int i1  = i;
        int q1 = q;

        // 在检查对角线是否有冲突
        // 左对角线
        while (i1 > 0 && q1 > 0) {
            i1 = i1 - 1;
            q1 = q1 - 1;
            if (trace[i1][q1] != 0) {
                return false;
            }
        }

        while (i > 0 && q < n - 1) {
            i = i - 1;
            q = q + 1;
            if (trace[i][q] != 0) {
                return false;
            }
        }
        return true;


    }

    private List<String> buildAnswer(int[][] trace, int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trace.length; i++) {
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                if (trace[i][j] != 0) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}
