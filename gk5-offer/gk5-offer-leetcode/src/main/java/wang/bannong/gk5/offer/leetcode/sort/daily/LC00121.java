package wang.bannong.gk5.offer.leetcode.sort.daily;

// 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
//
// 注意：你不能在买入股票前卖出股票。
//
//
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
// 输出: 5
// 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2:
//
// 输入: [7,6,4,3,1]
// 输出: 0
// 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
// Related Topics 数组 动态规划
// 👍 1298 👎 0
public class LC00121 {

    public static void main(String[] args) {
        System.out.println(new LC00121().maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new LC00121().maxProfit2(new int[]{7, 6, 4, 3, 1}));
    }

    /**
     * 动态规划：
     * dp[i] = max(dp[i - 1], day[i] - min)
     * i天的最大利润 = max(i-1天的最大利润, 第i天-前i-1天中最小的值)
     * <p>
     * 动态规划一定有顺序，因为"大"的结果需要从"小"的结果中来，所以需要先知道"小"的结果
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int benefit = 0;

        // 从i=1开始，注意边界问题
        for (int i = 1; i < prices.length; ++i) {
            int value = prices[i];
            benefit = Math.max(benefit, value - min);
            if (value < min) {
                min = value;
            }
        }

        return benefit;
    }


    /**
     * 动态规划：
     * 分析的链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/bao-li-mei-ju-dong-tai-gui-hua-chai-fen-si-xiang-b/
     *
     * 限制条件：
     * 1. 卖出股票之前必须先买入股票
     * 2. 只能经历一次股票买卖
     *
     * 那么思考需要将是否持有股票做为状态，标记到状态方程中呢。
     * 答案是需要算进去的，当前是否持股和昨天是否持股有关系，为此我们需要把 是否持股 设计到状态数组中
     *
     *
     * dp[i][j]：下标为 i 这一天结束的时候，手上持股状态为 j 时，我们持有的最大现金数。
     * - j = 0，表示当前不持股；
     * - j = 1，表示当前持股。
     *
     * 推导状态转移方程：
     *
     * dp[i][0]：规定了今天不持股，有以下两种情况：
     * - 昨天不持股，今天什么都不做；
     * - 昨天持股，今天卖出股票（现金数增加），
     *
     * dp[i][1]：规定了今天持股，有以下两种情况：
     * - 昨天持股，今天什么都不做；
     * - 昨天不持股，今天买入股票（注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数）
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        // 状态初始化
        // 第一天技术不持股只有一种可能性
        dp[0][0] = 0;
        // 第一天结束持股
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            // 今天不持股
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            System.out.println("dp[" + i + "][0]=" + dp[i][0] + ", dp[" + i + "][1]=" + dp[i][1]);
        }

        /**
         * 关于动态规划的思考：
         * 1. 动态规划就是填表
         * 2. 明确什么是状态，状态包含哪些因素。 dp数组的含义是什么
         * 3. 思考初始状态。 dp[0]、dp[0][0]、dp[0][1]等等这些值在场景中的初始化
         * 4. 状态转移方程。 思考每个过程的含义，环环相扣，每一步都不能有错 本题中的 dp[i][1] 他就是标识i天结束手中
         *    持股境况的最大利润（即使是亏也要亏的最少）
         */

        return dp[prices.length - 1][0];
    }


    /**
     * 对maxProfit2的优化
     * 只看转态转移方程：
     * 状态转移方程里下标为 i 的行只参考下标为 i - 1 的行（即只参考上一行），并且：
     * - 下标为 i 的行并且状态为 0 的行参考了上一行状态为 0 和 1 的行；
     * - 下标为 i 的行并且状态为 1 的行只参考了上一行状态为 1 的行。
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            // 今天不持股
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
            System.out.println("dp[" + i + "][0]=" + dp[0] + ", dp[" + i + "][1]=" + dp[1]);
        }

        return dp[0];
    }
}
