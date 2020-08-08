/**
 * @auth Nannf
 * @date 2020/8/8 15:49
 * @description:
 *
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution_99 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 第一反应就是递归，对每个节点而言，如果节点不为空，看左孩子节点是否比自己小，右孩子节点是否比自己大，如果是的直接交换
     * 但是这个无法跨代比较，然后我就想 如果我把路径上的节点都记录下来如何 记录的目的是为了 在判断当前节点的孩子节点和父节点比较之后，仍然可以和自己的祖先比较
     * 接下来又面临一个问题 如何表示祖先，左子树的祖先和右子树的祖先是要单独列出来的。 大致思路是这样的，下面详细的列出来：
     * 1. 定义递归函数，函数的含义，找到不满足大小条件的两个节点，并交换
     *     void  f(TreeNode node,left,right);
     *     其中 node 表示是当前的节点，当我想到这的时候，我发现我写不下去了，因为一个节点只有一个祖先，并没有左祖先和右祖先一说，它的祖先
     *     可能比它大，可能比它小；
     * 2. 树的处理方式我目前只有一个想法就是递归，我们重新来想下递归函数的定义，递归的原因，是因为我们觉得这个树的处理方式都是一致的，一个大问题能缩短
     * 为一个小问题，但是这边可以缩小吗？ 找到一个节点的不符合规范的节点 是否跟 寻找它的两个子节点的不规范的子节点等价？
     * 答案是不一定的因为 左右节点都符合的情况下，仍然存在不符合的情况。 这就导致了我们的递归似乎不能使用
     * @param root
     */
    public void recoverTree(TreeNode root) {

    }
}
