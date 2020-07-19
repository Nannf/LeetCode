import java.util.Stack;

/**
 * @auth Nannf
 * @date 2020/6/23 19:05
 * @description: 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class Solution_67 {
    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.trim().isEmpty() || a == "0") {
            return b;
        }
        if (b == null || b.trim().isEmpty() || b == "0") {
            return a;
        }
        int n = Math.min(a.length(), b.length());
        int symbol = 0;
        Stack<Integer> result = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int tmp = a.charAt(i)-'0' + b.charAt(i)-'0' + symbol;
            result.push(tmp % 2);
            symbol = tmp / 2;
        }
        if(a.length()> n) {
            for (int i = n; i< a.length();i++) {
                int tmp = a.charAt(i)-'0'  + symbol;
                result.push(tmp % 2);
                symbol = tmp / 2;
            }
        }
        if(b.length()> n) {
            for (int i = n; i< b.length();i++) {
                int tmp = b.charAt(i)-'0' + symbol;
                result.push(tmp % 2);
                symbol = tmp / 2;
            }
        }

        if (symbol == 1) {
            result.push(1);
        }
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop());
        }
        return sb.toString();

    }

    public static String addBinary_Slow(String a, String b) {
        if (a == null || a.trim().isEmpty() || a == "0") {
            return b;
        }
        if (b == null || b.trim().isEmpty() || b == "0") {
            return a;
        }

        char[] chars_1 = a.toCharArray();
        char[] chars_2 = b.toCharArray();
        Stack<Integer> nums1 = new Stack<>();
        Stack<Integer> nums2 = new Stack<>();
        for (char c : chars_1) {
            nums1.push(Integer.valueOf(String.valueOf(c)));
        }
        for (char c : chars_2) {
            nums2.push(Integer.valueOf(String.valueOf(c)));
        }
        int symbol = 0;
        Stack<Integer> resultStack = new Stack<>();
        while (!nums1.isEmpty() && !nums2.isEmpty()) {
            int tmp = nums1.pop() + nums2.pop() + symbol;
            if (tmp == 1) {
                resultStack.push(1);
                symbol = 0;
            } else if (tmp == 2) {
                resultStack.push(0);
                symbol = 1;
            } else if (tmp == 3) {
                resultStack.push(1);
                symbol = 1;
            } else {
                resultStack.push(0);
                symbol = 0;
            }
        }
        while (!nums1.isEmpty()) {
            int tmp = nums1.pop() + symbol;
            if (tmp == 1) {
                resultStack.push(1);
                symbol = 0;
            } else if (tmp == 2) {
                resultStack.push(0);
                symbol = 1;
            } else if (tmp == 3) {
                resultStack.push(1);
                symbol = 1;
            } else {
                resultStack.push(0);
                symbol = 0;
            }
        }
        while (!nums2.isEmpty()) {
            int tmp = nums2.pop() + symbol;
            if (tmp == 1) {
                resultStack.push(1);
                symbol = 0;
            } else if (tmp == 2) {
                resultStack.push(0);
                symbol = 1;
            } else if (tmp == 3) {
                resultStack.push(1);
                symbol = 1;
            } else {
                resultStack.push(0);
                symbol = 0;
            }
        }

        if (symbol == 1) {
            resultStack.push(1);
        }
        StringBuilder sb = new StringBuilder();
        while (!resultStack.isEmpty()) {
            sb.append(resultStack.pop());
        }
        return sb.toString();

    }
}
