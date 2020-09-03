import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/9/1 18:42
 * @description: 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
 * 然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * <p>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * <p>
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 * <p>
 * <p>
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于 10000000 。
 * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 */
public class Solution_486 {

    /**
     * 动态规划解法
     * 这个是典型的区间dp问题，就是在区间[1,n]求解最值问题
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner_dp(int[] nums) {
        // 偶数先拿一定赢
        if (nums.length % 2 == 0) {
            return true;
        }
        int length = nums.length;
        // 我们用 dp[i][j] 表示 [i,j] 上 先手拿的人 净胜后手的最大点数
        // 最后 dp[1][length] 是否大于等于零，来判断是否会胜利
        int[][] dp = new int[length][length];
        // 如果左右区间相等，那么先手拿的人会净胜，
        // 以此为基，推导出以后所有的值
        // dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])
        // 因为是个不断拆分的过程，所以最终所要求解的值是需要一步步推导上去的。
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        // 这个怎么一步步推导上去呢，传统的双循环并不能解决问题
        // 传统的双循环就是  i -> 0~n-1 j-> i+1,n-1

        // 问题就是我们怎么利用那些已知的值慢慢扩大

        // 下面这是一种方式，从左到右逐步扩充开来，先把右边的元素固定在第二个，然后计算[1,2],[1,2] 依赖于 [1,1][2,2] 然后 在把右边的放到3
        // 先计算[2,3] [2,3] 依赖于 [2,2],[3,3] 然后 计算 [1,3] [1,3] 依赖于 [1,2] [2,3] 以此类推。每次循环的目的都是 为了计算出[1,n]
        // [1,n] 依赖于 [1,2][2,3]......[n-1][n]这样逐步的推导上去，就得到了最终的结果
//        for (int r = 1; r < length; r++) {
//            for (int l = r - 1; l >= 0; l--) {
//                for (int k = l; k < r; k++) {
//                    dp[k][r] = Math.max(nums[k] - dp[k + 1][r], nums[r] - dp[k][r - 1]);
//                }
//            }
//        }

        // 还有一种合并方式，我们是要从长度为1 的 [1,1][2,2].....[n,n] 逐步向上 到 [1,2][2,3][3,4]....[n-1][n].........>[1,n-1]
        // 可以看到 我们的每个区间的大小 从1 一直到n，其中我们以1为基础 逐步上推，直到推到长度为n的时候
//        for (int len = 2; len <= length; len++) {
//            // 定义左端点，左端点的结束，就是 不越界
//            for (int l = 0; l + len - 1 < length; l++) {
//                // 下面是右端点
//                for (int k = 1; k < len; k++) {
//                    dp[l][l + k] = Math.max(nums[l] - dp[l + 1][l + k], nums[l + k] - dp[l][l + k - 1]);
//                }
//            }
//        }

        // 第三种合并方式
        for (int len = 2; len <= length; len++) {
            // 定义左端点，左端点的结束，就是 不越界
            for (int l = 0; l + len - 1 < length; l++) {
                int r = l + len - 1;
                // 下面是右端点
                for (int k = l+1; k <= r; k++) {
                   dp[l][k] = Math.max(nums[l] - dp[l + 1][k], nums[k] - dp[l][k - 1]);
                }
            }
        }
        return dp[0][length - 1] >= 0;
    }

    public boolean PredictTheWinner_dfs(int[] nums) {
        // 偶数先拿一定赢
        if (nums.length % 2 == 0) {
            return true;
        }
        int length = nums.length;
        int[][] memo = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        return dfs(0, length - 1, nums, memo) >= 0;
    }

    private int dfs(int l, int r, int[] nums, int[][] memo) {
        if (l > r) {
            return 0;
        }
        if (memo[l][r] != Integer.MIN_VALUE) {
            return memo[l][r];
        }
        int chooseLeft = nums[l] - dfs(l + 1, r, nums, memo);
        int chooseRight = nums[r] - dfs(l, r - 1, nums, memo);
        memo[l][r] = Math.max(chooseLeft, chooseRight);
        return memo[l][r];
    }


}
