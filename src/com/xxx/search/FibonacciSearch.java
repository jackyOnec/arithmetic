package com.xxx.search;

import java.util.Arrays;

/**
 * 斐波那契查找(黄金分割法)
 */
public class FibonacciSearch {
    static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int search = fibonacciSearch(arr, 1);
        System.out.println("search = " + search);
    }

    /**
     * 因为后面我们mid = low + F(k-1)-1, 需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契算法
     * 使用非递归方式编写
     *
     * @param arr 数组
     * @param key 查找的值
     * @return 返回对应的下标，没有返回-1
     */
    public static int fibonacciSearch(int[] arr, int key) {
        // 左下标
        int low = 0;
        // 右下标
        int high = arr.length - 1;
        // k表示斐波那契分割数列的下标
        int k = 0;
        // 存放mid的值
        int mid = 0;
        // 获取斐波那契数列
        int[] f = fib();
        // 获取斐波那契分割数列的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]值可能大于arr的长度，因此需要使用Arrays类，构成一个新数组，并指向temp[]
        // 不足部分使用零填充
        int[] temp = Arrays.copyOf(arr, f[k]);

        //需要使用arr数组最后的数填充到temp
        // temp = {1, 8, 10, 89, 1000, 1234, 0, 0} => {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        // 使用while来循环处理，找到我们的数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                // 应该继续向数组的前面查找(左边)
                high = mid - 1;
                // 全部元素 = 前面元素 + 后边元素
                // f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                // 即在f[k-1] 的前面继续查找k--
                // 下次循环mid = f[k-1 -1] - 1
                k--;
            } else if (key > temp[mid]) {
                // 应该继续向数组的前面查找(右边)
                low = mid + 1;
                // 全部元素 = 前面元素 + 后边元素
                // f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-2]个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                // 即在f[k-2] 的前面继续查找k-=2
                // 下次循环mid = f[k-1 -2] - 1
                k -= 2;
            } else {
                // 找到了
                // 需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
