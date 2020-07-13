import java.util.Arrays;

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

    public static void main(String[] args) {
        int[][] dungeon = new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(calculateMinimumHP(dungeon));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
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
        // dp[i][j] 表示 当骑士走到i，j 这个位置之后，至少需要多少血量才能存活
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[m - 1][n] = 1;
        dp[m][n - 1] = 1;

        for (int i = m - 1; i > -1; i--) {
            for (int j = n - 1; j > -1; j--) {
                // 先计算出 右面和下面的数字的最小值，这个最小值 一定是大于等于1的，
                int dest = Math.min(dp[i][j + 1], dp[i + 1][j]);
                // 如果当前房间可以获得10血量，右面的只需求1血量，下面的只需求5血量，那么最后算出 dp[i][j] 只需要-9血量就可以，这与骑士的血量必须大于等于一矛盾
                dp[i][j] = Math.max(dest - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }


    // 首先我们定义dp[i][j] 表示 当骑士走到i，j 这个位置之后，至少需要多少血量才能存活
    // 如果我们仍然按照从左上走向右下的方式来做的话 会有问题，因为dp[i][j] 依赖后面的值，但是我们在遍历时没办法获取到后面的值
    // 所以我们考虑从结果出发，对m*n的二维数组而言，dp[m-1][n-1] 定义为当骑士到这仍然存活需要的最低血量，这个是确定的 = Math.max(1 - dungeon[m - 1][n - 1], 1);
    // 这边去max的原因是骑士的最低血量不能低于1
    // dp[i][j] 的动态转移方程是 我们需要计算出 dp[i][j] 这个点 对应的右边一格的位置dp[i+1][j]和下面一格的位置 dp[i][j+1]
    // 这两个值是取较大的一个还是较小的一个呢 我们记 右边一格的数字是A，下面的一格的数字为B 假设A=5，B =1 我们记原数组中的 dungeon[i][j] 的值是C
    // dp[i][j] + C >= A | B  dp[i][j] >= A | B - C 当C 固定的时候 取A或者B中的最小值，可得dp[i][j] 的最小值 特别的 dp[i][j]是需要>=1 的 所以当 A | B -C <=0 时，dp[i][j] =1
    public int calculateMinimumHP_ans1(int[][] dungeon) {
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
        // dp[i][j] 表示 当骑士走到i，j 这个位置之后，至少需要多少血量才能存活
        int[][] dp = new int[m][n];
        // 对一些无法用动态转移方程推导的数字先进行初始化
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        for (int i = m - 2; i > -1; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int i = n - 2; i > -1; i--) {
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
        }

        for (int i = m - 2; i > -1; i--) {
            for (int j = n - 2; j > -1; j--) {
                // 先计算出 右面和下面的数字的最小值，这个最小值 一定是大于等于1的，
                int dest = Math.min(dp[i][j + 1], dp[i + 1][j]);
                // 如果当前房间可以获得10血量，右面的只需求1血量，下面的只需求5血量，那么最后算出 dp[i][j] 只需要-9血量就可以，这与骑士的血量必须大于等于一矛盾
                dp[i][j] = Math.max(dest - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
