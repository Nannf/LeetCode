import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/8/3 14:34
 * @description: 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_415 {
    public static void main(String[] args) {
        System.out.println(new Solution_415().addStrings("9","1"));
    }

    public String addStrings(String num1, String num2) {
        // 进位标志
        int sign = 0;
        List<Integer> resultList = new ArrayList<>();
        int i = num1.length();
        int j = num2.length();
        while (i > 0 && j > 0) {
            int n = Integer.parseInt(String.valueOf(num1.charAt(i-1)));
            int m = Integer.parseInt(String.valueOf(num2.charAt(j-1)));
            if (n+m+sign >9) {
                resultList.add(n+m+sign - 10);
                sign = 1;
            }else {
                resultList.add(n+m+sign);
                sign = 0;
            }
            i--;
            j--;
        }
        while (i>0) {
            int n = Integer.parseInt(String.valueOf(num1.charAt(i-1)));
            if (n+sign>9) {
                resultList.add(n+sign -10);
                sign = 1;
            }else {
                resultList.add(n+sign);
                sign=0;
            }
            i--;
        }
        while (j>0) {
            int n = Integer.parseInt(String.valueOf(num2.charAt(j-1)));
            if (n+sign>9) {
                resultList.add(n+sign -10);
                sign = 1;
            }else {
                resultList.add(n+sign);
                sign=0;
            }
            j--;
        }
        if(sign == 1) {
            resultList.add(1);
        }
        StringBuilder sb = new StringBuilder();
        for (int l = resultList.size(); l>0;l--) {
            sb.append(resultList.get(l-1));
        }
        return sb.toString();
    }
}
