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
        if (isValid(str)) {
            System.out.println("bingo!");
        }
    }

    public static boolean isValid(String s) {
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
