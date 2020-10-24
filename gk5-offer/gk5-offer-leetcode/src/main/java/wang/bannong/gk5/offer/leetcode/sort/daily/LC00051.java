package wang.bannong.gk5.offer.leetcode.sort.daily;

import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例：
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00051 {

    public static void main(String[] args) {
        List<List<String>> answers = new LC00051().solveNQueens(4);
        if (answers != null && answers.size() > 0) {
            for (List<String> answer : answers) {
                System.out.println(answer);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 记录某一列是否放置了皇后
        boolean[] col = new boolean[n];

        /**
         * 对角线冲突分两种：主对角线\ 和 副对角线/ 如4*4的棋盘
         * 主对角线\冲突看 i - j 值是否冲突   取值 x ∈ [-3,3]
         * 副对角线/冲突看 i + j 值是否冲突   取值 x ∈ [0,6]
         */
        // 记录主对角线上的单元格是否放置了皇后 由于主对角线出现负值  所以我们约定 i-j+n-1
        boolean[] main = new boolean[2 * n - 1];
        // 记录副对角线上的单元格是否放置了皇后
        boolean[] sub = new boolean[2 * n - 1];

        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, 0, path, col, main, sub, res);
        return res;
    }


    private void dfs(int n, int row, Deque<Integer> path, boolean[] col, boolean[] main, boolean[] sub, List<List<String>> res) {
        if (row == n) {
            // 深度优先遍历到下标为 n，表示 [0.. n - 1] 已经填完，得到了一个结果
            res.add(convert2board(n, path));
            return;
        }

        // 针对下标为 row 的每一列，尝试是否可以放置
        for (int j = 0; j < n; j++) {
            if (!col[j] && !sub[row + j] && !main[row - j + n - 1]) {
                path.addLast(j);
                // 设置对应的列
                col[j] = true;
                // 设置副对角线上的单元格
                sub[row + j] = true;
                // 设置主对角线上的单元格
                main[row - j + n - 1] = true;

                dfs(n, row + 1, path, col, main, sub, res);

                main[row - j + n - 1] = false;
                sub[row + j] = false;
                col[j] = false;
                path.removeLast();
            }
        }
    }

    private List<String> convert2board(int n, Deque<Integer> path) {
        List<String> board = new ArrayList<>();
        for (Integer num : path) {
            String row = CharBuffer.allocate(n).toString().replace('\0', '.');
            // String row = dots(n);
            StringBuilder sb = new StringBuilder(row);
            sb.replace(num, num + 1, "Q");
            board.add(sb.toString());
        }
        return board;
    }

    private String dots(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append('.');
        }
        return sb.toString();
    }

}
