import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/14 10:22
 * @description: 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * <p>
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Solution_120 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = init();
        System.out.println(minimumTotal(triangle));
    }

    // 我们定义dp[i][j] 为当移动到i，j这个位置时的最大数值
    // 动态转移方程是 dp[i][j] = dp[]
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int m = triangle.size();
        int maxSize = triangle.get(triangle.size() - 1).size();
        if (maxSize == 0) {
            return 0;
        }

        int[][] dp = new int[m][maxSize];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < maxSize; j++) {
                int leftIndex = j - 1;
                int onIndex = j;
                int onSize = triangle.get(i - 1).size();
                int highLeft = Integer.MAX_VALUE;
                int highOn = Integer.MAX_VALUE;
                if (leftIndex <= onSize - 1) {
                    highLeft = dp[i-1][j-1];
                }
                if (onIndex <= onSize - 1) {
                    highOn = dp[i-1][j];
                }
                int current = Integer.MAX_VALUE;
                if (j <= triangle.get(i).size() -1) {
                    current = triangle.get(i).get(j);
                }
                dp[i][j] = Math.min(highLeft, highOn) + current;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[m-1]) {
            ans = Math.min(i,ans);
        }
        return ans;
    }








    private static List<List<Integer>> init() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);
        return triangle;
    }
}
