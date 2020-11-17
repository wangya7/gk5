package wang.bannong.gk5.offer.leetcode.sort.daily;

/**
 * 10. 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00010 {

    public static void main(String[] args) {
        System.out.println(new LC00010().isMatch("aa", "a"));
        System.out.println(new LC00010().isMatch("aa", "a*"));
        System.out.println(new LC00010().isMatch("ab", ".*"));
        System.out.println(new LC00010().isMatch("aab", "c*a*b"));
        System.out.println(new LC00010().isMatch("mississippi", "mis*is*p*."));
        System.out.println(new LC00010().isMatch("", "..*"));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 创建表格并初始化表格
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 如果s不为空 p为空 那么全部false
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = false;
        }
        // 如果s为空 p不为空 那么需要考虑如下
        char c = '*';
        int pos = 1;
        for (; pos <= n; ++pos) {
            char tmp = p.charAt(pos - 1);
            if (tmp != c) {
                if (c != '*' && tmp != '*') {
                    dp[0][pos] = false;
                    break;
                } else {
                    dp[0][pos] = (tmp == '*');
                    c = tmp;
                }
            } else {
                dp[0][pos] = (c == '*');
            }
        }
        for (; pos <= n; ++pos) {
            dp[0][pos] = false;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 比较s的第i位和p的第j为是否相同
     */
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
