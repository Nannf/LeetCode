/**
 * @auth Nannf
 * @date 2020/10/21 19:52
 * @description: 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * <p>
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 * <p>
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * <p>
 * "vtkgn"
 * "vttkgnn"
 *
 * "pyplrz"
 * "ppyypllr"
 */
public class Solution_925 {
    public static void main(String[] args) {
        if (new Solution_925().isLongPressedName("pyplrz", "ppyypllr")) {
            System.out.println("bingo");
        }
    }

    public boolean isLongPressedName(String name, String typed) {
        // typed出现的次数大于等于name中的个数
        // 双指针，当发现name 和typed不同时判断上一个是否相同
        int i = 0;
        int j = 0;
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if(i ==0) {
                    return false;
                }
                if (typed.charAt(j) == name.charAt(--i)) {
                    j++;
                    i++;
                } else {
                    return false;
                }
            }
        }
        if (i<name.length()) {
            return false;
        }
        if (j == typed.length()) {
            return true;
        }
        while (j < typed.length()) {
            if (typed.charAt(j) == typed.charAt(j-1)) {
                j++;
            }else {
                return false;
            }
        }
        return true;

    }
}
