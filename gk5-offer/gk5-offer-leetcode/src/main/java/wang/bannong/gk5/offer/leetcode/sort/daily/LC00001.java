package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00001 {
    public static void main(String[] args) {
        int[] res = new LC00001().twoSum2(new int[]{1, 2, 6, 7}, 9);
        System.out.print(Arrays.toString(res));
    }

    /**
     * 最基础的操作，穷举
     * O(n^2)
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 使用一个hashmap，先比较剩余的值在不在，如果不在先装进去
     * 典型的空间换时间 O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
