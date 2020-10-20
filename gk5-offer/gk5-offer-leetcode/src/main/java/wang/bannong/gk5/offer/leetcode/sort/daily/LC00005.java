package wang.bannong.gk5.offer.leetcode.sort.daily;

/**
 * 5. 最长回文子串
 * 标签： 动态规划
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00005 {
    public static void main(String[] args) {
        System.out.println(new LC00005().longestPalindrome3("babad"));
        System.out.println(new LC00005().longestPalindrome3("cbbd"));
    }

    public String longestPalindrome2(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        char[] chars = s.toCharArray();
        boolean[][] matrix = new boolean[len][len];

        for (int i = 0; i < len; ++i) {
            matrix[i][i] = true;
        }

        int beginPos = 0;
        int maxLength = 1;

        for (int i = len - 2; i >= 0; --i) {
            for (int j = len - 1; j > i; --j) {
                if (chars[i] != chars[j]) {
                    matrix[i][j] = false;
                } else {
                    if (j - i < 3) {
                        matrix[i][j] = true;
                    } else {
                        matrix[i][j] = matrix[i + 1][j - 1];
                    }
                }
                if (matrix[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    beginPos = i;
                }
            }
        }
        return s.substring(beginPos, beginPos + maxLength);
    }


    /**
     * 暴力拆解 时间复杂度O(n^3) 空间复杂度O(1)
     */
    public String longestPalindrome1(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }

        char[] arr = s.toCharArray();
        int max = 1; // 最大的回文长度
        int begin = 0; // 回文在arr中起始下标

        /**
         * 枚举所有长度大于1的子串 除了最后一个元素不要  其他都要比较
         * 假设入参 babad 那么我需要比较4次分别是 "babad" "abad" "bad" "ad"
         */
        for (int i = 0; i < length - 1; ++i) {
            // 定住i，依次向后寻找最大的回文串
            for (int j = i + 1; j < length; ++j) {
                // max依次增加才行
                if (j - i + 1 > max && validPalindromic(arr, i, j)) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }


    /**
     * 判断是否是回文
     *
     * @param charArray 数组
     * @param left      左下标
     * @param right     右下标
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left++] != charArray[right--]) {
                return false;
            }
        }
        return true;
    }

}
