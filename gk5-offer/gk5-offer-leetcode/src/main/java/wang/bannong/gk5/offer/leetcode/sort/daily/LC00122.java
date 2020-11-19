package wang.bannong.gk5.offer.leetcode.sort.daily;

// 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
// 输出: 7
// 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
//
//
// 示例 2:
//
// 输入: [1,2,3,4,5]
// 输出: 4
// 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3:
//
// 输入: [7,6,4,3,1]
// 输出: 0
// 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 3 * 10 ^ 4
// 0 <= prices[i] <= 10 ^ 4
//
// Related Topics 贪心算法 数组
// 👍 991 👎 0
public class LC00122 {
    public static void main(String[] args) {
        System.out.println(new LC00122().maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(new LC00122().maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(new LC00122().maxProfit(new int[]{7,6,4,3,1}));
    }

    /**
     * 参考121动态规划
     *
     * 参考： https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
     * 但是还是把
     * 状态结束成这个样子
     * dp[i][j]
     * - j = 0，表示不持有股票；
     * - j = 1，表示持有股票。
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];

        // 初始化
        // 第一天不持有股票
        dp[0][0] = 0;
        // 第一天持有股票
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            // 注意区别于121题
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
