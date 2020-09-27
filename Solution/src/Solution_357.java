/**
 * @auth Nannf
 * @date 2020/9/25 19:13
 * @description: 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class Solution_357 {
    int ans = 0;

    public static void main(String[] args) {
        System.out.println(new Solution_357().countNumbersWithUniqueDigits(4));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // 因为n=1时只有[0,10) 是一位数
        // n = 2 时[0.100)是两位数，同理可推n=几就是几位数，但是最大不能超过十，因为超过十后面所有的数都是不满足需求的
        int maxLen = Math.min(n, 10);
        // 感觉这个不能从数字的大小着手，因为太大了
        // 可以从长度着手
        // 如果是一位数，那么要除去的个数是0；
        // 如果是两位数，需要出去的有9个，之所以有九个是因为
        // 如果是三位数，需要除去的有多少呢？
        int[] dp = new int[maxLen + 1];
        // 一位数 总共有十种取法
        dp[1] = 10;
        int sum = dp[1];
        for (int i = 2; i <= maxLen; i++) {
            // 因为首位只能取1-9
            dp[i] = count(i);
            sum += dp[i];
        }
        return sum;
    }

    private int count(int i) {
        if (i == 2) {
            return 9 * 9;
        }
        int sum = 81;
        for (int j = 3; j <= i; j++) {
            sum *= (11 - j);
        }
        return sum;
    }


    public int countNumbersWithUniqueDigits_dp(int n) {
        if (n == 0) {
            return 1;
        }

        int maxLen = Math.min(n, 10);
        // 感觉这个不能从数字的大小着手，因为太大了
        // 可以从长度着手
        // 如果是一位数，那么要除去的个数是0；
        // 如果是两位数，需要出去的有9个，之所以有九个是因为
        // 如果是三位数，需要除去的有多少呢？
        int[] dp = new int[maxLen + 1];
        dp[0] = 1;
        dp[1] = 9;
        int sum = dp[0] + dp[1];
        for (int i = 2; i <= maxLen; i++) {
            // dp[i] 表示之前已经用了i-1个数，还剩10-i +1 个数
            // 假设dp[i-1] 一共有k个数，那么对每个数而言，其前面都有11-i个选择，但是要去除那些答案中开始是包含0的解
            // 但是我们怎么找到之前没有放0的记录有多少条呢
            // TODO 这边我不清楚为什么不需要去除那些0大头的数
            dp[i] = dp[i - 1] * (11 - i);
            sum += dp[i];
        }
        return sum;
    }


    // 暴力解法，超时，
    public int countNumbersWithUniqueDigits_bruce(int n) {
        for (double i = 0d; i < Math.pow(10, n); i++) {
            if (occured(i)) {
                continue;
            }
            ans++;
        }
        return ans;
    }

    private void backtrace(double i, int n) {

        if (i >= Math.pow(10, n)) {
            System.out.println("bingo" + i);
            return;
        }
        System.out.println("before" + i);
        if (occured(i)) {
            return;
        }
        System.out.println("after" + i);
        ans++;
        backtrace(i + 1, n);
    }

    private boolean occured(double i) {
        int[] memo = new int[10];
        String str = String.valueOf(i);
        char[] array = str.toCharArray();
        for (int k = 0; k < array.length; k++) {
            if (!Character.isDigit(array[k])) {
                break;
            }
            int t = Integer.parseInt(String.valueOf(array[k]));
            if (memo[t] != 0) {
                return true;
            }
            memo[t] = 1;
        }
        return false;
    }
}
