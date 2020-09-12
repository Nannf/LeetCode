import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @auth Nannf
 * @date 2020/9/12 16:01
 * @description: 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * <p>
 * 输入：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_637 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<TreeNode> list = new ArrayList<>();
            List<TreeNode> tmp = new ArrayList<>();
            while (!queue.isEmpty()) {
                tmp.add(queue.poll());
            }
            ans.add(countTreeNode(tmp));
            for (TreeNode treeNode : tmp) {
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
        }
        return ans;
    }

    private Double countTreeNode(List<TreeNode> tmp) {
        Double sum = 0D;
        for (TreeNode treeNode : tmp) {
            sum += treeNode.val;
        }
        return sum / tmp.size();
    }
}
