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

    // 滑动窗口
    public int findLength(int[] A, int[] B) {
        // 滑动窗口的解法，有一个基础的集合，有一个滑动的集合，我们暂定A为滑动的集合
        // 滑动窗口要以小的那个味滑动集合，之所以这样是因为当达到窗口最大值的时候，如果要接着往后滑，你就要判断当前的滑动集合的长度和滑动窗口集合的长度的大小关系
        // 我们在一开始就判定好以小的集合为滑动窗口
        // 滑动的总次数是 A.length+B.length -1;
        return A.length > B.length ? slidingWindow(B, A) : slidingWindow(A, B);

    }

    /**
     * b是滑动的那个窗口
     * 滑动窗口其实分为了三段，一个是进入的时候，一个是完全进入尚未出去的时候，一个是开始出去，直到结束的时候
     * 窗口的大小变化趋势类似于 1,2,3,4,5....maxWindowSize,maxWindowSize......5.4.3.2.1
     *
     * @param b
     * @param a
     * @return
     */
    private int slidingWindow(int[] b, int[] a) {
        int ans = 0;
        // 窗口的最大值是两个数组长度的最小值
        int maxWindowSize = Math.min(a.length, b.length);
        // 一共的滑动次数是确定的
        for (int i = 1; i <= maxWindowSize; i++) {
            ans = Math.max(ans, findMax(b, b.length - i, a, 0, i));
            if (ans == maxWindowSize) {
                return ans;
            }
        }
        int j = 1;
        for (int i = a.length - maxWindowSize; i > 0; i--) {
            ans = Math.max(ans, findMax(b, 0, a, j++, maxWindowSize));
            if (ans == maxWindowSize) {
                return ans;
            }
        }

        for (int i = maxWindowSize - 1; i >= 1; i--) {
            ans = Math.max(ans,findMax(b,0,a,j++,i));
        }
        return ans;
    }

    private int findMax(int[] b, int bstart, int[] a, int astart, int currentWindowSize) {
        int ans = 0;
        int k = 0;
        for (int i = 0; i < currentWindowSize; i++) {
            if (b[bstart + i] == a[astart + i]) {
                k++;
            } else {
                k = 0;
            }
            ans = Math.max(ans, k);
        }
        return ans;
    }


    // 最短是0，最长是两个数组中长度最小的那个，确定上下边界，直接二分查找
    // 二分仍然超时
    public int findLength_binary(int[] A, int[] B) {
        int ans = 0;
        int low = 0;
        int high = Math.min(A.length, B.length);
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 判断这个长度的
            if (check(mid, A, B)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    // 用滑动窗口进行判断
    private boolean check(int mid, int[] A, int[] B) {
        int i = 0;
        int j = i + mid - 1;
        int minSize = Math.min(A.length, B.length);

        while (j < minSize) {
            int[] tmp = new int[mid];
            System.arraycopy(A, i, tmp, 0, mid);
            if (judge(mid, tmp, B)) {
                return true;
            }
            i++;
            j++;
        }
        return false;
    }

    private boolean judge(int mid, int[] dest, int[] b) {
        int i = 0;
        int j = i + mid - 1;
        while (j < b.length) {
            int[] tmp = new int[mid];
            System.arraycopy(b, i, tmp, 0, mid);
            if (judgeTwoArray(dest, tmp)) {
                return true;
            }
            i++;
            j++;
        }
        return false;
    }

    private boolean judgeTwoArray(int[] dest, int[] b) {
        for (int i = 0; i < b.length; i++) {
            if (dest[i] != b[i]) {
                return false;
            }
        }
        return true;
    }


    // 暴力解法有解吗，有解
    public int findLength_bruce(int[] A, int[] B) {
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
