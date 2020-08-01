/**
 * @auth Nannf
 * @date 2020/7/31 9:38
 * @description: 魔术索引。
 * 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
 * 给定一个有序整数数组，编写一种方法找出魔术索引，
 * 若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 * <p>
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * <p>
 * 输入：nums = [1, 1, 1]
 * 输出：1
 */
public class Review_0803 {

    public int findMagicIndex(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (nums[mid] == mid) {
                // 如何判断当前的是第一个呢
                // mid == 0 || nums[mid - 1] != mid - 1 可以吗  因为是有重复的 所以这个并不行
                // 举个例子 {0,2,2,4,6} 第一次二分会找到第三个数，而且 它前面的数 也满足上面的判断 但是这样的话 我们得出的结果就是2 实际上是0
                if (mid == 0 || nums[mid - 1] != mid - 1) {
                    return mid;
                }
                high = mid - 1;
            }
            // nums[mid] > mid  能推导出nums[mid+1] 一定大于 mid + 1 不能
            // 因为这个是有重复的 假设mid = 1,nums[mid]=2 nums[mid +1] 也等于2
            if (nums[mid] > mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //O(n)
    public int findMagicIndex_ans(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;
    }
}
