package com.xxx;

public class sparseArray {
    public static void main(String[] args) {
        // 创建一个原始二维数组 11 * 11
        // 0：表示没有棋子 1：黑子 2：白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组");
        // 输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
