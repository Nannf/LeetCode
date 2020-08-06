import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/8/6 9:44
 * @description: 在上次打劫完一条街道之后和一圈房屋后，
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，
 * 我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_337 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 动态规划
     * @param root
     * @return
     * 对每个节点而言，其能偷到的最大值可以分两种情况讨论:
     * 1. 偷了当前节点 = 当前节点的值 + 不偷其孩子节点的最大值
     * 2. 不偷当前节点 = 其孩子节点的最大值(孩子节点可偷可不偷)
     */
    public int rob_ans3(TreeNode root) {
        // 当我想向之前那样定义一个二维dp数组的时候，我卡住了，因为我并不知道有多少节点
        // 我想了一下我的dp数组的定义，应该是对一个节点而言，能盗取的最大值，因为一个节点只有取和不取两种，
        // 所以我们的dp数组应该只有两种状态，dp[][0] 表示不取改节点的值，dp[][1]表示取这个节点的值，
        // 最终dp[0][] 中的最大值就是最终的答案，我们需要从叶子节点一步步的推上去，因为一个节点的值跟其左右孩子节点有关
        // 所以类似于树的后续遍历，之前处理的都是数组相关的遍历，可以通过下标索引直接得到结果，但是这里不行
        // 我们需要递归的处理每一个节点，把每个节点的状态都返回上来，就是最终的状态
        // 所以我们定义一维数组即可
        int[]dp = dealDp(root);
        return Math.max(dp[0],dp[1]);
    }

    // 这个递归返回的就是每个节点 取和不取的值
    private int[] dealDp(TreeNode root) {
        // 递归终止条件是
        if (root == null) {
            return new int[]{0,0};
        }
        int[] left = dealDp(root.left);
        int[] right = dealDp(root.right);

        int[] dp = new int[2];
        // 如果不取当前节点，需要计算出其孩子节点取不取的最大值
        dp[0]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        // 如果取当前节点
        dp[1] = root.val + left[0] + right[0];
        return dp;
    }


    /**
     * 记忆化递归
     *
     * @param root
     * @return
     */
    public int rob_ans2(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return rob(root, memo);

    }

    private int rob(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 当前节点的值
        int money = root.val;
        int leftMoney = rob(root.left,memo);
        int rightMoney = rob(root.right,memo);
        // 如果左孩子节点不为空，那么计算左孩子节点的值
        // 左孩子节点的值等于
        if (root.left != null) {
            money += rob(root.left.left,memo) + rob(root.left.right,memo);
        }
        if (root.right != null) {
            money += rob(root.right.left,memo) + rob(root.right.right,memo);
        }
        memo.put(root, Math.max(money, leftMoney + rightMoney));
        return Math.max(money, leftMoney + rightMoney);
    }

    /**
     * 先用递归来解决，我们定义一个函数的功能是给定一个节点，能盗取的最大的值
     * 最大的值=当前节点的值的大小+其所有孙子的钱的大小 和其左右孩子加起来的大小 之间的最大值
     *
     * @param root
     * @return
     */
    public int rob_ans1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点的值
        int money = root.val;
        int leftMoney = rob_ans1(root.left);
        int rightMoney = rob_ans1(root.right);
        // 如果左孩子节点不为空，那么计算左孩子节点的值
        // 左孩子节点的值等于
        if (root.left != null) {
            money += rob_ans1(root.left.left) + rob_ans1(root.left.right);
        }
        if (root.right != null) {
            money += rob_ans1(root.right.left) + rob_ans1(root.right.right);
        }
        return Math.max(money, leftMoney + rightMoney);
    }
}
