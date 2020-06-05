import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Nannf
 * @date 2020/6/4 23:09
 * @description 给定两个 没有重复元素 的数组 nums1 和 nums2 ，
 * 其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2
 *  中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 */
public class Solution_496 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};
        int[] result = nextGreaterElement(nums1, nums2);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * o(n^3)我真是弱智
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement_solution1(int[] nums1, int[] nums2) {
        int[] resultArray = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                // 因为是子集，所以一定能找到相等的那个
                if (nums2[j] == nums1[i]) {
                    boolean flag = false;
                    for (int k = j; k < nums2.length; k++) {
                        if (nums2[k] > nums1[i]) {
                            flag = true;
                            resultArray[i] = nums2[k];
                            break;
                        }
                    }
                    if (!flag) {
                        resultArray[i] = -1;
                    }
                }

            }
        }
        return resultArray;
    }

    /**
     * 栈的题目，nums1 是 nums2 的子集，栈是后进先出 这之间有什么关系呢？
     * 自己想不到解法，看题解得知单调栈
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resultArray = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.isEmpty()) {
                stack.push(nums2[i]);
            } else {
                // 如果发现栈底的元素比自己小
                if (stack.peek() < nums2[i]) {
                    // 循环整个栈
                    while (!stack.isEmpty()) {
                        // 如果栈最上面的元素比自己小，说明新假如的元素是栈底元素的下一个最大值
                        if (stack.peek() < nums2[i]) {
                            map.put(stack.pop(), nums2[i]);
                        } else {
                            // 因为栈是单调递减的，所以比栈低的大，不代表比下面的值也大
                            stack.push(nums2[i]);
                            // 因为单调递减，所以当发现一个之后 应该结束循环
                            break;
                        }
                    }
                    // 如果此时栈已空，表示原来栈中所有的值都比新来的值小，这时候需要把新的元素入栈
                    if (stack.isEmpty()) {
                        stack.push(nums2[i]);
                    }

                } else {
                    // 栈低元素比自己大，直接入栈
                    stack.push(nums2[i]);
                }
            }
        }
        // 如果栈不空，表示有的元素找不到比自己更大的值
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            resultArray[i] = map.get(nums1[i]);
        }

        return resultArray;
    }

}
