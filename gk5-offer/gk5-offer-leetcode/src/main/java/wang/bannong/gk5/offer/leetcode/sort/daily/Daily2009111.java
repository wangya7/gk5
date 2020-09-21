package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 【216. 组合总和 III】
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Daily2009111 {

    public static void main(String[] args) {
        List<List<Integer>> answers = new Daily2009111().combinationSum3(3, 7);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        boolean[] used = new boolean[9 + 1];
        dfs(k, 0, n, used, combine, answers);
        return answers;
    }

    public void dfs(int k, int depth, int target, boolean[] used, List<Integer> combine, List<List<Integer>> answers) {
        // 当个数达到啦
        if (k == depth) {
            if (target == 0) answers.add(new ArrayList<>(combine));
            return;
        }


        for (int i = 1; i <= 9; ++i) {
            if (used[i]) {
                continue;
            }

            if (depth > 0) {
                int peek = combine.get(combine.size() - 1);
                if (i < peek) {
                    continue;
                }
            }


            // if (depth > 1 && i < combine.get(depth - 1)) {
            //     continue;
            // }

            combine.add(i);
            used[i] = true;
            dfs(k, depth + 1, target - i, used, combine, answers);
            used[i] = false;
            combine.remove(combine.size() - 1);
        }
    }

}
