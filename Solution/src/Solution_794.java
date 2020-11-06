import java.util.HashMap;
import java.util.Map;

/**
 * @author Nannf
 * @date 2020/11/4 13:06
 * @description 用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，
 * 玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
 * <p>
 * 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。
 * “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * <p>
 * 示例 1:
 * 输入: board = ["O  ", "   ", "   "]
 * 输出: false
 * 解释: 第一个玩家总是放置“X”。
 * <p>
 * 示例 2:
 * 输入: board = ["XOX", " X ", "   "]
 * 输出: false
 * 解释: 玩家应该是轮流放置的。
 * <p>
 * 示例 3:
 * 输入: board = ["XXX", "   ", "OOO"]
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: board = ["XOX", "O O", "XOX"]
 * 输出: true
 * <p>
 * 说明:
 * <p>
 * 游戏板 board 是长度为 3 的字符串数组，其中每个字符串 board[i] 的长度为 3。
 *  board[i][j] 是集合 {" ", "X", "O"} 中的一个字符。
 */
public class Solution_794 {

    public static void main(String[] args) {
        if (new Solution_794().validTicTacToe(new String[]{"XXX", "   ", "OOO"})) {
            System.out.println("bingo!");
        }
    }

    static Map<String, String> map = new HashMap<>();

    static {
        map.put("X", "O");
        map.put("O", "X");
    }

    boolean ans = false;
    boolean isEnd = false;

    // 先考虑如何用递归做，再考虑为什么用递归做
    // 递归其实就是一个把大问题拆分成小问题的过程（递），和利用小问题的答案解决大问题（归）
    // 那我们要解决的问题是什么呢？ 就是从一个3*3的空棋盘开始先手放X后手放O的开始下棋，问何时能下到给定的棋盘状态
    // 递归怎么大化小呢？就从给定的棋盘出发，每次下一步，知道所有的棋子全部下完 或者不能下为止，至此思路逐渐清晰
    // 实际实现的时候发现这个是需要回溯的，因为跟棋子的摆放位置有关我们不能假设下棋的双方都按照顺序来落子
    // 所以当一个不行的时候，需要往后回溯，这就是数独中的一个小方块
    // 何为回溯，到达一个节点做出选择，当处理完成之后，我们需要把之前所有的抉择全部回滚，到下一个节点做同样的抉择
    // 直到结束，首先就是一个问题，我们要如何定义节点呢？对本题而言，每个节点，都是给定棋盘中和当前要下的棋子值相同的节点
    // 在这个点，我们可以选择下或者不下
    public boolean validTicTacToe(String[] board) {
        // 先把参数改为二维数组
        String[][] info = new String[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                info[i][j] = String.valueOf(board[i].charAt(j));
            }
        }
        // 从0，0这个位置开始尝试
        dfs(info, "X");
        return ans;
    }

    private void dfs(String[][] info, String x) {
        // 这些都是结束条件
        if (isEnd) {
            return;
        }



        if (isAllDown(info)) {
            ans = true;
            isEnd = true;
            return;
        }

        if(isEnd(info)) {
            return;
        }

        // 下面开始做选择
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (info[i][j].equals(x)) {
                    info[i][j] = x + "1";
                    dfs(info, map.get(x));
                    info[i][j] = x;
                }
            }
        }


    }

    private boolean isAllDown(String[][] info) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (info[i][j].equals("X") || info[i][j].equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEnd(String[][] info) {
        // 结束的标志是出现不能摆的位置
        if (info[0][0].contains("1")) {
            if (info[0][1].equals(info[0][0]) && info[0][2].equals(info[0][0])) {
                return true;
            }
            if (info[1][1].equals(info[0][0]) && info[2][2].equals(info[0][0])) {
                return true;
            }
            if (info[1][0].equals(info[0][0]) && info[2][0].equals(info[0][0])) {
                return true;
            }
        }

        if (info[1][0].contains("1")) {
            if (info[1][1].equals(info[1][0]) && info[1][2].equals(info[1][0])) {
                return true;
            }
        }

        if (info[0][1].contains("1")) {
            if (info[1][1].equals(info[0][1]) && info[2][1].equals(info[0][1])) {
                return true;
            }
        }

        if (info[0][2].contains("1")) {
            if (info[1][2].equals(info[0][2]) && info[2][2].equals(info[0][2])) {
                return true;
            }
        }

        if (info[2][0].contains("1")) {
            if (info[2][1].equals(info[2][0]) && info[2][2].equals(info[2][0])) {
                return true;
            }

            if (info[1][1].equals(info[2][0]) && info[0][2].equals(info[2][0])) {
                return true;
            }
        }
        return false;
    }
}
