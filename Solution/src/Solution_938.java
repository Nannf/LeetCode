/**
 * @author Nannf
 * @date 2020/11/3 13:34
 * @description 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 * <p>
 * 二叉搜索树保证具有唯一的值。
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * <p>
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 */
public class Solution_938 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int rangeSumBST(TreeNode root, int L, int R) {
        return dfs(root,L,R);
    }

    private int dfs(TreeNode root, int l, int r) {
        // 先定义一系列终止条件
        if (root == null) {
            return 0;
        }
        if (root.val < l) {
            return dfs(root.right,l,r);
        }
        if (root.val > r) {
            return dfs(root.left,l,r);
        }
        return root.val + dfs(root.left,l,r)+dfs(root.right,l,r);
    }
}
