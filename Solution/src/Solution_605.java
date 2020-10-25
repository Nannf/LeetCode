/**
 * @auth Nannf
 * @date 2020/10/25 20:56
 * @description: 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * [1,0,0,0,1,0,1]
 * 1
 */
public class Solution_605 {
    public static void main(String[] args) {
        if (new Solution_605().canPlaceFlowers(new int[]{1,0,1,0,1,0,1},1)){
            System.out.println("bingo");
        }
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed.length < 1 || flowerbed.length < n) {
            return false;
        }
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 1) {
                return false;
            }
            return n > 1;
        }
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            n--;
        }
        for (int i = 1; i < flowerbed.length - 1; i++) {
            if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                flowerbed[i] = 1;
                n--;
            }
        }
        if (flowerbed[flowerbed.length-2] == 0 && flowerbed[flowerbed.length-1] == 0 ) {
            flowerbed[flowerbed.length -1] = 1;
            n--;
        }
        return n<=0;
    }
}
