import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/6/16 16:38
 * @description:
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 */
public class Solution_509 {
    Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new Solution_509().fib(2));
    }
    public int fib(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N 不能为负数");
        }
        if (N <= 1) {
            return N;
        }

        int num;
        int num2 ;
        if (map.containsKey(N -1 )){
            num = map.get(N-1);
        } else {
            num = fib(N-1);
        }
        if (map.containsKey(N-2)) {
            num2 = map.get(N-2);
        } else {
            num2 =  fib(N-2);
        }
        return num +num2;
    }
}
