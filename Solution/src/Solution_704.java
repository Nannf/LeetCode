/**
 * @auth Nannf
 * @date 2020/8/15 20:25
 * @description:
 *
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_704 {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        while (low <=high) {
            int mid = low + ((high - low ) >> 1);
            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                low = mid +1;
            } else {
                high = mid -1;
            }

        }
        return -1;
    }
}
