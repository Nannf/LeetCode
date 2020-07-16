import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/16 15:09
 * @description: 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 * <p>
 * 输入：4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class Review_0812 {
    List<List<String>> ans = new ArrayList<>();

    public static void main(String[] args) {
        for (List<String> str : new Review_0812().solveNQueens(4)) {
            System.out.println(str);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        // 思路就是探索出所有的解，并对其中满足条件的解进行记录
        int[] result = new int[n];
        Arrays.fill(result, -1);
        down(0, n, result);
        return ans;
    }

    // 下棋子
    public void down(int row, int n, int[] result) {
        // 当已经下到第n个，说明已经下完了n-1行，不需要继续下了
        if (row == n) {
            // 当有一个新的解出现时，原先的解一定被替换掉了
            // 之所以这边不重置result是因为之前的结果对我们还有用
            ans.add(build(result, n));
            return;
        }
        for (int column = 0; column < n; column++) {
            if (isOk(row, column, result, n)) {
                result[row] = column;
                down(row + 1, n, result);
            }
        }
    }

    private boolean isOk(int row, int column, int[] result, int n) {
        int leftUp = column - 1, rightUp = column + 1;
        while (row > 0) {
            row--;
            // 本列不能有值
            if (result[row] == column) {
                return false;
            }
            // 左对角线不能有值
            if (leftUp >= 0 && result[row] == leftUp) {
                return false;
            }
            // 右对角线不能有值
            if (rightUp < n && result[row] == rightUp) {
                return false;
            }
            --leftUp;
            ++rightUp;
        }
        return true;
    }

    private List<String> build(int[] result, int n) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sb.append(".");
                } else {
                    sb.append("Q");
                }
            }
            list.add(sb.toString());
        }
        return list;
    }

}
