/**
 * @auth Nannf
 * @date 2020/6/27 10:32
 * @description:
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    public static TreeNode getTreeNode(){
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
        return  tree;
    }
    public static TreeNode getSearchTreeNode(){
        TreeNode tree = new TreeNode(33);
        TreeNode t1 = new TreeNode(17);
        TreeNode t2 = new TreeNode(50);
        TreeNode t4 = new TreeNode(13);
        TreeNode t5 = new TreeNode(18);
        TreeNode t6 = new TreeNode(34);
        TreeNode t7 = new TreeNode(58);
        tree.left = t1;
        tree.right = t2;
        t1.left = t4;
        t1.right = t5;
        t2.left = t6;
        t2.right = t7;
        return  tree;
    }
}
