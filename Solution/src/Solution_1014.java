/**
 * @auth Nannf
 * @date 2020/6/17 11:21
 * @description: 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 */
public class Solution_1014 {

    public static void main(String[] args) {
        int[] a = new int[]{8,1,5,2,6};
        System.out.println(new Solution_1014().maxScoreSightseeingPair(a));
    }

    public int maxScoreSightseeingPair(int[] A) {
        // 先不管，上来先用暴力解法
        // 提交后 超出时间限制，需要找出其中多余计算的部分，进行优化
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int num = A[i] + A[j] + i - j;
                if (num > max) {
                    max = num;
                }
            }
        }

        return max;
    }
}
