import java.util.*;

/**
 * @auth Nannf
 * @date 2020/6/12 19:05
 * @description: 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * 根据给的提示，我们可以得出，题目对重复的定义是值不能相同，-1, 0, 1, 2, -1, -4
 */
public class Solution_15 {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        result = threeSum(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> threeSum_TimeOut(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        // 先固定一个值，然后问题退化成两数之和的问题，要解决一个就是不能包含重复三元组的问题
        List<List<Integer>> result = new ArrayList<>();
        // 如果有三个0 也应该是一个结果
        int zeroCount = 0;
        for (int i : nums) {
            if (i == 0) {
                zeroCount++;
            }
        }
        if (zeroCount >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            result.add(list);
        }
        if (result.size() != 0 && nums.length == 3) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < nums.length; j++) {
                map.put(nums[j], j);
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(target - nums[j]) && map.get(target - nums[j]) != j) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(target - nums[j]);
                    result.add(list);
                }
            }
        }
        // 找到了所有的结果，如果对结果进行去重呢？
        // 可以在插入的时候判定，也可以在所有的结果都找出来之后进行去重
        List<List<Integer>> finalResult = new ArrayList<>();
        for (List<Integer> list : result) {
            if (finalResult.isEmpty()) {
                finalResult.add(list);
            } else {
                if (!contains(finalResult, list)) {
                    finalResult.add(list);
                }
            }
        }

        return finalResult;
    }

    private static boolean contains(List<List<Integer>> finalResult, List<Integer> list) {
        for (List<Integer> list1 : finalResult) {
            Set<Integer> set = new HashSet<>();
            set.addAll(list1);
            int count = 0;
            for (int i : list) {
                if (!set.contains(i)) {
                    break;
                }
                count++;
            }
            if (count == list.size()) {
                return true;
            }
        }
        return false;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        // 假设（a,b,c）是一组解，如果我们限定了a<b<c 那么其他的解都不会在出现
        // 先进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第三个数的指针
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                // 保证 一个数只取一个
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                if (j == k) {
                    break;
                }
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
            }
        }
        return result;
    }
}
