import java.util.ArrayList;
import java.util.List;

/**
 * @author Nannf
 * @version v1.0
 * @date 2021/10/4 10:08
 * @Description 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * 1 <= numRows <= 30
 */
public class Solution_118 {


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>();
        first.add(1);
        ans.add(first);
        if (numRows == 1) {
            return ans;
        }
        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        ans.add(second);
        if (numRows == 2) {
            return ans;
        }

        for (int i = 3; i <= numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);
            List<Integer> last = ans.get(i - 2);
            for (int j = 1; j < i - 1; j++) {
                tmp.add(last.get(j - 1) + last.get(j));
            }
            tmp.add(1);
            ans.add(tmp);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_118().generate(5));
    }
}
