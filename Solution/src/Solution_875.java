import javax.swing.*;

/**
 * @auth Nannf
 * @date 2020/8/19 11:52
 * @description:
 *
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉
 * ，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 *
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 *
 * [312884470]
 * 312884469
 */
public class Solution_875 {
    public static void main(String[] args) {
        int[] piles = {312884470};
        System.out.println(new Solution_875().minEatingSpeed(piles,312884469));
    }

    public int minEatingSpeed(int[] piles, int H) {
        int ans = 0;
        // 我们要求的值 X 代表最少要吃多少
        // 最大值 是一次只能吃最大那一堆的数量，最少是吃一根
        int high =Integer.MIN_VALUE,low =1;
        for (int i= 0;i< piles.length; i++) {
            high = Math.max(high,piles[i]);
        }
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (check(piles,mid,H)) {
                ans =mid;
                high = mid-1;
            } else {
                low = mid + 1;
            }
        }
        return  ans;
    }

    private boolean check(int[] piles, int mid, int h) {
        // 按照mid 的速度来吃，看看最后需要多长时间
        int cnt = 0;
        int[] tmp = new int[piles.length];
        for (int i =0;i<piles.length;i++) {
            if (piles[i] % mid == 0) {
                tmp[i] = piles[i]/mid;
            }else {
                tmp[i] = piles[i] / mid + 1;
            }
        }
        for (int i : tmp) {
            cnt +=i;
        }
        //如果按照mid 来吃，最后的用时大于h，说明我们吃少了。mid 要增加
        if (cnt > h) {
            return false;
        }
        return true;
    }
}
