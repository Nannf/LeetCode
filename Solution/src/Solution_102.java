
import java.util.*;

/**
 * @auth Nannf
 * @date 2020/6/27 10:48
 * @description:二叉树的层序遍历 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 二叉树：[3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class Solution_102 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    public List<List<Integer>> levelOrder_Solution2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<TreeNode> list = new ArrayList<>();
            List<Integer> list1 = new ArrayList<>();
            while(!queue.isEmpty()) {
                TreeNode tmp = queue.poll();
                list.add(tmp);
                list1.add(tmp.val);
            }
            result.add(list1);
            for (TreeNode t :list) {
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
        }

        return result;
    }

    public List<List<Integer>> levelOrder_Solution1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        find(rootList,result);
        return result;
    }

    public  void find(List<TreeNode> list, List<List<Integer>> result) {
        if (list == null || list.size() == 0) {
            return;
        }
        List<TreeNode> tmpTreeNode = new ArrayList<>();
        List<Integer>  tmpValue = new ArrayList<>();

        for (TreeNode tree : list) {
            if (tree != null) {
                tmpValue.add(tree.val);
            }
            tmpTreeNode.addAll(findSonNode(tree));
        }
        result.add(tmpValue);
        find(tmpTreeNode,result);

    }

    private  List<TreeNode> findSonNode(TreeNode tree) {
        List<TreeNode> list = new ArrayList<>();
        if (tree.left != null) {
            list.add(tree.left);
        }
        if (tree.right!= null) {
            list.add(tree.right);
        }
        return list;
    }

}
