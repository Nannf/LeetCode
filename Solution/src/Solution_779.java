/**
 * @auth Nannf
 * @date 2020/10/31 16:13
 * @description: 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <p>
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 * <p>
 * 输入: N = 1, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 2
 * 输出: 1
 * <p>
 * 输入: N = 4, K = 5
 * 输出: 1
 * <p>
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 * <p>
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 */
public class Solution_779 {

    public static void main(String[] args) {
        System.out.println(new Solution_779().kthGrammar(2, 1));
    }

    // 这个其实感觉是个找父亲的过程，不停的找它的父亲是谁，一开始问的人不知道，它问它上一个，上一个的索引是
    public int kthGrammar(int N, int K) {
        return dfs(N, K);
    }

    // 这个函数的目的就是找出第n行的第k个数的爸爸是几
    private int dfs(int n, int k) {
        if (n <= 2) {
            if (k == 1) {
                return 0;
            }
            if (k == 2) {
                return 1;
            }
        }
        if (k == 1) {
            return 0;
        }
        if (k == 2) {
            return 1;
        }
        int m = dfs(n - 1, getNext(k));
        if (m == 0) {
            if (k % 2 == 0) {
                return 1;
            }
            return 0;
        }
        if (k % 2 == 0) {
            return 0;
        }
        return 1;
    }

    private int getNext(int k) {
        if (k % 2 == 0) {
            return k / 2;
        }
        return k / 2 + 1;
    }
}
