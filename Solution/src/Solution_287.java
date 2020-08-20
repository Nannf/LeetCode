import java.util.HashSet;
import java.util.Set;

/**
 * @auth Nannf
 * @date 2020/8/20 19:22
 * @description:
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_287 {

    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i :nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }

    public int findDuplicate_HashTable(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i :nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }
}
