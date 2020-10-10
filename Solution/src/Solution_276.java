import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/9 20:22
 * @description: 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 * <p>
 * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
 * <p>
 * 注意:
 * n 和 k 均为非负的整数。
 * <p>
 * 输入: n = 3，k = 2
 * 输出: 6
 * 解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:
 * <p>
 *             柱 1    柱 2   柱 3
 * -----      -----  -----  -----
 * 1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1 
 * 5         c2     c1     c2
 *    6         c2     c2     c1
 */
public class Solution_276 {
    public static void main(String[] args) {
        System.out.println(new Solution_276().numWays(4, 3));
    }


    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n <= 2) {
            return (int) Math.pow(k, n);
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (int) Math.pow(j, i);
            }
        }
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 每次在新增一个柱子的时候，新增柱子上的颜色都可以是k中的任何一个，但是要去除上一个情况下能组成两个连续的情况
                // 这个两个连续的数量有多少呢？
                // 拿 n=4,k=3 来说，当我们再涂第四个柱子的时候，因为再涂第四个的时候有3个选择
                // 如果不考虑不合法的情况 应该是 k (3) * dp[3][3]的数量，但是以 11 22 33 打头的每个都会出现一个非法数据
                // 那这个非法数据的数量是多少呢？当数量是三的时候，这个数量是6 112 113 221 223 331 332 这样
                // 当我们再做第五根柱子的时候，需要去判断前四根柱子中分别以 11 22 33打头的 1121 1122 1123  1131 1132 1133  （少了3个 1111 1112 1113）
                // 同样的对2211 2212 2213 2231 2232 2233
                // 3311 3312 3313 3321 3322 3323
                // 如果是6 跟柱子 11211 11212 11213 11221 11223 11
                // 3(颜色的数量) * 3(颜色的数量)  *
                dp[i][j] = Math.max(j * dp[i - 1][j] - ((j) * (j) * (j-1)), 0);
            }
        }
        return dp[n][k];
    }


    // 回溯可解，但是时间复杂都是k^n 贼高，先试试回溯会不会超时
    // 回溯最后还需要去重
    // 先试试把
    // 超出了内存限制
    public int numWays_backtrace(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrace(ans, trace, n, k, 0);
        return ans.size();
    }

    private void backtrace(List<List<Integer>> ans, List<Integer> trace, int n, int k, int i) {
        if (i == n) {
            List<Integer> tmp = new ArrayList<>(trace);
            if (!judgeUniqe(ans, tmp)) {
                ans.add(tmp);
            }

            return;
        }
        for (int j = 1; j <= k; j++) {
            if (judgeLegal(j, trace)) {
                trace.add(j);
                backtrace(ans, trace, n, k, i + 1);
                trace.remove(trace.size() - 1);
            }
        }
    }

    private boolean judgeLegal(int j, List<Integer> trace) {
        if (trace.size() <= 1) {
            return true;
        }
        int lastValue = trace.get(trace.size() - 1);
        int secondLastValue = trace.get(trace.size() - 2);
        return !(lastValue == secondLastValue && secondLastValue == j);
    }

    private boolean judgeUniqe(List<List<Integer>> ans, List<Integer> arrayList) {
        for (List<Integer> list : ans) {
            int tmpsize = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != arrayList.get(i)) {
                    break;
                }
                tmpsize++;
            }
            if (tmpsize == arrayList.size()) {
                return true;
            }
        }
        return false;
    }
}
