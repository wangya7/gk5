package wang.bannong.gk5.offer.leetcode.sort;

import java.util.Arrays;

/**
 * 核心算法思想是分治
 * <p>
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列
 * 段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 * <p>
 * 归并过程为：比较a[i]和b[j]的大小，若a[i]≤b[j]，则将第一个有序表中的元素
 * a[i]复制到r[k]中，并令i和k分别加上1；否则将第二个有序表中的元素b[j]复制
 * 到r[k]中，并令j和k分别加上1，如此循环下去，直到其中一个有序表取完，然后再
 * 将另一个有序表中剩余的元素复制到r中从下标k到下标t的单元。归并排序的算法我
 * 们通常用递归实现，先把待排序区间[s,t]以中点二分，接着把左边子区间排序，再
 * 把右边子区间排序，最后把左区间和右区间用一次归并操作合并成有序的区间[s,t]。
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = SortSwap.TEST_ARRAY.clone();
        System.out.println("快排之前：" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("快排之后：" + Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {
        // 如果只有一个元素，那就不用排序了
        if (left == right) {
            return;
        } else {
            // 取中间的数，进行拆分
            int middle = (left + right) / 2;
            // 左边的数不断进行拆分
            sort(arr, left, middle);
            // 右边的数不断进行拆分
            sort(arr, middle + 1, right);
            // 合并
            merge(arr, left, middle + 1, right);
        }
    }


    /**
     * 合并数组
     *
     * @param left   指向数组第一个元素
     * @param middle 指向数组分隔的元素
     * @param right  指向数组最后的元素
     */
    private static void merge(int[] arr, int left, int middle, int right) {
        // 左边的数组的大小
        int[] leftArray = new int[middle - left];

        // 右边的数组大小
        int[] rightArray = new int[right - middle + 1];

        // 往这两个数组填充数据
        for (int i = left; i < middle; i++) {
            leftArray[i - left] = arr[i];
        }
        for (int i = middle; i <= right; i++) {
            rightArray[i - middle] = arr[i];
        }

        int i = 0, j = 0;
        // 数组的第一个元素
        int k = left;

        // 比较这两个数组的值，哪个小，就往数组上放
        while (i < leftArray.length && j < rightArray.length) {
            // 谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
            if (leftArray[i] < rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        // 如果左边的数组还没比较完，右边的数都已经完了，那么将左边的数抄到大数组中(剩下的都是大数字)
        while (i < leftArray.length) {
            arr[k++] = leftArray[i++];
        }
        // 如果右边的数组还没比较完，左边的数都已经完了，那么将右边的数抄到大数组中(剩下的都是大数字)
        while (j < rightArray.length) {
            arr[k++] = rightArray[j++];
        }
    }
}
