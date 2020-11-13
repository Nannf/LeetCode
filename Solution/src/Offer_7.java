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

    // 前序的第一个是根，中序的跟前序的第一个相同之前的为左子树，后面为右子树
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return null;
    }

}
