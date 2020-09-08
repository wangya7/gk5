package wang.bannong.gk5.offer.leetcode.sort;

public final class SortSwap {

    public static final int[] TEST_ARRAY = {6, 5, 2, 7, 3, 9, 8, 4, 10, 1};

    /**
     * 交互数组arr中下标为a和b的数据
     *
     * @param arr 数组
     * @param a   数组下标A
     * @param b   数组下标B
     */
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
