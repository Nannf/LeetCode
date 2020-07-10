/**
 * @auth Nannf
 * @date 2020/7/10 20:30
 * @description: 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Solution_121 {

    public static void main(String[] args) {
        int[] nums = new int[] {7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int m = prices.length;
        int[][] dp = new int[m][2];
        // 如何定义数组，我想用数组表达什么含义
        // 因为我想求最大利润，每天的最大利润又是基于上一天的选择
        // 我就定义一个二维数组 dp[i][], 表示第i天时的最大利润
        // dp[i][0] 表示第i天结束时 手上如果持有股票的最大利润
        // dp[i][1] 表示第i天结束时，手上不持有股票的最大利润
        dp[0][0] = prices[0] * -1;
        dp[0][1] = 0;
        for (int i = 1; i < m; i++) {
            // 第i天结束的时候 持有股票 可能是 上一天留下的 它什么都没有变
            // 还可能是今天买的，上一天没有股票，取她两的最大值
            // 这边为什么不是  dp[i-1][1] - prices[i] 呢
            // 上一天没有持有股票 跟我今天持有股票之间有关系吗？
            // 没有关系的，我今天持有了股票，说明我今天买了，
            dp[i][0] = Math.max(dp[i - 1][0],  - prices[i]);
            // 第i天结束的时候，不持有股票，可能上一天就不持有，
            // 还可能是上一天买了，今天把它卖了
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[m - 1][0], dp[m - 1][1]);
    }
}
