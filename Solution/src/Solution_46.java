import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/6/9 8:19
 * @description: 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * 0 <= num < 2^31
 */
public class Solution_46 {

    public int translateNum(int num) {
        // 先递归获取所有的可能序列，在减去那些不合法的序列即可
        // 类似爬楼梯，一次爬一层 一次怕两层
        // 先从num中分离出单个字符
        String str = num + "";
        f(str);
        return f(str);
    }

    private int f(String str) {
        // 如果字符串最后只剩一个字符，这时候只有一个解法
        if (str.length() == 1) {
            return 1;
        }

        // 如果只剩两个字符
        if (str.length() == 2)
            // 如果这个字符不以0打头，并且小于26 则有两种解法 比如 23 有 2和3 以及23两种
            if (!str.startsWith("0") && Integer.parseInt(str) <= 25) {
                return 2;
            } else {
                // 否则只有一个解法
                return 1;
            }
        // 递归是有条件的
       String tmpStr = str.substring(0,2);
            //当当前的最前面的两个数字是合法的数字，表示是可以进行这时候跳过一个和跳过两个都是合法的
        // 举例而言，比如 624 ,62并不是一个合法的数字，这时候 就只有 6,24 和 62,4（不合法被舍弃） 这一种拆分方法，
        // 对123 而言，因为12是一个合法数字，这时候 123 <==> 1,23 + 12,3 的解法种类之和。
        if (!tmpStr.startsWith("0") && Integer.parseInt(tmpStr) <= 25) {
            return f(str.substring(1)) + f(str.substring(2));
        } else {
            return f(str.substring(1)) ;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution_46().translateNum(624));
    }


}
