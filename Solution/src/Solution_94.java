import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/14 20:17
 * @description:
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 
 * 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution_94 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root,ans);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        // 一直找到没有左孩子节点为止
        dfs(root.left,ans);
        // 把这个节点添加上去
        ans.add(root.val);
        dfs(root.right,ans);
    }
}
