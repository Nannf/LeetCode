import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/1 15:58
 * @description: 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * <p>
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * <p>
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * <p>
 * <p>
 * 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * <p>
 * <p>
 * 输入: strs = ["10", "0", "1"], m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 * <p>
 * <p>
 * ["10","0001","111001","1","0"]
 * 3
 * 4
 * <p>
 * ["10","0001","111001","1","0"]
 * 4
 * 3
 * <p>
 * ["11111","100","1101","1101","11000"]
 * 5
 * 7
 * <p>
 * ["0","1101","01","00111","1","10010","0","0","00","1","11","0011"]
 * 63
 * 36
 *
 * ["0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11",
 * "01","00","01111","0011","1","1000","0","11101","1","0","10","0111"]
 * 9
 * 80
 */
public class Solution_474 {

    public static void main(String[] args) {
        String[] strs = {"0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"};
        System.out.println(new Solution_474().findMaxForm(strs, 9, 80));
        System.out.println("totalCount : " + totalCount + " , skipCount : " + skipCount);
    }

    static int totalCount = 0;
    static int skipCount = 0;
    int ans = 0;

    // 动态规划，由小推大
    // 第一步就是建议每一步的状态
    // 本题回溯可以解
    // 先尝试使用回溯进行解法,不出意外，回溯超时
    // 回溯超时，就需要用备忘录了
    // 备忘录是为了不重复计算，哪些属于重复计算呢？
    // 我们可以单纯的使用m和n作为备忘录的条件吗
    // 其实是不可以的，我们需要把已做的选择
    public int findMaxForm(String[] strs, int m, int n) {
        List<List<Integer>> memo = new ArrayList<>();
        // 这是回溯过程中的轨迹
        List<Integer> trace = new ArrayList<>();
        backtrace(trace, strs, m, n, memo, 0);
        return ans;
    }

    private void backtrace(List<Integer> trace, String[] strs, int m, int n, List<List<Integer>> memo, int s) {
        // 我们在一开始需要判断，当前的情况我们有没有处理过
        if (contains(new ArrayList<>(trace), memo)) {
            return;
        }
        memo.add(new ArrayList<>(trace));
        // 结束条件是给定的0和1 全部用完了 或者 已经遍历到数组的最后了
        if ((m <= 0 && n <= 0) || judge(trace, strs.length - 1) || trace.size() == strs.length || s == strs.length) {
            ans = Math.max(ans, trace.size());
            return;
        }
        for (; s < strs.length; s++) {
            if (trace.contains(s)) {
                continue;
            }
            int oneNum = countOneNumber(strs[s].toCharArray(), 1);
            int zeroNum = countOneNumber(strs[s].toCharArray(), 0);
            if (m >= zeroNum && n >= oneNum) {
                trace.add(s);
                backtrace(trace, strs, m - zeroNum, n - oneNum, memo, s + 1);
                trace.remove(trace.size() - 1);
            } else {
                ans = Math.max(ans, trace.size());
            }
        }
    }

    private boolean contains(List<Integer> trace, List<List<Integer>> memo) {
        for (List<Integer> list : memo) {
            if (list.size() == trace.size()) {
                Collections.sort(trace);
                Collections.sort(list);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != trace.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean judge(List<Integer> trace, int len) {
        int max = 0;
        for (int i : trace) {
            max = Math.max(max, i);
        }
        return max == len;
    }

    private int countOneNumber(char[] chars, int num) {
        int ans = 0;
        for (char c : chars) {
            if (Integer.parseInt(String.valueOf(c)) == num) {
                ans++;
            }
        }
        return ans;
    }
}
