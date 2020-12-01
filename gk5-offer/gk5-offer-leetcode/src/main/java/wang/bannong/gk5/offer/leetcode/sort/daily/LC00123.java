package wang.bannong.gk5.offer.leetcode.sort.daily;

// 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
// 示例 1:
//
// 输入: [3,3,5,0,0,3,1,4]
// 输出: 6
// 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
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
// 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
// Related Topics 数组 动态规划
// 👍 553 👎 0

public class LC00123 {

    public static void main(String[] args) {
        System.out.println(new LC00123().maxProfit1(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(new LC00123().maxProfit1(new int[]{1,2,3,4,5}));
        System.out.println(new LC00123().maxProfit1(new int[]{7,6,4,3,1}));
    }

    /***
     * 参考：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/wu-chong-shi-xian-xiang-xi-tu-jie-123mai-mai-gu-pi/
     *
     *
     * 这里我们定义一个三维数组
     * i : 天数
     * j : i天结束时累计卖出股票次数（可以译成交易）；0-0次交易 1-1次交易 2-2次交易
     * k : i天结束是否持有股票；0-不持有股票 1-持有股票
     * dp[i][j][k]
     *
     * dp[i][0][0]：表示第i天交易了0次时不持有股票
     * dp[i][0][1]：表示第i天交易了0次时持有股票
     * dp[i][1][0]：表示第i天交易了1次时不持有股票
     * dp[i][1][1]：表示第i天交易了1次时持有股票
     * dp[i][2][0]：表示第i天交易了2次时不持有股票
     * dp[i][2][1]：表示第i天交易了2次时持有股票  实际是不存在的，因为交易两次后，就不能再买入了
     *
     *
     *
     * ！！！！！注意注意注意！！！！！！
     * 下面写的全部不算，
     * 直接参考解释，把买入的时候作为第二维度而不是卖出
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/dong-tai-gui-hua-by-liweiwei1419-7/
     *
     */
    public int maxProfit1(int[] prices) {
        if(prices==null || prices.length < 2) {
            return 0;
        }

        int[][][] dp = new int[prices.length][3][2];

        /**
         * 初始化状态 【注意  一天可以操作多次】
         */
        // 第一天交易了0次时不持有股票
        dp[0][0][0] = 0;
        // 第一天交易了0次时持有股票
        dp[0][0][1] = -prices[0];
        // 第一天交易了1次时不持有股票
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;


        for (int i = 1; i < prices.length; ++i) {
            // 表示第i天交易了0次时不持有股票
            dp[i][0][0] = dp[i - 1][0][0];

            // 表示第i天交易了0次时持有股票
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);

            // 表示第i天交易了1次时不持有股票
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);

            // 表示第i天交易了1次时持有股票
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);

            // 表示第i天交易了2次时不持有股票
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);

            // 无意义的
            dp[0][2][1] = Integer.MIN_VALUE;
        }

        return Math.max(Math.max(dp[prices.length - 1][2][0], dp[prices.length - 1][1][0]), dp[prices.length - 1][0][0]);
    }


    //***********************

    /**
     * 注意不要使用  会超时
     *
     * 作者：wang_ni_ma
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/wu-chong-shi-xian-xiang-xi-tu-jie-123mai-mai-gu-pi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * 回溯法 + DFS
     * 一开始处于初始状态，也就是什么都不做，不买也不卖
     * 初始状态只能转向买入1状态(第一次买入股票)，也就是买入一股；当买入一股后，我们有两种选择:
     * - 可以立马转向卖出1状态(第一次卖出股票)
     * - 或者转入保持不动状态，相当于手里一直有这一股，等待一个好时机之后再卖出。
     *
     * 在卖出1状态时:
     * - 我们可以立马转入买入2状态(第二次买入股票)
     * - 或者转入保持不动状态，相当于此时手里没有股票了，等待一个好时机后再买入。
     *
     * 买入2和卖出2也是类似的
     * 卖出2执行完后，就只能转入到交易结束状态了，后面就无法再买卖了。
     *
     * 通过上面的这个状态转换图，我们可以用三个变量来表示买卖两次的交易状态
     *
     * index，  用来表示当前是哪一天
     * status， 用来表示当前状态是买入、还是卖出
     * k，      用来表示交易了几次
     *
     * 如果状态是是买入:
     * - 那么可以保持不动
     * - 或者马上卖掉
     *
     * 如果状态是卖出:
     * - 可以保持不动(等待更好的股价出现，暂时不买)
     * - 或者立马再买一股
     * - 同时将count数+1，表示交易过一次了
     *
     */


    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        return dfs(prices,0,0,0);
    }

    private int dfs(int[] prices,int index,int status,int k) {
        //递归终止条件，数组执行到头了，或者交易了两次了
        if(index==prices.length || k==2) {
            return 0;
        }
        //定义三个变量，分别记录[不动]、[卖]、[买]
        int a=0,b=0,c=0;
        //保持不动
        a = dfs(prices,index+1,status,k);
        if(status==1) {
            //递归处理卖的情况，这里需要将k+1，表示执行了一次交易
            b = dfs(prices,index+1,0,k+1)+prices[index];
        }
        else {
            //递归处理买的情况
            c = dfs(prices,index+1,1,k)-prices[index];
        }
        //最终结果就是三个变量中的最大值
        return Math.max(Math.max(a,b),c);
    }

}
