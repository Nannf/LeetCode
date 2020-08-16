package race;

/**
 * @auth Nannf
 * @date 2020/8/16 10:35
 * @description: 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
 * <p>
 * 输入：arr = [2,6,4,1]
 * 输出：false
 * 解释：不存在连续三个元素都是奇数的情况。
 * <p>
 * 输入：arr = [1,2,34,3,4,5,7,23,12]
 * 输出：true
 * 解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
 */
public class Solution_5185 {

    public static void main(String[] args) {
        int[] arr = {1,2,34,3,4,5,7,23,12};
        if(new Solution_5185().threeConsecutiveOdds(arr)) {
            System.out.println("bingo!");
        }
    }

    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
