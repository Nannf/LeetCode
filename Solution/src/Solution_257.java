
import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/4 20:16
 * @description: 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_257 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 深度优先遍历
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        trace.add(root.val);
        dfs(ans, trace, root);
        return ans;
    }

    private void dfs(List<String> ans, List<Integer> trace, TreeNode root) {
        // 如果当前节点的左右子孩子全为空
        if (root.left == null && root.right == null) {
            ans.add(build(trace));
            return;
        }
        if (root.left != null) {
            trace.add(root.left.val);
            dfs(ans,trace,root.left);
            trace.remove(trace.size()-1);
        }
        if (root.right != null) {
            trace.add(root.right.val);
            dfs(ans,trace,root.right);
            trace.remove(trace.size()-1);
        }
    }

    private String build(List<Integer> trace) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trace.size() - 1; i++) {
            sb.append(trace.get(i));
            sb.append("->");
        }
        sb.append(trace.get(trace.size() - 1));
        return sb.toString();
    }
}
