import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/7/22 9:45
 * @description: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 注意数组中可能存在重复的元素。
 * <p>
 * 输入: [1,3,5]
 * 输出: 1
 * <p>
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_154 {

    public int findMin(int[] nums) {
        // 解法一
//        Arrays.sort(nums);
//        return nums[0];

        int low = 0;
        int hi = nums.length - 1;
        while (low <= hi) {
            // 防止相加溢出
            int mid = low + ((hi - low) >> 2);
            // 这边涉及到和队首的元素比还是队尾的元素比
            //  1,2，3,4,0 队首的值 如果出现了翻转 一定是大于队尾的值的，所以 大于队首的值 一定大于队尾的值
            // 如果中间的值比队首的大，表明 中间的位置还没有到翻转点，这时候 low 变成mid+1
            if (nums[mid] > nums[0]) {
                low = mid + 1;

            } else if (nums[mid] < nums[0]) {
                // 如果中间的值比队首的小，说明已经到了翻转点之后了，这时候mid的值可能就是翻转点，或者之后
                // 如果mid本身是翻转点，直接返回
                if (nums[mid - 1] >= nums[0]) {
                    return nums[mid];
                }
                // 否则翻转点处在mid 之前
                hi = mid - 1;
            } else {
                // 如果两者相等，没办法判断 mid 是处在翻转点前还是后，只能一步步挪过去
                // 问题是从后往前挪，还是从前往后挪 这个需要看你是跟队首比较还是队尾比较
                // 如果是和队首比较，就是从前往后，如果是和队尾比较，就是从后往前
                low = low + 1;
            }
        }
        return nums[0];
    }
}
