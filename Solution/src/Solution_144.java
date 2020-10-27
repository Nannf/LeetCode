import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/27 18:39
 * @description:
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution_144 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        // 先来试试递归的写法
        List<Integer> list = new ArrayList<>();
        backtrace(list,root);
        return list;
    }

    private void backtrace(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        backtrace(list,root.left);
        backtrace(list,root.right);
    }
}
