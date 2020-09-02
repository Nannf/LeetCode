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

    public boolean PredictTheWinner(int[] nums) {
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
