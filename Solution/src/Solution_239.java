import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @auth Nannf
 * @date 2020/9/8 23:03
 * @description: 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * <p>
 * [1,3,1,2,0,5]
 * 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_239 {

    public static void main(String[] args) {
        int[] nums = {4,3,11};
        for (int i : new Solution_239().maxSlidingWindow(nums, 3)) {
            System.out.println(i);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }

        int[] ans = new int[nums.length - k + 1];

        // 如何在每次移动的时候找到最大的那个值，维护一个单调递减的队列就行了
        // 每次在插入的时候，把队列中比自己小的数全部移出去
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int m = 0; m < k; m++) {
            if (queue.isEmpty()) {
                queue.add(m);
            } else {
                while (!queue.isEmpty()) {
                    if (nums[m] > nums[queue.getLast()]) {
                        queue.removeLast();
                    } else {
                        break;
                    }
                }
                queue.addLast(m);
            }
        }


        ans[0] = nums[queue.peek()];
        //双指针
        int i = 1;
        int j = k;
        while (j < nums.length) {
            while (!queue.isEmpty()) {
                int tmp = queue.peek();
                // 移除不在窗口中的元素
                if (tmp > j || tmp < i) {
                    queue.removeFirst();
                    continue;
                }
                if (nums[j] > nums[queue.getLast()]) {
                    queue.removeLast();
                }else {
                    break;
                }
            }
            queue.addLast(j);
            ans[i] = nums[queue.peek()];
            i++;
            j++;
        }

        return ans;
    }


    // 这种提交的时候会超时
    // 滑动的次数是跟nums的大小有关的，每次滑动之后的最大值的计算是跟窗口的大小有关的
    // 所以时间复杂度是 O((n-k+1)*k) n是数组的大小，k是窗口的大小
    // 如果要线性复杂度的话，就需要在每次移动的时候 都用常数级别的复杂度找到最大的那个值
    //
    public int[] maxSlidingWindow_Double(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] ans = new int[nums.length - k + 1];
        //双指针
        int i = 0;
        int j = k - 1;

        while (j < nums.length) {
            ans[i] = findMax(nums, i, k);
            i++;
            j++;
        }
        return ans;

    }

    private int findMax(int[] nums, int i, int k) {
        int max = Integer.MIN_VALUE;
        for (int j = i; j < i + k; j++) {
            max = Math.max(nums[j], max);
        }
        return max;
    }
}
