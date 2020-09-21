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
public class Daily2009101 {

    public static void main(String[] args) {
        int[] candidates = {1, 2, 3};
        List<List<Integer>> answers = new Daily2009101().permute(candidates);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> group = new ArrayList<>();
        dfs(nums, nums.length, 0, group, answers);
        return answers;
    }

    public void dfs(int[] nums, int length, int hight, List<Integer> group, List<List<Integer>> answers) {

        if (hight == length) {
            answers.add(new ArrayList<>(group));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (group.contains(nums[i])) {
                continue;
            }

            group.add(nums[i]);
            dfs(nums, length, hight + 1, group, answers);
            // 能不能回来就看这句话啦  哈哈
            group.remove(group.size() - 1);
        }
    }
}
