/**
 * @auth Nannf
 * @date 2020/8/5 20:44
 * @description:
 * 给定已经按升序排列、由不同整数组成的数组 A，返回满足 A[i] == i 的最小索引 i。如果不存在这样的 i，返回 -1。
 *
 * 输入：[-10,-5,0,3,7]
 * 输出：3
 * 解释：
 * 对于给定的数组，A[0] = -10，A[1] = -5，A[2] = 0，A[3] = 3，因此输出为 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fixed-point
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_1064 {


    public int fixedPoint(int[] A) {
        int low = 0;
        int high = A.length-1;
        while (low<=high) {
            int mid = low + ((high - low)>>1);
            if (A[mid] == mid) {
                if (mid == 0 || A[mid-1] != mid-1) {
                    return mid;
                }
                high = mid -1;
            }else if (A[mid] > mid) {
                high = mid-1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
