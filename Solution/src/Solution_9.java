/**
 * @auth Nannf
 * @date 2020/6/10 10:07
 * @description: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 输入: 121
 * 输出: true
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 */
public class Solution_9 {
    public boolean isPalindrome(int x) {
        // 负数是肯定不对的
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (getIntFromChar(chars[i]) == getIntFromChar(chars[j])) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    private int getIntFromChar(char c) {
        return (int) (c - '0');
    }

    public static void main(String[] args) {
        System.out.println(new Solution_9().isPalindrome(121));
    }
}
