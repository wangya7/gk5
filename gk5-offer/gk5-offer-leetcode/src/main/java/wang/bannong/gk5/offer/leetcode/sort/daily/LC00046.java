package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 【46.全排列】
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00046 {

    public static void main(String[] args) {
        int[] candidates = {1, 2, 3};
        List<List<Integer>> answers = new LC00046().permute(candidates);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> target = new ArrayList<>();
        List<Integer> choose = new ArrayList<>();
        boolean[] flags = new boolean[nums.length];
        dfs(nums, 0, flags, choose, target);
        return target;
    }

    public void dfs(int[] nums, int depth, boolean[] flags, List<Integer> choose, List<List<Integer>> target) {
        if (nums.length == depth) {
            target.add(new ArrayList<>(choose));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];

            boolean flag = flags[i];
            if (flag) {
                continue;
            }

            flags[i] = true;
            choose.add(num);

            dfs(nums, depth + 1, flags, choose, target);

            flags[i] = false;
            choose.remove(choose.size() - 1);
        }
    }
}
