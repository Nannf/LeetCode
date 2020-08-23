package race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/16 10:53
 * @description: 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，
 * 它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i]
 * ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * <p>
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * <p>
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * <p>
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。
 * 最小磁力为 3 。我们没办法让最小磁力大于 3 。
 * <p>
 * 输入：position = [5,4,3,2,1,1000000000], m = 2
 * 输出：999999999
 * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
 * <p>
 * [1,2,3,4,5,6,7,8,9,10] 4 --> 3;
 * [79,74,57,22] 4 --> 5
 */
public class Solution_5489 {
    public static void main(String[] args) {
        int[] position = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(new Solution_5489().maxDistance(position, 4));
    }


    public int maxDistance(int[] position, int m) {
        int ans = 0;
        // 先把数组按照从小到大排序，好用二分
        Arrays.sort(position);
        // 最小值是1
        int low = 1;
        // 最大值是恰好所有的值都在均分点上的时候，是最大的
        int high = (position[position.length - 1] - position[0]) / (m - 1);

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 如果按照中间这个mid次序来排列，看看能否找到这么多的数满足条件
            if (check(position, mid, m)) {
                // 说明mid 还是小了，可以接着往上加
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] position, int mid, int m) {
        int cnt = 1;
        int first = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - first >= mid) {
                cnt++;
                first = position[i];
            }
        }
        return cnt >= m;
    }


    int result = 0;

    // m 只有每次都放在最中间的时候 最小磁力才能最大 因为数组不是连续的，这个是不对的
    // 贪心算法可以做，每次放的时候 就取最小的数组中，最大的那个；
    // 后来发现贪心算法做不了，因为{1,2,3,4,5,6,7,8,9,10}当m=4时会在6，和7之间出问题
    // 回溯可以做，但是时间复杂度时2^(position.length -2)的时间复杂度
    public int maxDistance_backtrace(int[] position, int m) {
        Arrays.sort(position);
        int x = 0;
        int y = position.length - 1;
        result = 0;
        List<Integer> trace = new ArrayList<>();
        backtrace(trace, position, m - 2);
        return result;
    }

    private void backtrace(List<Integer> trace, int[] position, int m) {
        if (trace.size() == m) {
            int ans = count(trace, position);
            result = Math.max(result, ans);
            return;
        }
        for (int i = 1; i < position.length - 1; i++) {
            trace.add(i);
            backtrace(trace, position, m);
            trace.remove(trace.size() - 1);
        }
    }

    private int count(List<Integer> trace, int[] position) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.addAll(trace);
        list.add(position.length - 1);
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < list.size(); i++) {
            ans = Math.min(position[list.get(i)] - position[list.get(i - 1)], ans);
        }
        return ans;
    }
}
