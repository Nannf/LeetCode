/**
 * @auth Nannf
 * @date 2020/7/2 18:40
 * @description: 二分查找树
 */
public class BinarySearchTree {
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(10);
        insert(tree, 3);
        insert(tree, 53);
        insert(tree, 55);
        insert(tree, 18);
        insert(tree, 1);
        insert(tree, 5);
        TreeTraversal.levelOrder(tree);
    }

    public static TreeNode find(TreeNode root, int data) {
        if (root == null) {
            return null;
        }
        if (root.getVal() == data) {
            return root;
        }
        if (root.getVal() > data) {
            return find(root.getLeft(), data);
        } else {
            return find(root.getRight(), data);
        }
    }

    public static void insert(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
        }
        if (data == root.val) {
            return;
        }
        if (root.val > data) {
            if (root.left == null) {
                TreeNode tree = new TreeNode(data);
                root.left = tree;
                return;
            }
            insert(root.left, data);
        } else {
            if (root.right == null) {
                TreeNode tree = new TreeNode(data);
                root.right = tree;
                return;
            }
            insert(root.right, data);
        }
    }


    public static void delete(TreeNode tree, int data) {
        if (tree == null) {
            return;
        }

        // 要删除节点的父节点
        TreeNode parent = null;
        // 要被删除的节点
        TreeNode head = tree;
        while (head.val != data && head != null) {
            if (head.val > data) {
                parent = head;
                head = head.left;
            } else {
                parent = head;
                head = head.right;
            }
        }
        // 没有找到
        if (head == null) {
            return;
        }
        // 本来到这一步的时候 删除的操作就是从parent上吧head给删了
        // 但是当head 有两个子节点的时候，需要把head的值先替换成head右节点上最小的值
        // 并把那个右子树上最小的值对应的节点删除
        // 如果要删除的节点有两个子节点，那么要从右孩子节点树中找到最小的那个 然后父节点指向它，把它从原先的位置删除
        if (head.left != null && head.right != null) {
            // 右子树上最小的节点
            TreeNode minP = head.right;
            // 右子树上最小节点的父节点
            TreeNode minPP = head;

            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            head.val = minP.val;
            // 要删除的节点变了
            parent = minPP;
            head = minP;
        }

        // 如果删除的节点没有子节点，直接把其父节点的指针指向它的指针指为null就行
        // 记录下要删除节点的子节点
        TreeNode child;
        if (head.left != null) {
            child = head.left;
        } else if (head.right != null) {
            child = head.right;
        } else {
            child = null;
        }
        // parent == null 的时候 意味着原来的树至多只有一个子节点
        if (parent == null) {
            tree = child;
            // 这里的head 一定只有一个子节点了
            // 为什么呢，因为有两个子节点的时候，已经找到右子树最小的那个了，也就是最左的节点
            // 所以说到这一步删除的时候 要被删除的节点，只会有一个子节点
            // 如果要被删除的head 本身就只有一个左子节点，那这个child就是那个左子节点
            // 如果要删除的head本身就只有一个右子节点，那这个child就是head 的右子节点
            // 如果要删除的head 本身有两个子节点，那这个child 就是head 右子树上最小节点的右子节点
        } else if (parent.left == head) {
            parent.left = child;
        } else {
            parent.right = child;
        }


    }
}
