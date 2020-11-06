import java.util.*;

/**
 * @author Nannf
 * @date 2020/11/6 14:34
 * @description 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * <p>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * <p>
 * 请你返回排序后的数组。
 * <p>
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * <p>
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * <p>
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * <p>
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * <p>
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class Solution_1356 {
    public static void main(String[] args) {
        System.out.println(new Solution_1356().sortByBits(new int[]{10,100,1000,10000}).toString());
    }

    public int[] sortByBits(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int[] ans = new int[arr.length];
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            int oneNum = getOneNum(arr[i]);
            if (map.containsKey(oneNum)) {
                map.get(oneNum).add(arr[i]);
                Collections.sort(map.get(oneNum));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                map.put(oneNum, list);
            }
        }
        int i =0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            for (int k : entry.getValue()) {
                ans[i++] = k;
            }
        }
        return ans;
    }

    private int getOneNum(int i) {
        String str = Integer.toBinaryString(i);
        int ans = 0;
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '1') {
                ans++;
            }
        }
        return ans;
    }
}
