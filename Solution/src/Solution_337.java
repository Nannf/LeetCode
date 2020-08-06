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
     * 先用递归来解决，我们定义一个函数的功能是给定一个节点，能盗取的最大的值
     * 最大的值=当前节点的值的大小+其所有孙子的钱的大小 和其左右孩子加起来的大小 之间的最大值
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点的值
        int money = root.val;
        int leftMoney = rob(root.left);
        int rightMoney = rob(root.right);
        // 如果左孩子节点不为空，那么计算左孩子节点的值
        // 左孩子节点的值等于
        if (root.left != null) {
            money += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(money, leftMoney + rightMoney);
    }
}
