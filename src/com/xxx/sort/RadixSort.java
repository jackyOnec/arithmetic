package com.xxx.sort;

import java.util.concurrent.TimeUnit;

/**
 * 基数排序
 * 已空间换时间
 */
public class RadixSort {
    public static void main(String[] args) {
        // 80000 * 11 * 4 / 1024 / 1024 / 1024
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        radixSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");


//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);


        // 第一轮(针对每个元素的个位进行排序处理)
        // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 二维数组包含10个一维数组
        // 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        // 基数排序是使用空间换时间的经典算法
//        int[][] bucket = new int[10][arr.length];
//
//        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶中的每次放入的数据个数
//        // 可以理解为bucketElementCounts[0] ，记录就是bucket[0]桶的放入数据个数
//        int[] bucketElementCounts = new int[10];
//
//        System.out.println("第一轮");
//        // 第一轮(针对每个元素的个位进行排序处理)
//        for (int j = 0; j < arr.length; j++) {
//            // 取出每个元素的个位的值
//            int digitOfElement = arr[j] / 1 % 10;
//            // 放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        int index = 0;
//        // 遍历每一桶，并将桶中的数据，放入到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            // 如果桶中有数据我们才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                // 循环该桶即第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    // 取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            // 第一轮处理后，需要将每个bucketElementCounts[k] = 0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//
//        System.out.println("第二轮");
//        // 第二轮(针对每个元素的十位进行排序处理)
//        for (int j = 0; j < arr.length; j++) {
//            // 取出每个元素的个位的值
//            int digitOfElement = arr[j] / 10 % 10;
//            // 放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        index = 0;
//        // 遍历每一桶，并将桶中的数据，放入到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            // 如果桶中有数据我们才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                // 循环该桶即第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    // 取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//
//        System.out.println("第三轮");
//        // 第三轮(针对每个元素的百位进行排序处理)
//        for (int j = 0; j < arr.length; j++) {
//            // 取出每个元素的个位的值
//            int digitOfElement = arr[j] / 100 % 10;
//            // 放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        index = 0;
//        // 遍历每一桶，并将桶中的数据，放入到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            // 如果桶中有数据我们才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                // 循环该桶即第k个桶(即第k个一维数组)，放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    // 取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    /**
     * 基数排序
     * 定义一个二维数组用于存放数据，相当于桶，定义一个一维数组用于存放每个桶中有多少个元素
     * 获取数组中的最大的位数
     * 取出数组中每一个元素，获取对应的位数，第一个是十位，放到对应的桶中，记录到一维数组中以此类推
     * 用一维数组中的数据遍历桶中的数据，放回到原数组中，不断循环，最终完成排序
     *
     * @param arr 数组
     */
    public static void radixSort(int[] arr) {
        // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 二维数组包含10个一维数组
        // 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为 arr.length
        // 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶中的每次放入的数据个数
        // 可以理解为bucketElementCounts[0] ，记录就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        // 得到数组中最大数的位数
        // 假设第一数就是最大数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 针对每个元素的对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
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
                // 清空bucketElementCounts桶中的数据
                // 第i+1轮处理后，需要将每个bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;
            }

//            System.out.println("第" + (i + 1) + "轮");
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
