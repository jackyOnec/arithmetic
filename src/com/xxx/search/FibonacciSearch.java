package com.xxx.search;

import java.util.Arrays;

/**
 * 斐波那契查找(黄金分割法)
 * 1. 构建斐波那契数列；
 * 2. 找出查找表长度对应的斐波那契数列中的元素 F(n)；
 * 3. 如果查找表长度小于斐波那契数列中对应的元素 F(n) 的值，则补充查找表（以查找表最后一个元素补充）；
 * 4. 根据斐波那契数列特点对查找表进行区间分隔，确定查找点 mid = left + F(n-1)-1（减 1 是因为数组下标从 0 开始）；
 * 5. 判断中间值arr[mid]和目标值的关系，确定下一步策略：
 * 5.1. 如果目标值小于中间值，说明目标值在左区间。由于左区间长度为 F(n-1)，因此 n 应该更新为 n-1，然后再次执行 4、5 两步；
 * 5.2. 如果目标值大于中间值，说明目标值在右区间。由于右区间长度为 F(n-2)，因此 n 应该更新为 n-2，然后再次执行 4、5 两步；
 * 5.3. 如果目标值等于中间值，说明找到了目标值。但此时还需判别该目标值是原查找表中的元素还是填充元素：
 * 5.3.1. 如果是原查找表中的元素，直接返回索引；
 * 5.3.2. 如果是填充元素，则返回原查找表的最后一个元素的索引，即 arr.length-1。
 * （因为扩展数组是以原查找表最后一个元素来填充，如果目标值是填充元素，则说明原查找表最后一个元素值就是目标值）
 */
public class FibonacciSearch {
    static int maxSize = 20;

    public static void main(String[] args) {
        System.out.println("斐波那契数列： " + Arrays.toString(fibonacci()));
        // 斐波那契数列
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int search = fibonacciSearch(arr, 1);
        System.out.println("search = " + search);
    }

    /**
     * 因为后面我们mid = low + F(k-1)-1, 需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
     * 兔子队列
     * 非递归方法得到一个斐波那契数列
     */
    public static int[] fibonacci() {
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
        int[] f = fibonacci();
        // 获取斐波那契分割数列的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]值可能大于arr的长度，因此需要使用Arrays类，构成一个新数组，并指向temp[]
        // 不足部分使用零填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        System.out.println("k = " + k);
        System.out.println("f[k] = " + f[k]);
        System.out.println("temp = " + Arrays.toString(temp));
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
        // 需要使用arr数组最后的数填充到temp
        // temp = {1, 8, 10, 89, 1000, 1234, 0, 0} => {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        //  high + 1 == 6 替换掉两个零
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
