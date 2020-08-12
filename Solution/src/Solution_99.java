import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * @auth Nannf
 * @date 2020/8/8 15:49
 * @description: 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_99 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        node1.right = node2;
        new Solution_99().recoverTree(node1);
        System.out.println(1);
    }


    // 是否找到的标记
    boolean isFind = false;

    /**
     * 第一反应就是递归，对每个节点而言，如果节点不为空，看左孩子节点是否比自己小，右孩子节点是否比自己大，如果是的直接交换
     * 但是这个无法跨代比较，然后我就想 如果我把路径上的节点都记录下来如何 记录的目的是为了 在判断当前节点的孩子节点和父节点比较之后，仍然可以和自己的祖先比较
     * 接下来又面临一个问题 如何表示祖先，左子树的祖先和右子树的祖先是要单独列出来的。 大致思路是这样的，下面详细的列出来：
     * 1. 定义递归函数，函数的含义，找到不满足大小条件的两个节点，并交换
     * void  f(TreeNode node,left,right);
     * 其中 node 表示是当前的节点，当我想到这的时候，我发现我写不下去了，因为一个节点只有一个祖先，并没有左祖先和右祖先一说，它的祖先
     * 可能比它大，可能比它小；
     * 2. 树的处理方式我目前只有一个想法就是递归，我们重新来想下递归函数的定义，递归的原因，是因为我们觉得这个树的处理方式都是一致的，一个大问题能缩短
     * 为一个小问题，但是这边可以缩小吗？ 找到一个节点的不符合规范的节点 是否跟 寻找它的两个子节点的不规范的子节点等价？
     * 答案是不一定的因为 左右节点都符合的情况下，仍然存在不符合的情况。 这就导致了我们的递归似乎不能使用
     * <p>
     * 3. 换个思路，我们重新定义一个递归函数，f (TreeNode node , TreeNode child, boolean greatThan)
     * 函数的含义是计算给定的node，对其孩子节点是否存在比它大还是小的
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (isFind || root == null) {
            return;
        }
        boolean leftFind = false;
        TreeNode leftNode = null;
        boolean rightFind = false;
        TreeNode rightNode = null;
        List<TreeNode> leftChildren = getLeftChildren(root);
        for (TreeNode node : leftChildren) {
            if (root.val < node.val) {
                leftFind = true;
                leftNode = node;
            }
        }
        List<TreeNode> rightChildren = getRightChildren(root);
        for (TreeNode node : rightChildren) {
            if (root.val > node.val) {
                rightFind = true;
                rightNode = node;
            }
        }
        isFind = leftFind || rightFind;
        if (leftFind && rightFind) {
            convertNodes(leftNode,rightNode);
            return;
        }
        if (leftFind) {
            convertNodes(root,leftNode);
            return;
        }
        if (rightFind) {
            convertNodes(root,rightNode);
            return;
        }
        dfs(root.left);
        dfs(root.right);
    }

    private void convertNodes(TreeNode root, TreeNode node) {
        int tmp = root.val;
        root.val = node.val;
        node.val = tmp;
    }

    private List<TreeNode> getRightChildren(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root.right == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root.right);
        while (!queue.isEmpty()) {
            List<TreeNode> tmpList = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.add(node);
                tmpList.add(node);

            }
            for (TreeNode node : tmpList) {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return list;
    }

    private List<TreeNode> getLeftChildren(TreeNode root) {

        List<TreeNode> list = new ArrayList<>();
        if (root.left == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root.left);
        while (!queue.isEmpty()) {
            List<TreeNode> tmpList = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.add(node);
                tmpList.add(node);

            }
            for (TreeNode node : tmpList) {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return list;
    }
}
