import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/7/15 9:26
 * @description: 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_96 {

    public static void main(String[] args) {
        System.out.println(new Solution_96().numTrees(4));
    }

    // G(n) 表示 当数字数量是n时 一共有多少种解法
    // f(i,n) 表示，以i为底 数量为n是 一共有多少种解法
    // G(n) =
    public int numTrees(int n) {
        if (n<=1) {
            return n;
        }
        int[] g = new int[n+1];
        g[0] = 1;
        g[1] = 1;
        for(int i = 2; i<= n;i++) {
            for (int j = 1; j<=i; j++) {
                g[i] += g[j-1]*g[i-j];
            }
        }
        return  g[n];
    }



}
