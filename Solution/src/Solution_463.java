/**
 * @auth Nannf
 * @date 2020/10/30 23:17
 * @description: 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，
 * 但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）
 * 。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 */
public class Solution_463 {
    public static void main(String[] args) {
        System.out.println();
    }


    public int islandPerimeter(int[][] grid) {
        // 结果就是所有1的个数乘以四，减去每个1和上下左右的相连情况
        int numOneCount = 0;
        int totalLinkCount = 0;
        int n = grid.length;
        if (n ==0) {
            return 0;
        }
        int m = grid[0].length;
        if ( m ==0) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    numOneCount++;
                    // 左
                    if (j > 0 && grid[i][j-1] ==1) {
                        totalLinkCount++;
                    }
                    // 右
                    if (j < m-1 && grid[i][j+1] ==1){
                        totalLinkCount++;
                    }
                    // 上
                    if (i>0 && grid[i-1][j] == 1){
                        totalLinkCount++;
                    }
                    if (i<n-1 && grid[i+1][j] == 1){
                        totalLinkCount++;
                    }
                }

            }
        }
        return numOneCount * 4 - totalLinkCount;

    }
}
