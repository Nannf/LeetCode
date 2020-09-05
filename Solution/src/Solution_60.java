import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/9/5 10:29
 * @description: 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Solution_60 {
    boolean isFind = false;
    int matchCount = 0;

    public static void main(String[] args) {
        System.out.println(new Solution_60().getPermutation(9, 101140));
    }

    //全排列问题
    // 超时
    public String getPermutation(int n, int k) {
        long start = System.currentTimeMillis();
        List<Integer> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        dfs(n, k, trace, ans);
        System.out.println("cost " +  (System.currentTimeMillis() - start) +"");
        return build(ans);
    }

    private String build(List<Integer> trace) {
        StringBuilder sb = new StringBuilder();
        for (int i : trace) {
            sb.append(i);
        }
        return sb.toString();
    }

    private void dfs(int n, int k, List<Integer> trace, List<Integer> ans) {
        if (isFind) {
            return;
        }
        if (trace.size() == n) {
            matchCount++;
            if (matchCount == k) {
                isFind = true;
                ans.addAll(trace);
            }
            return;
        }

        // 然后从小到大选下一个数
        for (int i = 1; i <= n; i++) {
            if (!trace.contains(i)) {
                trace.add(i);
                dfs(n, k, trace, ans);
                trace.remove(trace.size() - 1);
            }

        }
    }
}
