import java.sql.SQLOutput;
import java.util.*;

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
        postOrderTraversal(tree);
    }

    /**
     * 前序遍历
     *
     * @param tree
     */
    public static void preOrderTraversal_recursion(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.val);
        preOrderTraversal_recursion(tree.left);
        preOrderTraversal_recursion(tree.right);
    }

    public static void preOrderTraversal(TreeNode tree) {
        if (tree == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            TreeNode root = stack.pop();
            System.out.println(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }


    /**
     * 中序遍历
     *
     * @param tree
     */
    public static void inOrderTraversal_recursion(TreeNode tree) {
        if (tree == null) {
            return;
        }
        inOrderTraversal_recursion(tree.left);
        System.out.println(tree.val);
        inOrderTraversal_recursion(tree.right);
    }

    public static void inOrderTraversal(TreeNode tree) {
        if (tree == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 链表的操作指针
        TreeNode tmp = tree;
        while (!stack.isEmpty() || tmp != null) {
            // 把当前节点和所有的左孩子节点全部入栈
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            TreeNode t1 = stack.pop();
            System.out.println(t1.val);
            if (t1.right != null) {
                tmp = t1.right;
            } else {
                tmp = null;
            }
        }
    }

    /**
     * 后续遍历
     *
     * @param tree
     */
    public static void postOrderTraversal_recursion(TreeNode tree) {
        if (tree == null) {
            return;
        }
        postOrderTraversal_recursion(tree.left);
        postOrderTraversal_recursion(tree.right);
        System.out.println(tree.val);
    }

    public static void postOrderTraversal(TreeNode tree) {
        if (tree == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = tree;
        TreeNode prev = null;
        while (!stack.isEmpty() || head != null) {
            // 左孩子全部入栈
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            // 获取栈顶元素
            TreeNode tmp = stack.peek();
            // 只有当栈顶元素没有右孩子或者右孩子已经访问过
            if (tmp.right== null || tmp.right == prev) {
                // 栈顶元素出栈
                tmp = stack.pop();
                System.out.println(tmp.val);
                //
                prev = tmp;
            } else {
              head = tmp.right;
            }
        }
    }

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<TreeNode> list = new ArrayList<>();
            while(!queue.isEmpty()) {
                TreeNode tmp = queue.poll();
                list.add(tmp);
            }
            for (TreeNode t : list) {
                System.out.print(t.getVal() + " ");
            }
            System.out.println();
            for (TreeNode t :list) {
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
        }
    }
}
