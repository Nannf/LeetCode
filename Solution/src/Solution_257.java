
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
        // 这个用来保存最终的结果
        List<String> ans = new ArrayList<>();
        // 这个用来存储回溯过程中的一些变量，当到达一定条件后，就会把这个路径变为最终答案的一部分
        List<Integer> trace = new ArrayList<>();
        // 所有的路径都需要以根节点为开始
        trace.add(root.val);
        // 开始回溯
        dfs(ans, trace, root);
        return ans;
    }

    private void dfs(List<String> ans, List<Integer> trace, TreeNode root) {
        // 如果当前节点的左右子孩子全为空，说明当前节点是叶子节点，这时候就到达了转换答案的条件
        if (root.left == null && root.right == null) {
            ans.add(build(trace));
            return;
        }
        // 如果左孩子不为空，沿着左孩子一路扫下去
        if (root.left != null) {
            trace.add(root.left.val);
            dfs(ans,trace,root.left);
            trace.remove(trace.size()-1);
        }
        // 如果右孩子不为空，一路扫下去
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
