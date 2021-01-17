import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2021/1/17 21:29
 * @description: 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
 * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * <p>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * <p>
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_1232 {

    public static void main(String[] args) {
        System.out.println(new Solution_1232().checkStraightLine(new int[][]{{0, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}}));
    }

    /**
     * 1. 如果数组长度=2 返回true；
     * 2. 如果数组长度大于2.我们计算前两个的x和y分别差值是多少，然后在后面的一个一个比，我们先假定数组中的元素是逐个出现的
     * 3. 但是我们实际提交的时候发现不是这样的。那我们首先是不是要先进行一个排序？
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) {
            return true;
        }
        boolean flag = true;
        // 要先判断是否是一条横线
        for (int i = 0; i < coordinates.length - 1; i++) {
            if (coordinates[i][0] != coordinates[i + 1][0]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        double x = coordinates[1][0] - coordinates[0][0];
        double y = coordinates[1][1] - coordinates[0][1];
        double ql = y / x;
        for (int i = 2; i < coordinates.length; i++) {
            double yt = coordinates[i][1] - coordinates[0][1];
            double xt = coordinates[i][0] - coordinates[0][0];
            if (yt / xt != ql) {
                return false;
            }
        }
        return true;
    }
}
