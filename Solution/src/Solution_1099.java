import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/11/21 17:13
 * @description: 给你一个整数数组 A 和一个整数 K，请在该数组中找出两个元素，使它们的和小于 K 但尽可能地接近 K，返回这两个元素的和。
 * <p>
 * 如不存在这样的两个元素，请返回 -1。
 * 输入：A = [34,23,1,24,75,33,54,8], K = 60
 * 输出：58
 * 解释：
 * 34 和 24 相加得到 58，58 小于 60，满足题意。
 * <p>
 * 输入：A = [10,20,30], K = 15
 * 输出：-1
 * 解释：
 * 我们无法找到和小于 15 的两个元素。
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
public class Solution_1099 {
    public static void main(String[] args) {
        System.out.println(new Solution_1099().twoSumLessThanK(new int[]{34,23,1,24,75,33,54,8},60));
    }

    /**
     * i = 0 -> A.length -1
     * j = A.length - 1 - > 0
     * i + j = K  ret
     *
     * @param A
     * @param K
     * @return
     */
    public int twoSumLessThanK(int[] A, int K) {
        int ans = -1;
        Arrays.sort(A);
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
           int result =  A[i] + A[j];
            if (result >= K) {
                j--;
            } else {
                ans = Math.max(result,ans);
                i++;
            }
        }
        return ans;
    }
}
