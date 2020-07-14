import java.util.Arrays;

/**
 * @auth Nannf
 * @date 2020/7/14 15:33
 * @description:
 * 楼下水果店正在促销，你打算买些苹果，arr[i] 表示第 i 个苹果的单位重量。
 *
 * 你有一个购物袋，最多可以装 5000 单位重量的东西，算一算，最多可以往购物袋里装入多少苹果。
 *
 *
 * 输入：arr = [100,200,150,1000]
 * 输出：4
 * 解释：所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
 */
public class Solution_1196 {

    public int maxNumberOfApples(int[] arr) {
        int ans = 0;
        Arrays.sort(arr);
        int count = 0;

        while(count <= 5000 && ans < arr.length) {
            count+=arr[ans++];
        }
        if (count <= 5000) {
            return arr.length;
        } else {
            return ans-1;
        }
    }
}
