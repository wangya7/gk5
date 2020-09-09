package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 【40.组合总和】
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Daily2009092 {

    public static void main(String[] args) {
        int[] candidates = {1, 1};
        List<List<Integer>> answers = new Daily2009092().combinationSum(candidates, 1);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        List<Integer> candidates_ = new ArrayList<>();
        for (int i : candidates) {
            if (i < target) {
                candidates_.add(i);
            } else if (i == target) {
                if (answers.size() == 0) {
                    answers.add(Collections.singletonList(i));
                }
            }
        }

        Collections.sort(candidates_);
        dfs(candidates_, 0, target, combine, answers);
        return answers;
    }

    /**
     * TODO 没有剪枝优化
     */
    public void dfs(List<Integer> candidates, int begin, int target, List<Integer> group, List<List<Integer>> answers) {
        int length = candidates.size();

        if (begin > length || target < 0) {
            return;
        }

        if (target == 0) {
            answers.add(new ArrayList<>(group));
            return;
        }

        for (int i = begin; i < length; i++) {
            int value = candidates.get(i);

            /***
             * 必须排序后再去重   注意：  没有使用剪枝优化
             */
            if (i > begin && value == candidates.get(i - 1)) {
                continue;
            }

            group.add(value);
            dfs(candidates, i + 1, target - value, group, answers);
            group.remove(group.size() - 1);
        }
    }

}
