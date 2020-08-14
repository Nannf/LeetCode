import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Nannf
 * @date 2020/6/1 19:13
 * @description
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 */
public class Solution_20 {

    public static void main(String[] args) {
        String str = "()";
        if (new Solution_20().isValid(str)) {
            System.out.println("bingo!");
        }
    }


    static Map<Character,Character> map = new HashMap<>();
    static {
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
    }
    public boolean isValid(String s) {
        int n = s.length();
        if (n == 0) {
            return true;
        }
        Stack<Character> stack = new Stack();

        for (int i = 0; i< n; i++) {
            switch (s.charAt(i)) {
                case '(':
                case '{':
                case '[':
                    stack.add(s.charAt(i));
                    break;
                default:
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char c= stack.pop();
                    char t = map.get(s.charAt(i));
                    if (c != t) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid_ans(String s) {
        int size = 0;
        char[] data = new char[s.length()];
        for (char c : s.toCharArray()) {
            switch (c){
                case '(':
                case '{':
                case '[':
                    data[size]=c;
                    size++;
                    break;
                case ')':
                    if(size == 0) {
                        return false;
                    }
                    if (data[size-1] == '('){
                        data[size-1] = ' ';
                        size --;
                        break;
                    } else {
                        return false;
                    }

                case'}':
                    if(size == 0) {
                        return false;
                    }
                    if (data[size-1] == '{'){
                        data[size-1] = ' ';
                        size --;
                        break;
                    } else {
                        return false;
                    }
                case']':
                    if(size == 0) {
                        return false;
                    }
                    if (data[size-1] == '['){
                        data[size-1] = ' ';
                        size --;
                        break;
                    } else {
                        return false;
                    }
                default:
                    throw new IllegalArgumentException("不合法的输入!");
            }
        }
        if (size > 0) {
            return false;
        }
        return true;
    }
}
