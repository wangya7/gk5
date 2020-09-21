package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【47.全排列②】
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Daily2009102 {

    public static void main(String[] args) {
        int[] candidates = {2, 1, 1};
        List<List<Integer>> answers = new Daily2009102().permuteUnique(candidates);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> group = new ArrayList<>();

        // 排序，为了出重和容易剪枝
        boolean[] positionUsed = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, nums.length, 0, positionUsed, group, answers);
        return answers;
    }

    public void dfs(int[] nums, int length, int hight, boolean[] positionUsed, List<Integer> group, List<List<Integer>> answers) {

        if (length == hight) {
            answers.add(new ArrayList<>(group));
            return;
        }

        for (int i = 0; i < length; i++) {

            if (positionUsed[i]) {
                continue;
            }

            int value = nums[i];

            // 注意这段代码 剪枝
            if (i > 0 && value == nums[i - 1] && !positionUsed[i - 1]) {
                continue;
            }


            group.add(value);
            positionUsed[i] = true;

            dfs(nums, length, hight + 1, positionUsed, group, answers);

            positionUsed[i] = false;
            group.remove(group.size() - 1);
        }
    }
}
