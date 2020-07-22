import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/7/22 9:21
 * @description: 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer_11 {

    public static void main(String[] args) {
        int[] nums = {10, 1, 10, 10, 10};

        System.out.println(new Offer_11().minArray(nums));
    }

    public int minArray_solution1(int[] numbers) {
        // 直接归并排序
        Arrays.sort(numbers);
        return numbers[0];
    }

    // 二分查找 找到第一个比首元素小的元素，如果不存在，返回首元素
    public int minArray(int[] numbers) {

        int low = 0;
        int hi = numbers.length - 1;
        while (low <= hi) {
            // 防止相加溢出
            int mid = low + ((hi - low) >> 2);
            // 这边涉及到和队首的元素比还是队尾的元素比
            //  1,2，3,4,0 队首的值 如果出现了翻转 一定是大于队尾的值的，所以 大于队首的值 一定大于队尾的值
            // 如果中间的值比队首的大，表明 中间的位置还没有到翻转点，这时候 low 变成mid+1
            if (numbers[mid] > numbers[0]) {
                low = mid + 1;

            } else if (numbers[mid] < numbers[0]) {
                // 如果中间的值比队首的小，说明已经到了翻转点之后了，这时候mid的值可能就是翻转点，或者之后
                // 如果mid本身是翻转点，直接返回
                if (numbers[mid - 1] >= numbers[0]) {
                    return numbers[mid];
                }
                // 否则翻转点处在mid 之前
                hi = mid - 1;
            } else {
                // 如果两者相等，没办法判断 mid 是处在翻转点前还是后，只能一步步挪过去
                // 问题是从后往前挪，还是从前往后挪 这个需要看你是跟队首比较还是队尾比较
                // 如果是和队首比较，就是从前往后，如果是和队尾比较，就是从后往前
                low = low + 1;
            }
        }
        return numbers[0];
    }
}
