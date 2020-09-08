/**
 * @auth Nannf
 * @date 2020/9/8 23:14
 * @description:
 * 和 solution_239相同
 */
public class Offer_59 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        for (int i : new Solution_239().maxSlidingWindow(nums, 3)) {
            System.out.println(i);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] ans = new int[nums.length - k + 1];
        //双指针
        int i = 0;
        int j = k - 1;

        while (j < nums.length) {
            ans[i] = findMax(nums, i, k);
            i++;
            j++;
        }
        return ans;

    }

    private int findMax(int[] nums, int i, int k) {
        int max = Integer.MIN_VALUE;
        for (int j = i; j < i + k; j++) {
            max = Math.max(nums[j], max);
        }
        return max;
    }
}
