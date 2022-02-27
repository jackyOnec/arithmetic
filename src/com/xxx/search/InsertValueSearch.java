package com.xxx.search;

/**
 * 插值查找排序
 */
public class InsertValueSearch {
    // 查找次数
    static int count = 0;

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int insertValueSearch = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("insertValueSearch = " + insertValueSearch);
        System.out.println("count = " + count);
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    /**
     * 插值查找算法(有序)
     *
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找值
     * @return 找到返回下标，找不到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        count++;
        // 查找的值大于最大，小于最小都退出
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 中间下标
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        // 向右边递归查找
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
