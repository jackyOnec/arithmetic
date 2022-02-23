package com.xxx.sort;

import java.util.concurrent.TimeUnit;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        quickSort(arr, 0, arr.length - 1);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");

//        int[] arr = {-9, 78, 0, 23, -567, 70, 99, 20, -1, 9};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 临时变量，作为数据交换
        int temp = 0;
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        // while循环的目的是让比pivot 值小放到左边
        // 比pivot值大放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果 l>=r 说明pivot的左右两个值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现这个arr[l] == pivot 相等 r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后，发现这个arr[r] == pivot 相等 l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l==r, 必须 l++ ,r--, 否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
