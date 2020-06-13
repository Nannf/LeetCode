import java.util.HashMap;
import java.util.Map;

/**
 * @auth Nannf
 * @date 2020/6/13 9:23
 * @description:
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 */
public class Solution_70 {

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
    }

    private static Map<Integer,Integer> map = new HashMap<>();
    public static int climbStairs(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n <= 2) {
            map.put(n,n);
            return n;
        }
        int num1 = climbStairs(n-1);
        map.put(n-1,num1);
        int num2 = climbStairs(n-2);
        map.put(n-2,num2);
        return num1+num2;
    }
}
