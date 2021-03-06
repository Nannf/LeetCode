/**
 * @auth Nannf
 * @date 2020/7/21 14:24
 * @description: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_72 {

    public static void main(String[] args) {
        System.out.println(new Solution_72().minDistance("horse", "ros"));
    }

    // 如何判定一个程序是否可以通过动态规划来解决呢？
    // 一个模型： 多阶段决策最优解模型，说人话就是可以从 00 --> nn 是有阶段的，也就是 是一个推导的过程，我们先知道了最简单的，然后根据最简单的总结出规律，然后推到n
    // 就本题而言，最简单的就是 当A 和 B 都是空字符，或者有一个是空字符，这种情况是最简单的，然后在慢慢推到下去
    // 特征一 : 最优子结构，就是最终的结果，可以由子问题的最优解推导出来
    // 就本题而言，horse --> ros 可以由 子问题 hors ->ros horse -> ro hors ->ro  这三个子问题的最优解推导出来
    // 特征二： 重复子问题 就是到达一个状态 会有不同的解法
    // 就本题而言，horse --> ros 可以由 子问题 hors ->ros horse -> ro hors ->ro  这三个子问题的最优解推导出来
    // 特征三： 无后效性 就是 我推导一个状态 只需考虑我之前的状态 无需考虑之后的状态
    // 就本题而言，
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {

                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[l1][l2];
    }
}
