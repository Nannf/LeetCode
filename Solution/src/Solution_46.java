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
        if (str.length() == 1) {
            return 1;
        }

        if (str.length() == 2)
            if (!str.startsWith("0") && Integer.parseInt(str) <= 25) {
                return 2;
            } else {
                return 1;
            }
       String tmpStr = str.substring(0,2);
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
