import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/29 17:53
 * @description:
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 *
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 */
public class Solution_129 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode rl1 = new TreeNode(9);
        TreeNode rr1 = new TreeNode(0);
        TreeNode rll1 = new TreeNode(5);
        TreeNode rlr1 = new TreeNode(1);
        root.left = rl1;
        root.right = rr1;
        rl1.left = rll1;
        rl1.right = rlr1;
        System.out.println(new Solution_129().sumNumbers(root));
    }
    int ans =0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> trace = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root,trace,sb);
        for (int i : trace) {
            ans+=i;
        }
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> trace, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
        }
        if (root.left == null && root.right == null) {
            trace.add(Integer.parseInt(sb.toString()));
            sb.setLength(sb.length() -1);
            return;
        }
        if (root.left != null) {
            dfs(root.left,trace,sb);
        }
        if (root.right != null) {
            dfs(root.right,trace,sb);
        }
        sb.setLength(sb.length()-1);
    }
}
