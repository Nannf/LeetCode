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

    public static void main(String[] args) {
        System.out.println(new Solution_60().getPermutation(9, 101140));
    }

    boolean isFind = false;


    //全排列问题
    // 超时
    public String getPermutation(int n, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = countFactorial(i);
        }
        int cut = factorial[n];
        dfs(n, k, trace, ans, factorial, cut);
        return build(ans);
    }

    private int countFactorial(int i) {
        if (i == 1) {
            return 1;
        }
        return i * countFactorial(i - 1);
    }

    private String build(List<Integer> trace) {
        StringBuilder sb = new StringBuilder();
        for (int i : trace) {
            sb.append(i);
        }
        return sb.toString();
    }

    private void dfs(int n, int k, List<Integer> trace, List<Integer> ans, int[] factorial, int cut) {
        if (isFind) {
            return;
        }
        // 这种剪枝的方式不够彻底，只剪了成功之后的，其实成功之前的也有很多可以被剪掉
        // 成功之前剪掉的逻辑是，每个选择对应的子节点的个数是一定的，所有的选择个数也是一定的
        // 比如有九个数，我要求第二十万个解答，因为选择一作为头之后，所有的可能只有8! = 40320 ，那么以1打头的我们无需递归，直接跳过
        // 类似的可以跳过前四个数打头的，直到以5打头的树才有后续的处理，这时候因为第五个也有8!个取法，我们要怎么过滤到只有一条呢？
        // 方法是类似的

        // 这边一旦找到就是最后的答案，那些不满足条件的全部被前剪枝给过滤了
        if (trace.size() == n) {

            // 这个参数是为了后剪枝
            isFind = true;
            ans.addAll(trace);
            return;
        }

        // 然后从小到大选下一个数
        for (int i = 1; i <= n; i++) {
            if (!trace.contains(i)) {
                trace.add(i);
                // 添加i之后 需要判断是否需要剪枝
                if (factorial[n - trace.size()] < k) {
                    // 随着跳过的数据，k的值也要减去那些跳过的数据
                    // 最终的那个值的时候，k应该等于一
                    // 这个就是疯狂剪枝
                    k = k - factorial[n - trace.size()];
                    trace.remove(trace.size() - 1);
                    continue;
                }
                dfs(n, k, trace, ans, factorial, cut);
                // 上一层dfs后，可能已经找到了，直接退出
                if (isFind) {
                    return;
                }
                trace.remove(trace.size() - 1);
            }

        }
    }
}
