package com.xxx.sort;

import java.util.concurrent.TimeUnit;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        shellSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");


        long millis1 = System.currentTimeMillis();
        System.out.println("millis = " + millis1);
        shellSort2(arr);
        long timeMillis1 = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis1);
        System.out.println(timeMillis1 - millis1 + "ms");
        long seconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis1 - millis1);
        System.out.println("seconds = " + seconds1 + "s");

//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

//        int temp = 0;
//        System.out.println("希尔排序第一轮排序");
//        // 因为第一轮排序是将10个数据分成五组
//        // 5 是由 arr.length / 2 得来的也就是步长
//        for (int i = 5; i < arr.length; i++) {
//            // 遍历各组中所有元素(共五组，每组有2个元素)，步长5
//            // j -= 5就是为了退出这次循环
//            for (int j = i - 5; j >= 0; j -= 5) {
//                // 如果当前元素大于加上步长后的那个元素，说明要交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//
//        System.out.println("希尔排序第二轮排序");
//        // 因为第二轮排序是将10个数据分成5/2 = 2组
//        // 5 是由 (arr.length / 2) / 2  得来的也就是步长
//        for (int i = 2; i < arr.length; i++) {
//            for (int j = i - 2; j >= 0; j -= 2) {
//                // 如果当前元素大于加上步长后的那个元素，说明要交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//
//        System.out.println("希尔排序第三轮排序");
//        // 因为第三轮排序是将10个数据分成5/2 = 2 / 2 = 1组
//        // 5 是由 (arr.length / 2) / 2 / 2 得来的也就是步长
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i - 1; j >= 0; j -= 1) {
//                // 如果当前元素大于加上步长后的那个元素，说明要交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    /**
     * 希尔排序--交换法
     * 组内冒泡排序
     *
     * @param arr 数组
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        // 将数据分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 遍历分组中的每一个数据
            for (int i = gap; i < arr.length; i++) {
                // 从第gap个元素，逐个对其所在的组进行冒泡排序
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮排序");
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序--交换法
     * 组内插入法
     *
     * @param arr 数组
     */
    public static void shellSort2(int[] arr) {
        // 增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                // 保存下标
                int j = i;
                // 保存数据
                int temp = arr[j];
                // 判断是否小于对应的数据
                if (arr[j] < arr[j - gap]) {
                    // 插入排序更换数据位置
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
