/**
 * @auth Nannf
 * @date 2020/8/13 19:38
 * @description: 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * <p>
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class Solution_200 {

    // 岛屿数量
    int islandCount = 0;

    /**
     * 我们假设全是1，这时候有多少岛屿，就是一个；其实就是看联通问题，我们遍历所有的岛，当遇到1时，把所有相连的1同化，计数器加一，最后返回计数器就行
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果当前这个点的值是1的话
                if (grid[i][j] == '1') {
                    // 岛屿数量增加一
                    islandCount++;
                    // 把周围连接的1同化
                    convertAroud(i, j, n, m, grid);
                }
            }
        }
        return islandCount;

    }

    private void convertAroud(int i, int j, int n, int m, char[][] grid) {
        // 递归终止条件
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1') {
            return;
        }
        // 把当前节点置为a
        grid[i][j] = 'a';
        // 其实这边的上下左右，就是与该点相连的点，就是深度优先搜索
        // 上
        convertAroud(i-1,j,n,m,grid);
        // 下
        convertAroud(i+1,j,n,m,grid);
        // 左
        convertAroud(i,j-1,n,m,grid);
        // 右
        convertAroud(i,j+1,n,m,grid);
    }
}
