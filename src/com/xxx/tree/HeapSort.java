package com.xxx.tree;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        heapSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis + "ms");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis - millis);
        System.out.println("seconds = " + seconds + "s");
    }

    /**
     * 堆排序
     *
     * @param arr 数组
     */
    public static void heapSort(int[] arr) {
        int temp = 0;
//        System.out.println("堆排序");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));

        // 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 将堆顶元素与末尾元素交换，将最大元素”沉“到数组末端
        // 重新调整结果，使其满足堆定义，然后继续交换堆顶元素与当前未尾元素，反复执行调整交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组(二叉树)转换为一个大顶堆
     * 完成将以i对应的非叶子节点的树调整成大顶堆
     * int[] arr = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => {4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是i=0 => 得到 {4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中索引
     * @param length 对多少个元素进行调整，length在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];

        // 开始调整
        // k = i * 2 + 1 k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左子节点的值小于右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k 指向右子节点
                k++;
            }

            // 如果子节点大于父节点
            if (arr[k] > temp) {
                // 把较大的赋值给当前节点
                arr[i] = arr[k];
                // i指向k，继续循环比较
                i = k;
            } else {
                break;
            }
        }
        // 当for循环结束后，已经将以i为父节点的树的最大值放在了最顶(局部)
        // 将temp值放到调整后的位置
        arr[i] = temp;
    }
}
