package race;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/23 10:32
 * @description: 圆形赛道上经过次数最多的扇区
 * <p>
 * <p>
 * 给你一个整数 n 和一个整数数组 rounds 。有一条圆形赛道由 n 个扇区组成，
 * 扇区编号从 1 到 n 。现将在这条赛道上举办一场马拉松比赛，
 * 该马拉松全程由 m 个阶段组成。其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，
 * 到扇区 rounds[i] 结束。举例来说，第 1 阶段从 rounds[0] 开始，到 rounds[1] 结束。
 * <p>
 * 请你以数组形式返回经过次数最多的扇区，按扇区编号 升序 排列。
 * <p>
 * 注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。
 * <p>
 * 输入：n = 4, rounds = [1,3,1,2]
 * 输出：[1,2]
 * 解释：本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示：
 * 1 --> 2 --> 3（阶段 1 结束）--> 4 --> 1（阶段 2 结束）--> 2（阶段 3 结束，即本场马拉松结束）
 * 其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4 都只经过了一次。
 * <p>
 * 输入：n = 2, rounds = [2,1,2,1,2,1,2,1,2]
 * 输出：[2]
 * <p>
 * 输入：n = 7, rounds = [1,3,5,7]
 * 输出：[1,2,3,4,5,6,7]
 * <p>
 * 2 <= n <= 100
 * 1 <= m <= 100
 * rounds.length == m + 1
 * 1 <= rounds[i] <= n
 * rounds[i] != rounds[i + 1] ，其中 0 <= i < m
 */
public class Solution_5945 {

    public static void main(String[] args) {
        int[] n = {2,1,2,1,2,1,2,1,2};
        for (int i : new Solution_5945().mostVisited(2, n)) {
            System.out.println(i);
        }
    }

    public List<Integer> mostVisited(int n, int[] rounds) {
        // 马拉松需要经过的轮数
        int m = rounds.length - 1;
        // 存放的是 0-n 这 n+1 个数
        int[] ans = new int[n + 1];
        // 第一个需要加上，单独处理，以后的所有的开始的那个都是不算的
        ans[rounds[0]] = 1;
        // 模拟每个阶段
        for (int i = 1; i <= m; i++) {
            // 开始的点
            int start = rounds[i - 1];
            // 结束的点
            int end = rounds[i];
            // 由题意可知，start 和 end 是不相同的那么需要分情况讨论
            // 如果结束的比开始的大
            if (end > start) {
                // 沿途路过的数的出现次数都加一
                for (int j = start+1; j <= end; j++) {
                    ans[j] = ans[j] + 1;
                }
            } else {
                // 如果结束 比开始的小，说明跨圈了
                // 跨圈 比如我们的数 是 1-7 现在start = 5，end =2 其实我们只要把 除了2-5之间的所有的值全部加一即可
                for (int j = 1; j <= n; j++) {
                    if (j <= end || j > start) {
                        ans[j] = ans[j] + 1;
                    }
                }
            }
        }
        // m圈跑完 我们需要对数组进行累计
        List<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (ans[i] > max) {
                result.clear();
                result.add(i);
                max = ans[i];
            } else if (ans[i] == max) {
                result.add(i);
            } else {
                // do nothing
            }
        }
        return result;
    }
}
