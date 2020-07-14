/**
 * @auth Nannf
 * @date 2020/7/14 15:43
 * @description: 剑指 Offer 51. 数组中的逆序对
 * <p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 输入: [7,5,6,4]
 * 输出: 5
 */
public class Offer_51 {
    static int ans = 0;

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7};
        System.out.println(new Offer_51().reversePairs(nums));
    }

    public int reversePairs(int[] nums) {
        ans =0;
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        sort(nums, 0, n - 1);
        return ans;
    }

    // 对数组的l -h 之间的进行排序
    private void sort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int mid = (h + l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    private void merge(int[] nums, int l, int mid, int h) {
        // l - h 之间的数据排序后
        int[] tmp = new int[h - l + 1];
        int i = l;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= h) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
                // 当出现大于的时候,这边加的时候 并不是只加一，是加上现在左边所有没被放到数组中的数据数量
                ans += mid - i + 1;
            }
        }
        for (; i <= mid; i++) {
            tmp[k++] = nums[i];
        }
        for (; j <= h; j++) {
            tmp[k++] = nums[j];
        }
        System.arraycopy(tmp, 0, nums, l, tmp.length);
    }
}
