package com.xxx.recursion;

/**
 * 八皇后问题
 * 用一维数组解决
 * array下标表示第几列，即第几个皇后，array[i] = val，val表示第i+1个皇后，放在第i+1行的第val+1列
 */
public class EightQueenProblemTest {
    //定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置位置的结果，比如 array = {0， 4， 7， 5，2， 6， 1， 3}
    int[] array = new int[max];

    public static void main(String[] args) {

    }
}
