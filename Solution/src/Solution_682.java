import java.util.Stack;

/**
 * @auth Nannf
 * @date 2020/6/14 16:33
 * @description: 你现在是棒球比赛记录员。
 * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 * <p>
 * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
 * 你需要返回你在所有回合中得分的总和。
 * <p>
 * 输入: ["5","-2","4","C","D","9","+","+"]
 * 输出: 27
 * 解释:
 * 第1轮：你可以得到5分。总和是：5。
 * 第2轮：你可以得到-2分。总数是：3。
 * 第3轮：你可以得到4分。总和是：7。
 * 操作1：第3轮的数据无效。总数是：3。
 * 第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
 * 第5轮：你可以得到9分。总数是：8。
 * 第6轮：你可以得到-4 + 9 = 5分。总数是13。
 * 第7轮：你可以得到9 + 5 = 14分。总数是27。
 */
public class Solution_682 {
    public static void main(String[] args) {
        String[] str = new String[]{"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(str));
    }
    public static int calPoints(String[] ops) {
        Stack<Integer> numberStack = new Stack<>();
        int result = 0;
        for (String str : ops) {
            switch (str) {
                case "+":
                    int num1 = numberStack.pop();
                    int num2 = numberStack.pop();
                    int num3 = num1 + num2;
                    numberStack.push(num2);
                    numberStack.push(num1);
                    numberStack.push(num3);
                    break;
                case "C":
                    numberStack.pop();
                    break;
                case "D":
                    int num4 = numberStack.pop();
                    int num5 = num4 * 2;
                    numberStack.push(num4);
                    numberStack.push(num5);
                    break;
                default:
                    numberStack.push(Integer.parseInt(str));
            }
        }

        while (!numberStack.isEmpty()) {
            result += numberStack.pop();
        }
        return result;
    }
}
