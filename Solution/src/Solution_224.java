import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auth Nannf
 * @date 2020/6/8 19:04
 * @description: 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Solution_224 {

    //
    public static int calculate(String s) {
        // 存放所有的操作数
        Stack<Character> opStack = new Stack<>();
        // 存放所有的数字
        Stack<Integer> numStack = new Stack<>();
        // 去除所有的空格
        String str = s.replaceAll("\\s+","");

        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 临时变量
        int result = 0;
        for (char c : chars) {
            switch (c) {
                case '-':
                case '+':
                    if (sb.length()!= 0) {
                        numStack.push(Integer.parseInt(sb.toString()));
                        sb.setLength(0);
                    }
                    if (opStack.isEmpty()) {
                        opStack.push(c);
                    } else {
                        char c1 = opStack.pop();

                        if (c1 == '+'){
                            int num1 = numStack.pop();
                            int num2 = numStack.pop();
                            result = (num2 + num1);
                            numStack.push(result);
                            opStack.push(c);
                        } else if (c1 == '-'){
                            int num1 = numStack.pop();
                            int num2 = numStack.pop();
                            result = (num2 - num1);
                            numStack.push(result);
                            opStack.push(c);
                        } else {
                            opStack.push(c1);
                            opStack.push(c);
                        }
                    }
                    break;
                case '(':
                    if (sb.length()!= 0) {
                        numStack.push(Integer.parseInt(sb.toString()));
                        sb.setLength(0);
                    }
                    opStack.push(c);
                    break;
                case ')':
                    if (sb.length()!= 0) {
                        numStack.push(Integer.parseInt(sb.toString()));
                        sb.setLength(0);
                    }
                    while (opStack.peek() != '(') {
                        char c1 = opStack.pop();
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        if (c1 == '+'){
                            result = (num2 + num1);
                        } else if (c1 == '-'){
                            result = (num2 - num1);
                        }
                        numStack.push(result);
                    }
                    opStack.pop();
                    break;
                default:
                    sb.append(c);
            }
        }
        if (sb.length()!= 0) {
            numStack.push(Integer.parseInt(sb.toString()));
            sb.setLength(0);
        }
        while (!opStack.isEmpty()) {
            char c = opStack.pop();
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            if (c == '+') {
                result = (num1 + num2);
            } else {
                result = (num2 - num1);
            }
            numStack.push(result);
        }
        return numStack.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculate("2-(5-6)"));
    }
}
