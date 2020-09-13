import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/11 22:34
 * @description: 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * <p>
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。
 * 但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * <p>
 * <p>
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * <p>
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * <p>
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 * <p>
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202
 * <p>
 * 提示：
 * <p>
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 */
public class Solution_1423 {

    public static void main(String[] args) {
        int[] cardPoints = new int[]{1, 79, 80, 1, 1, 1, 200, 1};
        System.out.println(new Solution_1423().maxScore(cardPoints, 3));
    }

    // 滑动窗口
    // 之前没有想到 这个求
    public int maxScore(int[] cardPoints, int k) {
        int ans = 0;
        int n = cardPoints.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + cardPoints[i];
        }

        for (int i = 0; i <= k; i++) {
            ans = Math.max(sum[i] + sum[n] - sum[n - k + i], ans);
        }
        return ans;

    }

    // 因为这个限定了只能从队首或者队尾取所以 如果要取k个数的话  可以做如下的排列
    //  队首取的数量  队尾取的数量
    //  0              k
    //  1              k-1
    // ...             ...
    //  k              0
    public int maxScore_dp(int[] cardPoints, int k) {
        int ans = 0;
        int n = cardPoints.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + cardPoints[i];
        }

        for (int i = 0; i <= k; i++) {
            ans = Math.max(sum[i] + sum[n] - sum[n - k + i], ans);
        }
        return ans;

    }

    // 用回溯有解,但是超时
    public int maxScore_backtrace(int[] cardPoints, int k) {
        List<Integer> trace = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        backtrace(ans, trace, k, cardPoints, 0, cardPoints.length - 1);

        Collections.sort(ans);
        return ans.get(ans.size() - 1);
    }

    private void backtrace(List<Integer> ans, List<Integer> trace, int k, int[] cardPoints, int start, int end) {
        if (trace.size() == k) {
            ans.add(sumList(trace, cardPoints));
            return;
        }
        trace.add(start);
        backtrace(ans, trace, k, cardPoints, start + 1, end);
        trace.remove(trace.size() - 1);
        trace.add(end);
        backtrace(ans, trace, k, cardPoints, start, end - 1);
        trace.remove(trace.size() - 1);
    }

    private Integer sumList(List<Integer> trace, int[] cardPoints) {
        int sum = 0;
        for (int i : trace) {
            sum += cardPoints[i];
        }
        return sum;
    }
}
