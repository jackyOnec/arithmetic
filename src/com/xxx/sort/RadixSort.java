package com.xxx.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        // 第一轮(针对每个元素的个位进行排序处理)
        // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 二维数组包含10个一维数组
        // 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        // 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶中的每次放入的数据个数
        // 可以理解为bucketElementCounts[0] ，记录就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        // 第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        // 遍历每一桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第k个桶(即第k个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }

            }
        }

        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    /**
     * 基数排序
     *
     * @param arr 数组
     */
    public static void radixSort(int[] arr) {

    }
}
