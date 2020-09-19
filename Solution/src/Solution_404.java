/**
 * @auth Nannf
 * @date 2020/9/19 19:25
 * @description: 计算给定二叉树的所有左叶子之和。
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class Solution_404 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static int ans = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        dfs(root.left, true);
        dfs(root.right, false);
        return ans;
    }

    private void dfs(TreeNode root, boolean leftFlag) {
        if (root == null) {
            return;
        }
        if (leftFlag) {
            if (root.left == null && root.right == null) {
                ans += root.val;
            }
        }
        if(root.left != null) {
            dfs(root.left,true);
        }
        if (root.right != null) {
            dfs(root.right,false);
        }
    }
}
