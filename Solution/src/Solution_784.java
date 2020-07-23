import java.util.ArrayList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/7/23 9:19
 * @description: 给定一个字符串S，通过将字符串S中的每个字母转变大小写，
 * 我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_784 {

    public static void main(String[] args) {
        List<String> list = new Solution_784().letterCasePermutation("3z4");
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     *
     * @param S
     * @return
     * 回溯是有套路的
     */
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        char[] choices = S.toCharArray();
        StringBuilder track = new StringBuilder();
        backtrack(track, choices, 0, result);
        return result;
    }

    /**
     * @param track   记录回溯的路径
     * @param choices 所有的选择
     * @param result  所有解的收集
     */
    public void backtrack(StringBuilder track, char[] choices, int index, List<String> result) {
        // 递归结束条件
        if (track.length() == choices.length) {
            result.add(track.toString());
            return;
        }

        // 遍历选择
        // 这边通过index 来隐形的控制了要跳过哪些字段
        // 第一个版本在写的时候 通过track的长度和i的大小的关系来判断的，但是实际上不对，因为track在程序运行的过程中是一直变化的，i也是一直变化的，如果靠人脑去把这种两个都是变化的情况考虑清楚
        // 是不太现实的，如果对也是懵对的 所以这边在对所有的情况进行遍历时 要跳过一些已经遍历过的情况 就需要细细的考量，像本例中，每次在递归调用的时候，如果用i表示指针的话，每调用一次递归
        // i的指针都要往后移动一位，这是肯定的。而且不容易出错，也是理所当然的，所以这边使用了i的值来做判断
        for (int i = index; i < choices.length; i++) {
            if (Character.isDigit(choices[i])) {
                track.append(choices[i]);
                backtrack(track, choices, i + 1, result);
                // 删除刚刚的选择
                // 这边的回退也是简单的
                track.setLength(track.length() - 1);
            } else {
                track.append(Character.toLowerCase(choices[i]));
                backtrack(track, choices, i + 1, result);
                track.setLength(track.length() - 1);
                track.append(Character.toUpperCase(choices[i]));
                backtrack(track, choices, i + 1, result);
                track.setLength(track.length() - 1);
            }
        }
    }
}
