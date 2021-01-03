package wang.bannong.gk5.offer.leetcode.sort.daily;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * 2. 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC00002 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        System.out.println(l1);
        System.out.println(l2);

        ListNode rsult = new LC00002().addTwoNumbers(l1, l2);
        System.out.println(rsult);
    }

    /**
     * 这道题直接计算就行，逢十进一，注意最高位的是否需要进一
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ll1 = l1;
        ListNode ll2 = l2;

        // 标识前一位计算是否"进1"
        boolean needAppend = false;

        ListNode head = null;
        ListNode result = null;
        while (ll1 != null || ll2 != null) {
            int value = needAppend ? 1 : 0;
            if (ll1 != null && ll2 != null) {
                value += ll1.val + ll2.val;
                ll1 = ll1.next;
                ll2 = ll2.next;
            } else if (ll1 != null) {
                value += ll1.val;
                ll1 = ll1.next;
            } else {
                value += ll2.val;
                ll2 = ll2.next;
            }
            needAppend = value >= 10;
            if (result == null) {
                head = new ListNode(value % 10);
                result = head;
            } else {
                result.next = new ListNode(value % 10);
                result = result.next;
            }
        }

        if (needAppend) {
            result.next = new ListNode(1);
        }
        return head;
    }


    public static class ListNode {
        int      val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringJoiner sj = new StringJoiner(" -> ");
            ListNode link = this;
            while (link != null) {
                sj.add(String.valueOf(link.val));
                link = link.next;
            }
            return sj.toString();
        }
    }

    /**
     * 利用三个指针实现反转
     * <p>
     * 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
     * 1 -> 0 -> 0 -> 0 -> 9 -> 9 -> 9 -> 8
     */
    public static ListNode reverse(ListNode head) {
        if (head == null)
            return head;

        ListNode p1, p2, p3;
        p1 = head;
        p2 = p1.next;
        while (p2 != null) {
            p3 = p2.next;       //要改变p2->next的指针，所以必须先保留p2->next
            p2.next = p1;
            p1 = p2;            //循环往后
            p2 = p3;
        }
        head.next = null;   //原先的head已经变成tail，别忘了置空，只有到这步才能置空
        head = p1;
        return head;
    }

    /**
     * 每个2个反转
     * 入参： 1 -> 3 -> 5 -> 7 -> 9 -> 11
     * 出参： 3 -> 1 -> 7 -> 5 -> 11 -> 9
     */
    public static ListNode reverseInterval2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode p = head.next;
        head.next = reverseInterval2(head.next.next);
        p.next = head;
        return p;
    }

    @Test
    public void listReverse() {
        ListNode list =
                new ListNode(1,
                        new ListNode(3,
                                new ListNode(5,
                                        new ListNode(7,
                                                new ListNode(9,
                                                        new ListNode(11, null))))));
        System.out.println(list);
        System.out.println(reverseInterval2(list));
    }


}
