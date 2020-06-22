/**
 * @auth Nannf
 * @date 2020/6/22 21:48
 * @description: 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 */
public class Solution_703 {
    int k;
    int[] nums;
    int result;

    public static void main(String[] args) {
        int[] array = new int[] {4,5,8,2};
        Solution_703 s = new Solution_703(3,array);
        System.out.println(s.add(3));
        System.out.println(s.add(5));
        System.out.println(s.add(10 ));
        System.out.println(s.add(9));
        System.out.println(s.add(4));
    }

    public Solution_703(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
    }

    public int add(int val) {
        int[] newArray = new int[nums.length + 1];
        System.arraycopy(nums, 0, newArray, 0, nums.length);
        newArray[nums.length] = val;
        nums = newArray;
        findK(nums, 0, nums.length - 1);
        System.out.println("size + :::: "+nums.length);
        return result;
    }

    private void findK(int[] nums, int p, int r) {
        int q = part(nums, p, r);
        if (q + 1 == k) {
            result = nums[q];
            return;
        }
        if (q + 1 < k) {
            findK(nums, q + 1, r);
        } else {
            findK(nums, p, q - 1);
        }
    }

    private int part(int[] nums, int p, int r) {
        int i = p;
        int x = nums[r];
        for (int j = p; j < r; j++) {
            if (nums[j] > x) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            }
        }
        int tmp = nums[i];
        nums[i] = nums[r];
        nums[r] = tmp;
        return i;
    }
}
