/**
 * @auth Nannf
 * @date 2020/7/28 13:45
 * @description:
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    返回它的最大深度 3
 */
public class Solution_104 {
    class TreeNode {
        int val;
        TreeNode left;
       TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        return find(root);
    }

    // 我们定义了一个函数，计算给定树节点的最大深度
    private int find(TreeNode root) {
        // 当当前节点为空时，返回0
        if (root == null) {
            return 0;
        }
        int left = find(root.left);
        int right = find(root.right);
        return Math.max(left,right) +1;
    }

}
