/**
 * @auth Nannf
 * @date 2020/10/17 13:32
 * @description:
 *
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 *
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 *
 *
 * 输入：[4,8,12,16]
 * 输出：2
 *
 * 输入：[100]
 * 输出：1
 */
public class Solution_978 {

    public static void main(String[] args) {

    }
    public  int maxTurbulenceSize(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        // 先找偶数大的湍流子数组长度
        int evenMax = 1;
        // 我们对动态转移的定义 和之前的子数组类似，以当前索引为结尾的子数组的长度作为
        int[] evenMaxDp = new int[nums.length];
        evenMaxDp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 是偶数
            if (i % 2 != 0) {
                // 表示上一个奇数是比当前的偶数大的，当前偶数是一个新的开始
                if (nums[i - 1] >= nums[i]) {
                    evenMaxDp[i] = 1;
                    evenMax = Math.max(evenMaxDp[i], evenMax);
                } else {
                    // 走到这一步，说明串起来了
                    evenMaxDp[i] = evenMaxDp[i - 1] + 1;
                    evenMax = Math.max(evenMaxDp[i], evenMax);
                }
            } else {
                // 如果奇数的前一个比它小，当前奇数是新的开始
                if (nums[i - 1] <= nums[i]) {
                    evenMaxDp[i] = 1;
                    evenMax = Math.max(evenMaxDp[i], evenMax);
                } else {
                    // 走到这一步，说明串起来了
                    evenMaxDp[i] = evenMaxDp[i - 1] + 1;
                    evenMax = Math.max(evenMaxDp[i], evenMax);
                }
            }
        }

        // 再找奇数大的湍流子数组的长度
        int oddMax = 1;
        int[] oddMaxDp = new int[nums.length];
        oddMaxDp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 是偶数
            if (i % 2 != 0) {
                if (nums[i - 1] <= nums[i]) {
                    oddMaxDp[i] = 1;
                    oddMax = Math.max(oddMaxDp[i], oddMax);
                } else {
                    // 走到这一步，说明串起来了
                    oddMaxDp[i] = oddMaxDp[i - 1] + 1;
                    oddMax = Math.max(oddMaxDp[i], oddMax);
                }
            } else {
                // 如果奇数的前一个比它小，当前奇数是新的开始
                if (nums[i - 1] >= nums[i]) {
                    oddMaxDp[i] = 1;
                    oddMax = Math.max(oddMaxDp[i], oddMax);
                } else {
                    // 走到这一步，说明串起来了
                    oddMaxDp[i] = oddMaxDp[i - 1] + 1;
                    oddMax = Math.max(oddMaxDp[i], oddMax);
                }
            }
        }
        // 取两者的最大值，即为最后的答案。
        return Math.max(evenMax, oddMax);
    }
}
