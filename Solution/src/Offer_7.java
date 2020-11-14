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
        if (preorder.length == 0) {
            return null;
        }
        return dfs(preorder, inorder, 0, preorder[preorder.length - 1]);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int rootIndex, int parent) {
        if (rootIndex >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[rootIndex]);
        int leftTreeSize = Math.max(getLeftTreeSize(preorder, inorder, rootIndex), 0);
        if (leftTreeSize > 0) {
            root.left = dfs(preorder, inorder, rootIndex + 1, preorder[rootIndex]);
        }

        int rightTreeSize = getRightTreeSize(preorder, inorder, rootIndex, parent);
        if (rightTreeSize > 0) {
            root.right = dfs(preorder, inorder, rootIndex + leftTreeSize + 1, preorder[rootIndex]);
        }
        return root;
    }

    private int getRightTreeSize(int[] preorder, int[] inorder, int rootIndex, int parent) {
        int startIndex = 0;
        int endIndex = preorder.length;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[rootIndex]) {
                startIndex = i;
            }
            if (inorder[i] == parent) {
                endIndex = i;
            }
        }
        return endIndex - startIndex - 1;

    }


    private int getLeftTreeSize(int[] preorder, int[] inorder, int rootIndex) {
        int target = preorder[rootIndex];
        int ans = 0;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] != target) {
                ans++;
            } else {
                break;
            }
        }
        return ans - rootIndex;
    }

}
