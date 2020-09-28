import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/9/28 20:09
 * @description: 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Solution_11 {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(new Solution_11().maxArea(height));
    }

    // 全排列可解
    // 但是我一看到最值，我就想到要用动态规划
    // 动态规划我现在的思路就是瞎蒙
    // 好像是要找动态转移方程，就是怎么由小推到大的
    // 假设有两个，就找[1,2]
    // 如果有三个，答案是多少呢 [1,2][2,3][1,3]这三个中间会取一个，会有[2,3]这种情况吗？
    // 有的，假设 1 = 1 2=8 3= 8 那最大就是 [2,3]
    // 我在计算[1,3]之间的最大值的时候用到了 [1,2]的结果
    // 同理我在计算[1,4]的时候，需要计算哪些[1,2],[1,3][1,4],[2,3][2,4][3,4],然后选出一个最大的作为[1,4]的解
    // [1,4]的解和[1,3]的解有什么关系呢？ 我们发现在我们计算[1,3]的解的时候  已经把[1,2][2,3][1,3]中的最大值计算过了，
    // 所以要求[1,4]的解，只用比较 [1,3]的解，和[1,4][2,4][3,4]就是比当前计算的小的到自己的大小，然后取两者的最大值即可
    // 然后我就悟了
    public int maxArea(int[] height) {
        int n = height.length;
        int[] dp = new int[n + 1];
        // 初始值
        dp[2] = Math.min(height[0], height[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], getCurrentValue(i-1, height));
        }
        return dp[n];
    }

    private int getCurrentValue(int i, int[] height) {
        int ans = 0;
        for (int j = 0; j < i; j++) {
            ans = Math.max(ans,(i- j) * Math.min(height[i],height[j]));
        }
        return ans;
    }
}
