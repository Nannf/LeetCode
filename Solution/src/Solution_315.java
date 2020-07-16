import java.util.*;


/**
 * @auth Nannf
 * @date 2020/7/11 14:54
 * @description: 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 * <p>
 * [26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41]
 * <p>
 * [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
 */
public class Solution_315 {
    // 索引数组
    int[] index;
    // 结果数组
    int[] ans;

    public static void main(String[] args) {
        int[] nums = new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41};
        System.out.println(new Solution_315().countSmaller(nums).toString());
    }


    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }
        if (n == 1) {
            list.add(0);
            return list;
        }
        index = new int[n];
        ans = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        sort(nums, 0, n - 1);
        for (int i : ans) {
            list.add(i);
        }
        return list;
    }

    private void sort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int mid = (l + h) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    /**
     * nums  [6,5,2,1]
     * index [0,1,2,3]
     *
     * @param a
     * @param l
     * @param mid
     * @param r
     */
    private void merge(int[] a, int l, int mid, int r) {
        int[] tempIndex = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            // 移动的是索引数组，真实的数据不变
            // 这个跟计算总逆序度的不同，计算总逆序度时，出现一次交换，就把逆序度加一即可，本题需要计算每个数字的逆序度
            // 因为一个数字随着对比合并的过程中是会不断移动的，每次移动 数字的逆序度都要加一，我们在移动一个数字的时候怎么定位到它的位置在原数组中的位置是多少呢？这就是这个问题的关键
            // 之前考虑过用map来存储每个数字对应的逆序度，但是这样的实现就要求一个条件，就是数据不能重复，否则就要修改数据结构，一个数据除了保存他的值之外，还需保存他的初始索引
            // 还有一种思路就是用一个索引数组，每次只移动索引数组，实际上原数组的数据是不变的
            // a      [5,2,6,1]
            // index  [0,1,2,3]
            // 第一次归并之后:
            // index  [1,0,2,3]
            // index  [1,0,3,2]
            // index  [3,1,0,2]
            //  如果左边比右边小，把左边的指针移动
            if (a[index[i]] <= a[index[j]]) {
                tempIndex[k++] = index[i++];
            } else {
                // 否则，移动右边的指针
                tempIndex[k++] = index[j++];
                // 左边的指针i的逆序度+1
                ans[index[i]] += 1;
                // 左边到mid的所有数字的逆序度都要+1
                int tmp = i + 1;
                while (tmp <= mid) {
                    ans[index[tmp++]] += 1;
                }
            }
        }
        for (; i <= mid; ) {
            tempIndex[k++] = index[i++];
        }
        for (; j <= r; ) {
            tempIndex[k++] = index[j++];
        }
        System.arraycopy(tempIndex, 0, index, l, r - l + 1);
    }


    /**
     * 暴力解法 O(n^2) 超时
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller_Ans1(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            list.add(i, count);
        }
        return list;
    }
}
