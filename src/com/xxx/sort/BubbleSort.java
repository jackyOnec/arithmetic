package com.xxx.sort;

import java.util.concurrent.TimeUnit;

/**
 * 冒泡排序
 * 时间复杂度O(n^2)
 * 定义一个临时变量用来保存数据，然后用两个for循环遍历数组
 */
public class BubbleSort {
    public static void main(String[] args) {
        // 测试冒泡排序的速度O(n^2)
        // 创建给8万个数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        bubbleSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");

        /*int[] arr = {3, 9, -1, 10, -2};

        System.out.println("排序前");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/


       /*// 临时变量
        int temp = 0;
        //标识变量，表示是否进行交换过数据
        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + i + "趟排序后的数组");
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

            if (!flag) {
                break;
            } else {
                // 重置flag
                flag = false;
            }
        }

        System.out.println("第一趟排序，就是将最大的数排在最后");
        for (int i = 0; i < arr.length - 1; i++) {
            // 如果前面的数比后面的数大则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        System.out.println("第一趟排序后的数组");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        System.out.println("第二趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            // 如果前面的数比后面的数大则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        System.out.println("第三趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            // 如果前面的数比后面的数大则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        System.out.println("第四趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            // 如果前面的数比后面的数大则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/
    }

    /**
     * 冒泡排序
     *
     * @param arr 数组
     */
    public static void bubbleSort(int[] arr) {
        // 临时变量
        int temp = 0;
        //标识变量，表示是否进行交换过数据
        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //            System.out.println("第" + i + "趟排序后的数组");
            //            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

            if (!flag) {
                break;
            } else {
                // 重置flag
                flag = false;
            }
        }
    }
}
