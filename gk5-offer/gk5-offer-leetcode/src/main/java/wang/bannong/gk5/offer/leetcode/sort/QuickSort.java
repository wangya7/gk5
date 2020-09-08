package wang.bannong.gk5.offer.leetcode.sort;

import java.util.Arrays;

/**
 * 核心算法思想是分治
 * <p>
 * 选择数组中某个数作为基数，通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数都比基数小，另外一部分的所有数都都比基数大，然后再按
 * 此方法对这两部分数据分别进行快速排序，循环递归，最终使整个数组变成有序。
 * <p>
 * 算法中最重要的就是基数选择，基数选择直接关系到快排的效率。事实上，选取
 * 基准元素应该遵循平衡子问题的原则：即使得划分后的两个子序列的长度尽量相
 * 同。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = SortSwap.TEST_ARRAY.clone();
        System.out.println("快排之前：" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("快排之后：" + Arrays.toString(arr));
    }


    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 一趟快排，并返回交换后基数的下标
            int index = sortOneTime(arr, left, right);

            sort(arr, left, index - 1);
            sort(arr, index + 1, right);
        }
    }

    private static int sortOneTime(int[] arr, int left, int right) {
        // 选取左侧作为基数
        int basePos = left;
        int base = arr[basePos];
        while (left < right) {
            // 从右往左找第一个小于基数的数
            while (arr[right] >= base && left < right) {
                right--;
            }
            // 从左往右找第一个大于基数的数
            while (arr[left] <= base && left < right) {
                left++;
            }
            // 找到后交换两个数
            SortSwap.swap(arr, left, right);
        }
        // 使划分好的数分布在基数两侧
        SortSwap.swap(arr, basePos, left);
        return left;
    }

}
