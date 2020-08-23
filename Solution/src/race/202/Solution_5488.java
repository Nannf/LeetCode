package race;

/**
 * @auth Nannf
 * @date 2020/8/16 10:41
 * @description: 存在一个长度为 n 的数组 arr ，其中 arr[i] = (2 * i) + 1 （ 0 <= i < n ）。
 * <p>
 * 一次操作中，你可以选出两个下标，记作 x 和 y （ 0 <= x, y < n ）
 * 并使 arr[x] 减去 1 、arr[y] 加上 1 （即 arr[x] -=1 且 arr[y] += 1 ）。
 * 最终的目标是使数组中的所有元素都 相等 。
 * 题目测试用例将会 保证 ：在执行若干步操作后，数组中的所有元素最终可以全部相等。
 * <p>
 * 给你一个整数 n，即数组的长度。请你返回使数组 arr 中所有元素相等所需的 最小操作数 。
 * <p>
 * 输入：n = 3
 * 输出：2
 * 解释：arr = [1, 3, 5]
 * 第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
 * 第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
 * <p>
 * 输入：n = 6
 * 输出：9
 */
public class Solution_5488 {
    public static void main(String[] args) {
        System.out.println(new Solution_5488().minOperations(6));
    }

    public int minOperations(int n) {
        if (n <= 1) {
            return 0;
        }
        int count = 0;
        int mid = (1 + 2 * n - 1) / 2;
        for (int i = 0; i < n / 2; i++) {
            count+= (mid - (i*2 + 1));
        }
        return count;
    }
}
