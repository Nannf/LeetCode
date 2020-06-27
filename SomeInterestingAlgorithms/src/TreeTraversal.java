/**
 * @auth Nannf
 * @date 2020/6/27 10:33
 * @description: 二叉树遍历
 */
public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        tree.left = t1;
        tree.right = t2;
        t1.left = t4;
        t1.right = t5;
        t2.left = t6;
        t2.right = t7;
        t4.left = t8;
        preOrderTraversal(tree);
    }

    /**
     * 前序遍历
     *
     * @param tree
     */
    public static void preOrderTraversal(TreeNode tree) {
        if (tree == null){
            return;
        }
        System.out.println(tree.val);
        preOrderTraversal(tree.left);
        preOrderTraversal(tree.right);
    }

    /**
     * 中序遍历
     *
     * @param tree
     */
    public static void inOrderTraversal(TreeNode tree) {
        if (tree == null){
            return;
        }
        inOrderTraversal(tree.left);
        System.out.println(tree.val);
        inOrderTraversal(tree.right);
    }

    /**
     * 后续遍历
     *
     * @param tree
     */
    public static void postOrderTraversal(TreeNode tree) {
        if (tree == null){
            return;
        }
        postOrderTraversal(tree.left);
        postOrderTraversal(tree.right);
        System.out.println(tree.val);
    }
}
