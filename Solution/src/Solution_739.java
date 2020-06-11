import java.util.Stack;

/**
 * @auth Nannf
 * @date 2020/6/11 16:41
 * @description: 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class Solution_739 {

    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        for (int i : dailyTemperatures(temperatures)) {
            System.out.println(i);
        }
    }

    public static int[] dailyTemperatures_bl(int[] T) {
        if (T.length == 1) {
            return new int[]{0};
        }
        // 先暴力解法，再分析暴力解法的问题
        // 暴力解法有啥问题呢？
        // 当数组完全逆序时 如 9,8,7,6,5,4,3,2,10
        // 此时 8,7,6,5,4,3,2都在重复执行无用的循环
        // 如果某个值得后续的值比他小，那么他们应该放到一起，直到遇到一个比较大的值
        // 这个思路让我想到了单调栈
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] dailyTemperatures_failed(int[] T) {
        if (T.length == 1) {
            return new int[]{0};
        }
        // 单调栈解法
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            int j = i;
            int distance = 1;
            // 如果栈为空，把当前的元素加进来
            if (stack.isEmpty()) {
                System.out.println(T[i] + "入栈");
                stack.push(T[i]);
            } else {
                // 如果栈顶的元素小于当前的元素，那么需要出栈，一直到栈为空或者栈顶的元素大于当前元素为止
                // 我的单调栈里一直都想存值，为什么不存索引呢，数组可以通过索引直接获取到值啊，我真是笨比
                // 存值，我就要单独用字段来判断当一个元素出栈时，这个字段是属于原数组中哪个下标的
                // 以及这个字段距离当前访问的字段之间的距离
                // 而且对下面的数字序列无法处理，75出栈时距离出错
                // 73, 74, 75, 71, 69, 72, 76, 73
                if (stack.peek() < T[i]) {
                    while (!stack.isEmpty() && stack.peek() < T[i]) {
                        int tmp = stack.pop();
                        System.out.println(tmp + "出栈");
                        result[--j] = distance++;
                    }
                }
                System.out.println(T[i] + "入栈");
                stack.push(T[i]);
            }
        }
        int l = T.length;
        while (!stack.isEmpty()) {
            result[--l] = 0;
            stack.pop();
        }
        return result;
    }

    public static int[] dailyTemperatures(int[] T) {
        // 单调栈解法
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            // 如果栈为空，把当前的元素加进来
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int tmp = stack.pop();
                result[tmp] = i - tmp;
            }
            stack.push(i);
        }
        return result;
    }
}
