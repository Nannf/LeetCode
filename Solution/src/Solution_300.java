import java.util.Arrays;
import java.util.Stack;

/**
 * @auth Nannf
 * @date 2020/9/29 20:12
 * @description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 */
public class Solution_300 {


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(new Solution_300().lengthOfLIS(nums));
    }
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int n = nums.length;
        // 我们用一个栈来保存，没一个数在遍历后面所有数据的时候所有比它值大的
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 多个值共用一个栈
            stack.clear();
            // 栈顶是我们当前的数字
            stack.push(nums[i]);
            for (int j = i + 1; j < n; j++) {
                // 只有比当前值大的才可以入栈
                if (nums[j] > nums[i]) {
                    while (!stack.isEmpty()) {
                        // 当栈顶的元素比当前的元素大，表示，虽然上升曲线出现了下坡
                        // 为了保证后面的序列最大，我们需要用比当前遍历元素大的，且最小的那个值
                        if (stack.peek() > nums[j]) {
                            ans = Math.max(stack.size(), ans);
                            stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(nums[j]);
                }
            }
            if (!stack.isEmpty()) {
                ans = Math.max(stack.size(), ans);
            }
        }
        return ans;
    }
}
