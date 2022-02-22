package com.xxx.sort;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        selectSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis);


        /*
          使用逐步推导的方式，讲解选择排序
          原始数组：101, 34, 119, 1
          第一轮排序 1, 34, 119 ,101
         */
        /*int[] arr = {101, 34, 119, 1, 0, -1, 99, 22, 2222};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        selectSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/


        // 第一轮排序
        /*int minIndex = 0;
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        arr[minIndex] = arr[0];
        arr[0] = min;
        System.out.println("第一轮后");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        // 第二轮排序
        minIndex = 1;
        min = arr[1];

        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        arr[minIndex] = arr[1];
        arr[1] = min;
        System.out.println("第二轮后");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        // 第三轮排序
        minIndex = 2;
        min = arr[2];

        for (int i = 2 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }
        arr[minIndex] = arr[2];
        arr[2] = min;
        System.out.println("第三轮后");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/
    }

    /**
     * 选择排序
     * 时间复杂度O(n^2)
     *
     * @param arr 数组
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            // 交换数据，如果最小索引刚好等于i就不交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第、" + (i + 1) + "轮后");
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
