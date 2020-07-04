/**
 * @auth Nannf
 * @date 2020/7/1 21:55
 * @description: 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 输入：
 * [1,2,3,2,1]
 * [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * [0,1,1,1,1]
 * [1,0,1,0,1]
 * <p>
 * [0,0,0,0,0,0,1,0,0,0]
 * [0,0,0,0,0,0,0,1,0,0]
 */
public class Solution_718 {


    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 2, 1};
        int[] B = new int[]{3, 2, 1, 4, 7};
        System.out.println(new Solution_718().findLength(A, B));
    }


    // 暴力解法有解吗，有解
    public int findLength(int[] A, int[] B) {
        int ans = -1;
        for (int i = 0; i < A.length; i++) {
            // i的公共长度
            int tmp = 0;
            // i的下标，恢复用
            int k = i;
            for (int j = 0; j < B.length; j++) {
                // j的下标 恢复用
                int m = j;
                //
                while (k < A.length && m < B.length) {
                    // 如果相同开始移动下标
                    if (A[k] == B[m]) {
                        tmp++;
                        k++;
                        m++;
                    } else {
                        // 否则计算最大的公共长度
                        ans = Math.max(ans, tmp);
                        // A数组恢复原状
                        k = i;
                        // B数组下标加一
                        m++;
                        // 本次的公共长度置0
                        tmp = 0;
                    }
                }
                // 循环结束后算下，是因为A和B可能一致，那样上面的else分支是不会走的
                ans = Math.max(ans, tmp);
                // A 数组恢复原状
                k = i;
                // 长度置为0
                tmp = 0;
            }
        }
        return ans;
    }
}
