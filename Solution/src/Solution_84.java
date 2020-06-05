/**
 * @author Nannf
 * @date 2020/6/5 22:56
 * @description 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Solution_84 {

    /**
     * 单调栈
     * 要构造单调增栈和单调减栈
     * 想不明白，先用暴力解法
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxNum = 0;
        for (int i = 0; i < heights.length; i++) {
            // 这是高
            int high = heights[i];
            int length = 1;
            // 计算底边长，计算方式是先算左边的长度 + 右边的长度 + 自身的长度1
             for (int j = i-1; j>= 0; j-- ) {
                 if (heights[j]>= heights[i]) {
                     length++;
                 } else {
                     break;
                 }
             }
             for (int j = i+1; j< heights.length;j++) {
                 if (heights[j] >= heights[i]) {
                     length++;
                 } else {
                     break;
                 }
             }
             int area = high * length;
             if (area > maxNum) {
                 maxNum = area;
             }

        }
        return maxNum;
    }
}
