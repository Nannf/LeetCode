import java.util.HashMap;
import java.util.Map;

/**
 * @author X5442(时子成)
 * @date 2020/5/28 17:51
 * @description <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class Solution_1 {

    public static void main(String[] args) {
        // 审题 给一个int数组，给一个目标值，数组中有两个值加起来会等于目标值，返回这两个值在数组中的下标索引
        // 假设有且仅有一个解，且一个索引只能被使用一次
    }

    // 解法一  时间复杂度 O(n^2)  空间复杂度 O(1)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    // 解法一在leetcode上的答案
    public int[] twoSum_Answer(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // 解法二 依据leetcode上的提示进行编写
    // 时间复杂度O(n),空间复杂度O(n)
    public int[] twoSum_Solution2(int[] nums, int target) {
        // 哈希表，查找时间复杂度是O(1)
        Map<Integer,Integer> map = new HashMap<>();
        // 第一遍循环，先把数据放到哈希表中
        // 问题就是 如果数组中有重复的数据怎么办？
        // 假设这个重复的数据就是解，那么就与题意违背，因为题意说有且仅有一个解，这意味着数字不可能重复
        // 实际submit时 发现是可以重复的，打脸了
        // 如果可以重复,就意味着 后出现的那个索引会覆盖之前的那个索引;
        // 覆盖会有问题吗? 因为我们是要获取一组解即可,并不是要所有解
        // 覆盖导致的问题就是找不全所有解,如果要找全所有解,可以使用Map<Integer,List<Integer>>来存储
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        // 第二遍循环
        for (int i = 0; i< nums.length;i++) {
            // 这样写有问题，因为如果结果是数组中某个值的两倍，那么返回的结果就有问题
//            if (map.containsKey(target - nums[i])) {
//                return new int[] {i,map.get(target - nums[i])};
//            }

            // 这样写也有问题,因为数字是可以重复的
//            int anotherNum = target - nums[i];
//            if (anotherNum == nums[i]) {
//                continue;
//            }
//            if(map.containsKey(anotherNum)) {
//                return new int[] {i,map.get(anotherNum)};
//            }
            int anotherNum = target - nums[i];
            if(map.containsKey(anotherNum) && i != map.get(anotherNum)) {
                return new int[]{i,map.get(anotherNum)};
            }

        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
