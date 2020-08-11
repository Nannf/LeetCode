import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/11 12:18
 * @description: 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * <p>
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，
 * 任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_130 {

    public static void main(String[] args) {
        char[][] chars ={{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        new Solution_130().solve(chars);

    }


    public void solve(char[][] board) {

        // 先找到所有边界上的o以及和边界相连的o的下标索引
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = 'Y';
            }
            if (board[n - 1][i] == 'O') {
                board[n - 1][i] = 'Y';
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'Y';
            }
            if (board[i][m - 1] == 'O') {
                board[i][m - 1] = 'Y';
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O') {
                    if (board[i - 1][j] == 'Y' || board[i][j - 1] == 'Y' || board[i + 1][j] == 'Y' || board[i][j + 1] == 'Y') {
                        board[i][j] = 'Y';
                        convertAround(board, i, j, n, m);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                    continue;
                }
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }

    private void convertAround(char[][] board, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] == 'X') {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = 'Y';
            return;
        }

        convertAround(board, i - 1, j, n, m);
        convertAround(board, i, j - 1, n, m);
        convertAround(board, i, j + 1, n, m);
    }

}
