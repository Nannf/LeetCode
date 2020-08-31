/**
 * @auth Nannf
 * @date 2020/8/31 21:47
 * @description: 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * <p>
 * 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明: 不存在返回-1。
 * <p>
 * <p>
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sparse-array-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Review_1005 {
    public int findString(String[] words, String s) {
        for (int i = 0 ; i < words.length;i++) {
            if (s.equals(words[i])) {
                return i;
            }
        }
        return -1;
    }

    // 其实暴力 用O(n)的时间复杂度就可以找到了，我们要怎么缩短这个查找的范围呢
    public int findString_binaryFind(String[] words, String s) {
        int low = 0;
        int high = words.length - 1;
        while (low <= high) {
            // 其实问题就出在这个稀疏上，因为我们并不知道 这个中间的值是多少，而且中间的值如果是空的话，中间的值是往大了还是往小了加呢？

            int mid = low + ((high - low) >> 1);
            if (words[mid] == "") {

            }
        }
        return -1;
    }
}
