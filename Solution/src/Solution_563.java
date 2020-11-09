/**
 * @auth Nannf
 * @date 2020/11/9 21:37
 * @description: 给定一个二叉树，计算 整个树 的坡度 。
 * <p>
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
 * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 * <p>
 * 整个树 的坡度就是其所有节点的坡度之和。
 */
public class Solution_563 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int ans = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (root.left == null) {
            int val = dfs(root.right);
            ans += val;
            return val + root.val;
        }

        if (root.right == null) {
            int val = dfs(root.left);
            ans += val;
            return val + root.val;
        }

        int rightVal = dfs(root.right);
        int leftVal = dfs(root.left);
        ans += Math.abs(rightVal - leftVal);
        return rightVal + leftVal + root.val;
    }
}
