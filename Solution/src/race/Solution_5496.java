package race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/23 11:00
 * @description: 你可以获得的最大硬币数目
 * <p>
 * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
 * <p>
 * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
 * Alice 将会取走硬币数量最多的那一堆。
 * 你将会取走硬币数量第二多的那一堆。
 * Bob 将会取走最后一堆。
 * 重复这个过程，直到没有更多硬币。
 * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
 * <p>
 * 返回你可以获得的最大硬币数目
 * <p>
 * 输入：piles = [2,4,1,2,7,8]
 * 输出：9
 * 解释：选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 7 枚硬币的那堆，Bob 取走最后一堆。
 * 选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 2 枚硬币的那堆，Bob 取走最后一堆。
 * 你可以获得的最大硬币数目：7 + 2 = 9.
 * 考虑另外一种情况，如果选出的是 (1, 2, 8) 和 (2, 4, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
 * <p>
 * 输入：piles = [2,4,5]
 * 输出：4
 * <p>
 * 输入：piles = [9,8,7,6,5,1,2,3,4]
 * 输出：18
 * <p>
 * 3 <= piles.length <= 10^5
 * piles.length % 3 == 0
 * 1 <= piles[i] <= 10^4
 */
public class Solution_5496 {

    int result = 0;

    // 换 回溯做
    public int maxCoins(int[] piles) {
        List<Integer> trace = new ArrayList<>();
        int n = piles.length / 3;
        backtrace(trace, n, piles);
        return result;
    }

    private void backtrace(List<Integer> trace, int n, int[] piles) {
        if (trace.size() == n) {
            result = Math.max(result, getSum(trace));
            return;
        }
        for (int i = 0; i < piles.length; i++) {

        }
    }

    private int getSum(List<Integer> trace) {
        int sum = 0;
        for (int i = 0; i < trace.size(); i++) {
            sum += trace.get(i);
        }
        return sum;
    }


    // 感觉可以二分，这个给定了最大值
    public int maxCoins_binary(int[] piles) {
        int ans = 0;
        Arrays.sort(piles);
        int n = piles.length / 3;
        int max = 0;
        int min = 0;
        // 这是能拿到的最大值和最小值
        for (int i = 0; i < n; i++) {
            max += piles[i];
            min += piles[piles.length - 1 - i];
        }

        while (min <= max) {
            // 这个是中间值
            int mid = min + ((max - min) >> 1);
            // 如果能满足 我们需要先保存 这个mid，然后因为我们需要找的是最大值，所以mid 需要变大
            if (check(mid, piles, n)) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    // 就是我们先看能不能找到 n个数相加 = mid的 如果有 再去找看看有没有 n 个数 比这三个大的，如果找到了 说明这个符号情况
    // 如果没找到 说明这个是大了还是小了呢？说明给定的mid 大了
    private boolean check(int mid, int[] piles, int n) {
        // 不是很好找 我放弃这种思路
        return false;
    }
}
