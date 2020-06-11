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
        int[] temperatures = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
        for (int i : dailyTemperatures(temperatures)) {
            System.out.println(i);
        }
    }

    public static int[] dailyTemperatures(int[] T) {
        if (T.length == 1) {
            return new int[] {0};
        }
        // 先暴力解法，再分析暴力解法的问题
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i+1; j <T.length;j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }
}
