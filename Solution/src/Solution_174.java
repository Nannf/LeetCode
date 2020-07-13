/**
 * @auth Nannf
 * @date 2020/7/12 19:20
 * @description: 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 *  
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 */
public class Solution_174 {

    // 要找的就是 一个变正之前最小的负数，且以后全是正数的这样一个路线
    // 这个最小的负数取反就是结果
    // 我们要如何定义数组来满足这个要求的解呢？
    // 我的思路是不是有问题？
    // 原来dp是需要要求无后效性的
    // 查看题解后发现可以反向dp
    // 我们定义dp[i][j] 为当移动到i，j这个位置时，初始值至少需要多少才能活着
    // 或者的最小值是1 也就是 dp[i+1][j] + dp[i][j] >=1 dp[i][j+1] + dp[i][j] >= 1;
    // dp[i][j] >= 1- dp[i+1][j]; dp[i][j] >= 1-dp[i][j+1]
    // 要求dp[i][j] 的最小值 <=> 求 1 -dp[i+1][j] 和 1-dp[i][j+1]的最小值  <=> 求 dp[i+1][j] 和 dp[i][j+1]的最大值
    // 当然这里有个前提条件 就是
    // dp[i][j] = Math (Math(dp[i+1][j],dp[i][j+1]),1)
    public int calculateMinimumHP(int[][] dungeon) {
        // 对一些边界情况进行处理
        if (dungeon == null) {
            return 1;
        }
        int m = dungeon.length;
        if (m == 0) {
            return 1;
        }
        int n = dungeon[0].length;
        if (n == 0) {
            return 1;
        }
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        for (int i = m - 2; i > -1; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int i = n - 2; i > -1; i--) {
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
        }
        for (int i = m - 2; i > -1; i--) {
            for (int j = n - 2; j > -1; j--) {
                int dest = Math.min(dp[i][j+1],dp[i+1][j]);
                dp[i][j] = Math.max(dest-dungeon[i][j],1);
            }
        }
        return dp[0][0];

    }
}
