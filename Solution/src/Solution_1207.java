import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nannf
 * @date 2020/10/28 21:02
 * @description 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * <p>
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 */
public class Solution_1207 {
    public static void main(String[] args) {
        if (new Solution_1207().uniqueOccurrences(new int[] {1,2,2,1,1,3})) {
            System.out.println("bingo");
        }
    }

    public boolean uniqueOccurrences(int[] arr) {
        Set<Integer> occurTime = new HashSet<>();
        Arrays.sort(arr);
        int j =1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                j++;
            }else {
                if (occurTime.contains(j)) {
                    return false;
                }
                occurTime.add(j);
                j=1;
            }
        }
        return !occurTime.contains(j);
    }

}
