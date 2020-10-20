package wang.bannong.gk5.offer.leetcode.sort.daily;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 【39.组合总和】
 * https://leetcode-cn.com/problems/combination-sum/
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 */
public class LC00039 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> answers = new LC00039().combinationSum2(candidates, 7);
        if (answers != null && answers.size() > 0) {
            for (List<Integer> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allAnswers = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        List<Integer> candidates_ = new ArrayList<>(candidates.length);
        for (int i : candidates) {
            candidates_.add(i);
        }

        dfs(candidates_, combine, target, 0, allAnswers);
        return allAnswers;
    }

    /**
     * DFS
     *
     * @param candidates 原始数组集合
     * @param combine    过程组合
     * @param target     目标值
     * @param position   数组的下标
     * @param allAnswers 所有可能性结果的集合
     */
    public void dfs(List<Integer> candidates, List<Integer> combine, int target, int position, List<List<Integer>> allAnswers) {
        if (position == candidates.size()) {
            return;
        }

        if (target == 0) {
            allAnswers.add(new ArrayList<>(combine));
            return;
        }

        // 不选择当前的节点
        dfs(candidates, combine, target, position + 1, allAnswers);

        //    选择当前节点
        int value = candidates.get(position);
        if (target >= value) {
            combine.add(value);
            dfs(candidates, combine, target - value, position, allAnswers);
            // 注意下面这句代码
            combine.remove(combine.size() - 1);
        }
    }


    //*********************************************************第二种解法
    // 作者：liweiwei1419
    // 链接：https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        dfs2(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
            dfs2(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

}
