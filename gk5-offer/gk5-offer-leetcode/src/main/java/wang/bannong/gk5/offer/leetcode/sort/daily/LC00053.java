package wang.bannong.gk5.offer.leetcode.sort.daily;


/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00053 {

    public static void main(String[] args) {
        System.out.println(new LC00053().maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(new LC00053().maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    /**
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 分治法实现
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int tmp = 0;
        int max = nums[0];
        for (int num : nums) {
            tmp = Math.max(tmp + num, num);
            max = Math.max(max, tmp);
        }
        return max;
    }

}
