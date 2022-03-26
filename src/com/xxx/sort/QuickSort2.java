package com.xxx.sort;

import java.util.concurrent.TimeUnit;

/**
 * 快速排序
 */
public class QuickSort2 {
    public static void main(String[] args) {
//        int[] arr = new int[]{3, 3, 3, 7, 9, 122344, 4656, 34, 34, 4656, 5, 6, 7, 8, 9, 343, 57765, 23, 12321};
//        int len = arr.length - 1;
//        arr = qsort(arr, 0, len);
//        for (int i : arr) {
//            System.out.print(i + "\t");
//        }

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        qsort(arr, 0, arr.length - 1);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");
    }

    /**
     * 快速排序
     *
     * @param arr   数组
     * @param start 开始下标
     * @param end   结束下标
     */
    public static int[] qsort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = qsort(arr, start, i - 1);
        if (j + 1 < end) arr = qsort(arr, j + 1, end);
        return (arr);
    }
}
