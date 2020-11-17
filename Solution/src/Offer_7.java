import java.util.HashMap;
import java.util.Map;

/**
 * @author Nannf
 * @date 2020/11/10 14:29
 * @description 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 0 <= 节点个数 <= 5000
 */
public class Offer_7 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        System.out.println(new Offer_7().buildTree(pre, in));
    }


    // 先找前序的第一个数，记为A，就是根，再在中序中找在A之前的所有的节点，就是左子树，右边的都是右子树，然后两个数组的索引发生变化，中序的索引开始变成A的索引位置加1，
    // 先序的位置索引变成 中序A值位置之前的数字
    // 以上就是递归函数的单c
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1, indexMap);
    }

    // 每次只构造一个节点，然后判断其左孩子和右孩子的下标位置
    private TreeNode dfs(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, Map<Integer, Integer> indexMap) {

        if (pStart > pEnd || pStart >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        if (pStart == pEnd) {
            return root;
        }
        // 根节点所在的下标索引
        int rootIndex = indexMap.get(preorder[pStart]);
        // 左孩子的个数
        int leftSize = rootIndex - iStart;
        // 右孩子的个数
        int rightSize = iEnd - rootIndex;

        TreeNode leftChild = dfs(preorder, pStart + 1, pStart +leftSize, inorder, iStart, rootIndex - 1, indexMap);
        TreeNode rightChild = dfs(preorder, pStart + leftSize + 1, pStart + leftSize + rightSize, inorder, rootIndex + 1, rootIndex  + rightSize, indexMap);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }


}
