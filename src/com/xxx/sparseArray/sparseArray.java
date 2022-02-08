package com.xxx.sparseArray;

/**
 * 稀疏数组
 * 当一个数组大部分元素为零,或者为同一个值的数组时,可以使用稀疏数组来保存该数组
 */
public class sparseArray {
    public static void main(String[] args) {
        // 创建一个原始二维数组 11 * 11
        // 0：表示没有棋子 1：黑子 2：白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组~~~");
        // 输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组
        // 1.先遍历二维数组得到非零数据
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (ints[j] != 0) {
                    sum++;
                }
            }
        }

        /*
            获取二维数组的行和列
            行：chessArr1.length
            列：chessArr1[0].length
         */

        System.out.println("sum = " + sum);
        // 2.创建对应的稀疏数组
        int[][] spareArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        spareArr[0][0] = chessArr1.length;
        spareArr[0][1] = chessArr1[0].length;
        spareArr[0][2] = sum;

        // 遍历二维数组将非零数据存放到spareArr中
        int count = 0; // count 用于记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组~~~");
        System.out.printf("%s\t%s\t%s\t\n", "row", "col", "value");
        for (int[] ints : spareArr) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }

        // 稀疏数组恢复原始二维数组
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建二维数组
        int[][] chessArr2 = new int[spareArr[0][0]][spareArr[0][1]];

        // 2.读取稀疏数组后几行数据（从第二行开始），并赋给原始二维数组
        for (int i = 1; i < spareArr.length; i++) {
            chessArr2[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
