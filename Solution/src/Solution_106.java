import java.util.HashMap;
import java.util.Map;

/**
 * @author Nannf
 * @date 2020/11/17 14:50
 * @description 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class Solution_106 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        new Solution_106().buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
    }

    private TreeNode dfs(int[] inorder, int iStart, int iEnd,
                         int[] postorder, int pStart, int pEnd, Map<Integer, Integer> indexMap) {

        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        if (pStart == pEnd) {
            return root;
        }
        int rootIndex = indexMap.get(postorder[pEnd]);
        int leftSize = rootIndex - iStart;
        int rightSize = iEnd - rootIndex;

        TreeNode leftNode = dfs(inorder, iStart, rootIndex - 1, postorder, pStart, pEnd - rightSize - 1, indexMap);
        TreeNode rightNode = dfs(inorder, rootIndex + 1, rootIndex + rightSize, postorder, pEnd - rightSize, pEnd - 1, indexMap);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
}
