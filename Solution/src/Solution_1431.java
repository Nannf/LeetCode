import java.util.ArrayList;
import java.util.List;

/**
 * @author Nannf
 * @date 2020/6/1 21:00
 * @description
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 *
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 *
 */
public class Solution_1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxValue = 0;
        for (int i = 0; i <candies.length;i++) {
            if (candies[i] > maxValue) {
                maxValue = candies[i];
            }
        }
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i <candies.length;i++) {
            if (candies[i]+extraCandies >= maxValue) {
                resultList.add(true);
            }else {
                resultList.add(false);
            }
        }
        return resultList;
    }
}
