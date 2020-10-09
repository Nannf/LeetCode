import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/9 20:22
 * @description:
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 *
 * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
 *
 * 注意:
 * n 和 k 均为非负的整数。
 *
 * 输入: n = 3，k = 2
 * 输出: 6
 * 解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:
 *
 *             柱 1    柱 2   柱 3
 *  -----      -----  -----  -----
 *    1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1 
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 *
 *
 */
public class Solution_276 {

    int ans = 0;
    // 回溯可解，但是时间复杂都是k^n 贼高，先试试回溯会不会超时
    // 回溯最后还需要去重
    // 先试试把
    public int numWays(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        return -1;
    }
}
