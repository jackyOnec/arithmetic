package com.xxx.dynamic;

import java.util.Arrays;

/**
 * 动态规划算法
 * 背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 物品重量
        int[] w = {1, 4, 3};
        // 物品价值，这里val[i]就是前面讲的v[i]
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = val.length;

        // 创建二维数组，v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        // 为了记录放入商品的情况，创建一个二维数组
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            // 将第一列数设置为0
            v[i][0] = 0;
        }
//        for (int i = 0; i < v[0].length; i++) {
//            // 将第一列设置为0
//            v[0][i] = 0;
//        }
        Arrays.fill(v[0], 0);

        // 动态规划处理
        for (int i = 1; i < v.length; i++) {// 不处理第一行，i是从1开始的
            for (int j = 0; j < v[0].length; j++) { // 不处理第一列，j是从1开始的
                // 因为i是从1开始的，因此需要修改为w[i-1]
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //为了记录商品存放到背包的情况，我们不能直接的使用下面的公式
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出数组
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        // 输出最后放入的商品
        // 行的最大下标
        int i = path.length - 1;
        // 列的最大下标
        int j = path[0].length - 1;

        while (i > 0 && j > 0) {// 从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
