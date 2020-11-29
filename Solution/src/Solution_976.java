import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/11/29 16:21
 * @description: 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * <p>
 * 如果不能形成任何面积不为零的三角形，返回 0。
 * <p>
 * 输入：[2,1,2]
 * 输出：5
 * <p>
 * 输入：[1,2,1]
 * 输出：0
 * <p>
 * 输入：[3,2,3,4]
 * 输出：10
 * <p>
 * 输入：[3,6,2,3]
 * 输出：8
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 */
public class Solution_976 {
    public static void main(String[] args) {
        System.out.println(new Solution_976().largestPerimeter(new int[]{3,2,3,4}));
    }


    // 我们如何定义三角形呢？
    // 任意一边的长度不大于任意两边的长度之和，即为三角形
    public int largestPerimeter(int[] A) {
        int ans = 0;
        Arrays.sort(A);
        LabelA:
        for (int j = A.length - 1; j >= 2; j--) {
            int n = j - 1;
            int m = j - 2;
            if (A[j] + A[n] + A[m] <= ans) {
                continue;
            }
            while (n > 0) {
                if (A[j] < A[n] + A[m]) {
                    ans = Math.max(A[j] + A[j - 1] + A[j - 2], ans);
                    continue LabelA;
                } else {
                    m--;
                    if (m < 0) {
                        n--;
                        m = n - 1;
                    }
                }
            }
        }
        return ans;
    }
}