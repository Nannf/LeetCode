/**
 * @auth Nannf
 * @date 2020/7/16 19:29
 * @description: 0-1背包问题
 */
public class PackageProblem {


    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 2};  // 物品重量
    private int[] value = {3, 4, 8, 9, 6}; // 物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public static void main(String[] args) {
        PackageProblem pp = new PackageProblem();
        pp.dpf();
        System.out.println(pp.maxW);
    }

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

    public void dpf() {
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[n][w + 1];
        // 对第一行进行初始化
        dp[0][0] = 1;
        if (weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (dp[i - 1][j] != 0) {
                    dp[i][0 + j] = 1;
                    if (weight[i] + j <= w) {
                        dp[i][weight[i] + j] = 1;
                    }
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (dp[n - 1][i] == 1) {
                maxW = i;
                break;
            }
        }
    }

    public void dpf_withValue() {
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[n][w + 1];
        // 对第一行进行初始化
        dp[0][0] = 1;
        if (weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (dp[i - 1][j] != 0) {
                    dp[i][0 + j] = 1;
                    if (weight[i] + j <= w) {
                        dp[i][weight[i] + j] = 1;
                    }
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (dp[n - 1][i] == 1) {
                maxW = i;
                break;
            }
        }
    }
}
